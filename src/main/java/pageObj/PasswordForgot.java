package pageObj;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PasswordForgot {
    WebDriver driver;
    public PasswordForgot(WebDriver driver) {
        this.driver = driver;}
    private By singInBtn = By.xpath(".//a[text()='Войти']"); //кнопка вход
    public void clickSingInBtn() {
        driver.findElement(singInBtn).click();
    }
}
