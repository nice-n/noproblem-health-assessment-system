package com.noproblem.has.Utils;

import org.springframework.context.annotation.Configuration;


public class StringUtils {
    public static String[] split(String str, char separator) {
        if (str == null) {
            return null;
        }
        int length = str.length();
        int[] positions = new int[str.length() + 1];
        int lastIndex = 0;
        int i = 0;
        while (i < length) {
            if (str.charAt(i) == separator) {
                positions[lastIndex++] = i;
            }
            i++;
        }
        positions[lastIndex] = length;

        String[] result = new String[lastIndex];
        for (i = 0, lastIndex--; i < lastIndex; i++) {
            int start = positions[i];
            int end = positions[i + 1];
            result[i] = str.substring(start, end);
        }

        if (lastIndex == 0) {
            return new String[] { str };
        }
        result[lastIndex] = str.substring(positions[lastIndex], length);
        return result;
    }
}
