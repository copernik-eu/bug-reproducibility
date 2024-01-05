compile: lib/log4j-api.jar
	mkdir -p target
	javac -cp lib/log4j-api.jar -d target src/example/Main.java
	javac -p lib/log4j-api.jar -d target src/module-info.java

lib/log4j-api.jar:
	mkdir -p lib
	curl 'https://repo.maven.apache.org/maven2/org/apache/logging/log4j/log4j-api/2.21.1/log4j-api-2.21.1.jar' -o lib/log4j-api.jar

clean:
	rm -r lib target


