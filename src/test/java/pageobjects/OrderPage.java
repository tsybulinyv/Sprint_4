package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class OrderPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Поле "Имя"
    private By nameInput = By.xpath("//input[@placeholder='* Имя']");

    // Поле "Фамилия"
    private By surnameInput = By.xpath("//input[@placeholder='* Фамилия']");

    // Поле "Адрес"
    private By addressInput = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");

    // Поле "Станция метро"
    private By metroInput = By.className("select-search__input");

    // Поле "Телефон"
    private By phoneNumberInput = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");

    // Кнопка "Далее"
    private By nextButton = By.xpath("//div[contains(@class,'Order_NextButton')]//button[text()='Далее']");

    public OrderPage(WebDriver driver) {

        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    // Ввод имени
    public void setName(String name) {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(nameInput)
        ).sendKeys(name);
    }

    // Ввод фамилии
    public void setSurname(String surname) {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(surnameInput)
        ).sendKeys(surname);
    }

    // Ввод адреса
    public void setAddress(String address) {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(addressInput)
        ).sendKeys(address);
    }

    // Ввод станции метро
    public void setMetro(String metro) {

        WebElement input = wait.until(
                ExpectedConditions.elementToBeClickable(metroInput)
        );

        input.click();
        input.sendKeys(metro);

        By metroOption = By.xpath(
                "//button[contains(@class,'select-search__option')]//div[text()='" + metro + "']"
        );

        wait.until(
                ExpectedConditions.elementToBeClickable(metroOption)
        ).click();

        wait.until(
                ExpectedConditions.attributeToBe(metroInput, "value", metro)
        );
    }

    // Ввод телефона
    public void setPhoneNumber(String phoneNumber) {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(phoneNumberInput)
        ).sendKeys(phoneNumber);
    }

    // Нажать кнопку "Далее"
    public void clickNextButton() {
        wait.until(
                ExpectedConditions.elementToBeClickable(nextButton)
        ).click();
    }

    // Заполнение первой формы
    public void fillOrderForm(String name, String surname, String address, String metro, String phoneNumber) {
        setName(name);
        setSurname(surname);
        setAddress(address);
        setMetro(metro);
        setPhoneNumber(phoneNumber);
    }
}