package ru.yandex.sprint4.sprint4.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class MainPage {

    private static final String URL = "https://qa-scooter.praktikum-services.ru/";

    private final WebDriver webDriver;

    // локатор кнопки "Заказать" вверху станицы
    private final By topOrderButton = By.xpath(".//button[@class = 'Button_Button__ra12g']");

    // локатор кнопки "Заказать" внизу страницы
    private final By bottomOrderButton = By.xpath(".//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM']");

    // локатор кнопки выпадающего списка в аккордионе
    private final By dropDownListButton = By.className("accordion__button");

    // локатор поля с текстом в выпадающем тексте
    private final By textDropDownList = By.cssSelector(".accordion__panel>p");


    public MainPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    // Открываем станицу яндекс Самокат
    public void open() {
        webDriver.get(URL);
    }

    // Кликаем на кнопку "Заказать" вверху станицы
    public void clickTopCheckOrder() {
        webDriver.findElement(topOrderButton).click();
    }

    // Кликаем на кнопку "Заказать" внизу страницы
    public void clickBottomCheckOrder() {
        webDriver.findElement(bottomOrderButton).click();
    }

    // Кликаем на кнопку в выпадающем списке
    public void clickDropDownList(String question) {
        List<WebElement> questions = webDriver.findElements(dropDownListButton);
        for (WebElement q : questions) {
            if (q.getText().equals(question)) {
                System.out.println(q.getText());
                q.click();
             break;
           }
        }
    }

    // Получаем текст из выпадающего списка
    public String getText(String locator) {
        return webDriver.findElement(By.cssSelector(locator)).getText();
    }
}


