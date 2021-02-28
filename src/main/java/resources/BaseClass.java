package resources;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseClass {
    public WebDriver driver;
    public Properties prop;
    public WebDriver initializerDriver() throws IOException {
        prop = new Properties();
        FileInputStream fis = new FileInputStream("C:\\Users\\hello\\FinalAssignment\\src\\main\\java\\resources\\data\\data.properties");
        prop.load(fis);
        String browserName = prop.getProperty("browser");
        if (browserName.equals("chrome")) {

            System.setProperty("webdriver.chrome.driver", "C:\\Users\\hello\\Downloads\\chromedriver_win32\\chromedriver.exe");


            driver = new ChromeDriver();
        } else if (browserName.equals("InternetExplorer")) {

            System.setProperty("webdriver.ie.driver","C:\\Users\\hello\\Downloads\\IEDriverServer_x64_3.150.1\\IEDriverServer.exe");


            driver = new InternetExplorerDriver();


        }
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        return driver;
    }
}