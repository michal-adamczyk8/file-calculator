package org.storware.calculator.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class FileReader {

    /**
     * Method responsible for reading file based on provided filePath and returning list of lines.
     *
     * @param filePath
     * @return
     */
    public static List<String> readFile(String filePath) {
        List<String> lines = new LinkedList<>();
        try (Stream<String> linesStream = Files.lines(Paths.get(filePath))) {
            lines = linesStream.collect(Collectors.toList());
        } catch (IOException e) {
            //log.error("Error occurred while reading the file: " + filePath, e);
        }
        return lines;
    }
}
