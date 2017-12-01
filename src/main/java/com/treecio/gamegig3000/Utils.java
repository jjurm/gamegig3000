package com.treecio.gamegig3000;

public class Utils {

    public static int toRange(int v, int min, int max) {
        return Math.max(min, Math.min(v, max));
    }

    public static double toRange(double v, double min, double max) {
        return Math.max(min, Math.min(v, max));
    }

}
