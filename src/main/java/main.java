import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class main {
    public static void main(String[] args) throws InterruptedException {
        List<WebDriver> drivers = new ArrayList<>();
        drivers.addFirst(new ChromeDriver());
        drivers.add(new EdgeDriver());
        drivers.add(new FirefoxDriver());
        for (WebDriver x : drivers) {
            x.get("http://www.google.com");
            sleep(100);
            x.quit();
        }
    }

}