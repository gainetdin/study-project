package com.company;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class FileTaskTest {

    private final String[] cmdArgs;
    private final FileTask expectedTask;

    public FileTaskTest(String[] cmdArgs, FileTask expectedTask) {
        this.cmdArgs = cmdArgs;
        this.expectedTask = expectedTask;
    }

    @Parameterized.Parameters
    public static List<Object[]> cmdTasks() {
        String file = "example.txt";
        String word = "the";
        FileTask tempTask = new FileTask(Paths.get(file), word);
        return Arrays.asList(new Object[][] {
                {new String[] {"-f", file, "-w", word}, tempTask}, //cmdLine args imitation
                {new String[] {"--file", file, "--word", word}, tempTask},
                {new String[] {"-w", word, "-f", file}, tempTask},
                {new String[] {"-f", word, "-w", file}, new FileTask(Paths.get(word), file)}
        });
    }

    @Test
    public void fromCMD() {
        FileTask actualTask = FileTask.fromCMD(cmdArgs);
        Assert.assertEquals(expectedTask, actualTask);
    }

//    @Test(expected = ParseException.class)
//    public void fromCMDWithException() throws ParseException {
//        FileTask.fromCMD("-f", "-w");
//    }
}