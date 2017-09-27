package com.qunar.lvshichang.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@Slf4j
public class PDFBoxTest {

    private static String path = "C:\\Users\\lvshi\\Desktop\\QFC-公共组件v2.pdf";

//    @Test
//    public void pdfBoxTest() throws IOException {
//
//        PDDocument document = PDDocument.load(new FileInputStream(path));
//        PDPageTree pages = document.getDocumentCatalog().getPages();
//
//        PDFRenderer pdfRenderer = new PDFRenderer(document);
//
//        BufferedImage bufferedImage = pdfRenderer.renderImage(1, 1, ImageType.RGB);
//        ImageIO.write(bufferedImage, "jpg", new File("one.jpg"));
//
//    }

    @Test
    public void pdfBoxTestOld() throws IOException {
        final PDDocument document = PDDocument.load(new FileInputStream(path));
        final List<PDPage> pages = document.getDocumentCatalog().getAllPages();

        // 取出代表封面的页面
        final PDPage keyFramePage = pages.get(0);
        // 将PDF页面转换为图片
        final BufferedImage image = keyFramePage.convertToImage(BufferedImage.TYPE_INT_RGB, 100);

        ImageIO.write(image, "jpg", new File("two.jpg"));
    }

}
