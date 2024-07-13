package com.qa.hs.keyword.engine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.codec.EncoderException;
//import org.apache.poi.hslf.model.Sheet;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.hs.keyword.base.Base;

public class KeyWordEngine {
    public WebDriver driver;
    public Properties prop;

    public static Workbook book;
    public static Sheet sheet;
    public Base base;
    public WebElement element;
    public final String SCENARIO_SHEET_PATH = "/Users/dayanandrajuk/visual/keyworddrivenhubspot/src/main/java/com/qa/hs/keyword/scenarios/hubspot_scenarios.xlsx";

    public void startExecution(String sheetname) {
        String locatorName=null;
        String locatorValue=null;
        FileInputStream file = null;
        try {
            file = new FileInputStream(SCENARIO_SHEET_PATH);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            book = WorkbookFactory.create(file);
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet = book.getSheet(sheetname);
        int k=0;
        for(int i=0;i<sheet.getLastRowNum();i++){
            try {
            String locatorColvalue=sheet.getRow(i+1).getCell(k+1).toString().trim();
            if(!locatorColvalue.equalsIgnoreCase("NA")){
                locatorName= locatorColvalue.split("=")[0].trim();
                locatorValue=locatorColvalue.split("=")[1].trim();
            }     

            String action=sheet.getRow(i+1).getCell(k+2).toString().trim();
            String value=sheet.getRow(i+1).getCell(k+3).toString().trim();

            switch (action) {
                case "open browser":
                    base=new  Base();
                    prop=base.init_properties();
                        if(value.isEmpty() || value.equals("NA")){
                                driver=base.init_driver(prop.getProperty("browser"));
                        }    
                        else{
                             driver=base.init_driver(value);   

                        }           
                    break;
                 case "enter url" :
                        if(value.isEmpty() || value.equals("NA")){
                            driver.get(prop.getProperty("url"));
                        }
                        else{
                            driver.get(value);
                        }
                    break;    
                  case  "quit" :
                        driver.quit();
                     break;
                           
                default:
                    break;
            }

            switch (locatorName) {
                case "id":
                    element= driver.findElement(By.id(locatorValue));
                    if(action.equalsIgnoreCase("sendKeys")){
                        element.clear();
                        element.sendKeys(value);
                    }
                    else if (action.equalsIgnoreCase("click")){
                        element.click();

                    }
                    locatorName=null;
                    break;
                    case "linkText":
                     element =driver.findElement(By.linkText(locatorValue));
                     element.click();
                     locatorName=null;
                     break;
                default:
                    break;
            }
            
        }
        catch( Exception e){
            
        }


    }
}

}
