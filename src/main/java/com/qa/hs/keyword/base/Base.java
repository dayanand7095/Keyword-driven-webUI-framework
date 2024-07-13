package com.qa.hs.keyword.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Base {


    public  WebDriver driver;
    public Properties prop;

    public WebDriver init_driver(String browsername){
        if(browsername.equals("chrome")){

            System.setProperty("webdriver.chrome.driver", "/Users/dayanandrajuk/Downloads/chromedriver-mac-x64/chromedriver");
            if(prop.getProperty("headless").equals("yes")){

                ChromeOptions s=new ChromeOptions();
                s.addArguments("--headless");
                driver = new ChromeDriver(s);
            }else {

                driver = new ChromeDriver();
            }
        }

return driver;
    }
public Properties init_properties(){

    prop = new Properties();
    try {
        
        FileInputStream ip = new FileInputStream("/Users/dayanandrajuk/visual/keyworddrivenhubspot/src/main/java/com/qa/hs/keyword/config/config.properties");
        prop.load(ip); 
    }
    catch (IOException e) {
        e.printStackTrace();
    }
return prop;
}
}
