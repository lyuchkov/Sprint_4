import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import page_objects.MainPage;

import java.util.ArrayList;
import java.util.List;

public class FAQTest extends BaseTest {
    private final static List<String> CORRECT_FAQ_ANSWERS = new ArrayList<>();
    private static List<String> actualFAQAnswers;
    static {
        initTestValues();
    }
    @Override
    @Before
    public void before() {
        if(actualFAQAnswers==null) {
            super.before();
            driver.get(MainPage.URL);

            MainPage mainPage = new MainPage(driver);

            actualFAQAnswers = mainPage.getFAQAnswersList();
        }
    }

    @Override
    @After
    public void after() {
        if(driver!=null){super.after();}
    }

    private static void initTestValues() {
        CORRECT_FAQ_ANSWERS.add("Сутки — 400 рублей. Оплата курьеру — наличными или картой.");
        CORRECT_FAQ_ANSWERS.add("Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.");
        CORRECT_FAQ_ANSWERS.add("Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.");
        CORRECT_FAQ_ANSWERS.add("Только начиная с завтрашнего дня. Но скоро станем расторопнее.");
        CORRECT_FAQ_ANSWERS.add("Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.");
        CORRECT_FAQ_ANSWERS.add("Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.");
        CORRECT_FAQ_ANSWERS.add("Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.");
        CORRECT_FAQ_ANSWERS.add("Да, обязательно. Всем самокатов! И Москве, и Московской области.");
    }


    @Test
    public void checkFirstFAQAnswer(){
        answerAssertion(0);
    }
    @Test
    public void checkSecondFAQAnswer(){
        answerAssertion(1);
    }
    @Test
    public void checkThirdFAQAnswer(){
        answerAssertion(2);
    }
    @Test
    public void checkForthFAQAnswer(){
        answerAssertion(3);
    }
    @Test
    public void checkFifthFAQAnswer(){
        answerAssertion(4);
    }
    @Test
    public void checkSixthFAQAnswer(){
        answerAssertion(5);
    }
    @Test
    public void checkSeventhFAQAnswer(){
        answerAssertion(6);
    }
    @Test
    public void checkEighthFAQAnswer(){
        answerAssertion(7);
    }
    private void answerAssertion(int i){
        Assert.assertEquals("Ошибка в строке №" + i + ". "
                        + "Ожидаемая:" + CORRECT_FAQ_ANSWERS.get(i)
                        + "Фактическая:" + actualFAQAnswers.get(i),
                CORRECT_FAQ_ANSWERS.get(i),  actualFAQAnswers.get(i));
    }
}
