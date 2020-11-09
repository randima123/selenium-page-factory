package test;

import fragment.LoginFragment;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.HomePage;
import page.SearchResultPage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SearchTest extends BaseTest {

    private SearchResultPage searchResultPage;

    @BeforeMethod
    public void searchBeforeMethod() {
        PageFactory.initElements(driver, LoginFragment.class)
                .login("randima@mailinator.com","abc123");
        PageFactory.initElements(driver, HomePage.class)
                .searchItem("T-shirt");
        searchResultPage = PageFactory.initElements(driver,SearchResultPage.class);
    }

    @Test
    public void testSearchResultCount() {
        int actualSearchResultCount = searchResultPage.getSearchResultCount();
        assertTrue(actualSearchResultCount > 0);
        assertEquals(actualSearchResultCount,1);
    }

    @Test
    public void testSearchResult() {
        assertTrue(searchResultPage.getFirstSearchResultName().contains("Faded Short Sleeve T-shirts"));
        assertEquals(searchResultPage.getFirstSearchResultPrice(), "$16.51");
    }
}
