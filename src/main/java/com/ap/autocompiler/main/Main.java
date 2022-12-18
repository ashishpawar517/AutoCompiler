package com.ap.autocompiler.main;

import com.ap.autocompiler.compilation.GetErrorsFromSource;
import com.ap.autocompiler.compilation.model.Error;
import com.ap.autocompiler.errorfix.SolveErrorsFromSource;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {

  private static String FILE_PATH =
    "src/main/resources/samples/HelloWorldProgram.java";

  public static void main(String[] args) throws IOException {
    if (args != null && args.length > 0) {
      FILE_PATH = args[0];
    }
    System.out.println(Arrays.toString(args));
    List<Error> errors = GetErrorsFromSource.compile(FILE_PATH);
    SolveErrorsFromSource.solve(errors, FILE_PATH);
  }
}
