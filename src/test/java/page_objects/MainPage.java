package page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class MainPage {
    private WebDriver driver;
    public static final String URL = "https://qa-scooter.praktikum-services.ru";
    //Кнопки с вопросами
    private By QuestionButtons = By.className("accordion__button");
    //Панели с ответами
    private By AnswerPanels = By.className("accordion__panel");
    //Верхняя кнопка "Заказать"
    private By upperOrderButton = By.xpath("(.//button[text()='Заказать'])[1]");
    //Нижняя кнопка "Заказать"
    private By lowerOrderButton = By.xpath("(.//button[text()='Заказать'])[2]");
    //Кнопка для принятия куки
    private By cookiesButton = By.xpath("//*[@id=\"rcc-confirm-button\"]");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<String> getFAQAnswersList() {
        List<String> res = new ArrayList<>();

        List<WebElement> elements = driver.findElements(QuestionButtons);
        List<WebElement> elements1 = driver.findElements(AnswerPanels);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        int i = 0;
        for (WebElement element: elements) {
            executor.executeScript("arguments[0].click();", element);
            new WebDriverWait(driver, 3)
                    .until(ExpectedConditions.visibilityOf(elements1.get(i)));
            res.add(elements1.get(i).getText());
            i++;
        }
        return res;
    }

    public void clickUpperOrderButton() {
        driver.findElement(upperOrderButton).click();
    }

    public void clickLowerOrderButton() {
        driver.findElement(lowerOrderButton).click();
    }

    public void clickCookiesButton() {
        driver.findElement(cookiesButton).click();
    }
}
