package com.ap.autocompiler.compilation;

import com.ap.autocompiler.compilation.model.Error;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

/**
 *
 * Find all possible errors from source
 * version: 1.0
 */
public class GetErrorsFromSource {

  private static final Logger LOGGER = Logger.getLogger(
    GetErrorsFromSource.class.getName()
  );

  public static List<Error> compile(String fileName) throws IOException {
    JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
    DiagnosticCollector<JavaFileObject> diagnosticsCollector = new DiagnosticCollector<JavaFileObject>();
    StandardJavaFileManager fileManager = compiler.getStandardFileManager(
      diagnosticsCollector,
      null,
      null
    );
    Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromStrings(
      Arrays.asList(fileName)
    );
    JavaCompiler.CompilationTask task = compiler.getTask(
      null,
      fileManager,
      diagnosticsCollector,
      null,
      null,
      compilationUnits
    );
    boolean success = task.call();
    List<Error> errors = new ArrayList<>();
    if (!success) {
      List<Diagnostic<? extends JavaFileObject>> diagnostics = diagnosticsCollector.getDiagnostics();
      for (Diagnostic<? extends JavaFileObject> diagnostic : diagnostics) {
        // read error dertails from the diagnostic object
        errors.add(
          new Error(
            (int) diagnostic.getLineNumber(),
            diagnostic.getMessage(null)
          )
        );
      }
    }
    fileManager.close();
    LOGGER.info("Success: " + success);
    return errors;
  }
}
