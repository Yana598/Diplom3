package pageObj;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class HomePage {
    WebDriver driver;

    //локатор для кнопки личный кабинет
    //private By buttonLK = By.xpath(".//p[text()='Личный Кабинет']");
    private By buttonLK = By.xpath(".//p[contains(text(),'Личный Кабинет')]"); //кнопка личный кабинет

    //локатор для поля фамилия

    //локатор для поля адрес
    private By name = By.xpath(".//label[text()='Имя']");

    private By email=By.xpath(".//label[text()='Email']");
    private By password=By.xpath(".//label[text()='Пароль']");

    private By buttonRegisterReady=By.xpath(".//button[text()='Зарегистрироваться']");//Зарегистрироваться
    private By bunBtn = By.xpath(".//div[span[text()='Булки']]"); // булки
    private By saucesBtn = By.xpath(".//div[span[text()='Соусы']]"); //соусы
    private By fillingsBtn = By.xpath(".//div[span[text()='Начинки']]"); //начинки
    private By constructorBtn = By.xpath(".//p[text()='Конструктор']"); //конструктор

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickLK() {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",driver.findElement(buttonLK));
        driver.findElement(buttonLK).click();
    }

    public void checkBurgerInDisplayed() {
         assertThat("соберите бургер на дисплее", true,
                 equalTo(driver.findElement(By.xpath(".//h1[text() = 'Соберите бургер']")).isDisplayed()));
        }

    public void clickSaucesBtn() {
        driver.findElement(saucesBtn).click();
    }

    public void clickFillingsBtn() {
        driver.findElement(fillingsBtn).click();
    }

    public void clickBunBtn() {
        driver.findElement(bunBtn).click();
    }

    public void checkSaucesIsDisplayed() {
        assertThat("Вкладка Соусы отображается", true,
                equalTo(driver.findElement(By.xpath("//div[contains(span/text(),'Соусы') and contains(@class,'current')]")).isDisplayed()));
    }

    public void checkFillingsIsDisplayed() {
        assertThat("Вкладка Начинки отображается", true,
                equalTo(driver.findElement(By.xpath("//div[contains(span/text(),'Начинки') and contains(@class,'current')]")).isDisplayed()));
    }

    public void checkBunsIsDisplayed() {
        assertThat("Вкладка Булки отображается", true,
                equalTo(driver.findElement(By.xpath("//div[contains(span/text(),'Булки') and contains(@class,'current')]")).isDisplayed()));
    }
    }

