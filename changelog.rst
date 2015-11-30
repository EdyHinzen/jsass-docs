Changelog
=========

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
