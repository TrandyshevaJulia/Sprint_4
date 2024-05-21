package ru.yandex.sprint4.sprint4.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderClientDataPage {

    protected final WebDriver webDriver;

        // локатор элемента ввода "Имя"
        private final By fieldName = By.xpath(".//input[@placeholder='* Имя']");

        // локатор элемента ввода "Фамилия"
        private final By fieldSurname = By.xpath(".//input[@placeholder='* Фамилия']");

        // локатор элемента ввода "Адрес:куда привести заказ"
        private final By fieldAddress = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");

       // локатор элемента ввода "Станция метро"
        private final By fieldMetro = By.xpath(".//input[contains(@class,'select-search__input')]");

       // локатор выпадающего списка станций метро
        private final By metroDropdown = By.className("select-search__select");

        // локатор элемента ввода "Телефон: на него позвонит курьер"
        private final By fieldPhoneNumber = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");

        // локатор кнопки "Далее"
        private final By nextButton = By.xpath(".//button[text()='Далее']");

    public OrderClientDataPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    // Вводим значение в поле "Имя"
        public void inputFieldName(String name) {
            new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOfElementLocated(fieldName)).sendKeys(name);
        }

        // Вводим значение в поле "Фамилия"
        public void inputFieldSurname(String surname) {
            new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOfElementLocated(fieldSurname)).sendKeys(surname);
        }

        //Вводим значение в поле "Адрес"
        public void inputFieldAddress(String address) {
            new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOfElementLocated(fieldAddress)).sendKeys(address);
        }

        //Выбираем станцию метро из выпадающего списка
        public void inputFieldMetro(String metro) {
            WebDriverWait wait = new WebDriverWait(webDriver, 10);
            WebElement metroElement = wait.until(ExpectedConditions.visibilityOfElementLocated(fieldMetro));
            metroElement.sendKeys(metro);

            // Ждем, пока появится выпадающий список, и выбираем нужную станцию
            WebElement metroDropdownElement = wait.until(ExpectedConditions.visibilityOfElementLocated(metroDropdown));
            WebElement desiredStation = metroDropdownElement.findElement(By.xpath(".//div[normalize-space(text())='" + metro + "']"));
            desiredStation.click();
        }

        // Вводим значение в поле Телефон
        public void inputFieldPhoneNumber(String phoneNumber) {
            WebDriverWait wait = new WebDriverWait(webDriver, 10);
            WebElement phoneElement = wait.until(ExpectedConditions.visibilityOfElementLocated(fieldPhoneNumber));
            phoneElement.sendKeys(phoneNumber);
        }

        // Кликаем на кнопку "Далее"
        public void clickNextButton() {
            WebDriverWait wait = new WebDriverWait(webDriver, 10);
            WebElement nextButtonElement = wait.until(ExpectedConditions.elementToBeClickable(nextButton));
            nextButtonElement.click();
        }
    }

