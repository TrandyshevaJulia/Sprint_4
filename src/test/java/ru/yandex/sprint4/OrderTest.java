package ru.yandex.sprint4;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.JavascriptExecutor;
import ru.yandex.sprint4.sprint4.pom.MainPage;
import ru.yandex.sprint4.sprint4.pom.OrderClientDataPage;
import ru.yandex.sprint4.sprint4.pom.OrderRentPage;

@RunWith(Parameterized.class)
public class OrderTest extends GeneralClassTest {


    private final boolean useTopOrderButton;
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String metroStation;
    private final String phone;
    private final String rentalPeriod;
    private final String colour;
    private final String comment;

    public OrderTest(boolean useTopOrderButton, String firstName, String lastName, String address, String metroStation, String phone, String rentalPeriod, String colour, String comment) {
        this.useTopOrderButton = useTopOrderButton;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentalPeriod = rentalPeriod;
        this.colour = colour;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][] {
                { true, "Ирина", "Григорьева", "ул. Титова, д. 111", "Лубянка", "+79136549875", "сутки", "black", "Позвоните за час" },
                { false, "Галина", "Кукушкина", "ул. Фабричная, д. 88", "Фрунзенская", "+79536457896", "двое суток", "grey", "Не звоните" }
        };
    }

    @Test
    public void testOrderFlow() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.open();
        String script = "document.getElementsByClassName(\"App_CookieConsent__1yUIN\")[0].remove();";
        ((JavascriptExecutor)webDriver).executeScript(script);

        if (useTopOrderButton) {
            mainPage.clickTopCheckOrder();
        } else {
            mainPage.clickBottomCheckOrder();
        }

        OrderClientDataPage clientDataPage = new OrderClientDataPage(webDriver);
        clientDataPage.inputFieldName(firstName);
        clientDataPage.inputFieldSurname(lastName);
        clientDataPage.inputFieldAddress(address);
        clientDataPage.inputFieldMetro(metroStation);
        clientDataPage.inputFieldPhoneNumber(phone);
        clientDataPage.clickNextButton();

        OrderRentPage rentPage = new OrderRentPage(webDriver);
        rentPage.inputFieldDate();
        rentPage.inputFieldRentalPeriod(rentalPeriod);
        rentPage.inputColourScooter(colour);
        rentPage.inputFieldComment(comment);
        rentPage.clickOrderButtonLocator();
        rentPage.clickOrderConfirmationButton();


        Assert.assertTrue("Заказ не оформлен", rentPage.checkSuccessOrderMessage());
    }
    }