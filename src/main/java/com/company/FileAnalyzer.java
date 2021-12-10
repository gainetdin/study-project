package com.company;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

public class FileAnalyzer {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {

        FileTask task = FileTask.fromCMD(args);
        FileWordsCounter counter = new FileWordsCounter();
        int wordsNumber = counter.wordCounter(task);
        Configurator.setAllLevels(FileAnalyzer.class.getName(), Level.INFO);
        logger.info("For task to find {}: {} were found", task, wordsNumber);
    }
}
