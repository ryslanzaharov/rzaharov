package com.example.demo.search;

import java.io.File;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SearchFiles {

    private static final int MAPSIZE = 4 * 1024 ; // 4K - make this * 1024 to 4MB in a real system.

    public List<Path> displayIt(File node, String type_file, List<Path> paths) {

        if (node.getName().endsWith(type_file)) {
            paths.add(node.toPath());
        }

        if (node.isDirectory()) {
            String[] subNote = node.list();
            for (String filename : subNote) {
                displayIt(new File(node, filename), type_file, paths);
            }
        }
        return paths;
    }

    public HashMap<Path, List<Integer>> searchFor(String grepfor, List<Path> paths) throws IOException {
        HashMap<Path, List<Integer>> foundFiles = new HashMap<>();
        for (Path path : paths) {
            List<Integer> lineCounts = new ArrayList<>();
            final byte[] tosearch = grepfor.getBytes(StandardCharsets.UTF_8);
            StringBuilder report = new StringBuilder();
            int padding = 1; // need to scan 1 character ahead in case it is a word boundary.
            // нужно сканировать 1 символ впереди, если это граница слова.
            int linecount = 0;
            int matches = 0;
            boolean inword = false;
            boolean scantolineend = false;
            try (FileChannel channel = FileChannel.open(path, StandardOpenOption.READ)) {
                final long length = channel.size();
                int pos = 0;
                while (pos < length) {
                    long remaining = length - pos;
                    // int conversion is safe because of a safe MAPSIZE.. Assume a reaosnably sized tosearch.
                    //Преобразование int безопасно из-за безопасного MAPSIZE. Предположим, tosearch разумного размера.
                    int trymap = MAPSIZE + tosearch.length + padding;
                    int tomap = (int) Math.min(trymap, remaining);
                    // different limits depending on whether we are the last mapped segment.
                    //различные ограничения в зависимости от того, являемся ли мы последним сопоставленным сегментом.
                    int limit = trymap == tomap ? MAPSIZE : (tomap - tosearch.length);
                    MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, pos, tomap);
                    pos += (trymap == tomap) ? MAPSIZE : tomap;
                    for (int i = 0; i < limit; i++) {
                        final byte b = buffer.get(i);
                        if (scantolineend) {
                            if (b == '\n') {
                                scantolineend = false;
                                inword = false;
                                linecount++;
                            }
                        } else if (b == '\n') {
                            linecount++;
                            inword = false;
                        } else if (b == '\r' || b == ' ') {
                            inword = false;
                        } else if (!inword) {
                            if (wordMatch(buffer, i, tomap, tosearch)) {
                                i += tosearch.length - 1;
                                if (report.length() > 0) {
                                    report.append(", ");
                                }
                                lineCounts.add(linecount);
                                foundFiles.put(path.toAbsolutePath(), lineCounts);
                                scantolineend = true;
                            } else {
                                inword = true;
                            }
                        }
                    }
                }
            }
        }
        return foundFiles;
    }

    private  boolean wordMatch(MappedByteBuffer buffer, int pos, int tomap, byte[] tosearch) {
        for (int i = 0; i < tosearch.length; i++) {
            if (tosearch[i] != buffer.get(pos + i)) {
                return false;
            }
        }
        byte nxt = (pos + tosearch.length) == tomap ? (byte)' ' : buffer.get(pos + tosearch.length);
        return nxt == ' ' || nxt == '\n' || nxt == '\r';
    }
}
