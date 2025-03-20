package com.framework.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class BaiduPage extends BasePage {
    private final By searchInput = By.id("kw");
    private final By searchButton = By.id("su");
    private final By resultCount = By.cssSelector("#container > div > div.nums");

    public BaiduPage(WebDriver driver) {
        super(driver);
    }

    @Step("输入搜索关键词: {keyword}")
    public void search(String keyword) {
        driver.findElement(searchInput).sendKeys(keyword);
        driver.findElement(searchButton).click();
    }

    @Step("获取结果数量")
    public String getResultCountText() {
        return driver.findElement(resultCount).getText();
    }

    @Step("验证页面标题包含: {expectedTitle}")
    public void verifyTitleContains(String expectedTitle) {
        Assert.assertTrue(driver.getTitle().contains(expectedTitle));
    }
}