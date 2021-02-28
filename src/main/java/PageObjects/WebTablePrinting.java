package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class WebTablePrinting {
    public WebDriver driver;
    By rows = By.xpath("//table[@id='customers']/tbody/tr");

    By coloumnHeader = By.xpath("//table[@id='customers']/tbody/tr/th");

    public WebTablePrinting(WebDriver driver) {
        this.driver = driver;
    }

    public int rows() {
        return driver.findElements(rows).size();
    }

    public int colomn() {
        return driver.findElements(coloumnHeader).size();
    }

    public List<WebElement> ColomnHeader() {
        return driver.findElements(coloumnHeader);
    }

    public void TableData() {
        for (int i = 2; i <= rows(); i++) {
            for (int j = 1; j <= colomn(); j++) {
                By tData = By.xpath("//table[@id='customers']/tbody/tr[" + i + "]/td[" + j + "]");
                String values = driver.findElement(tData).getText();
                System.out.format("%-35s", values);
            }
            System.out.println("");
        }
    }
}