# Problem with trailing `module-info.class` descriptors

The `javac` Java compiler behaves differently depending on whether it finds a **compiled** module descriptor in the target directory.
IMHO this is a bug.

To reproduce it run:

```sh
make
```

twice.

Although the inputs didn't change, the second compilation will fail.

## Bug reports

This was reported as [JDK-8323757](https://bugs.openjdk.org/browse/JDK-8323757) and dismissed as `won't fix`.
Apparently it is the side effect of a feature that enables incremental compilation of modules.