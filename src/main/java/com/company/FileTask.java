package com.company;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Path;
import java.nio.file.Paths;

class FileTask {

    private static final Logger logger = LogManager.getLogger();

    private final Path fileName;
    private final String wordToCount;

    private FileTask(Path fileName, String wordToCount) {
        this.fileName = fileName;
        this.wordToCount = wordToCount;
    }

    static FileTask fromCMD(String[] args) {
        Path fileName = Paths.get(args[0]);
        String wordToCount = args[1];
        return new FileTask(fileName, wordToCount);
    }

    public Path getFileName() {
        return fileName;
    }

    public String getWordToCount() {
        return wordToCount;
    }

    @Override
    public String toString() {
        return "\"" + wordToCount + "\" word in " + fileName.getFileName();
    }
}
