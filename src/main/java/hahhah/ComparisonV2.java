package hahhah;

import com.beust.jcommander.internal.Lists;
import com.github.romankh3.image.comparison.ImageComparison;
import com.github.romankh3.image.comparison.ImageComparisonUtil;
import com.github.romankh3.image.comparison.model.ImageComparisonResult;
import com.github.romankh3.image.comparison.model.ImageComparisonState;
import com.github.romankh3.image.comparison.model.Rectangle;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ComparisonV2 {
    public static List<Rectangle> excludeRectangles;
    public static String resultRoot;
    public static String resultImagePath;

    public static void main(String[] args) throws DocumentException, IOException {
        String testVersion = "1.49.0";
        String baseVersion = "1.48.0";
        String reportRoot = "/Users/michelle.chen/Desktop/";

        File testFolder = new File(reportRoot + testVersion);
        if (!testFolder.isDirectory()) {
            throw new IllegalArgumentException("test file is not a directory");
        }
        List<String> deviceNames = Arrays.stream(testFolder.listFiles()).map(file -> file.getName()).filter(fileName -> isDeviceName(fileName)).collect(Collectors.toList());
        Map<String, List<String>> casePicsMap = FileUtil.getCaseScreenshotsMap();
        String executeTime = ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        resultRoot = "result/" + executeTime + "/";
        resultImagePath = resultRoot + "image/";
        File imageFileDir = new File(resultImagePath);
        if (!imageFileDir.exists()) {
            imageFileDir.mkdirs();
        }

        for (String deviceName : deviceNames) {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(resultRoot + deviceName + ".pdf"));
            document.open();

            PdfPTable table = new PdfPTable(5);
            table.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.setWidthPercentage(100);
            PdfUtil.addTableHeader(table);
            //only when there's other element before, this can be worked,chunk is not treated as element
            table.setSpacingBefore(5);
            Font font = FontFactory.getFont(FontFactory.HELVETICA, 10, BaseColor.RED);
            document.add(new Paragraph(10, deviceName, font));

            if (casePicsMap.keySet().iterator().hasNext()) {
                String caseName = casePicsMap.keySet().iterator().next();
                String expectedPath = String.format(reportRoot + baseVersion + "/%s/allure_results/%s/%s", deviceName, deviceName, caseName);
                List<String> testPages = casePicsMap.get(caseName);
                setExcludeRectangles(expectedPath, testPages.get(0), deviceName);
            }
            for (String caseName : casePicsMap.keySet()) {
                List<String> testPages = casePicsMap.get(caseName);
                String expectedPath = String.format(reportRoot + baseVersion + "/%s/allure_results/%s/%s", deviceName, deviceName, caseName);
//            String baseDyPath = String.format(reportRoot + "/1.48.0/%s/base_screenshots", deviceName);
                String actualPath = String.format(reportRoot + "%s/%s/allure_results/%s/%s", testVersion, deviceName, deviceName, caseName);
                testPages.forEach(testPage -> {
                    PdfUtil.addTextCell(table, testPage);
                    compareFileAndSaveResult(expectedPath, actualPath, testPage, table);
                });
            }
            document.add(table);
            document.close();
        }
    }

    private static boolean isDeviceName(String fileName) {
//        return fileName.contains("IOS") || fileName.contains("Android") || fileName.contains("iPhone") || fileName.contains("Samsung") || fileName.contains("Google");
        return fileName.contains("iPhone") || fileName.contains("Samsung") || fileName.contains("Google");
    }

    private static void setExcludeRectangles(String filePath, String testPage, String deviceName) {
        try {
            excludeRectangles = Lists.newArrayList();
            String baseFilePath = FileUtil.getFileByName(filePath, testPage).getPath();
            BufferedImage expectedImage = ImageComparisonUtil.readImageFromResources(baseFilePath);
            if (deviceName.contains("Samsung")) {
                int buttonHeight = (int) (expectedImage.getHeight() * 0.05);
                Rectangle bottomRectangle = new Rectangle(0, expectedImage.getHeight() - buttonHeight, expectedImage.getWidth(), expectedImage.getHeight());
                excludeRectangles.add(bottomRectangle);
            }
            // TODO: 2021/9/29 if the page is restaurant_detail or other special image, add exclude path
            //w-width,h-height
            Rectangle rectangle = new Rectangle(0, 0, expectedImage.getWidth(), (int) (expectedImage.getHeight() * 0.045));
            excludeRectangles.add(rectangle);
        } catch (FileNotFoundException e) {
            System.out.println("Base file not found" + filePath);
        }
    }

    private static void compareFileAndSaveResult(String baseDirPath, String testDirPath, String testPage, PdfPTable table) {
        try {
            String baseFilePath = FileUtil.getFileByName(baseDirPath, testPage).getPath();
            //add row2-basePic;row3-testPic
            PdfUtil.addImageCell(table, baseFilePath);
            String testFilePath = FileUtil.getFileByName(testDirPath, testPage).getPath();
            PdfUtil.addImageCell(table, testFilePath);

            //file to save diff image
            File resultDestination = new File(resultImagePath + testPage + "result.jpg");

            //List<Rectangle> dynamicRectangles = getDynamicRectangles(baseDyPath);
            ImageComparisonResult imageComparisonResult = CompareImages(baseFilePath, testFilePath);

            //ImageComparisonResult imageComparisonResult = CompareImages(baseFilePath, testFilePath);
            float differencePercent = imageComparisonResult.getDifferencePercent();
            System.out.println("differencePercent = " + differencePercent);
            //将结果存储起来
            BufferedImage resultImage = imageComparisonResult.getResult();
            //resultDestination-the path to save image
            ImageComparisonUtil.saveImage(resultDestination, resultImage);

            PdfUtil.addImageCell(table, resultDestination.getAbsolutePath());
            PdfUtil.addDiffPercentCell(table, differencePercent);
        } catch (FileNotFoundException e) {
            if (e.getMessage().contains(baseDirPath)) {
                PdfUtil.addTextCell(table, "base file not found");
                PdfUtil.addTextCell(table, "base file not found");
                PdfUtil.addTextCell(table, "base file not found");
                PdfUtil.addTextCell(table, "base file not found");
            } else if (e.getMessage().contains(testDirPath)) {
                PdfUtil.addTextCell(table, "test file not found");
                PdfUtil.addTextCell(table, "test file not found");
                PdfUtil.addTextCell(table, "test file not found");
            }
            e.printStackTrace();
        }
    }

    private static ImageComparisonResult CompareImages(String expectedPath, String actualPath) {
        BufferedImage expectedImage = ImageComparisonUtil.readImageFromResources(expectedPath);
        BufferedImage actualImage = ImageComparisonUtil.readImageFromResources(actualPath);

        ImageComparisonResult imageComparisonResult;
        //setPixelToleranceLevel
        imageComparisonResult = new ImageComparison(expectedImage, actualImage).setRectangleLineWidth(4).setPixelToleranceLevel(0.05f).setExcludedAreas(excludeRectangles).setDrawExcludedRectangles(true).compareImages();
        if (imageComparisonResult.getImageComparisonState() == ImageComparisonState.SIZE_MISMATCH) {
            throw new IllegalArgumentException("The size is mismatched");
        }
        return imageComparisonResult;
    }

    private static List<Rectangle> getDynamicRectangles(String baseDirPath) throws FileNotFoundException {
        //Rectangle rectangle=new Rectangle(0,0,1,90);
        File commonBaseFile1 = FileUtil.getFileByName(baseDirPath, "base_screenshot1");
        File commonBaseFile2 = FileUtil.getFileByName(baseDirPath, "base_screenshot2");
        BufferedImage expectedImage = ImageComparisonUtil.readImageFromResources(commonBaseFile2.getPath());
        ImageComparisonResult baseImageComparisonResult = CompareImages(commonBaseFile1.getPath(), commonBaseFile2.getPath());
        return baseImageComparisonResult.getRectangles();
    }
}
