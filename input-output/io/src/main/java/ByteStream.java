import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class ByteStream {

    public boolean isNumber(InputStream in) throws IOException {
        boolean isEvening = false;
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        try {
            Integer result = Integer.parseInt(br.readLine());
            if (result % 2 == 0)
                isEvening = true;
        } catch (Exception e) {
            System.out.println("sorry enter numbers");
        }
        return isEvening;
    }

    public static void main(String[] args) throws IOException{
        InputStream inputStream = new BufferedInputStream(System.in);
        ByteStream bs = new ByteStream();
        System.out.println(bs.isNumber(inputStream));
    }

}
