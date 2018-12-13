import org.apache.commons.io.IOUtils;

import java.io.*;

public class ByteStream {

    public boolean isNumber(InputStream in) {
        boolean isEvening = false;
        try(BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            Integer result = Integer.parseInt(br.readLine());
            if (result % 2 == 0)
                isEvening = true;
        } catch (IOException e) {
            System.out.println("sorry enter numbers");
        }
        return isEvening;
    }

    public static void main(String[] args){
        try(InputStream inputStream = new BufferedInputStream(System.in)) {
            ByteStream bs = new ByteStream();
            System.out.println(bs.isNumber(inputStream));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
