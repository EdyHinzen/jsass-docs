import static java.lang.System.err;
import static java.lang.System.out;

import io.bit3.jsass.CompilationException;
import io.bit3.jsass.Compiler;
import io.bit3.jsass.Options;
import io.bit3.jsass.Output;

import java.io.File;
import java.net.URI;

public class CompileFileExample {
  public static void main(String[] args) {
    URI inputFile = new File("stylesheet.scss").toURI();
    URI outputFile = new File("stylesheet.css").toURI();

    Compiler compiler = new Compiler();
    Options options = new Options();

    try {
      Output output = compiler.compileFile(inputFile, outputFile, options);

      out.println("Compiled successfully");
      out.println(output.getCss());
    } catch (CompilationException e) {
      err.println("Compile failed");
      e.printStackTrace(err);
    }
  }
}
