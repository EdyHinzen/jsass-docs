Changelog
=========

Version 5.0.0
-------------

In jsass 5 the error handling has changed to full exceptions.

Before jsass 5:

.. code-block:: java

    Output output = compiler.compileFile(inputFile, outputFile, options);

    if (0 == output.getErrorStatus()) {
      out.println("Compiled successfully");
      out.println(output.getCss());
    } else {
      out.println("Compiled failed");
      out.println(output.getErrorText());
    }

Since jsass 5:

.. code-block:: java

    try {
      Output output = compiler.compileFile(inputFile, outputFile, options);

      out.println("Compiled successfully");
      out.println(output.getCss());
    } catch (CompilationException e) {
      out.println("Compiled failed");
      out.println(e.getErrorText());
    }

Version 4.0.0
-------------

jsass 4+ using libsass 3.3+. The importer API has changed a little bit, so the jsass API changed
too.

- ``Import#uri`` has been renamed to ``Import#importUri``
- ``Import#base`` has been renamed to ``Import#absoluteUri``

Here is an example what is the effect of the change:

.. code-block:: java

    // jsass 3+ style
    Import fileImport = new Import(
            new URI("import.scss"),
            new File("public/assets").toURI(),
            contents
    );

.. code-block:: java

    // jsass 4+ style
    Import fileImport = new Import(
            new URI("import.scss"),
            new File("public/assets/import.scss").toURI(),
            contents
    );

As you can see you must provide the absolute file path as second argument now, instead of the
absolute base directory path.
