import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageobjects.MainPage;

import java.util.ArrayList;
import java.util.List;

public class AccordionFirefoxTest {
    private WebDriver driver;
    private final List<String> correctFAQAnswers = new ArrayList<>();
    @Before
    public void before(){
        driver = new FirefoxDriver();
        initTestValues();
    }
    private void initTestValues(){
        correctFAQAnswers.add("Сутки — 400 рублей. Оплата курьеру — наличными или картой.");
        correctFAQAnswers.add("Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.");
        correctFAQAnswers.add("Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.");
        correctFAQAnswers.add("Только начиная с завтрашнего дня. Но скоро станем расторопнее.");
        correctFAQAnswers.add("Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.");
        correctFAQAnswers.add("Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.");
        correctFAQAnswers.add("Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.");
        correctFAQAnswers.add("Да, обязательно. Всем самокатов! И Москве, и Московской области.");
    }
    @Test
    public void checkAccordionPanelsWithPreparedStrings(){
        driver.get("https://qa-scooter.praktikum-services.ru");

        MainPage mainPage = new MainPage(driver);

        List<String> faqAnswersList = mainPage.getFAQAnswersList();
        for (int i = 0; i < correctFAQAnswers.size(); i++) {
            Assert.assertEquals("Ошибка в строке №" + i + ". "
                    +"Ожидаемая:"+correctFAQAnswers.get(i)
                    +"Фактическая:"+faqAnswersList.get(i),
                    correctFAQAnswers.get(i),faqAnswersList.get(i));
        }
    }


    @After
    public void after(){
        driver.quit();
    }
}
