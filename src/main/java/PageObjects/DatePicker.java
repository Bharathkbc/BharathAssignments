package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class DatePicker {

    public WebDriver driver;
    private By frame = By.xpath("//iframe[@class='demo-frame']");
    private By DatePicker = By.cssSelector(".hasDatepicker");
    private By title = By.cssSelector(".ui-datepicker-title");
    private By navigate = By.xpath("//*[@id='ui-datepicker-div']/div/a[2]/span");
    private By dates = By.cssSelector(".ui-state-default");


    public DatePicker(WebDriver driver) {
        this.driver=driver;
    }

    public WebElement getFrame(){
        return driver.findElement(frame);
    }
    public WebElement getDatePicker(){
        return driver.findElement(DatePicker);
    }
    public WebElement getTitle(){
        return driver.findElement(title);
    }
    public WebElement getNavigation(){
        return driver.findElement(navigate);
    }
    public List<WebElement> getDates(){
        return driver.findElements(dates);
    }
}