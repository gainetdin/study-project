package com.company;

import java.io.*;
import java.util.Scanner;
import java.util.logging.Logger;

public class FileAnalyzer {

    public static void main(String[] args) {
        Logger logger = Logger.getLogger(FileAnalyzer.class.getName());

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(args[0]))) {
            String wordToCount = args[1];
            int wordsNumber = wordCounter(bufferedReader, wordToCount);
            System.out.println("Number of \"" + wordToCount + "\" words found: " + wordsNumber);

        } catch (ArrayIndexOutOfBoundsException e) {
            logger.warning("Specify 2 arguments: \"file path\" \"word to count\"");

        } catch (IOException e) {
            logger.warning(e.getMessage());
        }
    }

    static int wordCounter(BufferedReader bufferedReader, String wordToCount) {
        Scanner scanner = new Scanner(bufferedReader);
        int wordsNumber = 0;
        scanner.useDelimiter("[^a-zA-Zа-яА-Я0-9]+"); //regex to ignore punctuation marks

        while (scanner.hasNext()) {
            if (scanner.next().equalsIgnoreCase(wordToCount)) {
                wordsNumber++;
            }
        }
        return wordsNumber;
    }
}
