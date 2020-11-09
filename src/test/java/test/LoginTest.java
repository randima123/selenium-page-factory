package test;

import fragment.LoginFragment;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.HomePage;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

    private LoginFragment fragment;

    @BeforeMethod
    public void loginBeforeMethod() {
        fragment = PageFactory.initElements(driver, LoginFragment.class);
    }

    @Test
    public void testValidLogin() {
        fragment.login("randima@mailinator.com","abc123");
        HomePage homePage = PageFactory.initElements(driver,HomePage.class);
        assertEquals(homePage.getLoggedInUsername(), "Randima Senanayake", "Logged in username invalid \n");
    }

    @Test
    public void testInvalidLogin() {
        fragment.login("randima@mailinator.com","abc12");
        assertEquals(driver.getTitle(), "Login - My Store");
    }
}
