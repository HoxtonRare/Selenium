package asserts;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.*;

public class Asserts {

    public static void checkForNewTab(String originalTab, WebDriver driver) {
        assertNotEquals(originalTab, driver.getWindowHandle());
    }

    public static void checkText(String expected, String actual) {
        assertEquals(expected, actual);
    }

    public static void checkOriginalTab(String originalTab, WebDriver driver) {
        assertEquals(originalTab, driver.getWindowHandle());
    }

    public static void checkTextOfAlert(String expected, WebDriver driver) {
        assertEquals(expected, driver.switchTo().alert().getText());
    }

    public static void checkForCloseAlert(WebDriver driver) {
        assertThrows(NoAlertPresentException.class, () -> driver.switchTo().alert());
    }

    public static void checkForEnteredText(String expected, WebElement element) {
        assertEquals(expected, element.getText());
    }
}
