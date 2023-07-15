package org.example;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumTest {
    private static final String TEST_PAGE = "https://the-internet.herokuapp.com/login";
    private static final String LOGIN_TRUE = "tomsmith";
    private static final String PASSWORD_TRUE = "SuperSecretPassword!";
    private static final String LOGIN_FALSE = "garrypotter";
    private static final String PASSWORD_FALSE = "avadakedabra";
    public static WebDriver chromeDriver;
    /*
     https://the-internet.herokuapp.com/login - написати 2 тести на коректний та некоректний логін.
     В першому випадку перевірити, що відображається повідомлення "You logged into a secure area!",
     в другому випадку - що відображається повідомлення про неправильний логін/пароль
     */
    @BeforeEach
    public void beforeEach(){
         chromeDriver = new ChromeDriver();
    }
    private void loggingIn(WebDriver chromeDriver, String login, String password){
        WebElement inputLogin = chromeDriver.findElement(By.id("username"));
        inputLogin.sendKeys(login);
        WebElement inputPassword = chromeDriver.findElement(By.id("password"));
        inputPassword.sendKeys(password);
        WebElement buttonLogin = chromeDriver.findElement(By.xpath("//button[@type='submit']"));
        buttonLogin.click();
    }

    @Test
    public void checkTrueAuthorisationProcess() {
        chromeDriver.get(TEST_PAGE);
        loggingIn(chromeDriver, LOGIN_TRUE,PASSWORD_TRUE);
        WebElement completeMessage = chromeDriver.findElement(By.id("flash"));
        Assertions.assertEquals(completeMessage.getText().contains("You logged into a secure area!\n"),true);
    }
    @Test
    public void checkLoginFalseAuthorisationProcess(){
        chromeDriver.get(TEST_PAGE);
        loggingIn(chromeDriver, LOGIN_FALSE,PASSWORD_TRUE);
        WebElement completeMessage = chromeDriver.findElement(By.id("flash"));
        Assertions.assertEquals(completeMessage.getText().contains("Your username is invalid!\n"),true);
    }
    @Test
    public void checkPasswordFalseAuthorisationProcess(){
        chromeDriver.get(TEST_PAGE);
        loggingIn(chromeDriver, LOGIN_TRUE,PASSWORD_FALSE);
        WebElement completeMessage = chromeDriver.findElement(By.id("flash"));
        Assertions.assertEquals(completeMessage.getText().contains("Your password is invalid!\n"),true);
    }
    @AfterEach
    public void afterEach(){
        chromeDriver.quit();
    }
}