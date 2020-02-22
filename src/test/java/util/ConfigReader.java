package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    public static String readProperty(String property){
        Properties prop = null;

        try {
            FileInputStream fileInput = new FileInputStream(Driver.propertyPath);
            prop = new Properties();
            prop.load(fileInput);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop.getProperty(property);
    }





}
