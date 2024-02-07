package utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ObjectManager {

    private WebDriver driver;
    private Map<String, By> objectRepository;

    public ObjectManager(WebDriver driver, String csvFilePath) {
        this.driver = driver;
        this.objectRepository = new HashMap<>();
        initializeORFromCSV(csvFilePath);
    }

    private void initializeORFromCSV(String csvFilePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                String elementName = parts[0].trim();
                String xpathLocator = parts[1].trim();
                objectRepository.put(elementName, By.xpath(xpathLocator));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public WebElement getObject(String objectName) {
        By locator = objectRepository.get(objectName);
        if (locator == null) {
            throw new RuntimeException( objectName + " not found in object repository");
        }
        WebElement element = null;
        try {
            element = driver.findElement(locator);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to find element with locator: " + locator);
        }
        if (element == null) {
            throw new RuntimeException(locator + " not found on the web page");
        }
        return element;
    }
}