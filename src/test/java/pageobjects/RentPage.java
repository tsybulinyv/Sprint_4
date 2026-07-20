package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RentPage {

    private WebDriver driver;
    private WebDriverWait wait;

    //Поле "Когда привезти самокат"
    private By deliveryDateInput =
            By.xpath("//input[@placeholder='* Когда привезти самокат']");

    //Поле "Срок аренды"
    private By rentalPeriodInput = By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[2]/div/div[1]");

    //Вариант срока аренды в выпадающем списке
    private By rentalPeriodOption(String period) {
        return By.xpath(
                "//div[contains(@class,'Dropdown-option') and text()='" + period + "']"
        );
    }

    //Чекбоксы цвета
    private By colorCheckbox(String color) {
        return By.id(color);
    }

    //Поле "Комментарий для курьера"
    private By commentInput = By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[4]/input");

    //Кнопка "Заказать"
    private By orderButton = By.xpath("//*[@id=\"root\"]/div/div[2]/div[3]/button[2]");

    // Кнопка "Да" в окне подтверждения
    private By confirmYesButton = By.xpath("/html/body/div/div/div[2]/div[5]/div[2]/button[2]");

    // Окно успешного заказа
    private By successMessage = By.xpath(
            "//div[contains(@class,'Order_ModalHeader') and contains(text(),'Заказ оформлен')]"
    );

    public RentPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    //Ввод даты доставки
    public void setDeliveryDate(String date) {

        WebElement input = wait.until(
                ExpectedConditions.elementToBeClickable(deliveryDateInput)
        );

        input.click();

        String[] parts = date.split("\\.");

        String day = parts[0];

        By dateLocator = By.xpath(
                "//div[contains(@class,'react-datepicker__day') and text()='" + Integer
                        .parseInt(day) + "']"
        );
        wait.until(
                ExpectedConditions.elementToBeClickable(dateLocator)
        ).click();
    }

    //Выбор срока аренды
    public void selectRentalPeriod(String period) {

        wait.until(
                ExpectedConditions.elementToBeClickable(rentalPeriodInput)
        ).click();

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        rentalPeriodOption(period))
        ).click();
    }

    //Выбор цвета
    public void selectColor(String color) {
        wait.until(
                ExpectedConditions.elementToBeClickable(colorCheckbox(color))
        ).click();
    }

    //Ввод комментария
    public void setComment(String comment) {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(commentInput)
        ).sendKeys(comment);
    }

    //Нажать кнопки "Заказать"
    public void clickOrderButton() {
        wait.until(
                ExpectedConditions.elementToBeClickable(orderButton)
        ).click();
    }

    //Подтвердить заказ
    public void confirmOrder() {
        wait.until(
                ExpectedConditions.elementToBeClickable(confirmYesButton)
        ).click();
    }

    public boolean isOrderCreated() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(successMessage)
        ).isDisplayed();
    }

    // Заполнение второй формы целиком
    public void fillRentForm(
            String date,
            String period,
            String color,
            String comment
    ) {
        setDeliveryDate(date);
        selectRentalPeriod(period);
        selectColor(color);
        setComment(comment);
    }
}