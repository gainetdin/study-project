package com.company;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.HelpFormatter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

class FileTask {

    private static final Logger logger = LogManager.getLogger();

    private final Path fileName;
    private final String wordToCount;

    FileTask(Path fileName, String wordToCount) {
        this.fileName = fileName;
        this.wordToCount = wordToCount;
    }

    static FileTask fromCMD(String... args) {
        //Creating flags parser
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

    /**
     *  Setting flags options to input file name and word to count in this file
     */
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileTask fileTask = (FileTask) o;
        return fileName.equals(fileTask.fileName) && wordToCount.equals(fileTask.wordToCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileName, wordToCount);
    }
}
