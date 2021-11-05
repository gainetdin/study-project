package com.company;

import org.apache.commons.cli.*;
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
        //creating flags to input file name and word to count in this file
        Options options = getFlagsOptions();
        CommandLineParser cmdParser = new DefaultParser();
        CommandLine cmdLine = null;

        try {
            cmdLine = cmdParser.parse(options, args);
        } catch (ParseException e) {
            logger.error(e.getMessage());
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("java -jar FileWordCounter-1.0-SNAPSHOT.jar", options);
            System.exit(1);
        }

        Path fileName = Paths.get(cmdLine.getOptionValue("f"));
        String wordToCount = cmdLine.getOptionValue("w");

        return new FileTask(fileName, wordToCount);
    }

    private static Options getFlagsOptions() {
        return new Options()
                .addOption(Option.builder("f")
                        .required()
                        .hasArg()
                        .argName("filename")
                        .desc("File name")
                        .longOpt("file")
                        .build())
                .addOption(Option.builder("w")
                        .required()
                        .hasArg()
                        .argName("word")
                        .desc("Word to count")
                        .longOpt("word")
                        .build());
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
