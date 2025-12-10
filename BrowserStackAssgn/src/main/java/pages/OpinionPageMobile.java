/**

package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class OpinionPageMobile {

    private WebDriver driver;
    private WebDriverWait wait;

    // EXACT article hrefs from your message
    private By article1 = By.cssSelector(
            "a[href='https://elpais.com/opinion/2025-12-10/demasiadas-dudas.html']"
    );

    private By article2 = By.cssSelector(
            "a[href='https://elpais.com/opinion/2025-12-10/la-mano-dura-de-la-ue.html']"
    );

    private By article3 = By.cssSelector(
            "a[href='https://elpais.com/opinion/2025-12-10/el-debate-la-oposicion-venezolana-ante-la-posibilidad-de-una-intervencion-de-estados-unidos.html']"
    );

    public OpinionPageMobile(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private String getTitle(By locator) {
        try {
            WebElement link = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return link.getText().trim();
        } catch (Exception e) {
            return "(no title)";
        }
    }

    private String getSummary(By locator) {
        try {
            WebElement article = driver.findElement(locator).findElement(By.xpath("./ancestor::article"));
            return article.findElement(By.cssSelector("p.c_d")).getText().trim();
        } catch (Exception e) {
            return "(no summary)";
        }
    }

    public void printMobileArticles() {
        System.out.println("===== MOBILE ARTICLES =====");

        printArticle(1, article1);
        printArticle(2, article2);
        printArticle(3, article3);

        System.out.println("===================================");
    }

    private void printArticle(int index, By locator) {
        String title = getTitle(locator);
        String summary = getSummary(locator);

        System.out.println("Article " + index + ":");
        System.out.println("Title: " + title);
        System.out.println("Summary: " + summary);
    }
}
**/