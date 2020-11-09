package fragment;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import page.BasePage;
import page.LoginPage;

public class LoginFragment extends BasePage {

    public LoginFragment(WebDriver driver) {
        super(driver);
    }

    public void login(String email, String password) {
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.setEmail(email);
        loginPage.setPassword(password);
        loginPage.clickSignIn();
    }
}
