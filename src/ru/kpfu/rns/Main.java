package ru.kpfu.rns;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        int amountOfRandomNumbers = 200;
        String fileWithRandomNumbers = "files/file_with_random_numbers.txt";
        String fileWithUniqueNumbers = "files/file_with_unique_numbers.txt";
        File f = new File("files");
        if (!f.exists() || !f.isDirectory()) {
            try {
                f.mkdir();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            createFileAndFillWithRandomNumbers(amountOfRandomNumbers, fileWithRandomNumbers);
            createFileWithUniqueNumbersFromOtherFile(fileWithUniqueNumbers, fileWithRandomNumbers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createFileAndFillWithRandomNumbers(int sequenceLength, String filePath) throws IOException {
        List<String> lines = new ArrayList<>();
        for (int i = 0; i < sequenceLength; i++) {
            Integer randomNumber = new Random().nextInt(151);
            lines.add(randomNumber.toString());
        }
        Files.write(Paths.get(filePath), lines);
    }

    private static void createFileWithUniqueNumbersFromOtherFile(String newFilePath, String otherFilePath)
            throws IOException {
        List<String> listOfLines = Files.readAllLines(Paths.get(otherFilePath));
        Set<String> setOfLines = new HashSet<>(listOfLines);
        Files.write(Paths.get(newFilePath), setOfLines);
    }
}
