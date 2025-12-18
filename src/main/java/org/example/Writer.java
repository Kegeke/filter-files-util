package org.example;

import org.example.statistic.FloatData;
import org.example.statistic.IntegerData;
import org.example.statistic.StringData;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

import static org.example.DataPatternRegex.isFloat;
import static org.example.DataPatternRegex.isInteger;
import static org.example.DataPatternRegex.isString;

public class Writer {

    private static final String STRING_FILE = "strings";
    private static final String INTEGER_FILE = "integers";
    private static final String FLOAT_FILE = "floats";

    private static final FloatData floatData = new FloatData();
    private static final IntegerData integerData = new IntegerData();
    private static final StringData stringData = new StringData();

    public static void write(FileProperty fileProperty, List<String> pathFiles) throws IOException {
        BufferedWriter stringsWriter = null;
        BufferedWriter integersWriter = null;
        BufferedWriter floatsWriter = null;

        String output = fileProperty.getPathResult();
        if (!Files.exists(Path.of(output))) {
            System.out.printf("Invalid path: %s%n", output);
            output = "";
        }
        output += fileProperty.getPrefixName();

        for (String path : pathFiles) {
            boolean appendMode = fileProperty.isAppendMode();

            try (Scanner scanner = new Scanner(new File(path))) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();

                    if (isString(line)) {
                        stringsWriter = getOrCreateWriter(stringsWriter, output + STRING_FILE, appendMode);
                        stringsWriter.write(line);
                        stringsWriter.newLine();
                        stringData.collect(line);
                    } else if (isInteger(line)) {
                        integersWriter = getOrCreateWriter(integersWriter, output + INTEGER_FILE, appendMode);
                        integersWriter.write(line);
                        integersWriter.newLine();
                        integerData.collect(Long.parseLong(line));
                    } else if (isFloat(line)) {
                        floatsWriter = getOrCreateWriter(floatsWriter, output + FLOAT_FILE, appendMode);
                        floatsWriter.write(line);
                        floatsWriter.newLine();
                        floatData.collect(Double.parseDouble(line));
                    }
                }
            } catch (IOException e) {
                System.out.println("Error: " + e);
            }
        }

        closeIfNotNull(integersWriter);
        closeIfNotNull(floatsWriter);
        closeIfNotNull(stringsWriter);

        printStatistics(fileProperty);
    }

    private static void closeIfNotNull(BufferedWriter writer) throws IOException {
        if (writer != null) {
            writer.close();
        }
    }

    private static BufferedWriter getOrCreateWriter(BufferedWriter writer, String name, boolean appendMode)
            throws IOException {
        if (writer == null) {
            return new BufferedWriter(new FileWriter(name, appendMode));
        }

        return writer;
    }

    private static void printStatistics(FileProperty fileProperty) {
        if (fileProperty.isShortStats()) {
            System.out.printf("Total numbers of elements: %d%n",
                    integerData.getCount() + floatData.getCount() + stringData.getCount());
        }

        if (fileProperty.isFullStats()) {
            integerData.dropStats();
            floatData.dropStats();
            stringData.dropStats();
        }
    }
}
