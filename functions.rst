Functions
=========

libsass allow registration of custom functions. These functions are equivalent to `@function` functions in the sass
language. jsass automatically maps methods from any java object to libsass and internally converts java values to
libsass values and vise versa for you.

First you must write an object with public methods.

.. code-block:: java
   :linenos:

    import de.bit3.jsass.annotation.Name;

    public class MyFunctions {
        public String hello(@Name("name") String name) {
            return "Hello " + name;
        }
    }

Then register the object instance to the options.

.. code-block:: java

    options.getFunctionProviders().add(new MyFunctions());

jsass will map the method `MyFunctions::hello(name)` to libsass as `hello($name)`.

What methods are registered?
----------------------------

All directly declared public methods are registered as libsass functions.
Non-public and inherited methods are not registered.

Function signature
------------------

The function signature is build from the method name and the parameter annotation `@Name`.
If the `@Name` annotation is missing, the name will be `argX`.

Default values
--------------

With the `@Default...Value` annotations, `@DefaultStringValue` for strings for example, you can set a default value.
The default value is passed by libsass to your method. There is no way / need to use method overloading.

Value types
-----------

jsass convert as good as it can the java values to libsass values and vise versa.

Java to libsass
^^^^^^^^^^^^^^^

+---------------+---------------+-------------------------------------------+
| Java type     | Libsass type  | Notes                                     |
+===============+===============+===========================================+
| SassString*   | string        | quoting depend on the SassString settings |
+---------------+---------------+-------------------------------------------+
| String        | string        | always quoted with single quotes,         |
|               |               | expect the string contain single quotes   |
+---------------+---------------+-------------------------------------------+
| Boolean       | boolean       |                                           |
+---------------+---------------+-------------------------------------------+
| SassNumber    | double        | unit depend on the SassNumber settings    |
+---------------+---------------+-------------------------------------------+
| Number        | double        | without unit                              |
+---------------+---------------+-------------------------------------------+
| SassColor     | color         |                                           |
+---------------+---------------+-------------------------------------------+
| SassList      | list          | separator depend on the SassList settings |
+---------------+---------------+-------------------------------------------+
| Collection    | list          | always with comma separator               |
+---------------+---------------+-------------------------------------------+
| Map           | map           |                                           |
+---------------+---------------+-------------------------------------------+

libsass to Java
^^^^^^^^^^^^^^^

+---------------+---------------------+---------------------+-----------------------------+
| Libsass type  | Parameter type      | resulting Java type | Notes                       |
+===============+=====================+=====================+=============================+
| string        | SassString*         | SassString*         |                             |
+---------------+---------------------+---------------------+-----------------------------+
| string        | CharSequence        | SassString*         |                             |
+---------------+---------------------+---------------------+-----------------------------+
| string        | String              | String              | Quotation status get lost   |
+---------------+---------------------+---------------------+-----------------------------+
| boolean       | Boolean             | Boolean             |                             |
+---------------+---------------------+---------------------+-----------------------------+
| double        | SassNumber          | SassNumber          |                             |
+---------------+---------------------+---------------------+-----------------------------+
| double        | Number              | SassNumber          |                             |
+---------------+---------------------+---------------------+-----------------------------+
| double        | Double              | Double              | unit get lost               |
+---------------+---------------------+---------------------+-----------------------------+
| double        | Float               | Float               | unit and precision get lost |
+---------------+---------------------+---------------------+-----------------------------+
| double        | Long                | Long                | unit and fraction get lost  |
+---------------+---------------------+---------------------+-----------------------------+
| double        | Integer             | Integer             | unit and fraction get lost  |
+---------------+---------------------+---------------------+-----------------------------+
| double        | Short               | Short               | unit and fraction get lost  |
+---------------+---------------------+---------------------+-----------------------------+
| double        | Byte                | Byte                | unit and fraction get lost  |
+---------------+---------------------+---------------------+-----------------------------+
| color         | SassColor           | SassColor           |                             |
+---------------+---------------------+---------------------+-----------------------------+
| list          | SassList            | SassList            |                             |
+---------------+---------------------+---------------------+-----------------------------+
| list          | Collection          | SassList            |                             |
+---------------+---------------------+---------------------+-----------------------------+
| map           | Map<Object, Object> | Map<Object, Object> |                             |
+---------------+---------------------+---------------------+-----------------------------+

.. note::

    Remind that `SassString implements CharSequence` which is incompatible with `java.lang.String`.
    If possible it is a good idea to use the `Sass*` type classes, but there is no need.

.. note::

    Primitive types are also supported. jsass internally only use object types, but thanks to auto-boxing
    primitive type support is also provided.
