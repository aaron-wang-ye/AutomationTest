package com.framework.tests;

import com.framework.config.ConfigReader;
import com.framework.pages.BaiduPage;
import com.framework.testdata.TestData;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@Epic("百度搜索功能测试")
@Feature("核心搜索功能验证")
public class BaiduSearchTest {
    private WebDriver driver;
    private BaiduPage baiduPage;

    @BeforeMethod
    @Parameters("browser")
    public void setup(@Optional("chrome") String browser) {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        baiduPage = new BaiduPage(driver);
        baiduPage.openUrl("https://www.baidu.com");
    }

    @DataProvider(name = "searchData")
    public Object[][] provideData() throws IOException {
        return ConfigReader.getBaiduSearchData().stream()
                .map(data -> new Object[]{data})
                .toArray(Object[][]::new);
    }

    @Test(dataProvider = "searchData", description = "验证搜索结果数量和标题")
    @Story("搜索结果验证")
    @Severity(SeverityLevel.CRITICAL)
    public void testSearch(TestData data) {
        // 执行搜索
        baiduPage.search(data.getKeyword());

        // 验证标题
        baiduPage.verifyTitleContains(data.getExpected_title());

        // 验证结果数量
        String actualCount = baiduPage.getResultCountText()
                .replaceAll("[^0-9]", "");
        Assert.assertTrue(
                Integer.parseInt(actualCount) >= data.getExpected_result_count(),
                "实际结果数量不符合预期"
        );
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            Allure.addAttachment("失败截图", new ByteArrayInputStream(baiduPage.takeScreenshot()));
        }
        driver.quit();
    }
}