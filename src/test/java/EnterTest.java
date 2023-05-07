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
import pageObj.PasswordForgot;
import userAndApi.User;
import userAndApi.UserClient;
import userAndApi.UserGenerator;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class EnterTest {

    private WebDriver driver;
    User user;
    UserClient userClient = new UserClient();
    private String bearerToken;

    @Before
    public void initTest(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        //Яндекс
//        System.setProperty("webdriver.chrome.driver", "src/main/resources/yandexdriver.exe");
//        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://stellarburgers.nomoreparties.site/");

        userClient=new UserClient();
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

    @Test
    @DisplayName("Переход в ЛK NOAUTH")
    @Description("Проверка, что открывается ЛK NOAUTH")
    public void goToAccountNoAuth() {
        HomePage homePage =new HomePage(driver);
        AuthPage authPage = new AuthPage(driver);
        homePage.clickLK();
        authPage.checkSignInBtnInDisplayed();
}

    @Test
    @DisplayName("Переход из ЛK NOAUTH в Конструктор")
    @Description("Проверка, что открывается раздел Конструктор NOAUTH")
    public void goFromAccountToConstructorNoAuth() {
        HomePage homePage =new HomePage(driver);
        AuthPage authPage = new AuthPage(driver);

        homePage.clickLK();;
        authPage.clickBtnConstructor();
        homePage.checkBurgerInDisplayed();
    }

    @Test
    @DisplayName("Переход из ЛK AUTH в Конструктор")
    @Description("Проверка, что открывается раздел Конструктор AUTH USER")
    public void goFromAccToConstrAuth() {
        HomePage homePage =new HomePage(driver);
        AuthPage authPage = new AuthPage(driver);
        AccountPage accountPage=new AccountPage(driver);

        userClient.create(user);
        userClient.login(user);
        homePage.clickLK();
        accountPage.clickConstructorBtn();
        homePage.checkBurgerInDisplayed();
    }

    @Test
    @DisplayName("Переход из ЛК по клику на логотип AUTH USER")
    @Description("Проверка, что открывается главная станица при клике LOGO AUTH USER")
    public void goFromLkClickLogoAuth() {
        HomePage homePage =new HomePage(driver);
        AuthPage authPage = new AuthPage(driver);
        AccountPage accountPage=new AccountPage(driver);

        userClient.create(user);
        userClient.login(user);
        homePage.clickLK();
        accountPage.clickLogoBtn();
        homePage.checkBurgerInDisplayed();
    }
    @Test
    @DisplayName("Кнопка ВЫХОД")
    @Description("Проверка, что авторизованный пользователь может выйти из аккаунта")
    public void goOut() {
        HomePage homePage =new HomePage(driver);
        AuthPage authPage = new AuthPage(driver);
        AccountPage accountPage=new AccountPage(driver);

        userClient.create(user);
        homePage.clickLK();
        authPage.auth(user.getEmail(), user.getPassword());
        authPage.clickSignButton();
        homePage.clickLK();
        accountPage.clickGoOut();
        authPage.checkSignInBtnInDisplayed();
    }

    @Test
    @DisplayName("Вход через страницу восстановление пароля")
    @Description("Проверка, что можем войти в аккаунт через восстановление пароля")
    public void goForgotPassword() {
        HomePage homePage =new HomePage(driver);
        AuthPage authPage = new AuthPage(driver);
        PasswordForgot passwordForgot=new PasswordForgot(driver);

        userClient.create(user);
        homePage.clickLK();
        authPage.clickButtonFoggotPassword();
        passwordForgot.clickSingInBtn();
        authPage.auth(user.getEmail(), user.getPassword());
        authPage.clickSignButton();
        homePage.checkBurgerInDisplayed();
    }
}
