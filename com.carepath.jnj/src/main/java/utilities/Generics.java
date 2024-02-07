package utilities;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Generics {

    private WebDriver driver;
    private ObjectManager objectManager;

    public Generics(WebDriver driver) {
        this.driver = driver;
        this.objectManager = new ObjectManager(driver,"C:\\Users\\0033RC744\\Desktop\\test.csv");
    }

    

    @Then("I click on cell {string} in row {int} and column {int} of the web table {string}")
public void iClickOnTableCell(String tableName, String cellName, int rowNum, int colNum) {
    WebElement table = objectManager.getObject(tableName);
    WebElement cell = table.findElement(By.xpath("//tr[" + rowNum + "]/td[" + colNum + "][contains(text(),'" + cellName + "')]"));
    cell.click();
}

@Then("I get text from cell in row {int} and column {int} of the web table {string}")
public String iGetTextFromTableCell(int rowNum, int colNum, String tableName) {
    WebElement table = objectManager.getObject(tableName);
    WebElement cell = table.findElement(By.xpath("//tr[" + rowNum + "]/td[" + colNum + "]"));
    return cell.getText();
}



    @Then("I toggle checkbox {string}")
public void iToggleCheckbox(String checkboxName) {
    WebElement checkbox = objectManager.getObject(checkboxName);
    checkbox.click();
}
@Then("I verify if checkbox {string} is unchecked")
public void iVerifyCheckboxIsUnchecked(String checkboxName) {
    WebElement checkbox = objectManager.getObject(checkboxName);
    Assert.assertFalse( checkbox.isSelected(), "Checkbox is checked");
}

@Then("I verify if checkbox {string} is checked")
public void iVerifyCheckboxIsChecked(String checkboxName) {
    WebElement checkbox = objectManager.getObject(checkboxName);
    Assert.assertTrue(checkbox.isSelected(), "Not Checked");
}

@Then("I uncheck checkbox {string}")
public void iUncheckCheckbox(String checkboxName) {
    WebElement checkbox = objectManager.getObject(checkboxName);
    if (checkbox.isSelected()) {
        checkbox.click();
    }
}

@Then("I check checkbox {string}")
public void iCheckCheckbox(String checkboxName) {
    WebElement checkbox = objectManager.getObject(checkboxName);
    if (!checkbox.isSelected()) {
        checkbox.click();
    }
}

@Then("I verify if combo box {string} is enabled")
public void iVerifyComboBoxIsEnabled(String comboBoxName) {
    WebElement comboBox = objectManager.getObject(comboBoxName);
    Assert.assertTrue(comboBox.isEnabled(), "Combo box is not enabled");
}
@Then("I select option {string} from combo box {string}")
public void iSelectOptionFromComboBox(String optionText, String comboBoxName) {
    WebElement comboBox = objectManager.getObject(comboBoxName);
    Select select = new Select(comboBox);
    select.selectByVisibleText(optionText);
}



    @Then("I select option with value {string} from dropdown {string}")
public void iSelectOptionWithValueFromDropdown(String optionValue, String dropdownName) {
    WebElement dropdown = objectManager.getObject(dropdownName);
    Select select = new Select(dropdown);
    select.selectByValue(optionValue);
}
@Then("I select option {string} from dropdown {string}")
public void iSelectOptionFromDropdown(String optionText, String dropdownName) {
    WebElement dropdown = objectManager.getObject(dropdownName);
    Select select = new Select(dropdown);
    select.selectByVisibleText(optionText);
}

    @Then("I verify if option {string} is present in dropdown {string}")
public void iVerifyOptionIsPresentInDropdown(String optionText, String dropdownName) {
    WebElement dropdown = objectManager.getObject(dropdownName);
    Select select = new Select(dropdown);
    List<WebElement> options = select.getOptions();
    boolean optionPresent = options.stream().anyMatch(option -> option.getText().equals(optionText));
    Assert.assertTrue(optionPresent, "Option not found in dropdown");
}


    

@Then("I verify that {string} contains text {string}")
public void iVerifyElementContainsText(String elementName, String expectedText) {
    WebElement element = objectManager.getObject(elementName);
    String actualText = element.getText();
    Assert.assertEquals("Actual text doesn't match expected text", expectedText, actualText);
}



    @Then("I verify that {string} is enabled")
public void iVerifyElementIsEnabled(String elementName) {
    WebElement element = objectManager.getObject(elementName);
    Assert.assertTrue(element.isEnabled(), "Element is not enabled");
}

    @Then("I compare {string} with {string}")
public void iCompareTwoValues(String actualValue, String expectedValue) {
    Assert.assertEquals("Values do not match", expectedValue, actualValue);
}



    @Then("I scroll to element {string}")
public void iScrollToElement(String elementName) {
    WebElement element = objectManager.getObject(elementName);
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
}



    @Then("I scroll to the bottom of the page")
public void iScrollToBottomOfPage() {
    ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
}


    @Then("I scroll to the top of the page")
public void iScrollToTopOfPage() {
    ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
}

    

@Then("I double click on {string}")
    public void iDoubleClickOnElement(String elementName) {
        WebElement element = objectManager.getObject(elementName);
        Actions actions = new Actions(driver);
        actions.doubleClick(element).perform();
    }
 @Then("I wait until {string} is clickable")
    public void iWaitUntilElementIsClickable(String elementName) {
        WebElement element = objectManager.getObject(elementName);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    @Then("I clear the text from {string}")
    public void iClearTextFromElement(String elementName) {
        WebElement element = objectManager.getObject(elementName);
        element.clear();
    }

    @Then("I press the {string} key")
    public void iPressKey(String key) {
        //need to check for other action class
        new Actions(driver).sendKeys(key).perform();
    }

 @Then("I should see {string}")
    public void iShouldSeeText(String expectedText) {
        WebElement element = objectManager.getObject("elementLocator");
        String actualText = element.getText();
        //asset
    }

    @Then("I should be on {string} page")
    public void iShouldBeOnPage(String pageTitle) {
        String actualTitle = driver.getTitle();
        //assert
    }

    @Then("I wait for {int} seconds")
    public void iWaitForSeconds(int seconds) throws InterruptedException {
        Thread.sleep(seconds * 1000);
    }

    @Then("I wait until {string} is visible")
    public void iWaitUntilElementIsVisible(String elementName) {
        WebElement element = objectManager.getObject(elementName);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    @Then("I switch to frame {string}")
    public void iSwitchToFrame(String frameName) {
        driver.switchTo().frame(frameName);
    }

    @Then("I accept alert")
    public void iAcceptAlert() {
        driver.switchTo().alert().accept();
    }

    @Then("I dismiss alert")
    public void iDismissAlert() {
        driver.switchTo().alert().dismiss();
    }

    
    @Then("I navigate back")
    public void iNavigateBack() {
        driver.navigate().back();
    }

     @When("I click on {string}")
    public void iClickOnElement(String elementName) {
        WebElement element = objectManager.getObject(elementName);
        element.click();
    }

    @When("I enter {string} into {string}")
    public void iEnterTextIntoElement(String text, String elementName) {
        WebElement element = objectManager.getObject(elementName);
        element.sendKeys(text);
    }

    @When("I hover over {string}")
    public void iHoverOverElement(String elementName) {
        WebElement element = objectManager.getObject(elementName);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }
    @Then("I verify if cell in row {int} and column {int} of the web table {string} contains text {string}")
public void iVerifyIfTableCellContainsText(int rowNum, int colNum, String tableName, String expectedText) {
    WebElement table = objectManager.getObject(tableName);
    WebElement cell = table.findElement(By.xpath("//tr[" + rowNum + "]/td[" + colNum + "]"));
    String actualText = cell.getText();
    Assert.assertEquals("Actual text doesn't match expected text", expectedText, actualText);
}

}
