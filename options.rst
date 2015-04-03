Options
=======

The options class allow to customize each compilation process. Most options are equals to the well known command line
options from sass compilers.

Precision
---------

Precision for outputting fractional numbers.

.. code-block:: java

    options.setPrecision(6);

Output style
------------

Output style for the generated css code.

.. code-block:: java

    options.setOutputStyle(de.bit3.jsass.OutputStyle.NESTED);

Inline source comments
----------------------

If you want inline source comments.

.. code-block:: java

    options.setSourceComments(true);

Embedded source map
-------------------

Embed sourceMappingUrl as data uri.

.. code-block:: java

    options.setSourceMapEmbed(true);

Embed contents in source map
----------------------------

Embed include contents in maps.

.. code-block:: java

    options.setSourceMapContents(true);

Omit source map url
-------------------

Disable sourceMappingUrl in css output

.. code-block:: java

    options.setOmitSourceMapUrl(true);

SASS syntax
-----------

Treat source_string as sass (as opposed to scss).

.. code-block:: java

    options.setIsIndentedSyntaxSrc(true);

Image url
---------

For the image-url Sass function.

.. code-block:: java

    options.setImageUrl("img/");

Include paths
-------------

.. code-block:: java

    options.getIncludePaths().add(new File("bower_components/foundation/scss");

Source map
----------

Path to source map file. Enables the source map generating. Used to create sourceMappingUrl.

.. code-block:: java

    options.setSourceMapFile(new File("stylesheet.css.map");

Function providers
------------------

Register custom function providers.

.. code-block:: java

    options.getFunctionProviders().add(new MyFunctions());

Importers
---------

Register custom importers.

.. code-block:: java

    options.getImporters().add(new MyImporter());
