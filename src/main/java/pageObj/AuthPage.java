package pageObj;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class AuthPage {
    WebDriver driver;

    private By buttonReg =By.className("Auth_link__1fOlj");
    private By authEmail=By.xpath("//label[contains(text(),'Email')]/../input");
    private By authPassword=By.xpath("//label[contains(text(),'Пароль')]/../input");
    private By singInBtn = By.xpath(".//button[text()='Войти']");

    private By constructorBtn=By.xpath(".//p[text()='Конструктор']");
    private By btnforgottPassword = By.xpath(".//a[(@class = 'Auth_link__1fOlj' and text()= 'Восстановить пароль')]"); //кнопка восстановить пароль


    public AuthPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickRegButton() {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",driver.findElement(buttonReg));
        buttonReg.findElement(driver).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    public void setEmail(String email1) {driver.findElement(authEmail).sendKeys(email1);}

    public void setPassword(String word) {driver.findElement(authPassword).sendKeys(word);}

    public void auth(String email1, String word) {
        setEmail(email1);
        setPassword(word);
    }

    public void clickSignButton() {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",driver.findElement(singInBtn));
        singInBtn.findElement(driver).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    public void checkSignInBtnInDisplayed(){
        assertThat("После выхода из профиля отображается кнопка Войти", true,
                equalTo(driver.findElement(singInBtn).isDisplayed()));
    }

    public void clickBtnConstructor() {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",driver.findElement(constructorBtn));
        constructorBtn.findElement(driver).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    public void clickButtonFoggotPassword() {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",driver.findElement(btnforgottPassword));
        btnforgottPassword.findElement(driver).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }
    }

