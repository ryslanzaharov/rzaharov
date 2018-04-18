package ru.job4j.xslt;



import java.io.*;


/**
 * AddressBookReader -- an application that reads an address book file
 * exported from Netscape Messenger using the Line Delimited Interchange
 * Format (LDIF).
 * <p>
 * LDIF address book files have this format:<pre>
 *   dn: cn=FirstName LastName,mail=emailAddress
 *   modifytimestamp: 20010328014700Z
 *   cn: FirstName LastName  --display name (concatenation of givenname+sn)
 *   xmozillanickname: Fred        --------+
 *   mail: fred                            |
 *   xmozillausehtmlmail: TRUE             +-- We care about these
 *   givenname: Fred                       |
 *   sn: Flintstone   --(surname)          |
 *   telephonenumber: 999-Quarry           |
 *   homephone: 999-BedrockLane            |
 *   facsimiletelephonenumber: 888-Squawk  |
 *   pagerphone: 777-pager                 |
 *   cellphone: 666-cell           --------+
 *   xmozillaanyphone: Work#
 *   objectclass: top
 *   objectclass: person
 * </pre>
 *
 * @author Eric Armstrong
 */
public class AddressBookReader01 {
    public static void main(String[] argv) {
        // Check the arguments
        if (argv.length != 1) {
            System.err.println("Usage: java AddressBookReader filename");
            System.exit(1);
        }

        String filename = argv[0];
        File f = new File(filename);
        AddressBookReader01 reader = new AddressBookReader01();
        reader.parse(f);
    }

    /** Parse the input */
    public void parse(File f) {
        try {
            // Get an efficient reader for the file
            FileReader r = new FileReader(f);
            BufferedReader br = new BufferedReader(r);

            // Read the file and display it's contents.
            String line = br.readLine();

            while (null != (line = br.readLine())) {
                if (line.startsWith("xmozillanickname: ")) {
                    break;
                }
            }

            output("nickname", "xmozillanickname", line);
            line = br.readLine();
            output("email", "mail", line);
            line = br.readLine();
            output("html", "xmozillausehtmlmail", line);
            line = br.readLine();
            output("firstname", "givenname", line);
            line = br.readLine();
            output("lastname", "sn", line);
            line = br.readLine();
            output("work", "telephonenumber", line);
            line = br.readLine();
            output("home", "homephone", line);
            line = br.readLine();
            output("fax", "facsimiletelephonenumber", line);
            line = br.readLine();
            output("pager", "pagerphone", line);
            line = br.readLine();
            output("cell", "cellphone", line);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void output(String name, String prefix, String line) {
        int startIndex = prefix.length() + 2; // 2=length of ": " after the name
        String text = line.substring(startIndex);
        System.out.println(name + ": " + text);
    }
}
