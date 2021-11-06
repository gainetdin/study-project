package com.company;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class FileWordsCounterTest {

    private final String wordToCount;
    private final int expectedNumber;
    private FileWordsCounter counter;
    private FileTask task;

    @Before
    public void setUp() {
        counter = new FileWordsCounter();
        task = new FileTask(Paths.get("testFiles/fnk.txt"), wordToCount);
    }

    public FileWordsCounterTest(String wordToCount, int expectedNumber) {
        this.wordToCount = wordToCount;
        this.expectedNumber = expectedNumber;
    }

    @Parameterized.Parameters(name = "Word \"{0}\": {1} were found")
    public static List<Object[]> numberOfWords() {
        return Arrays.asList(new Object[][] {
                {"", 0},
                {"Месси", 4},
                {"в", 75},
                {"1", 10}
        });
    }

    @Test
    public void wordCounter() {
        int actualNumber = counter.wordCounter(task);
        Assert.assertEquals(expectedNumber, actualNumber);
    }
}