package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest
{
    public WebDriver driver;

    public String strEmail = "kamuranakcay@gmail.com";
    public String strPassword = "passwordtest";
    public String strUrlWeb = "https://www.trendyol.com/";
    public String strTestStatus = "Başarılı";

    public BaseTest() {
    }

    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

}