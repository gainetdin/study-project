package com.company;

import org.junit.Assert;
import org.junit.Test;

import java.io.*;

public class FileAnalyzerTest {

    File testFile = new File("testFiles/fnk.txt");

    @Test
    public void wordCounter() throws IOException {
        FileReader fileReader = new FileReader(testFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        Assert.assertEquals(4, FileAnalyzer.wordCounter(bufferedReader, "Месси"));

        fileReader = new FileReader(testFile);
        bufferedReader = new BufferedReader(fileReader);
        Assert.assertEquals(75, FileAnalyzer.wordCounter(bufferedReader, "в"));

        fileReader = new FileReader(testFile);
        bufferedReader = new BufferedReader(fileReader);
        Assert.assertEquals(0, FileAnalyzer.wordCounter(bufferedReader, ""));
    }
}