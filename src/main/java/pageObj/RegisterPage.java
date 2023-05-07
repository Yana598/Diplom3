package pageObj;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class RegisterPage {
    WebDriver driver;

    private By name = By.xpath("//label[contains(text(),'Имя')]/../input");

    private By email=By.xpath("//label[contains(text(),'Email')]/../input");
    private By password=By.xpath("//label[contains(text(),'Пароль')]/../input");

    private By buttonRegisterReady=By.xpath(".//button[text()='Зарегистрироваться']");
    private By errorPassword = By.xpath(".//p[text() = 'Некорректный пароль']");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setName(String name1) {
        driver.findElement(name).sendKeys(name1);
    }

    public void setEmail(String email1) {driver.findElement(email).sendKeys(email1);}

    public void setPassword(String word) {driver.findElement(password).sendKeys(word);}

    public void login(String name1, String email1, String word) {
        setName(name1);
        setEmail(email1);
        setPassword(word);
    }

    public void clickButtonLogin() {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",driver.findElement(buttonRegisterReady));
        driver.findElement(buttonRegisterReady).click();
    }

    public void checkErrorPassword() {
        assertThat("В авторизованном профиле отображается информационное сообщение", true,
                equalTo(driver.findElement(errorPassword).isDisplayed()));
    }
}
