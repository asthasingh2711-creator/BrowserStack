package pages;

import org.openqa.selenium.*;
import utils.GcpTranslate;
import utils.ImageDownloadUtil;
import utils.TextUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OpinionPage {

    private WebDriver driver;
    private By articleList = By.cssSelector("article.c");

    public OpinionPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> getFirstFiveArticles() {
        List<WebElement> articles = driver.findElements(articleList);
        int max = Math.min(5, articles.size());
        return articles.subList(0, max);
    }

    private WebElement refreshArticle(WebElement oldArticle) {
        try {
            String id = oldArticle.getAttribute("data-article-id");
            if (id != null && !id.isEmpty()) {
                return driver.findElement(By.cssSelector("article[data-article-id='" + id + "']"));
            }
        } catch (Exception ignored) {}
        return oldArticle;
    }

    public String getArticleTitle(WebElement article) {
        try {
            WebElement fresh = refreshArticle(article);
            WebElement title = fresh.findElement(By.cssSelector("h2.c_t a, h2.c_t_s a"));
            return title.getText().trim();
        } catch (Exception e) {
            return "(no title)";
        }
    }

    public String getArticleContent(WebElement article) {
        try {
            WebElement fresh = refreshArticle(article);
            WebElement summary = fresh.findElement(By.cssSelector("p.c_d"));
            return summary.getText().trim();
        } catch (Exception e) {
            return "(no summary)";
        }
    }

    public String getArticleImageUrl(WebElement article) {
        try {
            WebElement fresh = refreshArticle(article);
            List<WebElement> imgs = fresh.findElements(By.cssSelector("img.c_m_e"));
            if (!imgs.isEmpty()) return imgs.get(0).getAttribute("src");
            imgs = fresh.findElements(By.cssSelector("img"));
            if (!imgs.isEmpty()) return imgs.get(0).getAttribute("src");
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public static void runAssignmentTasks(OpinionPage opinion) {

        List<WebElement> articles = opinion.getFirstFiveArticles();
        List<String> translatedTitles = new ArrayList<>();

        for (int i = 0; i < articles.size(); i++) {

            WebElement article = articles.get(i);

            String title = opinion.getArticleTitle(article);
            String translated = GcpTranslate.translate(title);
            translatedTitles.add(translated);

            String content = opinion.getArticleContent(article);
            String imageUrl = opinion.getArticleImageUrl(article);

            System.out.println("===== ARTICLE " + (i + 1) + " =====");
            System.out.println("TITLE (in Spanish): " + title);
            System.out.println("CONTENT: " + content);
            System.out.println("TRANSLATED (in English) TITLE: " + translated);

            if (imageUrl != null) {
                String filePath = "downloads/article_" + (i + 1) + ".jpg";
                ImageDownloadUtil.downloadImage(imageUrl, filePath);
            } else {
                System.out.println("IMAGE: none");
            }

            System.out.println("=================================");
        }

        Map<String, Integer> repeated = TextUtils.getRepeatedWords(translatedTitles);

        System.out.println("===== REPEATED WORD ANALYSIS =====");
        for (Map.Entry<String, Integer> entry : repeated.entrySet()) {
            if (entry.getValue() > 2) {
                System.out.println("Repeated word → " + entry.getKey() + " appears " + entry.getValue() + " times");
            }
        }
        System.out.println("===================================");
    }
}
