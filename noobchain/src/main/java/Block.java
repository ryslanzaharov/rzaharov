import java.util.Date;

public class Block {

    private String data;
    public String hash;
    public String previousHash;
//    private long timeStamp;
    private int nonce;
    private Date date;

    public Block(String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.date = new Date();

        this.hash = calculateHash();
    }

    public String calculateHash() {
        String calculatedhash = StringUtil.applySha256(
                previousHash +
                        Long.toString(date.getTime()) +
                        Integer.toString(nonce) +
                        data
        );
        return calculatedhash;
    }

    //создаем хеш с уровне сложности difficulty
    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0');
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();
        }
    }

    @Override
    public String toString() {
        return " блок{"+ "\n" +
                " данные='" + data + '\'' + "\n" +
                " хеш код='" + hash + '\''  + "\n" +
                " предыдущий хеш код='" + previousHash + '\'' + "\n" +
                " индекс=" + nonce + '\''  + "\n" +
                " время=" + date +  "\n" +
                '}';
    }
}
