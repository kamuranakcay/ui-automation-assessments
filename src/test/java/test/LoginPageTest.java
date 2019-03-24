package test;

import base.BaseTest;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.HomePage;
import page.LoginPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class LoginPageTest extends BaseTest {
    public LoginPageTest() {
    }

    @Test
    public void loginScenarios() throws Exception {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);

        File src = new File("data\\loginData.xlsx");
        FileInputStream fis = new FileInputStream(src);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0);

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            homePage.getPage(strUrlWeb);
            homePage.isHomePage();
            homePage.closeIfHomepagePopup();
            homePage.clickBtnLogin();
            loginPage.isLoginPopupHere();

            XSSFRow row = sheet.getRow(i);
            loginPage.setTbEmail(row.getCell(0).toString());
            row = sheet.getRow(i);
            loginPage.setTbPassword(row.getCell(1).toString());
            row = sheet.getRow(i);
            strTestStatus = row.getCell(2).toString();

            loginPage.clickBtnLogin();
            boolean isSuccess = homePage.isLoginSuccess();
            if (isSuccess) {
                Assert.assertEquals("Başarılı", strTestStatus);
                Assert.assertEquals(homePage.checkUserName(), row.getCell(0).toString());
            }
            else {
                WebDriver augmentedDriver = new Augmenter().augment(driver);
                File screenshot = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
                try {
                    FileUtils.copyFile(screenshot, new File("reportFailImages\\"+ System.currentTimeMillis()+ ".png"));
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                Assert.assertEquals("Başarısız", strTestStatus);
            }
        }
    }
}