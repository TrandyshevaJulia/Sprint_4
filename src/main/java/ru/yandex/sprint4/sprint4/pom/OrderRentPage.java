package ru.yandex.sprint4.sprint4.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class OrderRentPage {

    private WebDriver webDriver;

    // локатор элемента "Когда привезти самокат"
    private final By fieldDateInput = By.xpath(".//input[@placeholder='* Когда привезти самокат']");

    // локатор элемента "Срок аренды"
    private final By fieldRentalPeriodInput = By.className("Dropdown-control");

    // локатор выпадающего списка Срока аренды
    private final By rentalPeriodDropdown = By.className("Dropdown-menu");

    // локатор элемента "Цвет самоката"
    private final By fieldScooterColourInputBlack = By.xpath(".//label[normalize-space(text())='чёрный жемчуг']");
    private final By fieldScooterColourInputGrey = By.xpath(".//label[normalize-space(text())='серая безысходность']");

    // локатор элемента "Комментарий"
    private final By fieldCommentInput = By.xpath(".//input[@placeholder='Комментарий для курьера']");

    // локатор кнопки "Заказать" для перехода на следующую страницу
    private final By orderButtonLocator = By.cssSelector("div.Order_Buttons__1xGrp button:nth-child(2)");


    // локатор кнопки подтверждения заказа, нопки "Да"
    private final By orderButtonConfirmation = By.xpath(".//button[contains(@class, 'Button_Button__ra12g') and contains(@class, 'Button_Danger__1Xo8c')]");

    // локатор подтверждения заказа
    private final By successOrderMessage = By.xpath(".//div[contains(@class, 'Order_ModalHeader__3FDaJ')]");


    public OrderRentPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    // Кликаем на поле "Дата" и прибавляем к текщей дате +1 день и форматируем
    public void inputFieldDate() {
        webDriver.findElement(fieldDateInput).click();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date = LocalDate.now().plusDays(1).format(formatter);
        webDriver.findElement(fieldDateInput).sendKeys(date);
        webDriver.findElement(fieldDateInput).sendKeys(Keys.ENTER);
    }

    // Кликаем на поле "Срок аренды"
    public void inputFieldRentalPeriod(String rentalPeriod) {
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        WebElement rentalPeriodElement = wait.until(ExpectedConditions.visibilityOfElementLocated(fieldRentalPeriodInput));
        rentalPeriodElement.click();

        // Выбираем нужный срок аренды из выпадающего списка
        WebElement rentalPeriodDropdownElement = wait.until(ExpectedConditions.visibilityOfElementLocated(rentalPeriodDropdown));
        rentalPeriodDropdownElement.findElement(By.xpath(".//div[text()='" + rentalPeriod + "']")).click();
    }

    // Кликаем на поле "Цвет самоката"
    public void inputColourScooter(String colour) {
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        if (colour.equalsIgnoreCase("black")) {
            WebElement blackColourElement = wait.until(ExpectedConditions.visibilityOfElementLocated(fieldScooterColourInputBlack));
            blackColourElement.click();
        } else if (colour.equalsIgnoreCase("grey")) {
            WebElement greyColourElement = wait.until(ExpectedConditions.visibilityOfElementLocated(fieldScooterColourInputGrey));
            greyColourElement.click();
        }
    }

    // Вводим значение в поле "Комментарий"
    public void inputFieldComment(String comment) {
        new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOfElementLocated(fieldCommentInput)).sendKeys(comment);

    }

    // Кликаем на кнопку "Заказать"
    public void clickOrderButtonLocator() {
        new WebDriverWait(webDriver, 20).until(ExpectedConditions.elementToBeClickable(orderButtonLocator)).click();
    }

    // Кликаем на кнопку подтверждения заказа
    public void clickOrderConfirmationButton() {
        WebDriverWait wait = new WebDriverWait(webDriver, 20);
        WebElement confirmButton = wait.until(ExpectedConditions.elementToBeClickable(orderButtonConfirmation));
        confirmButton.click();
    }

    // Проверить, появилось ли сообщение об успешном заказе
    public boolean checkSuccessOrderMessage() {
        return webDriver.findElement(successOrderMessage).getText().equals("Заказ оформлен");
    }
}

