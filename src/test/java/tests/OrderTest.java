package tests;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageobjects.MainPage;
import pageobjects.OrderPage;
import pageobjects.RentPage;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderTest {

    private WebDriver driver;

    private final String name;
    private final String surname;
    private final String address;
    private final String metro;
    private final String phone;
    private final String date;
    private final String period;
    private final String color;
    private final String comment;
    private final String orderButton;

    public OrderTest(String name, String surname, String address, String metro, String phone,
                     String date, String period, String color, String comment, String orderButton) {

        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.date = date;
        this.period = period;
        this.color = color;
        this.comment = comment;
        this.orderButton = orderButton;
    }

    @Parameterized.Parameters
    public static Object[][] getOrderData() {
        return new Object[][]{
                {
                        "Иван",
                        "Петров",
                        "Москва",
                        "Черкизовская",
                        "89999999999",
                        "20.07.2026",
                        "сутки",
                        "black",
                        "Позвонить перед доставкой",
                        "top"
                },
                {
                        "Анна",
                        "Сидорова",
                        "Москва",
                        "Тверская",
                        "87777777777",
                        "21.07.2026",
                        "двое суток",
                        "grey",
                        "Оставить у двери",
                        "bottom"
                },
                {
                        "Иван",
                        "Петров",
                        "Москва",
                        "Черкизовская",
                        "89999999999",
                        "20.07.2026",
                        "сутки",
                        "black",
                        "Позвонить перед доставкой",
                        "bottom"
                },
                {
                        "Анна",
                        "Сидорова",
                        "Москва",
                        "Тверская",
                        "87777777777",
                        "21.07.2026",
                        "двое суток",
                        "grey",
                        "Оставить у двери",
                        "top"
                },
        };
    }

    @Test
    public void orderScooterTest() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");

        driver = new ChromeDriver(options);

        MainPage mainPage = new MainPage(driver);
        OrderPage orderPage = new OrderPage(driver);
        RentPage rentPage = new RentPage(driver);

        mainPage.openPage();

        mainPage.clickCookieButton();

        if (orderButton.equals("top")) {
            mainPage.clickTopOrderButton();
        } else {
            mainPage.clickBottomOrderButton();
        }

        orderPage.fillOrderForm(name, surname, address, metro, phone);
        orderPage.clickNextButton();
        rentPage.fillRentForm(date, period, color, comment);
        rentPage.clickOrderButton();
        rentPage.confirmOrder();
        assertTrue(rentPage.isOrderCreated());
    }

    @After
    public void tearDown() {
            driver.quit();
    }
}
