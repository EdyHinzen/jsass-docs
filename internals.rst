Internals
=========

jsass use an JNI adapter written in C to bind libsass to java. You can find the
`sources in our repository <https://github.com/bit3/jsass/tree/master/src/main/c>`_ .

Binaries are generally provided for Linux x86_64, Mac OS X and Windows 64bit. If binaries are
missing they where deleted due to changes on the jni code. Feel free to rebuild them an make a
PR with the new binaries. You can use one of our `bin/make-*` scripts to build the binaries.
