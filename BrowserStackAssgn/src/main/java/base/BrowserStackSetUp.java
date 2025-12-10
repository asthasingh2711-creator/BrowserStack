package base;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class BrowserStackSetUp {

    public static WebDriver getDriver(String browser,
                                      String os,
                                      String osVersion,
                                      String device) throws Exception {

        // Selenium 4 standard capabilities object
        MutableCapabilities caps = new MutableCapabilities();

        // BrowserStack options inside bstack:options
        Map<String, Object> bstackOptions = new HashMap<>();
        bstackOptions.put("userName", "asthasingh_vOhCUo");
        bstackOptions.put("accessKey", "ZK6kqsz3Z9rszw624hqV");
        bstackOptions.put("buildName", "ElPais Assignment Build");
        bstackOptions.put("sessionName", "Parallel Test");

        // DESKTOP mode (you removed mobile)
        caps.setCapability("browserName", browser);
        caps.setCapability("browserVersion", "latest");

        bstackOptions.put("os", os);
        bstackOptions.put("osVersion", osVersion);

        // attach bstack options
        caps.setCapability("bstack:options", bstackOptions);

        // Create the RemoteWebDriver session
        return new RemoteWebDriver(
                new URL("https://hub.browserstack.com/wd/hub"),
                caps
        );
    }
}
