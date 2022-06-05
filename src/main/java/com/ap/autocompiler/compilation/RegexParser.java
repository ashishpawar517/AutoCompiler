package com.ap.autocompiler.compilation;

import com.ap.autocompiler.utils.FileUtils;
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class RegexParser {

  private static final String JAVA_CLASS_REGEX =
    "(((|public|final|abstract|private|static|protected)(\\s+))?(class)(\\s+)(\\w+)(<.*>)?(\\s+extends\\s+\\w+)?(<.*>)?(\\s+implements\\s+)?(.*)?(<.*>)?(\\s*))\\{$";

  // private String interfaceRegExp =
  //   "(((|public|final|abstract|private|static|protected)(\\s+))?interface(\\s+)(\\w+)(<.*>)?(\\s+extends\\s+\\w+)?(<.*>)?(\\s+implements\\s+)?(.*)?(<.*>)?(\\s*))\\{$";

  // private String enumRegExp =
  //   "((((public|final|private|static|protected)(\\s+))?enum(\\s+)(\\w+)?(\\s+implements\\s+\\w+)?(.*)?\\s*))\\{$";

  public static boolean check(String filePath) throws IOException {
    return Pattern
      .compile(JAVA_CLASS_REGEX)
      .matcher(
        FileUtils.readFile(filePath).stream().collect(Collectors.joining())
      )
      .lookingAt();
  }
}
