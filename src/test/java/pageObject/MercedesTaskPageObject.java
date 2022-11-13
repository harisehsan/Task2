package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MercedesTaskPageObject {
//    @FindBy(name = "q") public WebElement searchbar_txt;
//    @FindBy(name = "btnK") public WebElement searchbutton_btn;
//    @FindBy(id = "result-stats") public WebElement result_lbl;
//    @FindBy(id = "L2AGLb") public List<WebElement> acceptALL_btn;

    @FindBy(xpath = "//dh-io-vmos[@class='webcomponent aem-GridColumn aem-GridColumn--default--12']") public WebElement our_models_shadow_root;
    @FindBy(xpath = "(//cmm-cookie-banner[@class='hydrated'])[1]") public WebElement main_shadow_root;
    @FindBy(xpath = "//owcc-car-configurator[@class='webcomponent aem-GridColumn aem-GridColumn--default--12']") public WebElement car_config_root;

    public By class_A_lbl_by = By.className("dh-io-vmos_1PW4e");
    public By hatchback_price_lbl_by = By.className("dh-io-vmos_GF728");
    public By first_Car_options_lst_by = By.className("dh-io-vmos_3HxTq");
    public By accept_All_btn_by = By.className("wb-button--accept-all");
    public By main_shadow_root_by = By.xpath("//cmm-cookie-banner[@class='hydrated'])[1]");
    public By car_widgt_home_screen_by = By.className("dh-io-vmos_2YogX");
    public By our_models_header_by = By.className("dh-io-vmos_3zIAO");
    public By fuel_type_drpDown_by = By.className("wb-multi-select-control__label");
    public By diesal_type_chkBox_by = By.name("bds/checkmark/16");
    public By catelog_items_lbl_by = By.className("cc-motorization-header__vehicle-name");
    public By fuel_info_lbl_by = By.className("cc-motorization-comparison__tag");
    public By item_price_lst_by = By.className("cc-motorization-header__price");


}
