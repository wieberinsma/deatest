package httpserver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;

public class HtmlPageReader
{
    private static final String HTML_ROOT = "pages";

    public String readFile(String filename) throws IOException
    {
        ClassLoader classLoader = getClass().getClassLoader();
        URL localURL = classLoader.getResource(HTML_ROOT + filename);

        if (localURL != null)
        {
            var systemPath = new File(localURL.getFile()).toPath();
            return new String(Files.readAllBytes(systemPath));
        }
        else
        {
            throw new FileNotFoundException("Could not find file.");
        }
    }
}
