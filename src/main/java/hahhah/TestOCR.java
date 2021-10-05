package hahhah;

import net.sourceforge.tess4j.ITessAPI;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import com.recognition.software.jdeskew.ImageDeskew;
import net.sourceforge.tess4j.ITessAPI.TessPageIteratorLevel;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.ITesseract.RenderedFormat;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.Word;
import net.sourceforge.tess4j.util.ImageHelper;
import net.sourceforge.tess4j.util.LoggHelper;
import net.sourceforge.tess4j.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestOCR {
    public static void main(String[] args) {
        testDoOCR_ImageByte();
    }

    public static void testDoOCR_ImageByte() {
        try {
            File file=new File("/Users/michelle.chen/Documents/RDP1.png");
            BufferedImage img = ImageIO.read(file);
            ITesseract instance = new Tesseract();
            //设置语言库所在的文件夹位置，最好是绝对的，不然加载不到就直接报错了
            instance.setDatapath("/Users/michelle.chen/Documents/RDP1.png");
            //设置使用的语言库类型：chi_sim 中文简体
            //instance.setLanguage("chi_sim");
            String result = instance.doOCR(img);
            System.out.println("result = " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testGetSegmentedRegions() throws Exception {
        Tesseract instance = new Tesseract();
//        logger.info("getSegmentedRegions at given TessPageIteratorLevel");
        File imageFile = new File("testResourcesDataPath", "ocr.png");
        BufferedImage bi = ImageIO.read(imageFile);
        int level = ITessAPI.TessPageIteratorLevel.RIL_TEXTLINE;
        //logger.info("PageIteratorLevel: " + Utils.getConstantName(level, TessPageIteratorLevel.class));
        List<Rectangle> result = instance.getSegmentedRegions(bi, level);
//        instance.doOCR();
        for (int i = 0; i < result.size(); i++) {
            Rectangle rect = result.get(i);
//            logger.info(String.format("Box[%d]: x=%d, y=%d, w=%d, h=%d", i, rect.x, rect.y, rect.width, rect.height));
        }
    }
}
