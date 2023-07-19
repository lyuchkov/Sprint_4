package page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class OrderPage {
    private final WebDriver driver;

    //Поле для имени
    private By firstNameInput = By.xpath(".//input[@placeholder='* Имя']");
    //Поле для фамилии
    private By lastNameInput = By.xpath(".//input[@placeholder='* Фамилия']");
    //Поле для адреса
    private By addressInput = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    //Поле для выбора станции метро
    private By metroStationInput = By.className("select-search__input");
    //Опции для выбора станции
    private By metroStationChoose = By.className("select-search__row");
    //Поле для телефона
    private By phoneNumberInput = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    //Кнопка далее
    private By submitButton = By.xpath(".//button[text()='Далее']");
    //Чекбокс для черного самоката
    private By blackScooterCheckbox = By.id("black");
    //Чекбокс для серого самоката
    private By grayScooterCheckbox = By.id("grey");
    //Выпадающий список с выбором времени аренды
    private By daysListButton = By.className("Dropdown-control");
    //Поле для ввода даты
    private By dateInput = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    //Поле для ввода комментария
    private By commentaryInput = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    //Кнопка для отправки формы
    private By submitOrderFormButton = By.xpath("(.//button[text()='Заказать'])[2]");
    //Поле для подтверждения отправки формы
    private By agreeButton = By.xpath("(.//button[text()='Да'])");
    //Заголовок во всплывающем окне с оформленным заказом
    private By orderIsCreatedTitle = By.className("Order_ModalHeader__3FDaJ");
    //Заголовок первой части формы
    private By title = By.className("Order_Header__BZXOb");
    //Кнопка выбора длительности аренды
    private String daysQuantityOptionXpathPattern = "(.//div[@class='Dropdown-option'])[%d]";

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
        driver.findElement(By.xpath(String.format(daysQuantityOptionXpathPattern, quantity))).click();
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
    public boolean isOrderCreated() {
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(driver.findElement(orderIsCreatedTitle)));
        String text = driver.findElement(orderIsCreatedTitle).getText();
        return text.contains("Заказ оформлен");
    }
    public String getFormTitle(){
        return driver.findElement(title).getText();
    }
}
