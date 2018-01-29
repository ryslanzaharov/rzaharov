package ru.job4j.multithreading.monitoresynchronizy;

import org.omg.PortableInterceptor.INACTIVE;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ParallerSearch{

    //путь до папки откуда надо осуществлять поиск.
    private String root;
    //заданных текст.
    private String text;
    //расширения файлов в которых нужно делать поиск.
    private List<String> exts;


    private volatile List<String> fileWithWord = new ArrayList<>();

    public ParallerSearch(String root, String text, List<String> exts) {
        this.root = root;
        this.text = text;
        this.exts = exts;
    }


    //возвращает список всех файлов содержащих text.
    public List<String> result(String root)
            throws IOException {
        File dir = new File(root);
        File[] list = dir.listFiles();
        for (File f : list) {
            String path = f.getCanonicalPath();
            if (f.isFile()) {
                    String ext = path.substring(path.lastIndexOf(".") + 1);
                    if (exts.contains(ext)) {
                        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));) {
                            String line;
                            while ((line = br.readLine()) != null) {
                                if (line.contains(text)) {
                                    synchronized (fileWithWord) {
                                        fileWithWord.add(path);
                                        break;
                                    }
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
            } else {
                if (list.length == 1)
                    result(path);
                else {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                result(path);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }
            }
        }
        return fileWithWord;
    }

}
