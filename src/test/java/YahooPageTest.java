import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class YahooPageTest {
    public WebDriver driver;
    public String baseUrl = "https://www.yahoo.com/";
    @BeforeTest
    public void setBaseUrl() {
        driver = new ChromeDriver();
        driver.get(baseUrl);
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

    @Test (description = "Yahoo search results")
    public void  test_search(){
        long waitTime =5;
        String expectedKeyword = "java";
        System.out.println("Testing Yahoo Search");
        driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(waitTime));

        WebElement searchBox = driver.findElement(By.name("p"));

        searchBox.sendKeys("Java");
        searchBox.submit();
        driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(waitTime));

        WebElement search_result= new WebDriverWait(driver, Duration.ofMinutes(waitTime)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='web']/ol/li[1]/div/div[1]/h3/a")));

        //Display results
        String[] result1 = search_result.getText().split("\n",2);
        String url = result1[0];
        String title = result1[1];
        System.out.println("First url is " + url);
        System.out.println("First title is " + title);
        //Verify results
        Assert.assertFalse(search_result.getText().isEmpty(), "Text not found");
        Assert.assertTrue(url.contains("www"), "Source link must exist");
        Assert.assertTrue(title.toLowerCase().contains(expectedKeyword),"Search result does not have keyword");

    }
}
