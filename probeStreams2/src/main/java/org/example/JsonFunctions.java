package org.example;

public class JsonFunctions {

    public static String escapeJsonSymbols(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0;i<s.length();i++) {
            char c = s.charAt(i);
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }

    public static String quotes(String s) {
        return "\"" + s + "\"";
    }


}
