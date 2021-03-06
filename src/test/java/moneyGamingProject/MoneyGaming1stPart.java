package moneyGamingProject;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.WebDriverFactory;

import java.util.concurrent.TimeUnit;

public class MoneyGaming1stPart {

    private WebDriver driver;
    private String baseUrl;

    @BeforeMethod
    public void setUp() throws Exception {
        driver = WebDriverFactory.getDriver("chrome");
        baseUrl = "https://moneygaming.qa.gameaccount.com/sign-up.shtml";
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void testMoneyGame() throws Exception {
        driver.get(baseUrl);

        //public static void main(String[] args) throws InterruptedException {

        // WebDriver driver = WebDriverFactory.getDriver("chrome");

        // driver.get("https://moneygaming.qa.gameaccount.com/sign-up.shtml");

        Faker faker = new Faker();

        driver.findElement(By.id("forename")).sendKeys(faker.name().firstName());

        driver.findElement(By.name("map(lastName)")).sendKeys(faker.name().lastName());

        WebElement checkboxTC = driver.findElement(By.xpath("//*[@id=\"checkbox\"][3]"));

        checkboxTC.click();

        Assert.assertTrue(checkboxTC.isSelected());

        driver.findElement(By.id("form")).click();

        WebElement expectedMessage = driver.findElement(By.cssSelector("label[for='dob']"));

        Assert.assertEquals("This field is required", expectedMessage.getText());

    }
    @AfterMethod
    public void tearDown() throws Exception {
        driver.quit();
    }
}

