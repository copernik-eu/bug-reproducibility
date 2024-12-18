# Problem with multi-release JARs

The Eclipse `jar-in-jar` loader does not open multi-release JARs correctly.

To reproduce this, compile and run this project with JDK 9 or later.

```sh
mvn package
java -jar target/eclipse-jar-in-jar-1.jar
```

With a correctly working loader you should see:

```
Multi-Release version: <your JDK version>
```

both times.

Due to a bug the `jar:rsrc:` URL will open the JAR file with a multi-release version of 8.

## Bug reports

This was reported in
[eclipse-jdt/eclipse.jdt.ui#1057](https://github.com/eclipse-jdt/eclipse.jdt.ui/issues/1057)
and fixed in
[eclipse-jdt/eclipse.jdt.ui#1058](https://github.com/eclipse-jdt/eclipse.jdt.ui/pull/1058).