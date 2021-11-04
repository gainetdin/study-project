package com.company;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileAnalyzer {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {

        FileTask task = FileTask.fromCMD(args);
        FileWordsCounter counter = new FileWordsCounter();
        int wordsNumber = counter.wordCounter(task);
        logger.info("For task to find {}: {} were found", task, wordsNumber);
    }
}
