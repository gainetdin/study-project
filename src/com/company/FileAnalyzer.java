package com.company;

import java.io.*;
import java.util.Scanner;
import java.util.logging.Logger;

public class FileAnalyzer {

    public static void main(String[] args) {
        Logger logger = Logger.getLogger(FileAnalyzer.class.getName());

        try (Scanner scanner = new Scanner(new File(args[0]))) {

            String matchingWord = args[1];
            int countMatching = 0;
            scanner.useDelimiter("[^a-zA-Zа-яА-Я0-9]+"); //regex to ignore punctuation marks

            while (scanner.hasNext()) {
                if (scanner.next().equalsIgnoreCase(matchingWord)) {
                    countMatching++;
                }
            }
            System.out.println("Number of \"" + args[1] + "\" words found: " + countMatching);

        } catch (ArrayIndexOutOfBoundsException e) {
            logger.warning("Specify 2 arguments: \"file path\" \"word to count\"");

        } catch (IOException e) {
            logger.warning(e.getMessage());
        }
    }
}
