import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseTest {
    protected WebDriver driver;
    @Before
    public void before(){
        driver = new FirefoxDriver();

    }
    @After
    public void after(){
        driver.quit();
    }
}
