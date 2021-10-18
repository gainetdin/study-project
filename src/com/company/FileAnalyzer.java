package com.company;

import java.io.*;
import java.util.logging.Logger;

public class FileAnalyzer {

    public static void main(String[] args) {
        Logger logger = Logger.getLogger(FileAnalyzer.class.getName());
        logger.info("Program started");
        File exampleFile = new File("/home/gainetdin/IdeaProjects/FileWordCounter/testFiles/fnk.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(exampleFile))) {
            int fileSymbol = reader.read();
            while (fileSymbol != -1) {
                System.out.print((char) fileSymbol);
                fileSymbol = reader.read();
            }
        } catch (IOException e) {
            logger.warning( e.getMessage() );
        }
    }
}
