package stepdefinitions;

import Pages.MercedezTaskPage;
import base.BaseUtil;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.io.IOException;

public class MercedezTaskSteps {

    private BaseUtil base;
    MercedezTaskPage mercedezTaskPage;

    public MercedezTaskSteps(BaseUtil base)  {
        this.base = base;
        mercedezTaskPage = new MercedezTaskPage(base.driver);
    }

//    @Given("I open the google page")
//    public void iOpenTheGooglePage() {
//        mercedezTaskPage.navigateToGooglePage();
//    }
//
//    @When("I search the word {string}")
//    public void iSearchTheWord(String keyWord) throws InterruptedException {
//        mercedezTaskPage.searchKeyWord(keyWord);
//    }
//
//    @Then("I should see the search results")
//    public void iShouldSeeTheSearchResults() {
//        mercedezTaskPage.viewResults();
//    }


    @Given("I navigate to the mercedes-benz UK site")
    public void iNavigateToTheMercedezBenzUKSite() throws InterruptedException {
        mercedezTaskPage.navigateToMercedesBenzPageUK();
    }

    @Then("I should be on the mercedes-benz site")
    public void iShouldBeOnTheMercedezBenzUKSite() {
        Assert.assertTrue(mercedezTaskPage.verifyTheNavigationToMercedesBenzPage(),"Not Successfully Navigated to Mercedes-Benz Page!");
    }


    @When("I select HatchBack under our model section on main page")
    public void iSelectHatchBackUnderOurModelSectionOnMainPage() {
        mercedezTaskPage.selectHatchbacks();
    }

    @Then("I verify that A class Hatchback should exists in the lists")
    public void iVerifyThatAClassHatchbackShouldExistsInTheLists() throws InterruptedException {
    Assert.assertTrue(mercedezTaskPage.verifyClassACarShouldExist(), "Class A Hatch backs are not existed!");
    }

    @Then("I Hover the mouse on the A class models to get {string} option and select it")
    public void iHoverTheMouseOnTheAClassModelsToGetOption(String arg0) throws InterruptedException {
      Assert.assertTrue(mercedezTaskPage.HoverOnClassACarToGetBuildYourCar(arg0),"Build your car option is not available!");
    }

    @Then("I should be navigated to the build you car page")
    public void iShouldBeNavigatedToTheBuildYouCarPage() {
     Assert.assertTrue(mercedezTaskPage.shouldBeOnBuildYourCarPage(),"Not successfully navigated to build your car page!");
    }

    @Then("I check that fuel type {string} should be available and select it")
    public void iCheckThatFuelTypeShouldBeAvailable(String arg0) throws InterruptedException {
      mercedezTaskPage.selectTheExistenceOfDisealFuel();
    }

    @Then("I verify that one or more item\\(s) should be present in the list")
    public void iVerifyThatOneOrMoreItemSShouldBePresentInTheList() throws InterruptedException {
     Assert.assertTrue(mercedezTaskPage.oneOrMoreItemsShouldBePresentInCatelog(),"No item is available in catalog!");
    }

    @Then("I should see that all the available items fuel type should be {string}")
    public void iShouldSeeThatAllTheAvailableItemsFuelTypeShouldBeDiesal(String arg0) {
    Assert.assertTrue(mercedezTaskPage.allItemsMustHaveDiesalFuelType(arg0),"One or more item(s) not have fuel type "+arg0+"!");
    }

    @Then("I verify that the catalog should be sorted according the default sorting method")
    public void iVerifyThatTheCatelogShouldBeSortedAccordigTheDefualtSortingMethod() {
      Assert.assertTrue(mercedezTaskPage.verifyTheItemCatelogIsSortedInTheDefaultOrder(),"Item(s) are not sorted in default order!");
    }

    @When("I save the screenshot of the resultant screen")
    public void iSaveTheScreenshotOfTheResultantScreen() throws IOException {
       mercedezTaskPage.getScreenShotOfWebPage();
    }

    @Then("The screenshot should be present in the directory")
    public void theScreenshotShouldBePresentInTheDirectory() {
     Assert.assertTrue(mercedezTaskPage.verifyTheScreenshotExists(),"Unable to capture the screenshot!");
    }

    @And("I save Highest and lowest price in the text file")
    public void iSaveHighestAndLowestPriceInTheTextFile() throws IOException {
        mercedezTaskPage.saveTheLowestAndHighestPriceInTextFile();
    }

    @Then("I verify that the text file should exist and should not be empty")
    public void iVerifyThatLowestAndHighestPriceShouldBePresentInTheTextFile() {
    Assert.assertTrue(mercedezTaskPage.verifyTheTextFileShouldExistAndNotEmpty(), "Either the text file not exists or does not contain any text!");
    }

    @Then("FINALLY I verify that the price range should be between {string} and {string}")
    public void finallyIVerifyThatThePriceRangeShouldBeBetweenAnd(String arg0, String arg1) {
        Assert.assertTrue(mercedezTaskPage.verifyThatAllItemsShouldBeBetweenSpecifiedRange(arg0,arg1),"One or more item price is not between the specified range!");
    }





}
