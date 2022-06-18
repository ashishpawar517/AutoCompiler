package com.ap.autocompiler.compilation;

import com.ap.autocompiler.utils.FileUtils;
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class RegexParser {

  private static final String JAVA_CLASS_REGEX =
    "(private|public)\\s(class)\\s(\\w+)\\s?\\{(\\W*.*)*\\}$";

  public static boolean check(String filePath) throws IOException {
    return Pattern
      .compile(JAVA_CLASS_REGEX, Pattern.MULTILINE)
      .matcher(
        FileUtils.readFile(filePath).stream().collect(Collectors.joining())
      )
      .lookingAt();
  }
}
