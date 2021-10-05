package hahhah;

import com.beust.jcommander.internal.Lists;
import com.github.romankh3.image.comparison.ImageComparison;
import com.github.romankh3.image.comparison.ImageComparisonUtil;
import com.github.romankh3.image.comparison.model.ImageComparisonResult;
import com.github.romankh3.image.comparison.model.ImageComparisonState;
import com.github.romankh3.image.comparison.model.Rectangle;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Comparison {

    public static void main(String[] args) throws DocumentException, IOException {
        String expectedPath="/Users/michelle.chen/Desktop/basePics";
        String actualPath="/Users/michelle.chen/Desktop/testPics";
        List<String> testPages = Lists.newArrayList("RDP.png");

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("result" + ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + ".pdf"));
        document.open();
        document.add(generateDiffTable(expectedPath, actualPath, testPages));
        document.close();
    }

    private static PdfPTable generateDiffTable(String expectedPath, String actualPath, List<String> testPages) {
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);
        PdfUtil.addTableHeader(table);

        testPages.forEach(testPage -> {
            PdfUtil.addTextCell(table, testPage);
            compareFileAndSaveResult(expectedPath, actualPath, testPage, table);
        });

        return table;
    }

    private static void compareFileAndSaveResult(String baseDirPath, String testDirPath, String testPage, PdfPTable table) {
        String baseFilePath = getFileByName(baseDirPath, testPage).getPath();
        String testFilePath = getFileByName(testDirPath, testPage).getPath();
        //add row2-basePic;row3-testPic
        PdfUtil.addImageCell(table, baseFilePath);
        PdfUtil.addImageCell(table, testFilePath);

        List<Rectangle> dynamicRectangles = getDynamicRectangles(baseDirPath);
        //file to save diff image
        File resultDestination = new File(testPage + "result.jpg");
        ImageComparisonResult imageComparisonResult = CompareImages(baseFilePath, testFilePath, dynamicRectangles);
        float differencePercent = imageComparisonResult.getDifferencePercent();
        System.out.println("differencePercent = " + differencePercent);
        //将结果存储起来
        BufferedImage resultImage = imageComparisonResult.getResult();
        //resultDestination-the path to save image
        ImageComparisonUtil.saveImage(resultDestination, resultImage);

        PdfUtil.addImageCell(table, resultDestination.getAbsolutePath());
        PdfUtil.addDiffPercentCell(table, differencePercent);
    }

    private static File getFileByName(String dirPath, String pageName) {
        File rootFile = new File(dirPath);
        if (!rootFile.isDirectory()) {
            throw new IllegalArgumentException("root file is not directory");
        }
        List<File> matchedFiles = Arrays.stream(rootFile.listFiles()).filter(file -> file.getName().equals(pageName) || file.getName().equals(pageName)).collect(Collectors.toList());
        if (matchedFiles.size() == 0) {
            throw new IllegalArgumentException("file with name " + pageName + " is not found");
        }
        if (matchedFiles.size() > 1) {
            throw new IllegalArgumentException("more than 1 files are found by name" + pageName);
        }
        return matchedFiles.get(0);
    }

    private static List<Rectangle> getDynamicRectangles(String baseDirPath) {
        File commonPicFolder = getFileByName(baseDirPath, "commonBasePics");
        if (!commonPicFolder.isDirectory()) {
            throw new IllegalArgumentException("commonPic is not folder");
        }
        File commonBaseFile1 = Arrays.stream(commonPicFolder.listFiles()).filter(file -> file.getName().equals("common_base_1.png")).collect(Collectors.toList()).get(0);
        File commonBaseFile2 = Arrays.stream(commonPicFolder.listFiles()).filter(file -> file.getName().equals("common_base_2.png")).collect(Collectors.toList()).get(0);
        ImageComparisonResult baseImageComparisonResult = CompareImages(commonBaseFile1.getPath(), commonBaseFile2.getPath());
        return baseImageComparisonResult.getRectangles();
    }

    // TODO: 2021/9/18 这个代码是不是可以抽象接口编程的形式？
    private static ImageComparisonResult CompareImages(String expectedPath, String actualPath, List<Rectangle> excludeRectangles) {
        BufferedImage expectedImage = ImageComparisonUtil.readImageFromResources(expectedPath);
        BufferedImage actualImage = ImageComparisonUtil.readImageFromResources(actualPath);
        ImageComparisonResult imageComparisonResult;
        imageComparisonResult = new ImageComparison(expectedImage, actualImage).setExcludedAreas(excludeRectangles).compareImages();
        if (imageComparisonResult.getImageComparisonState() == ImageComparisonState.SIZE_MISMATCH) {
            throw new IllegalArgumentException("The size is mismatched");
        }
        return imageComparisonResult;
    }

    private static ImageComparisonResult CompareImages(String expectedPath, String actualPath) {
        BufferedImage expectedImage = ImageComparisonUtil.readImageFromResources(expectedPath);
        BufferedImage actualImage = ImageComparisonUtil.readImageFromResources(actualPath);
        ImageComparisonResult imageComparisonResult;
        imageComparisonResult = new ImageComparison(expectedImage, actualImage).compareImages();
        if (imageComparisonResult.getImageComparisonState() == ImageComparisonState.SIZE_MISMATCH) {
            throw new IllegalArgumentException("The size is mismatched");
        }
        return imageComparisonResult;
    }
}
