Basic examples
==============

Compile file
------------

Compiling a file is pretty simple, give an input file and an output file, the rest is just magic.

.. literalinclude:: examples/CompileFileExample.java
   :language: java
   :linenos:

.. literalinclude:: examples/CompileFileContextExample.java
   :language: java
   :linenos:

Compile string
--------------

Compiling a string is pretty simple, give an input string, the rest is just magic.
Providing an input file and output file is always a good idea. With this informations libsass can determine the default
include path and calculate relative paths.

.. literalinclude:: examples/CompileStringExample.java
   :language: java
   :linenos:

.. literalinclude:: examples/CompileStringContextExample.java
   :language: java
   :linenos:
