package eu.copernik.eclipse;

import java.io.IOException;
import java.net.JarURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.jar.JarFile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    public static void main(String[] args) throws Exception {
        final URL main = Class.forName("org.eclipse.jdt.internal.jarinjarloader.JarRsrcLoader", false, ClassLoader.getSystemClassLoader())
                .getProtectionDomain()
                .getCodeSource()
                .getLocation();
        printURL(new URL("jar:" + main.toExternalForm() + "!/#runtime"));
        final URL log4j = new URL("jar:rsrc:log4j-api.jar!/#runtime");
        printURL(log4j);
    }

    private static void printURL(final URL url) throws IOException {
        System.out.println("Testing URL " + url);
        final URLConnection urlConnection = url.openConnection();
        System.out.println("URLConnection type: " + urlConnection.getClass());
        if (urlConnection instanceof JarURLConnection) {
            final JarURLConnection jarURLConnection = (JarURLConnection) urlConnection;
            final JarFile jarFile = jarURLConnection.getJarFile();
            System.out.println("JAR file type: " + jarFile.getClass());
            System.out.println("JAR file name: "  + jarFile.getName());
            System.out.println("Multi-Release: " + jarFile.isMultiRelease());
            System.out.println("Multi-Release version: " + jarFile.getVersion());
        }
    }
}
