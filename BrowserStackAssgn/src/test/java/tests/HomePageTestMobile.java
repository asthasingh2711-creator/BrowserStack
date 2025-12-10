/**

package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.HomePageMobile;
import pages.OpinionPage;

public class HomePageTestMobile extends BaseTest {

    @Test
    public void verifyMobileFlow() {

        HomePageMobile homeMobile = new HomePageMobile(driver);

        homeMobile.openHomePage("https://elpais.com/");
        homeMobile.acceptCookiesIfPresent();

        HomePage commonHome = new HomePage(driver);
        Assert.assertTrue(
                commonHome.isPageInSpanish(),
                "Home page does NOT appear to be in Spanish on mobile!"
        );

        System.out.println("Home Page language validated as Spanish (mobile).");

        homeMobile.openHamburger();
        homeMobile.clickOpinion();

        OpinionPage opinion = new OpinionPage(driver);
        OpinionPage.runAssignmentTasks(opinion);
    }
}
**/