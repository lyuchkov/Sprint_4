package pageobjects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;

public class OrderPage {
    private final WebDriver driver;

    //Поле для имени
    private final By firstNameInput = By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[1]/input");
    //Поле для фамилии
    private final By lastNameInput = By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[2]/input");
    //Поле для адреса
    private final By addressInput = By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[3]/input");
    //Поле для выбора станции метро
    private final By metroStationInput = By.className("select-search__input");
    //Опции для выбора станции
    private final By metroStationChoose = By.className("select-search__row");
    //Поле для телефона
    private final By phoneNumberInput = By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[5]/input");
    //Кнопка далее
    private final By submitButton = By.xpath("//*[@id=\"root\"]/div/div[2]/div[3]/button");
    //Чекбокс для черного самоката
    private final By blackScooterCheckbox = By.id("black");
    //Чекбокс для серого самоката
    private final By grayScooterCheckbox = By.id("grey");
    //Выпадающий список с выбором времени аренды
    private final By daysListButton = By.className("Dropdown-control");
    //Поле для ввода даты
    private final By dateInput = By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[1]/div[1]/div/input");
    //Поле для ввода комментария
    private final By commentaryInput = By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[4]/input");
    //Поле для отправки формы
    private final By submitOrderFormButton = By.xpath("//*[@id=\"root\"]/div/div[2]/div[3]/button[2]");
    //Поле для подтверждения отправки формы
    private final By agreeButton = By.xpath("/html/body/div/div/div[2]/div[5]/div[2]/button[2]");
    //Заголовок во всплывающем окне с оформленным заказом
    private final By orderIsCreatedTitle = By.className("Order_ModalHeader__3FDaJ");
    //Заголовок первой части формы
    private final By title = By.className("Order_Header__BZXOb");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }


    public void clickAgreementButton() {
        new WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(agreeButton));
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click()", driver.findElement(agreeButton));
    }

    public void clickSecondSubmitButton() {
        driver.findElement(submitOrderFormButton).click();
    }

    public void setCommentary(String commentary) {
        driver.findElement(commentaryInput).sendKeys(commentary);
    }

    public void selectDaysQuantity(int quantity) {
        driver.findElement(daysListButton).click();
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[2]/div[2]/div[" + quantity + "]")).click();
    }

    public void markGrayScooterCheckbox() {
        driver.findElement(grayScooterCheckbox).click();
    }

    public void markBlackScooterCheckbox() {
        driver.findElement(blackScooterCheckbox).click();
    }

    public void setDate(String date) {
        driver.findElement(dateInput).sendKeys(date);
    }

    public void clickFirstSubmitButton() {
        driver.findElement(submitButton).click();
    }

    public void setPhoneNumber(String phone) {
        driver.findElement(phoneNumberInput).sendKeys(phone);
    }

    public void chooseMetroStationInSelectorWithName(String station) {
        driver.findElement(metroStationInput).click();

        driver.findElement(metroStationInput).sendKeys(station);
        List<WebElement> elements = driver.findElements(metroStationChoose);
        elements.get(0).click();
    }

    public void setAddress(String address) {
        driver.findElement(addressInput).sendKeys(address);
    }

    public void setFirstName(String firstname) {
        driver.findElement(firstNameInput).sendKeys(firstname);
    }
    public void setLastName(String lastname){
        driver.findElement(lastNameInput).sendKeys(lastname);
    }
    public void isOrderCreated() {
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(driver.findElement(orderIsCreatedTitle)));
        String text = driver.findElement(orderIsCreatedTitle).getText();
        Assert.assertThat("Заказ не оформлен.",text, containsString("Заказ оформлен"));
    }
    public String getFormTitle(){
        return driver.findElement(title).getText();
    }
}
