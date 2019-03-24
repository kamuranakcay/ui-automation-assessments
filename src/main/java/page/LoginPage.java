package page;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage
{
    public By tbEmail = By.id("email");
    public By tbPassword = By.id("password");
    public By btnLogin = By.id("loginSubmit");
    public By popupLogin = By.id("commonPopupRoot");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void setTbEmail(String strTbEmail) {
        setTextBox(tbEmail,strTbEmail);
    }

    public void setTbPassword(String strTbPassword) {
        setTextBox(tbPassword,strTbPassword);
    }

    public void clickBtnLogin() {
        clickButton(btnLogin);
    }

    public boolean isLoginPopupHere(){
        return doesExist(TIME_OUT_LONG, popupLogin);
    }

}
