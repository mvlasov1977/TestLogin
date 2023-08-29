package org.example;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverInfo;
import org.openqa.selenium.chrome.ChromeOptions;

public class SeleniumTest {
    private static final String TEST_PAGE = "https://the-internet.herokuapp.com/login";
    private static final String LOGIN_TRUE = "tomsmith";
    private static final String PASSWORD_TRUE = "SuperSecretPassword!";
    private static final String LOGIN_FALSE = "garrypotter";
    private static final String PASSWORD_FALSE = "avadakedabra";

    /*
     https://the-internet.herokuapp.com/login - написати 2 тести на коректний та некоректний логін.
     В першому випадку перевірити, що відображається повідомлення "You logged into a secure area!",
     в другому випадку - що відображається повідомлення про неправильний логін/пароль
     */

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
        //System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        WebDriver chromeDriver = new ChromeDriver();
        chromeDriver.get(TEST_PAGE);
        loggingIn(chromeDriver, LOGIN_TRUE,PASSWORD_TRUE);
        WebElement completeMessage = chromeDriver.findElement(By.id("flash"));
        Assertions.assertTrue(completeMessage.getText().contains("You logged into a secure area!\n"));
        chromeDriver.quit();
    }
    @Test
    public void checkLoginFalseAuthorisationProcess(){
        //System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        WebDriver chromeDriver = new ChromeDriver();
        chromeDriver.get(TEST_PAGE);
        loggingIn(chromeDriver, LOGIN_FALSE,PASSWORD_TRUE);
        WebElement completeMessage = chromeDriver.findElement(By.id("flash"));
        Assertions.assertTrue(completeMessage.getText().contains("Your username is invalid!\n"));
        chromeDriver.quit();
    }
    @Test
    public void checkPasswordFalseAuthorisationProcess(){
        //System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        WebDriver chromeDriver = new ChromeDriver();
        chromeDriver.get(TEST_PAGE);
        loggingIn(chromeDriver, LOGIN_TRUE,PASSWORD_FALSE);
        WebElement completeMessage = chromeDriver.findElement(By.id("flash"));
        Assertions.assertTrue(completeMessage.getText().contains("Your password is invalid!\n"));
        chromeDriver.quit();
    }
}
