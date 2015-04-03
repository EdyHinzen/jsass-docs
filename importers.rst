Importers
=========

Importers are an experimental feature of libsass.
They allow to manipulate the way how `@import` works.

.. warning::

    The import source string must be in SCSS syntax. SASS syntax is not supported yet!

In jsass importers must implement the `de.bit3.jsass.importer.Importer` interface.

.. code-block:: java
   :linenos:

    import de.bit3.jsass.context.Context;
    import de.bit3.jsass.importer.Import;
    import de.bit3.jsass.importer.Importer;

    import java.io.File;
    import java.net.URI;
    import java.net.URISyntaxException;
    import java.util.Arrays;
    import java.util.Collection;

    public class MyImporter implements Importer {
        @Override
        public Collection<Import> apply(String url, String previous, Context originalContext) {
            try {
                return Arrays.asList(
                        new Import(
                                new URI("import.scss"),
                                new File("public/assets").toURI()
                        )
                );
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }
    }

Then register the object instance to the options.

.. code-block:: java

    options.getImporters().add(new MyImporter());

Thats all! From now on, each `@import` will be passed through your custom importer.

Skip importer
-------------

If you importer should be skipped, just return `null`.

.. code-block:: java
   :linenos:

    public class MyImporter implements Importer {
        @Override
        public Collection<Import> apply(String url, String previous, Context originalContext) {
            // ...

            if (someReasonToSkipThisImporter) {
                return null;
            }

            // ...
        }
    }

Skip import
-----------

Sometimes you may want to omit an `@import` directive. In this case, return an empty list.

.. code-block:: java
   :linenos:

    public class MyImporter implements Importer {
        @Override
        public Collection<Import> apply(String url, String previous, Context originalContext) {
            // ...

            if (someReasonToSkipThisImportRule) {
                return new LinkedList<>();
            }

            // ...
        }
    }

Import a file
-------------

Importing a file is one of the basic ways to import a source. Fill the path URI with the relative file name
and the base URI with the absolute base path, leave everything else empty. libsass will search the file in
the path and import it.

.. code-block:: java

    Import fileImport = new Import(
            new URI("import.scss"),
            new File("public/assets").toURI()
    );

Import a string
---------------

Importing a string is as simple as importing a file. Just add the string contents to the import.

.. code-block:: java

    String contents = ".hello { content: 'Hello world'! }";

    Import fileImport = new Import(
            new URI("import.scss"),
            new File("public/assets").toURI(),
            contents
    );
