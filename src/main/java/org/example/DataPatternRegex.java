package org.example;

import java.util.regex.Pattern;

public class DataPatternRegex {

    private static final Pattern STRING_REGEX = Pattern.compile("^[\\p{L}\\s]+$");
    private static final Pattern INTEGER_REGEX = Pattern.compile("^[-+]?\\d+$");
    private static final Pattern FLOAT_REGEX = Pattern.compile("^[-+]?\\d*\\.?\\d+([eE][-+]?\\d+)?$");

    public static boolean isInteger(String line) {
        return INTEGER_REGEX.matcher(line).matches();
    }

    public static boolean isFloat(String line) {
        return FLOAT_REGEX.matcher(line).matches();
    }

    public static boolean isString(String line) {
        return STRING_REGEX.matcher(line).matches();
    }
}
