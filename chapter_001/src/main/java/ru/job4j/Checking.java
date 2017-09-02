package ru.job4j;

public class Checking {
    public boolean contains(String origin, String sub) {
        boolean cont = false;
        char[] morigin = origin.toCharArray();
        char[] msub = sub.toCharArray();
        int kol = 0;
        for (int i = 0; i <morigin.length; i++) {
            kol = i;
            if (morigin[i] == msub[0]) {
                for (int n = 0; n < msub.length; n++) {
                    if (morigin[i++] == msub[n]) {
                        if (n == msub.length - 1)
                            cont = true;
                    } else {
                        i = kol;
                        break;
                    }
                }
            }
        }
        return cont;
    }
}
