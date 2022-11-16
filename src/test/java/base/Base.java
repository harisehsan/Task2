package base;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;


public class Base extends BaseUtil {

    public Base(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    protected void waitUntilPageReady(Duration DEFAULT_TIMEOUT){
        new WebDriverWait(driver, DEFAULT_TIMEOUT).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    protected void javaScriptButtonClick(WebElement ele)
    {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()",ele);
    }
    protected SearchContext getShadowRoot(WebElement ele)
    {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return  (SearchContext) js.executeScript("return arguments[0].shadowRoot",ele);
    }
    protected String getPageTitle()
    {
        return driver.getTitle();
    }

    protected String getPageURL()
    {
        return driver.getCurrentUrl();
    }

    protected void mouseHoverCustom(WebElement ele1, WebElement ele2, List <WebElement> resele) throws InterruptedException {
        int count = 0;
        while(count < 20) {
            try {
                do {
                    try {
                        count++;
                        Actions action = new Actions(driver);
                        action.moveToElement(ele1).moveToElement(ele2).
                                build().perform();
                    } catch (Exception e) {
                        continue;
                    }
                } while (!resele.get(resele.size() - 1).getText().contains("Build your car") && count < 20);
                return;
            } catch (Exception e) {
               continue;
            }
        }
    }

    protected List<Integer> getPriceValues(List<Integer> pricelst, List<WebElement> itemPricelst) {
        for (WebElement webElement : itemPricelst) {
            pricelst.add(Integer.parseInt(webElement.getText().replaceAll("\\D+", "").replace("£", "").replace(",", "")));
        }
        return pricelst;
    }

    protected int convertToInteger(String price)
    {
       return (Integer.parseInt(price.replaceAll("\\D+", "").replace("£", "").replace(",", "")));
    }

    public void takeScreenShot() throws IOException {
            TakesScreenshot scrShot =((TakesScreenshot)driver);
            File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
            File DestFile=new File("screenshots/screenshot.png");
            FileUtils.copyFile(SrcFile, DestFile);
    }

    protected void waitUntilVisible(WebElement ele, Duration DEFAULT_TIMEOUT) {
        new WebDriverWait(driver, DEFAULT_TIMEOUT).until(ExpectedConditions.visibilityOf(ele));
    }

    protected void goToPage(String url)
    {
        driver.navigate().to(url);
    }

    protected void scrollToView(WebElement ele) throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", ele);
    }

    public boolean elementExist(List<WebElement> ele)
    {
         return (ele.size() > 0);
    }
}
