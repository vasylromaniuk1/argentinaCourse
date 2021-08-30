package com.solvd.delivery.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyFileParser {

  public static Properties getPropertiesForFile(String filename) {
    Properties prop = new Properties();

    try (InputStream input = PropertyFileParser.class.getClassLoader().getResourceAsStream(filename)) {

      if (input == null) {
        System.out.println("Sorry, unable to find config.properties");
        return null;
      }

      //load a properties file from class path, inside static method
      prop.load(input);


    } catch (IOException ex) {
      ex.printStackTrace();
    }

    return prop;

  }


}
