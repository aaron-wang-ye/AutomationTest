package com.framework.pages;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("打开URL: {url}")
    public void openUrl(String url) {
        driver.get(url);
    }

    @Attachment(value = "截图", type = "image/png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }
}