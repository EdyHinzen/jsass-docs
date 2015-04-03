Basic examples
==============

Compile file
------------

Compiling a file is pretty simple, give an input file and an output file, the rest is just magic.

.. code-block:: java
   :linenos:

    import de.bit3.jsass.CompilationException;
    import de.bit3.jsass.Compiler;
    import de.bit3.jsass.Options;
    import de.bit3.jsass.Output;

    import java.io.File;

    public class App {
        public static void main(String[] args) {
            File inputFile = new File("stylesheet.scss");
            File outputFile = new File("stylesheet.css");

            Compiler compiler = new Compiler();
            Options options = new Options();

            try {
                Output output = compiler.compileFile(inputFile, outputFile, options);
                System.out.println(output.getCss());
            } catch (CompilationException e) {
                e.printStackTrace();
            }
        }
    }

Compile string
--------------

Compiling a string is pretty simple, give an input string, the rest is just magic.
Providing an input file and output file is always a good idea. With this informations libsass can determine the default
include path and calculate relative paths.

.. code-block:: java
   :linenos:

    import de.bit3.jsass.CompilationException;
    import de.bit3.jsass.Compiler;
    import de.bit3.jsass.Options;
    import de.bit3.jsass.Output;

    import java.io.File;

    public class App {
        public static void main(String[] args) {
            String input = "body { color: red; }";
            File inputFile = new File("stylesheet.scss");
            File outputFile = new File("stylesheet.css");

            Compiler compiler = new Compiler();
            Options options = new Options();

            try {
                Output output = compiler.compileString(
                        input, inputFile, outputFile, options);
                System.out.println(output.getCss());
            } catch (CompilationException e) {
                e.printStackTrace();
            }
        }
    }

Compiling context
-----------------

A context is an object that contains all information to compile specific sass code.

There are two types of contextes. The file context that compile a file and the string context that compile an
in-memory sass code.

Both `Compiler.compileFile()` and `Compiler.compileString()` are convenience methods around the
`Context.compile(FileContext)` and `Context.compile(StringContext)` methods.

.. code-block:: java
   :linenos:

    import de.bit3.jsass.CompilationException;
    import de.bit3.jsass.Compiler;
    import de.bit3.jsass.Options;
    import de.bit3.jsass.Output;
    import de.bit3.jsass.context.StringContext;

    import java.io.File;

    public class App {
        public static void main(String[] args) {
            String input = "body { color: red; }";
            File inputFile = new File("stylesheet.scss");
            File outputFile = new File("stylesheet.css");

            Compiler compiler = new Compiler();
            Options options = new Options();

            StringContext context = new StringContext(
                    input, inputFile, outputFile, options);

            try {
                Output output = compiler.compile(context);
                System.out.println(output.getCss());
            } catch (CompilationException e) {
                e.printStackTrace();
            }
        }
    }

