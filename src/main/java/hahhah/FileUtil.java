package hahhah;

import com.beust.jcommander.internal.Lists;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.PrefixFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class FileUtil {
    public static File getFileByName(String dirPath, String fileName) throws FileNotFoundException {
        File dirFile=new File(dirPath);
        if(!dirFile.exists()){
            throw new FileNotFoundException(String.format("Dir %s Not found", dirPath));
        }
        IOFileFilter ioFileFilter = new PrefixFileFilter(fileName);
        Iterator<File> fileIterator = FileUtils.iterateFiles(new File(dirPath), ioFileFilter, TrueFileFilter.INSTANCE);
        if (fileIterator.hasNext()) {
            return fileIterator.next();
        } else {
            throw new FileNotFoundException(String.format("File %s Not found", dirPath));
        }
    }

    // TODO: 2021/9/28 改成从yml里面读取
    public static Map<String, List<String>> getCaseScreenshotsMap2() {
        String caseName1 = "CheckPostPurchasePage";
        List<String> testPages1 = Lists.newArrayList("cooking_status", "driving_status", "received_status", "serving_status");

        String caseName2 = "ModifyItemFromCart";
        List<String> testPages2 = Lists.newArrayList("first_time_to_shop_cart",
                "second_time_to_shop_cart");

        String caseName3 = "VisitorCheckRLPCarouselAndCategory";
        List<String> testPages3 = Lists.newArrayList("VisitorCheckRLPCarouselAndCategory",
                "category on RDP page", "bundle detail page", "carousel_add_to_order_popup");

        String caseName4 = "VisitorRegisterAndPlaceOrderByAppleOrGooglePay";
        List<String> testPages4 = Lists.newArrayList("register_page",
                "edit_address_page");

        String caseName5 = "ModifyCreditCardInCheckoutPage";
        List<String> testPages5 = Lists.newArrayList("payment_list_page",
                "login_success_home_page");
        String caseName6 = "ModifyAddressInCheckoutPage";
        List<String> testPages6 = Lists.newArrayList("register_user_to_checkout_page",
                "add_address_page");

        Map<String, List<String>> casePicsMap = new HashMap<>();
        casePicsMap.put(caseName1, testPages1);
        casePicsMap.put(caseName2, testPages2);
        casePicsMap.put(caseName3, testPages3);
        casePicsMap.put(caseName4, testPages4);
        casePicsMap.put(caseName5, testPages5);
        casePicsMap.put(caseName6, testPages6);

        return casePicsMap;
    }

    // TODO: 2021/9/28 改成从yml里面读取
    public static Map<String, List<String>> getCaseScreenshotsMap() {
        String caseName1 = "DefaultAddressUnableToGetStarted";
        List<String> testPages1 = Lists.newArrayList("after click get start", "location page");

        String caseName2 = "MealDetailNutritionInfo";
        List<String> testPages2 = Lists.newArrayList("Nutrition And Allergy Displayed",
                "restaurant detail page");

        String caseName3 = "MissNumberAddressAddInstructionGetStarted";
        List<String> testPages3 = Lists.newArrayList("empty instruction unable to continue",
                "instruction added");

        String caseName4 = "AddMissNumberAddressByAddInstructionInCheckoutPage";
        List<String> testPages4 = Lists.newArrayList("address is MISS_NUMBER_ADDRESS",
                "matched address MISS_NUMBER_ADDRESS");

        String caseName5 = "AllergensProfileUpdate";
        List<String> testPages5 = Lists.newArrayList("MilkFishSoyGlutenSelected");

        String caseName6 = "RestaurantFullScreenshotWithItems";
        List<String> testPages6 = Lists.newArrayList("restaurant full screenshot with item[0]", "restaurant full screenshot with item[10]");

        String caseName7 = "RestaurantFullScreenshotWithShopCartEmpty";
        List<String> testPages7 = Lists.newArrayList("restaurant full screenshot without item[0]", "restaurant full screenshot without item[9]");

        String caseName8 = "DefaultAddressUnableToGetStarted";
        List<String> testPages8 = Lists.newArrayList("unservedAreaHopup", "welcome page");

        String caseName9 = "HomePageFullScreenshotWithItems";
        List<String> testPages9 = Lists.newArrayList("home page full screenshot with item[0]");

        String caseName10 = "HomePageFullScreenshotWithShopCartEmpty";
        List<String> testPages10 = Lists.newArrayList("home page full screenshot without item[0]");

        String caseName11 = "LoginByPhoneWithPassword";
        List<String> testPages11 = Lists.newArrayList("enter your code", "login by phone");

        String caseName12 = "MealDetailWithOption";
        List<String> testPages12 = Lists.newArrayList("quantity limited", "special request clicked", "meal with option");

        String caseName13 = "OutOfStockInCart";
        List<String> testPages13 = Lists.newArrayList("Out Of Stock Cart View", "Out Of Stock Message Clicked");

        String caseName14 = "OutOfStockItemView";
        List<String> testPages14 = Lists.newArrayList("outOfStockItem", "Out Of Stock Message Clicked");
        String caseName15 = "Register";
        List<String> testPages15 = Lists.newArrayList("phone number filled");
        String caseName16 = "VisitorRegisterToMakeOrder";
        List<String> testPages16 = Lists.newArrayList("google or apple pay logo", "send verification code");

        Map<String, List<String>> casePicsMap = new HashMap<>();
        casePicsMap.put(caseName1, testPages1);
        casePicsMap.put(caseName2, testPages2);
        casePicsMap.put(caseName3, testPages3);
        casePicsMap.put(caseName4, testPages4);
        casePicsMap.put(caseName5, testPages5);
        casePicsMap.put(caseName6, testPages6);
        casePicsMap.put(caseName7, testPages7);
        casePicsMap.put(caseName8, testPages8);
        casePicsMap.put(caseName9, testPages9);
        casePicsMap.put(caseName10, testPages10);
        casePicsMap.put(caseName11, testPages11);
        casePicsMap.put(caseName12, testPages12);
        casePicsMap.put(caseName13, testPages13);
        casePicsMap.put(caseName14, testPages14);
        casePicsMap.put(caseName15, testPages15);
        casePicsMap.put(caseName16, testPages16);


        return casePicsMap;
    }

}
