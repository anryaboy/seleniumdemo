import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class GooglePageTest {
    public WebDriver driver;
    public String baseUrl = "https://www.google.com/";
    @BeforeTest
    public void setBaseUrl() {
        driver = new ChromeDriver();
        driver.get(baseUrl);
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }


    @Test(description = "Google search results")
    public void test_search() {
        System.out.println("Testing Google Search");
        long waitTime1 = 1000;
        long waitTime2 = 100000;
        long waitTime3 =5;
        String searchTerm = "Java tutorial";
        driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(waitTime1));
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys(searchTerm);
        searchBox.submit();
        driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(waitTime2));
        WebElement search_result= new WebDriverWait(driver, Duration.ofMinutes(waitTime3)).until(ExpectedConditions.visibilityOfElementLocated(By.id("search")));
        Assert.assertFalse(search_result.getText().isEmpty(), "Text not found");
        System.out.println("End of Google Search Test. Result verification is not part of this test");
    }
}
