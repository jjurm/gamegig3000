package com.treecio.gamegig3000;

public class Utils {

    public static int toRange(int v, int min, int max) {
        return Math.min(min, Math.max(v, max));
    }

}
