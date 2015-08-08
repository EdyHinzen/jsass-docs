Functions
=========

libsass allow registration of custom functions. These functions are equivalent to `@function` functions in the sass
language. jsass automatically maps methods from any java object to libsass and internally converts java values to
libsass values and vise versa for you.

First you must write an object with public methods.

.. code-block:: java
   :linenos:

    import io.bit3.jsass.annotation.Name;

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

jsass brings all sass types as java types. If you prefer to use native java types, you can.
jsass will convert the values for you as good as it can. For details have a look into the
`TypeUtils <https://github.com/bit3/jsass/blob/master/src/main/java/io/bit3/jsass/type/TypeUtils.java>`_
class, which will do the conversion.

Java to libsass
^^^^^^^^^^^^^^^

+------------------------+---------------+--------------------------------------------+
| Java type              | Libsass type  | Notes                                      |
+========================+===============+============================================+
| **Primitive types**                                                                 |
+------------------------+---------------+--------------------------------------------+
| SassNull               | *null*        |                                            |
+------------------------+---------------+--------------------------------------------+
| *null*                 | *null*        |                                            |
+------------------------+---------------+--------------------------------------------+
| SassBoolean            | boolean       |                                            |
+------------------------+---------------+--------------------------------------------+
| Boolean                | boolean       |                                            |
+------------------------+---------------+--------------------------------------------+
| SassNumber             | double        | Unit depend on the SassNumber settings.    |
+------------------------+---------------+--------------------------------------------+
| ? extends Number       | double        | A number without any unit.                 |
+------------------------+---------------+--------------------------------------------+
| **Complex types**                                                                   |
+------------------------+---------------+--------------------------------------------+
| SassString             | string        | Quoting depend on the SassString settings. |
+------------------------+---------------+--------------------------------------------+
| String                 | string        | Always quoted with double quotes.          |
+------------------------+---------------+--------------------------------------------+
| ? extends CharSequence | string        | Always quoted with double quotes.          |
+------------------------+---------------+--------------------------------------------+
| SassColor              | color         |                                            |
+------------------------+---------------+--------------------------------------------+
| SassList               | list          | Separator depend on the SassList settings. |
+------------------------+---------------+--------------------------------------------+
| ? extends Collection   | list          | always with comma separator                |
+------------------------+---------------+--------------------------------------------+
| SassMap                | map           |                                            |
+------------------------+---------------+--------------------------------------------+
| ? extends Map          | map           |                                            |
+------------------------+---------------+--------------------------------------------+
| **Errors and warnings**                                                             |
+------------------------+---------------+--------------------------------------------+
| SassError              | error         |                                            |
+------------------------+---------------+--------------------------------------------+
| SassWarning            | warning       |                                            |
+------------------------+---------------+--------------------------------------------+
| ? extends Throwable    | error         | If your function throw an exception or     |
|                        |               | error, it will be returned to libsass as   |
|                        |               | error value.                               |
+------------------------+---------------+--------------------------------------------+

libsass to Java
^^^^^^^^^^^^^^^

+---------------+------------------------+---------------------+------------------------------+
| Libsass type  | Parameter type         | resulting Java type | Notes                        |
+===============+========================+=====================+==============================+
| **Primitive types**                                                                         |
+---------------+------------------------+---------------------+------------------------------+
| *null*        | \*                     | *null*              | Simply a *null* value!       |
+---------------+------------------------+---------------------+------------------------------+
| boolean       | SassBoolean            | SassBoolean         |                              |
+---------------+------------------------+---------------------+------------------------------+
| boolean       | Boolean                | Boolean             |                              |
+---------------+------------------------+---------------------+------------------------------+
| double        | SassNumber             | SassNumber          |                              |
+---------------+------------------------+---------------------+------------------------------+
| double        | Number                 | SassNumber          |                              |
+---------------+------------------------+---------------------+------------------------------+
| double        | Double                 | Double              | Unit get lost.               |
+---------------+------------------------+---------------------+------------------------------+
| double        | Float                  | Float               | Unit and precision get lost. |
+---------------+------------------------+---------------------+------------------------------+
| double        | Long                   | Long                | Unit and fraction get lost.  |
+---------------+------------------------+---------------------+------------------------------+
| double        | Integer                | Integer             | Unit and fraction get lost.  |
+---------------+------------------------+---------------------+------------------------------+
| double        | Short                  | Short               | Unit and fraction get lost.  |
+---------------+------------------------+---------------------+------------------------------+
| double        | Byte                   | Byte                | Unit and fraction get lost.  |
+---------------+------------------------+---------------------+------------------------------+
| **Complex types**                                                                           |
+---------------+------------------------+---------------------+------------------------------+
| string        | SassString*            | SassString*         |                              |
+---------------+------------------------+---------------------+------------------------------+
| string        | String                 | String              | Quotation status get lost.   |
+---------------+------------------------+---------------------+------------------------------+
| string        | CharSequence           | SassString*         |                              |
+---------------+------------------------+---------------------+------------------------------+
| color         | SassColor              | SassColor           |                              |
+---------------+------------------------+---------------------+------------------------------+
| list          | SassList               | SassList            |                              |
+---------------+------------------------+---------------------+------------------------------+
| list          | Collection             | SassList            |                              |
+---------------+------------------------+---------------------+------------------------------+
| map           | Map<String, SassValue> | SassMap             |                              |
+---------------+------------------------+---------------------+------------------------------+

.. note::

    Remind that `SassString implements CharSequence` which is incompatible with `java.lang.String`.
    If possible it is a good idea to use the `Sass*` type classes, but there is no need.

.. note::

    Primitive types are also supported. jsass internally only use object types, but thanks to auto-boxing
    primitive type support is also provided.
