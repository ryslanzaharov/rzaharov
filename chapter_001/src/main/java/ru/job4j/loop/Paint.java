package ru.job4j.loop;

public class Paint {
    public String piramid(int h) {
        StringBuilder piramida = new StringBuilder();
        int znak = 1;
        for (int i = 1; i <= h; i++) {
                for (int k = 0; k < znak; k++) {
                    if (i==1)
                        piramida.append(" ^ "+System.getProperty("line.separator"));
                    else if (i != h) {
                        if (k == 0)
                            piramida.append(" ^");
                        else if (k == znak-1)
                            piramida.append("^ "+System.getProperty("line.separator"));
                        else
                        piramida.append("^");
                    }
                    else
                    piramida.append("^");

                }
                znak += 2;

        }
        return piramida.toString();
    }

}
