import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObj.AccountPage;
import pageObj.AuthPage;
import pageObj.HomePage;
import pageObj.RegisterPage;
import userAndApi.User;
import userAndApi.UserClient;
import userAndApi.UserGenerator;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class RegisterTest{

        private WebDriver driver;
       User user;
      UserClient userClient = new UserClient();
      private String bearerToken;

    @Before
    public void initTest(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        //WebDriverManager.firefoxdriver().setup();
        //driver=new FirefoxDriver();
        //Яндекс
//        System.setProperty("webdriver.chrome.driver", "src/main/resources/yandexdriver.exe");
//        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://stellarburgers.nomoreparties.site/");

        userClient = new UserClient();
        user = new UserGenerator().getRandomUser();}

    @After
    public void teardown() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.quit();
        if (user != null) {
            ValidatableResponse responseLogin = userClient.login(user);
            bearerToken = responseLogin.extract().path("accessToken");
            if (bearerToken == null) {
                return;
            }
            userClient.userDelete(bearerToken);
        }
    }

        @Test()
        @DisplayName("Регистрация пользователя с валидными данными")
        @Description("Проверка, что можно зарегистрировать пользователя с валидными данными ")
        public void registerTest(){
            HomePage homePage =new HomePage(driver);
            AuthPage authPage = new AuthPage(driver);
            RegisterPage registerPage=new RegisterPage(driver);
            AccountPage accountPage=new AccountPage(driver);

            homePage.clickLK();
            authPage.clickRegButton();

            registerPage.login(user.getName(), user.getEmail(), user.getPassword());
            registerPage.clickButtonLogin();
            userClient.login(user);
            authPage.auth(user.getEmail(), user.getPassword());
            authPage.clickSignButton();
            homePage.clickLK();
           accountPage.checkTextIsDisplayed();
        }

    @Test
    @DisplayName("Регистрация пользователя с коротким паролем")
    @Description("Проверка невозможности регистрации с коротким паролем")
    public void createNewUserWithShortPassword() {
        HomePage homePage =new HomePage(driver);
        AuthPage authPage = new AuthPage(driver);
        RegisterPage registerPage=new RegisterPage(driver);
        homePage.clickLK();
        authPage.clickRegButton();

        registerPage.login(user.getName(), user.getEmail(), "ytry");
        registerPage.clickButtonLogin();
        registerPage.checkErrorPassword();
    }
}
