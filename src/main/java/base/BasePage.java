package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BasePage
{
    protected WebDriver driver;
    public static int TIME_OUT_LONG = 30;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }


    public WebElement waitForElement(int seconds, By elementBy) {
        WebDriverWait wait = new WebDriverWait(driver, seconds, 1000);
        return wait.until(ExpectedConditions.presenceOfElementLocated(elementBy));
    }

    public List<WebElement> waitForElements(int seconds, By elementBy) {
        WebDriverWait wait = new WebDriverWait(driver, seconds, 1000);
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(elementBy));
    }

    public void clickAndWaitForElement(By byElement, int Seconds) throws InterruptedException {
        Thread.sleep(2000);
        WebDriverWait wait = new WebDriverWait(driver, Seconds);
        wait.until(ExpectedConditions.elementToBeClickable(byElement)).click();
    }

    public void clickButton(By by) {
        driver.findElement(by).click();
    }

    public void setTextBox(By by, String str) {
        driver.findElement(by).sendKeys(str);
    }

    public void getPage(String url) {
        driver.get(url);
    }

    public boolean doesExist(int seconds, By by){
        try {
            waitForElement(seconds, by);
            return true;
        } catch (Exception e) {
            return false;
        }
    }



}
