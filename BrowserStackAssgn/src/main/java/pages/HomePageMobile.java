/**
package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


 
 
public class HomePageMobile {

    private WebDriver driver;

    private By acceptCookiesBtn = By.cssSelector("#didomi-notice-agree-button");
    private By hamburgerBtn = By.id("btn_open_hamburger");
    private By opinionLinkMobile = By.xpath("//a[@href='https://elpais.com/opinion/']");

    public HomePageMobile(WebDriver driver) {
        this.driver = driver;
    }

    public void openHomePage(String url) {
        driver.get(url);
    }

    public void acceptCookiesIfPresent() {
        try {
            WebElement btn = new WebDriverWait(driver, Duration.ofSeconds(6))
                    .until(ExpectedConditions.elementToBeClickable(acceptCookiesBtn));
            btn.click();
            Thread.sleep(1500);
            System.out.println("Cookies accepted automatically (mobile).");
        } catch (Exception e) {
            System.out.println("No cookie popup appeared on mobile.");
        }
    }

    public void openHamburger() {
        try {
            WebElement menu = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(hamburgerBtn));

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", menu);
            Thread.sleep(1500);
        } catch (Exception e) {
            throw new RuntimeException("Unable to click mobile hamburger menu", e);
        }
    }

    public void clickOpinion() {
        try {
            Thread.sleep(1200);

            WebElement shadowHost = driver.findElement(By.cssSelector("div[data-mrf-root='true']"));

            JavascriptExecutor js = (JavascriptExecutor) driver;
            WebElement shadowRoot = (WebElement) js.executeScript("return arguments[0].shadowRoot", shadowHost);

            WebElement opinionLink = (WebElement) js.executeScript(
                    "return arguments[0].querySelector(\"a[href='https://elpais.com/opinion/']\")",
                    shadowRoot
            );

            js.executeScript("arguments[0].scrollIntoView(true);", opinionLink);
            js.executeScript("arguments[0].click();", opinionLink);

            Thread.sleep(800);

        } catch (Exception e) {
            throw new RuntimeException("Unable to click Opinión on mobile — likely Shadow DOM issue.", e);
        }
    }

}
*/