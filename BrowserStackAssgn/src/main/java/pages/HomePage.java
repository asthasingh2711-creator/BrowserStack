package pages;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage {

    private WebDriver driver;

    private By cookieBtn = By.cssSelector("button[id*='onetrust'], button[aria-label*='Accept'], button[class*='didomi']");
    private By opinionLinkDesktop = By.cssSelector("header a[href='https://elpais.com/opinion/']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isPageInSpanish() {
        try {
            String lang = driver.findElement(By.tagName("html")).getAttribute("lang");
            return lang != null && lang.startsWith("es");
        } catch (Exception e) {
            return false;
        }
    }

    public void handleCookiesDesktop() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
            WebElement acceptBtn = wait.until(ExpectedConditions.elementToBeClickable(cookieBtn));
            acceptBtn.click();
        } catch (Exception ignored) {
            System.out.println("No cookie popup appeared.");
        }
    }

    public void openOpinionLink() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(12));

            WebElement opinionLink = wait.until(
                    ExpectedConditions.elementToBeClickable(opinionLinkDesktop)
            );

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", opinionLink);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", opinionLink);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to click Opinion link.");
        }
    }
}
