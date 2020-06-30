package crawler.helper;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import java.io.*;
import java.net.*;

public class CrawlingHelper {
    public static BufferedReader getBufferedReaderForUrl(String urlString) throws IOException {
        URL url = new URL(urlString);
        CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
        URLConnection connection = url.openConnection();
        String userAgent = "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.180 Mobile Safari/537.36";
        connection.addRequestProperty("User-agent", userAgent);
        InputStream is = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));

        return reader;
    }

    public static XMLEventReader parseStringToXMLEventReader(String xmlString)
            throws UnsupportedEncodingException, XMLStreamException {
        byte[] byteArr = xmlString.getBytes("UTF-8");
        ByteArrayInputStream inputStream = new ByteArrayInputStream(byteArr);
        XMLInputFactory factory = XMLInputFactory.newFactory();
        XMLEventReader eventReader = factory.createXMLEventReader(inputStream);

        return eventReader;
    }

    public static String normalizeHTML(String url) throws IOException {
        String htmlContent = "<?xml version=\\\"1.0\\\" encoding=\\\"UTF-8\\\"?>\\n";
        String line;

        try (BufferedReader reader = getBufferedReaderForUrl(url)) {
            while ((line = reader.readLine()) != null) {
                htmlContent += line + "\n";
            }
        }

        return htmlContent;
    }
}