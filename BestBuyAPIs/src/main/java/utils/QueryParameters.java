package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class QueryParameters {
    public static String BASE_URI;
    public static int DEFAULT_PRODUCT_ID;

    static {
        try {
            Properties props = new Properties();
            FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
            props.load(fis);

            BASE_URI = props.getProperty("baseUri");
            DEFAULT_PRODUCT_ID = Integer.parseInt(props.getProperty("defaultProductId"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
