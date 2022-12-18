package com.ap.autocompiler.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Logger;

public class FileUtils {

  private static Logger LOGGER = Logger.getLogger(FileUtils.class.getName());

  public static void writeToFileWithNewLine(
    String filePath,
    String data,
    Integer lineNumber
  )
    throws IOException {
    List<String> lines = readFile(filePath);
    int lineIndex = lineNumber - 1;

    String currentLine = lines.get(lineIndex);
    lines.set(lineIndex, currentLine.concat(data));
    Files.write(Paths.get(filePath), lines);

    LOGGER.info("data written to file.");
  }

  public static List<String> readFile(String filePath) throws IOException {
    return Files.readAllLines(Paths.get(filePath));
  }

  /**
   * Support for longer files
   *
   * String line;
   * try (Stream<String> lines = Files.lines(Paths.get("file.txt"))) {
   * line = lines.skip(n).findFirst().get();
   * }
   *
   * @param filePath
   * @param data
   * @param lineNumber
   */
  public static void writeToFileWithNewLine(
    String filePath,
    String data,
    Long lineNumber
  ) {}
}
