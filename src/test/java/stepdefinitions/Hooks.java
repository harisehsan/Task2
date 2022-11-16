package stepdefinitions;

import base.BaseUtil;
import base.ScreenRecorderUtil;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.File;


public class Hooks extends BaseUtil {

    private String scenarioName;
    private BaseUtil base;


    public Hooks(BaseUtil base) {
        this.base = base;
    }

    @Before
    public void InitializeTest(Scenario scenario) throws Exception {

        if (System.getProperty("os.name").contains("Win"))
            System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        else
            System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
          scenarioName = scenario.getName();
          ScreenRecorderUtil.startRecord(scenarioName);
    }

    @After
    public void TearDownTest(Scenario scenario) throws Exception {
        try {
            ScreenRecorderUtil.stopRecord();
            driver.quit();
//        if (scenario.isFailed()) {

//        }
            Allure.addAttachment("Boundary prices: ",FileUtils.openInputStream(new File("text-files/Boundary_price.txt")));
            Allure.addAttachment("Screenshot: ",FileUtils.openInputStream(new File("screenshots/screenshot.png")));
            Allure.addAttachment("Scenario Video: ",FileUtils.openInputStream(new File("test-recordings/Video.mp4")));

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
