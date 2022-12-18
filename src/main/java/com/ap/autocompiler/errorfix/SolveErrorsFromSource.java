package com.ap.autocompiler.errorfix;

import com.ap.autocompiler.compilation.BracketParser;
import com.ap.autocompiler.compilation.RegexParser;
import com.ap.autocompiler.compilation.model.Error;
import com.ap.autocompiler.utils.FileUtils;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * Solving all the errors that we got from GetErrorsFromSource
 * version : 1.0
 */
public class SolveErrorsFromSource {

  private static final String REACHED_END_OF_FILE_WHILE_PARSING =
    "reached end of file while parsing";
  private static final String EXPECTED = "expected";

  private static Logger logger = Logger.getLogger(
    SolveErrorsFromSource.class.getName()
  );

  public static void solve(List<Error> errors, String filePath)
    throws IOException {
    if (!RegexParser.check(filePath)) {
      System.out.println("File is not having proper class syntax.");
      return;
    }

    for (Error error : errors) {
      //solve all expected symbolic errors
      if (error.getMessage().split(" ")[1].equals(EXPECTED)) {
        String missingSymbol = error
          .getMessage()
          .split(" ")[0].replaceAll("'", "");

        FileUtils.writeToFileWithNewLine(
          filePath,
          missingSymbol,
          error.getLineNumer()
        );

        logger.info("SOLVED error with missing symbol: " + missingSymbol);
      } else if (error.getMessage().equals(REACHED_END_OF_FILE_WHILE_PARSING)) {
        BracketParser
          .parse(filePath)
          .forEach(
            bracketElement -> {
              if (bracketElement.getKey() == '{') {
                try {
                  FileUtils.writeToFileWithNewLine(
                    filePath,
                    String.valueOf('}'),
                    error.getLineNumer()
                  );
                } catch (IOException e) {
                  e.printStackTrace();
                }
              }
            }
          );
        logger.info("SOLVED error with brackets");
      }
    }
  }
}
