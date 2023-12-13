
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import java.util.Properties;
public class TestBase {
    protected static WebDriver driver;
    protected static int EXPLICIT_WAIT;
    @Parameters({ "BrowserType" })
    @BeforeClass(alwaysRun = true)
    public void setUp(@Optional("googlechrome") String browserType) throws Exception {
        browserType = browserType.toLowerCase();
        switch (browserType) {
            case "firefox":
                driver = new FirefoxDriver();
                break;
            //case "iexplore":
                //driver = new InternetExplorerDriver();
                //break;
            case "googlechrome":
                ChromeOptions options = new ChromeOptions();
                DesiredCapabilities cap = DesiredCapabilities.chrome();
                cap.setCapability(ChromeOptions.CAPABILITY , options);
                driver = new ChromeDriver(cap);
                break;
        }
    }
    @AfterClass(alwaysRun =true)
    public void tearDown() throws Exception {
        driver.quit();
    }

}
