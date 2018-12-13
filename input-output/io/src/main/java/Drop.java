import java.io.*;

public class Drop {

    public void dropAbuses(InputStream in, OutputStream out, String[] abuse) {

        try(BufferedReader br = new BufferedReader(new InputStreamReader(in));
                PrintStream ps = new PrintStream(out)
        ) {

            String strs = br.readLine().replaceAll("abuse", "");
            ps.print(strs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Drop drop = new Drop();
        drop.dropAbuses(System.in, System.out, new String[12]);
    }
}
