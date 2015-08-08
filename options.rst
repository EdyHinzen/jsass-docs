Options
=======

The options class allow to customize each compilation process. Most options are equals to the well known command line
options from sass compilers.

Function providers
------------------

.. code-block:: java

    options.getFunctionProviders().add(new MyFunctions());

Headers
-------

.. code-block:: java

    options.getHeaderImporters().add(new MyHeaderImporter());

Importers
---------

.. code-block:: java

    options.getImporters().add(new MyImporter());

Include paths
-------------

.. code-block:: java

    options.getIncludePaths().add(new File("bower_components/foundation/scss");

Indention
---------

.. code-block:: java

    options.setIndent("\t");

SASS syntax
-----------

Treat source_string as sass (as opposed to scss).

.. code-block:: java

    options.setIsIndentedSyntaxSrc(true);

Linefeed
--------

.. code-block:: java

    options.setLinefeed("\r\n");

Omit source map url
-------------------

Disable sourceMappingUrl in css output.

.. code-block:: java

    options.setOmitSourceMapUrl(true);

Output style
------------

Output style for the generated css code.

.. code-block:: java

    options.setOutputStyle(io.bit3.jsass.OutputStyle.NESTED);

Precision
---------

Precision for outputting fractional numbers.

.. code-block:: java

    options.setPrecision(6);

Inline source comments
----------------------

If you want inline source comments.

.. code-block:: java

    options.setSourceComments(true);

Embed contents in source map
----------------------------

Embed include contents in maps.

.. code-block:: java

    options.setSourceMapContents(true);

Embedded source map
-------------------

Embed sourceMappingUrl as data uri.

.. code-block:: java

    options.setSourceMapEmbed(true);

Source map
----------

Path to source map file. Enables the source map generating. Used to create sourceMappingUrl.

.. code-block:: java

    options.setSourceMapFile(new File("stylesheet.css.map");
