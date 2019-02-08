import java.io.*;
import java.util.Arrays;

public class Drop {


    public void dropAbuses(InputStream in, OutputStream out, String[] abuse) {

        try(BufferedReader br = new BufferedReader(new InputStreamReader(in));
                PrintStream ps = new PrintStream(out)
        ) {
            String line = br.readLine();
            for(String ab : abuse) {
                if (line.contains(ab))
                    line.replaceAll(ab, "");
            }
            ps.print(line);
            ps.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
