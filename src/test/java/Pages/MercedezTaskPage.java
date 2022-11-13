package Pages;

import base.Base;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import pageObject.MercedesTaskPageObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class MercedezTaskPage extends Base {
    WebDriver driver;
    MercedesTaskPageObject mercedesTaskPageObject = new MercedesTaskPageObject();

    public MercedezTaskPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, mercedesTaskPageObject);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public void navigateToMercedesBenzPageUK() throws InterruptedException {

        goToPage("https://www.mercedes-benz.co.uk/");
        waitUntilPageReady(Duration.ofMinutes(1));
        List<WebElement> acceptAll_btn = getShadowRoot(mercedesTaskPageObject.main_shadow_root).findElements(mercedesTaskPageObject.accept_All_btn_by);
        if (elementExist(acceptAll_btn))
            for (WebElement webElement : acceptAll_btn)
                     javaScriptButtonClick(webElement);
    }
    public boolean verifyTheNavigationToMercedesBenzPage()
    {
        return getPageTitle().contains("Mercedes-Benz");
    }

   public void selectHatchbacks()
   {
       goToPage("https://www.mercedes-benz.co.uk/passengercars.html?group=all&subgroup=all.BODYTYPE.hatchback&view=BODYTYPE");
       waitUntilPageReady(Duration.ofMinutes(1));
   }

   public boolean verifyClassACarShouldExist() throws InterruptedException {
        List <WebElement> hatchbackcars = getShadowRoot(mercedesTaskPageObject.our_models_shadow_root).findElements(mercedesTaskPageObject.class_A_lbl_by);
        WebElement our_model = getShadowRoot(mercedesTaskPageObject.our_models_shadow_root).findElement(mercedesTaskPageObject.our_models_header_by);
       scrollToView(our_model);
       for (int i=0; i< hatchbackcars.size()-1; i+=2) {
           if (hatchbackcars.get(i).getText().contains("A-Class") && hatchbackcars.get(i+1).getText().contains("Hatchback"))
               return true;
       }
       return false;
   }

   public boolean HoverOnClassACarToGetBuildYourCar(String buildYourCar) throws InterruptedException {
     List<WebElement> hatchBackCarPrices = getShadowRoot(mercedesTaskPageObject.our_models_shadow_root).findElements(mercedesTaskPageObject.hatchback_price_lbl_by);
       WebElement hatchBackCarPrice = getShadowRoot(mercedesTaskPageObject.our_models_shadow_root).findElement(mercedesTaskPageObject.hatchback_price_lbl_by);
       List<WebElement> carOptions = getShadowRoot(mercedesTaskPageObject.our_models_shadow_root).findElements(mercedesTaskPageObject.first_Car_options_lst_by);
     List<WebElement> carWidgts = getShadowRoot(mercedesTaskPageObject.our_models_shadow_root).findElements(mercedesTaskPageObject.car_widgt_home_screen_by);
     boolean build_your_car_flag = false;
       waitUntilVisible(hatchBackCarPrice,Duration.ofSeconds(5));
       do {
           try {
               mouseHoverCustom(carWidgts.get(1),hatchBackCarPrices.get(1),carOptions);
               List<WebElement> carOptions2 = getShadowRoot(mercedesTaskPageObject.our_models_shadow_root).findElements(mercedesTaskPageObject.first_Car_options_lst_by);
               for (WebElement webElement : carOptions2) {
                   if (webElement.getText().contains(buildYourCar)) {
                       build_your_car_flag = true;
                       webElement.click();
                       waitUntilPageReady(Duration.ofMinutes(1));
                   }
               }
               break;
           } catch (Exception ignored) {}

       } while (!build_your_car_flag);
       return build_your_car_flag;
   }

   public boolean shouldBeOnBuildYourCarPage()
   {
       return (getPageURL().contains("car-configurator"));
   }

   public void selectTheExistenceOfDisealFuel() throws InterruptedException {
       WebElement fuelType = getShadowRoot(mercedesTaskPageObject.car_config_root).findElement(mercedesTaskPageObject.fuel_type_drpDown_by);
        WebElement fuelchkBox = getShadowRoot(mercedesTaskPageObject.car_config_root).findElement(mercedesTaskPageObject.diesal_type_chkBox_by);
       List <WebElement> fuelchkBoxlst = getShadowRoot(mercedesTaskPageObject.car_config_root).findElements(mercedesTaskPageObject.diesal_type_chkBox_by);
       do {
           try {
               scrollToView(fuelType);
               javaScriptButtonClick(fuelType);
               waitUntilVisible(fuelchkBox,Duration.ofSeconds(5));
               if (!fuelchkBoxlst.get(0).isSelected())
                   fuelchkBoxlst.get(0).click();
               if (fuelchkBoxlst.get(1).isSelected())
                   fuelchkBoxlst.get(1).click();
               if (fuelchkBoxlst.get(1).isSelected())
                   fuelchkBoxlst.get(2).click();
               javaScriptButtonClick(fuelType);
           } catch (Exception ignored) {

           }
       } while (fuelchkBoxlst.get(0).isSelected());
   }

   public boolean oneOrMoreItemsShouldBePresentInCatelog() throws InterruptedException {
       List <WebElement> catelog = getShadowRoot(mercedesTaskPageObject.car_config_root).findElements(mercedesTaskPageObject.catelog_items_lbl_by);
       scrollToView(catelog.get(1));
       return elementExist(catelog);
   }

   public boolean allItemsMustHaveDiesalFuelType(String Diesal)
   {
     List <WebElement> fuelType = getShadowRoot(mercedesTaskPageObject.car_config_root).findElements(mercedesTaskPageObject.fuel_info_lbl_by);
       for (int i=1; i<fuelType.size()-1; i+=3)
       {
           if (!fuelType.get(i).getText().contains(Diesal))
               return false;
       }
       return true;
   }

   public boolean verifyTheItemCatelogIsSortedInTheDefaultOrder()
   {
       List<Integer> pricelst = getPricesListInInteger();
       return pricelst.stream().sorted().collect(Collectors.toList()).equals(pricelst);
   }

    public void getScreenShotOfWebPage() throws IOException {
       takeScreenShot();
   }

   public boolean verifyTheScreenshotExists()
   {
       File f = new File("screenshots\\screenshot.png");
       return (f.exists());
   }

   public void saveTheLowestAndHighestPriceInTextFile() throws IOException {
       String path = "text-files\\Boundary_price.txt";
       Path path1 = Paths.get(path);
       File f = new File(path);
       if (!Files.isRegularFile(path1)) {
           new File("text-files").mkdirs();
           f.createNewFile();
       }
       List <WebElement> itemPricelst = getShadowRoot(mercedesTaskPageObject.car_config_root).findElements(mercedesTaskPageObject.item_price_lst_by);
       String content = "The Lowest price is: "+itemPricelst.get(0).getText()+"\nThe Highest price is: "+itemPricelst.get(itemPricelst.size()-1).getText();
       Files.write(path1, content.getBytes());
   }

   public boolean verifyTheTextFileShouldExistAndNotEmpty()
   {
       File f = new File("text-files\\Boundary_price.txt");
       return (f.exists() && f.length() > 0);
   }
   public boolean verifyThatAllItemsShouldBeBetweenSpecifiedRange(String lowerBound, String upperBound)
   {
       int lowerBound_range = convertToInteger(lowerBound);
       int upperBound_range = convertToInteger(upperBound);
       List<Integer> pricelst = getPricesListInInteger();
       for (Integer price : pricelst) {
           if (price < lowerBound_range || price > upperBound_range)
               return false;
       }
           return true;
   }

    private List<Integer> getPricesListInInteger() {
        List <Integer> pricelst = new ArrayList<>();
        List <WebElement> itemPricelst = getShadowRoot(mercedesTaskPageObject.car_config_root).findElements(mercedesTaskPageObject.item_price_lst_by);
        pricelst = getPriceValues(pricelst, itemPricelst);
        return pricelst;
    }
}