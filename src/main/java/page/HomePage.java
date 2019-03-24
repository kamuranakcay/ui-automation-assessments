package page;

import base.BasePage;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import util.ImageResponse;
import util.LinkResponse;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends BasePage {
    public By btnLogin = By.className("login-register-button-container");
    public By txtbSearch = By.id("AutoCompleteBox");
    public By containerLoggedIn = By.xpath("//span[@id='logged-in-container']//div[@class='user-email']");
    public By popupHomePage = By.className("homepage-popup");
    public By closeHomePagePopup = By.xpath("//a[@title='Kapat']");
    public By aBoutiqueLink = By.xpath("//div[@class='butik-large-image']/a");
    public By imgBoutiqueImageSrc = By.xpath("//img[contains(@class, 'bigBoutiqueImage')]");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void clickBtnLogin() throws InterruptedException {
        clickAndWaitForElement(btnLogin, TIME_OUT_LONG);
    }

    public void isHomePage() {
        doesExist(TIME_OUT_LONG, txtbSearch);
    }

    public String checkUserName() {
        WebElement messageElement = waitForElement(TIME_OUT_LONG, containerLoggedIn);
        return messageElement.getAttribute("innerText");
    }

    public boolean isLoginSuccess() {
        return doesExist(TIME_OUT_LONG, containerLoggedIn);
    }

    public void closeIfHomepagePopup() {
        if (doesExist(TIME_OUT_LONG, popupHomePage))
            clickButton(closeHomePagePopup);
    }

    public List<LinkResponse> getBoutiqueLinksAndResponseCodes() {
        List<LinkResponse> linkResponseList = new ArrayList<LinkResponse>();
        List<WebElement> list = waitForElements(TIME_OUT_LONG, aBoutiqueLink);
        for (WebElement e : list) {
            LinkResponse linkResponse = new LinkResponse();
            linkResponse.link = e.getAttribute("href");
            linkResponse.responseCode = Integer.toString(RestAssured.get(linkResponse.link).statusCode());
            linkResponseList.add(linkResponse);
        }
        return linkResponseList;
    }

    public List<ImageResponse> getBoutiqueImageLinksAndResponseTimes() {
        List<ImageResponse> imageResponseList = new ArrayList<ImageResponse>();
        List<WebElement> list = waitForElements(TIME_OUT_LONG, imgBoutiqueImageSrc);
        for (WebElement e : list) {
            ImageResponse imageResponse = new ImageResponse();
            imageResponse.imageLink = e.getAttribute("data-original");
            Response response = RestAssured.get(imageResponse.imageLink);
            imageResponse.responseCode = Integer.toString(response.statusCode());
            imageResponse.responseTime = Long.toString(response.getTime());
            imageResponseList.add(imageResponse);
        }
        return imageResponseList;
    }
}
