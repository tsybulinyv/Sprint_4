package pageobjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class MainPage {
    private WebDriver driver;
    private WebDriverWait wait;

    //Верхняя кнопка "Заказать"
    private By topOrderButton = By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/button[1]");

    //Нижняя кнопка "Заказать"
    private By bottomOrderButton = By.xpath("//*[@id=\"root\"]/div/div/div[4]/div[2]/div[5]/button");

    //Кнопка принятия cookies
    private By cookieButton = By.id("rcc-confirm-button");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    //Открыть страницу
    public void openPage() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    //Клик по вопросам
    public void clickQuestion(int index) {
        By question = By.id("accordion__heading-" + index);
        wait.until(
                ExpectedConditions.elementToBeClickable(question)
        ).click();
    }

    //Текст ответов
    public String getAnswerText(int index) {
        By answer = By.id("accordion__panel-" + index);
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(answer)
        ).getText();
    }

    //Клик по верхней кнопке "Заказать"
    public void clickTopOrderButton() {
        wait.until(
                ExpectedConditions.elementToBeClickable(topOrderButton)
        ).click();
    }

    //Клик по нижней кнопке "Закзать"
    public void clickBottomOrderButton() {
        wait.until(
                ExpectedConditions.elementToBeClickable(bottomOrderButton)
        ).click();
    }

    //Клик по кнопке куки
    public void clickCookieButton() {
        wait.until(
                ExpectedConditions.elementToBeClickable(cookieButton)
        ).click();
    }
}