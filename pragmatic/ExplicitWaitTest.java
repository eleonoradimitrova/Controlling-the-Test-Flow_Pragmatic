package bg.pragmatic;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

/**
 * This test: 1.Find an element with id hello-message and verify it Is text
 *            2. Find an element with id anotherDoc p and verify it Is text
 *  and using explicit custom-wait
 */
public class ExplicitWaitTest{
    @Test
    public void testExplicitWait() {
        //Go to the AjAX-waits Application
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Eli\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://pragmatic.bg/automation/ajax-waits/ajax.html");
        try {
            //Get link for First step button and click on it
            WebElement firstStepButton = driver.findElement(By.xpath("//button[contains(text(),'First step')]"));
            firstStepButton.click();
            //Create Wait using WebDriverWait
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            //Get an element with id hello-message and verify it is text
			WebElement messageFirstStep = wait.until(new ExpectedCondition<WebElement>() {
				@Override
				public WebElement apply(WebDriver driver) {
					return driver.findElement(By.cssSelector("#hello-message"));
			}
			});
            //Verify Title
            Assert.assertTrue(messageFirstStep.getText().contains("Hello Pragmatic Student, please assert this text."));

            //Get link for Click the button please! button and click on it
            WebElement clickTheButton = driver.findElement(By.xpath("//button[contains(text(),'Click the button please!')]"));
            clickTheButton.click();
            //Get an element with id anotherDoc p and verify it is text
            WebElement messageClickTheButton = wait.until(new ExpectedCondition<WebElement>() {
                @Override
                public WebElement apply(WebDriver driver) {
                    return driver.findElement(By.cssSelector("#anotherDoc p"));
                }
            });
            //Verify Title
            Assert.assertTrue(messageClickTheButton.getText().contains("You did a good job!"));
        }
        catch (NoSuchElementException e) {
            Assert.fail("Element not found!!");
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
