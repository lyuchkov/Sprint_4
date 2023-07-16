package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class MainPage {
    private final WebDriver driver;
    //Кнопки с вопросами
    private final By accordionFAQButtons = By.className("accordion__button");
    //Панели с ответами
    private final By accordionFAQPanels = By.className("accordion__panel");
    //Верхняя кнопка "Заказать"
    private final By upperOrderButton = By.xpath("//*[@id=\"root\"]/div/div[1]/div[1]/div[2]/button[1]");
    //Нижняя кнопка "Заказать"
    private final By lowerOrderButton = By.xpath("//*[@id=\"root\"]/div/div[1]/div[4]/div[2]/div[5]/button");
    //Кнопка для принятия куки
    private final By cookiesButton = By.xpath("//*[@id=\"rcc-confirm-button\"]");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<String> getFAQAnswersList() {
        List<String> res = new ArrayList<>();

        List<WebElement> elements = driver.findElements(accordionFAQButtons);
        List<WebElement> elements1 = driver.findElements(accordionFAQPanels);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        int i = 0;
        for (WebElement element : elements) {
            executor.executeScript("arguments[0].click();", element);
            new WebDriverWait(driver, 3)
                    .until(ExpectedConditions.visibilityOf(elements1.get(i)));
            res.add(elements1.get(i).getText());
            i++;
        }
        return res;
    }
    public void clickUpperOrderButton(){
        driver.findElement(upperOrderButton).click();
    }
    public void clickLowerOrderButton(){
        driver.findElement(lowerOrderButton).click();
    }
    public void clickCookiesButton(){
        driver.findElement(cookiesButton).click();
    }
}
