package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.OpinionPage;

public class HomePageTest extends BaseTest {

    @Test
    public void verifyHomePageIsInSpanish() {

        HomePage home = new HomePage(driver);

        home.handleCookiesDesktop();

        Assert.assertTrue(home.isPageInSpanish(), "Home page does NOT appear to be in Spanish!");

        home.openOpinionLink();

        OpinionPage opinion = new OpinionPage(driver);
        OpinionPage.runAssignmentTasks(opinion);
    }
}
