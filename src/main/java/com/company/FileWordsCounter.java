package com.company;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

class FileWordsCounter {

    private static final Logger logger = LogManager.getLogger();
    private int wordsNumber;

    public int wordCounter(FileTask task) {
        try (BufferedReader bufferedReader = Files.newBufferedReader(task.getFileName())) {
            String wordToCount = task.getWordToCount();
            wordsNumber = FileWordsCounter.counter(wordToCount, bufferedReader);

        } catch (ArrayIndexOutOfBoundsException e) {
            logger.error("Specify 2 arguments: \"file path\" \"word to count\"");

        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return wordsNumber;
    }
    private static int counter(String wordToCount, BufferedReader bufferedReader) {
        Scanner scanner = new Scanner(bufferedReader);
        int wordsNumber = 0;
        String regEx = "[^a-zA-Zа-яА-Я0-9]+"; //regex to ignore punctuation marks
        scanner.useDelimiter(regEx);

        while (scanner.hasNext()) {
            if (scanner.next().equalsIgnoreCase(wordToCount)) {
                wordsNumber++;
            }
        }
        return wordsNumber;
    }
}
