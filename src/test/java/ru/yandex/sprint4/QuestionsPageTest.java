package ru.yandex.sprint4;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.sprint4.sprint4.pom.MainPage;


@RunWith(Parameterized.class)
public class QuestionsPageTest extends GeneralClassTest {

    private final String question;
    private final String answer;

    private final String index;

    public QuestionsPageTest(String question, String answer, String index) {
        this.question = question;
        this.answer = answer;
        this.index = index;
    }

    @Parameterized.Parameters
    public static Object[][] getCredentials() {
        return new Object[][] {
                {"Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой.", "1"},
                {"Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.", "2"},
                {"Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.", "3"},
                {"Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее.", "4"},
                {"Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.", "5"},
                {"Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.", "6"},
                {"Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.", "7"},
                {"Я жизу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области.", "8"}
        };
    }

    @Test
    public void checkTextIsDisplayed() {

        String locator = String.format(".accordion > div:nth-child(%s) > div:nth-child(2) >p",index);
        System.out.println(locator);
        MainPage mainPage = new MainPage(webDriver);
        mainPage.open();
        String script = "document.getElementsByClassName(\"App_CookieConsent__1yUIN\")[0].remove();";
        ((JavascriptExecutor)webDriver).executeScript(script);
        ((JavascriptExecutor) webDriver).executeScript("document.getElementsByClassName(\"Home_SubHeader__zwi_E\")[3].scrollIntoView()"); // прокрутка вниз
        mainPage.clickDropDownList(question);
        Wait<WebDriver> wait = new WebDriverWait(webDriver, 5);
        wait.until(d -> webDriver.findElement(By.cssSelector(locator)).isDisplayed()); // поставили паузу чтобы получить эл-т
        String actualAnswer = mainPage.getText(locator);
        Assert.assertEquals("Сообщение об ошибке", answer, actualAnswer);
    }
}