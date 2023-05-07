import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObj.HomePage;

import java.util.concurrent.TimeUnit;

public class ConstructorTest {

    WebDriver driver;

    @Before
    public void initTest(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        //Яндекс
        //System.setProperty("webdriver.chrome.driver", "src/main/resources/yandexdriver.exe");
        //driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://stellarburgers.nomoreparties.site/");}

    @After
    public void teardown() {
        // Закрываем браузер
        driver.quit();
    }

    @Test
    @DisplayName("Переход к разделу Buns")
    @Description("Проверка, что можно перейти в раздел Buns ")
    public void openBuns() {
        HomePage homePage = new HomePage(driver);

        homePage.clickSaucesBtn();
        homePage.clickBunBtn();
        homePage.checkBunsIsDisplayed();
    }


    @Test
    @DisplayName("Переход к разделу Sauses")
    @Description("Проверка, что можно перейти в раздел Sauses ")
    public void openSauces() {
        HomePage homePage = new HomePage(driver);

       homePage.clickFillingsBtn();
        homePage.clickSaucesBtn();
        homePage.checkSaucesIsDisplayed();
    }


    @Test
    @DisplayName("Переход к разделу Fillings")
    @Description("Проверка, что можно перейти в раздел Fillings ")
    public void openFillings() {
        HomePage homePage = new HomePage(driver);

        homePage.clickSaucesBtn();
        homePage.clickFillingsBtn();
        homePage.checkFillingsIsDisplayed();
    }
}
