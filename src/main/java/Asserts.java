import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Asserts {
    public static void checkAddedCart(String expected, WebElement element) {
        assertEquals(expected, element.getText());
    }
}
