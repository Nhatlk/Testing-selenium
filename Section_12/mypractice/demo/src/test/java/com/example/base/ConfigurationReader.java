package com.example.base;

import java.io.InputStream;
import java.util.Properties;
  public class ConfigurationReader { 
    private static Properties properties = new Properties(); 
    static { 
      try (InputStream inputStream = ConfigurationReader.class.getClassLoader()
                                    .getResourceAsStream("config.properties")) { 
        if (inputStream != null) { 
          properties.load(inputStream); 
        } else { 
          System.out.println("&gt;&gt;&gt; Không tìm thấy file config.properties trong resources!"); 
        } 
      } catch (Exception e) { 
          e.printStackTrace(); } 
        } 
        
    public static String getProperty(String key) { 
      return properties.getProperty(key); 
    } 
  }