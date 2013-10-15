package org.springframework.data.aerospike.helper;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Extract all <b>Class</b>es from a given package. Will scan jars too.
 * 
 */
public final class ClassReader {

    private ClassReader() {
        // do not instantiate
    }
    
    @SuppressWarnings("rawtypes")
    public static List<Class> getClasses(String packageName) throws IOException, URISyntaxException, ClassNotFoundException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL packageURL;
        ArrayList<String> names = new ArrayList<String>();
        packageName = packageName.replace(".", "/");
        packageURL = classLoader.getResource(packageName);
        if ("jar".equals(packageURL.getProtocol())) {
            // build jar file name, then loop through zipped entries
            String jarFileName = URLDecoder.decode(packageURL.getFile(), "UTF-8");
            JarFile jf = new JarFile(jarFileName.substring(5, jarFileName.indexOf("!")));
            Enumeration<JarEntry> jarEntries = jf.entries();
            while (jarEntries.hasMoreElements()) {
                String entryName = jarEntries.nextElement().getName();
                if (entryName.startsWith(packageName) && entryName.endsWith(".class") && entryName.length() > packageName.length() + 5) {
                    names.add(entryName.replace("/", ".").substring(0, entryName.length() - 6));
                }
            }
            // loop through files in classpath
        } else {
            URI uri = new URI(packageURL.toString());
            File folder = new File(uri.getPath());
            // won't work with path which contains blank (%20)
            // File folder = new File(packageURL.getFile()); 
            File[] contenuti = folder.listFiles();
            for (File actual : contenuti) {
                String entryName = actual.getName();
                entryName = entryName.substring(0, entryName.lastIndexOf('.'));
                names.add(entryName);
            }
        }
        List<Class> clazzez = null;
        if (names.size() > 0) {
            clazzez = new ArrayList<Class>();
            for (String name : names) {
                clazzez.add(Class.forName(name));
            }
        }
        return clazzez;
    }
}
