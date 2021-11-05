package com.company;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Stream;

class FileWordsCounter {

    private static final Logger logger = LogManager.getLogger();
    private int wordsNumber;

    public int wordCounter(FileTask task) {
        try (Stream<String> lines = Files.lines(task.getFileName())) {
            String wordToCount = task.getWordToCount();
            wordsNumber = FileWordsCounter.counter(wordToCount, lines);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return wordsNumber;
    }

    private static int counter(String wordToCount, Stream<String> lines) {
        int wordsNumber;
        String regEx = "[^a-zA-Zа-яА-ЯёЁ0-9]+"; //regex to ignore punctuation marks
        wordsNumber = (int) lines.flatMap(line -> Stream.of(line.split(regEx)))
                .filter(word -> word.equalsIgnoreCase(wordToCount))
                .count();
        return wordsNumber;
    }
}
