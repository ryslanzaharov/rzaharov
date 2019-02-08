import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class NoobChain {

    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    public static int difficulty = 5;

    public static void main(String[] args) {

        System.out.println(new String(new char[difficulty]).replace('\0', '0'));

        blockchain.add(new Block("1 блок", "0"));
        blockchain.get(0).mineBlock(difficulty);

        blockchain.add(new Block("2 блок",blockchain.get(blockchain.size()-1).hash));
        blockchain.get(1).mineBlock(difficulty);

        blockchain.add(new Block("3 блок",blockchain.get(blockchain.size()-1).hash));
        blockchain.get(2).mineBlock(difficulty);

        System.out.println("\nДействителен ли блокчейн: " + isChainValid());

        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println("\nБлокчейн : ");
        System.out.println(blockchainJson);
    }


    public static Boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');

        //перебираем блок цепочку для проверки хешей
        for(int i=1; i < blockchain.size(); i++) {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i-1);
            //сравнение зарегестрированного и вычисленного хеша
            if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
                System.out.println("зарегестрированный и вычисленный хеши не соответствуют");
                return false;
            }
            //сравнение предыдущего хеша и зарегистрированного предыдущего хеша
            if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
                System.out.println("предыдущий хеш и зарегистрированный предыдущий хеши не соответствуют");
                return false;
            }
            //проверка на уровень сложности
            if(!currentBlock.hash.substring( 0, difficulty).equals(hashTarget)) {
                System.out.println("Этот блок не соответсвует уровню сложности либо  не является зарегестрированным");
                return false;
            }
        }
        return true;
    }
}