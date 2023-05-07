package pageObj;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class AccountPage {
    WebDriver driver;
    private  By changeData = By.xpath(".//p[text() = 'В этом разделе вы можете изменить свои персональные данные']");
    private  By constructorBtn = By.xpath(".//p[text()='Конструктор']");
    private  By BtnLogo = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']");
    private By BtnEnd=By.xpath(".//button[text()='Выход']");
    public AccountPage(WebDriver driver) {
        this.driver = driver;
    }
    public void checkTextIsDisplayed() {
        assertThat("В авторизованном профиле отображается информационное сообщение", true,
                equalTo(driver.findElement(changeData).isDisplayed()));
    }

    public void clickConstructorBtn() {
            driver.findElement(constructorBtn).click();
        }

    public void clickLogoBtn() {
        driver.findElement(BtnLogo).click();
    }

    public void clickGoOut(){
        driver.findElement(BtnEnd).click();
    }
}

