import java.io.*;
import java.util.Arrays;

public class Drop {


    public void dropAbuses(InputStream in, OutputStream out, String[] abuse) {

        try(BufferedReader br = new BufferedReader(new InputStreamReader(in));
                PrintStream ps = new PrintStream(out)
        ) {
            abuse = br.readLine().split(" ");
            for (int i = 0; i != abuse.length; i++ ) {
                abuse[i] = abuse[i].replace("abuse", "");
            }
            ps.print(Arrays.toString(abuse));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Drop drop = new Drop();
        drop.dropAbuses(System.in, System.out, new String[12]);
    }
}
