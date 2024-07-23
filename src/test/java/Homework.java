import asserts.asserts;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static asserts.asserts.*;

import java.time.Duration;
import java.util.Set;

public class Homework {
    WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    @Test
    public void testWindows() {
        String expectedText = "New page";

        driver.get("http://the-internet.herokuapp.com/windows");
        String originalTab = driver.getWindowHandle();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Click Here"))).click();

        Set<String> allTabs = driver.getWindowHandles();
        for (String tab : allTabs) {
            if(!tab.equals(originalTab)) {
                driver.switchTo().window(tab);
                break;
            }
        }
        checkForNewTab(originalTab, driver);

        String actualText = driver.findElement(By.xpath("//h3")).getText();

        checkText(expectedText, actualText);

        driver.close();
        driver.switchTo().window(originalTab);

        checkOriginalTab(originalTab, driver);
        driver.close();
    }

    @Test
    public void testAlerts() {
        String expectedTextAlert = "I am a JS Alert";
        String expectedResult = "You entered: Hello World";

        driver.get("http://the-internet.herokuapp.com/javascript_alerts");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@onclick = 'jsAlert()']"))).click();
        wait.until(ExpectedConditions.alertIsPresent());

        checkTextOfAlert(expectedTextAlert, driver);
        driver.switchTo().alert().accept();

        checkForCloseAlert(driver);

        driver.findElement(By.xpath("//button[@onclick = 'jsConfirm()']")).click();
        driver.switchTo().alert().dismiss();

        checkForCloseAlert(driver);

        driver.findElement(By.xpath("//button[@onclick = 'jsPrompt()']")).click();
        driver.switchTo().alert().sendKeys("Hello World");
        driver.switchTo().alert().accept();
        WebElement element = driver.findElement(By.id("result"));

        checkForEnteredText(expectedResult, element);
        driver.close();
    }
}
