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

