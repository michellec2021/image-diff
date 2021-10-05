package hahhah;

import com.beust.jcommander.internal.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StandardScreenshot {
    public static void main(String[] args) {
        String rootPath = "/Users/michelle.chen/Desktop/1.48.0";
        String destinationPath = "/Users/michelle.chen/Desktop/standard-screenshots";
        String deviceName = "iPhone_XS_Max_14.7";
        loadAllStandardScreenshot(rootPath, destinationPath,deviceName);
    }

    public static void loadAllStandardScreenshot(String rootPath, String destinationPath, String deviceName) {
        Map<String, List<String>> caseScreenshotsMap = FileUtil.getCaseScreenshotsMap();
        for (String caseName : caseScreenshotsMap.keySet()) {

        }
    }


}
