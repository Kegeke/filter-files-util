package org.example;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Path> pathFiles = new ArrayList<>();
        FileProperty fileProperty = new FileProperty();

        for (int i = 0; i < args.length; i++) {
            String arg = args[i];

            if (arg.endsWith(".txt")) {
                pathFiles.add(Paths.get(arg));
                continue;
            }

            int nextIndex = i + 1;
            switch (arg) {
                case "-p" -> {
                    if (nextIndex == args.length) {
                        System.out.printf("Prefix not found%n");
                        break;
                    }
                    if (args[nextIndex].startsWith("-") || args[nextIndex].endsWith(".txt")) {
                        System.out.printf("Invalid prefix %s%n", args[nextIndex]);
                        break;
                    }
                    fileProperty.setPrefixName(args[nextIndex]);
                    i++;
                }
                case "-o" -> {
                    if (nextIndex == args.length) {
                        System.out.printf("Path not found%n");
                        break;
                    }
                    if (args[nextIndex].startsWith("-") || args[nextIndex].endsWith(".txt")) {
                        System.out.printf("Invalid path %s%n", args[nextIndex]);
                        break;
                    }
                    fileProperty.setPathResult(args[nextIndex]);
                    i++;
                }
                case "-a" -> fileProperty.setAppendMode(true);
                case "-s" -> fileProperty.setShortStats(true);
                case "-f" -> fileProperty.setFullStats(true);
                default -> System.out.printf("Unknow parameter %s%n", arg);
            }
        }

        if (pathFiles.isEmpty()) {
            System.out.printf("Files not found%n");
            return;
        }

        try {
            Writer.write(fileProperty, pathFiles);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}