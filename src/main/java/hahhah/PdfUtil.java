package hahhah;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.stream.Stream;

public final class PdfUtil {
    public static void addTableHeader(PdfPTable table) {
        try {
            table.setTotalWidth(new float[]{50, 200, 200, 200, 70});
            Stream.of("Page", "Standard Screenshot", "Test Screenshot", "Diff Result", "Diff%")
                    .forEach(columnTitle ->
                    {
                        PdfPCell header = new PdfPCell();
                        header.setBackgroundColor(BaseColor.WHITE);

                        Font font = FontFactory.getFont(FontFactory.HELVETICA, 10, BaseColor.BLACK);
                        Phrase elements = new Phrase(columnTitle, font);
                        header.setPhrase(elements);
                        header.setHorizontalAlignment(Element.ALIGN_CENTER);
                        header.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                        table.addCell(header);
                    });
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    // TODO: 2021/9/29 有点别扭
    public static void addImageCell(PdfPTable table, String imagePath) {
        try {
            Path path = Paths.get(getFileURI(imagePath));
            Image img = Image.getInstance(path.toAbsolutePath().toString());
            img.scalePercent(10);

            PdfPCell imageCell = new PdfPCell(img);
            imageCell.setPaddingTop(1);
            table.addCell(imageCell);
        } catch (BadElementException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void addTextCell(PdfPTable table, String text) {
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 6, BaseColor.BLACK);
        PdfPCell row = new PdfPCell(new Phrase(text, font));
        row.setHorizontalAlignment(Element.ALIGN_CENTER);
        row.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(row);
    }

    public static void addDiffPercentCell(PdfPTable table, float diffPercent) {
        DecimalFormat percentFormat = new DecimalFormat("0.000%");
        String format = percentFormat.format(diffPercent * 0.01);

        Font font = FontFactory.getFont(FontFactory.HELVETICA, 10, BaseColor.BLACK);
        PdfPCell row = new PdfPCell(new Phrase(format, font));
        row.setBackgroundColor(diffPercent == 0 ? BaseColor.GREEN : BaseColor.RED);
        row.setHorizontalAlignment(Element.ALIGN_CENTER);
        row.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(row);
    }

    private static URI getFileURI(String path) {
        File file = new File(path);
        return file.toURI();
    }

     /*  public static void main(String[] args) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("result" + ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + ".pdf"));
        document.open();
        //insertTable(document);
        document.close();
    }

    public static void insertTable(Document document) throws DocumentException {
        PdfPTable table = new PdfPTable(5);
        //设置表格在页面中占的宽度
        table.setWidthPercentage(100);
        addTableHeader(table);
        addRows(table);
        document.add(table);
    }*/

/*    public static void addRows(PdfPTable table) {
        addTextCell(table, "RDP");
        addImageCell(table, "/Users/michelle.chen/Desktop/basePics/RDP.png");
        addImageCell(table, "/Users/michelle.chen/Desktop/testPics/RDP.png");
        addImageCell(table, "/Users/michelle.chen/Documents/code/my-practice/result.jpg");
        //第二行的第二列和第三列也要设置，不然图片插入不进去
        addTextCell(table, "row4");

        addTextCell(table, "RLP");
        addImageCell(table, "/Users/michelle.chen/Desktop/basePics/RDP.png");
        addImageCell(table, "/Users/michelle.chen/Desktop/testPics/RDP.png");
        addImageCell(table, "/Users/michelle.chen/Documents/code/my-practice/result.jpg");
        //第二行的第二列和第三列也要设置，不然图片插入不进去
        addTextCell(table, "row4");
        addTextCell(table, "row5");
    }*/
}
