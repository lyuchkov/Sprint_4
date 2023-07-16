import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.MainPage;
import pageobjects.OrderPage;
import utils.Browser;
import utils.Form;

@RunWith(Parameterized.class)
public class SendFormTest {
    //remove with type and enum
    private WebDriver driver;
    private final Browser browser;
    private final Form form;

    static {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void before() {
        //write creation browser by type
        if (browser.equals(Browser.CHROME)) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        if (browser.equals(Browser.FIREFOX)) {
            driver = new FirefoxDriver();
        }
    }

    public SendFormTest(Browser browser, Form form) {
        this.browser = browser;
        this.form = form;
    }

    @Parameterized.Parameters
    public static Object[][] getValues() {
        Form form1 = new Form("Имя", "Фамилия", "ул. Адрес д.2", "Сокольники", "79993332211", "20.07.2024", 1, true, false, "Комментарий");
        Form form2 = new Form("Иван", "Иванов", "ул. Другой адрес д.77", "Пионерская", "71293335500", "20.09.2023", 7, true, true, "");
        return new Object[][]{
                {Browser.CHROME, form1},
                {Browser.FIREFOX, form1},
                {Browser.CHROME, form2},
                {Browser.FIREFOX, form2},
        };
    }

    @Test
    public void orderWithUpperOrderButtonSuccessful() {
        System.out.println("Используемый браузер: " + ((RemoteWebDriver) driver).getCapabilities().getBrowserName());
        System.out.println("Тестовые данные: " + form.toString());
        driver.get("https://qa-scooter.praktikum-services.ru");
        MainPage mainPage = new MainPage(driver);
        mainPage.clickCookiesButton();
        mainPage.clickUpperOrderButton();
        new WebDriverWait(driver, 3)
                .until(driver -> (driver.findElement(By.className("Order_Header__BZXOb")).getText() != null));

        OrderPage orderPage = new OrderPage(driver);
        Assert.assertEquals("Для кого самокат", orderPage.getFormTitle());

        orderPage.setFirstName(form.getFirstname());
        orderPage.setLastName(form.getLastname());
        orderPage.setAddress(form.getAddress());
        orderPage.chooseMetroStationInSelectorWithName(form.getStation());
        orderPage.setPhoneNumber(form.getPhone());

        orderPage.clickFirstSubmitButton();

        Assert.assertEquals("Про аренду", orderPage.getFormTitle());

        orderPage.setDate(form.getDate());
        if (form.isBlackScooter()) orderPage.markBlackScooterCheckbox();
        if (form.isGrayScooter()) orderPage.markGrayScooterCheckbox();

        orderPage.selectDaysQuantity(form.getDaysQuantity());

        if (form.getCommentary().length() > 0) orderPage.setCommentary(form.getCommentary());
        orderPage.clickSecondSubmitButton();
        orderPage.clickAgreementButton();
        orderPage.isOrderCreated();
    }

    @Test
    public void orderWithLowerOrderButtonSuccessful() {
        System.out.println("Используемый браузер: " + ((RemoteWebDriver) driver).getCapabilities().getBrowserName());
        System.out.println("Тестовые данные: " + form.toString());
        driver.get("https://qa-scooter.praktikum-services.ru");
        MainPage mainPage = new MainPage(driver);
        mainPage.clickCookiesButton();
        mainPage.clickLowerOrderButton();

        new WebDriverWait(driver, 3)
                .until(driver -> (driver.findElement(By.className("Order_Header__BZXOb")).getText() != null));

        OrderPage orderPage = new OrderPage(driver);


        Assert.assertEquals("Для кого самокат", orderPage.getFormTitle());

        orderPage.setFirstName(form.getFirstname());
        orderPage.setLastName(form.getLastname());
        orderPage.setAddress(form.getAddress());
        orderPage.chooseMetroStationInSelectorWithName(form.getStation());
        orderPage.setPhoneNumber(form.getPhone());
        orderPage.clickFirstSubmitButton();

        Assert.assertEquals("Про аренду", orderPage.getFormTitle());
        orderPage.setDate(form.getDate());
        if (form.isBlackScooter()) orderPage.markBlackScooterCheckbox();
        if (form.isGrayScooter()) orderPage.markGrayScooterCheckbox();

        orderPage.selectDaysQuantity(form.getDaysQuantity());

        if (form.getCommentary().length() > 0) orderPage.setCommentary(form.getCommentary());
        orderPage.clickSecondSubmitButton();
        orderPage.clickAgreementButton();
        orderPage.isOrderCreated();
    }

    @After
    public void after() {
        driver.quit();
    }
}
