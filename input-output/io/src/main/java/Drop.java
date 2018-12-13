import java.io.*;

public class Drop {

    public void dropAbuses(InputStream in, OutputStream out) {

        try(BufferedReader br = new BufferedReader(new InputStreamReader(in));
                PrintStream ps = new PrintStream(out)
        ) {
            String[] abuse = br.readLine().split(" ");
            for (String str : abuse) {
                str = str.replace("abuse", "");
                ps.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Drop drop = new Drop();
        drop.dropAbuses(System.in, System.out);

    }
}
