package com.taotao.common.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.apache.commons.io.FileUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 二维码生成工具类
 * Created by zhangyu3.
 */
public class QRCodeUtils {
    public static final int width = 300;

    public static final int height = 300;

    /**
     * qr code generate
     *
     * @param url     url
     * @param logoImg byte array of logo image
     * @return byte array of qr code image
     */
    public static BufferedImage qrCode(String url, byte[] logoImg) {
        try {
            BufferedImage image = generate(url, logoImg);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ImageIO.write(image, "png", out);
            return image;
        } catch (Exception e) {
            throw new RuntimeException("Error: generate qr code.", e);
        }
    }

    /**
     * @param url    　　　链接地址
     * @param logImg 　二维码图标img
     * @return result
     * @throws WriterException
     * @throws IOException
     */
    private static BufferedImage generate(String url, byte[] logImg) throws WriterException, IOException {
        if (logImg != null && logImg.length > 0) {
            ByteArrayInputStream in = new ByteArrayInputStream(logImg);
            BufferedImage logo = ImageIO.read(in);
            return generateImg(url, logo);
        } else {
            return generateImg(url, null);
        }
    }

    /**
     * @param url    链接地址
     * @param logImg 二维码图标img
     * @return result
     * @throws WriterException
     */
    private static BufferedImage generateImg(String url, BufferedImage logImg) throws WriterException, IOException {
        BitMatrix bitMatrix;
        Map<EncodeHintType, Object> hints = new HashMap<>();

        if (logImg != null) {
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            bitMatrix = new MultiFormatWriter().encode(url,BarcodeFormat.QR_CODE, width, height, hints);
            int logoSize = (int) (width / (4.0 + 20 / (url.length() + 10))); // into 4~6
            int start = (width - logoSize) / 2;
            bitMatrix.setRegion(start, start, logoSize, logoSize);// logo region
            BufferedImage qrCode = MatrixToImageWriter.toBufferedImage(bitMatrix);

            //combine qr code and logo
            BufferedImage combine = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics g = combine.getGraphics();
            g.drawImage(qrCode, 0, 0, null);
            genBarcode(combine,bitMatrix,logoSize);
            g.drawImage(resizeImg(logImg, logoSize, logoSize), start, start, null);
            return combine;
        } else {
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            bitMatrix = new MultiFormatWriter().encode(url,BarcodeFormat.QR_CODE, width, height, hints);
            return genBarcode(MatrixToImageWriter.toBufferedImage(bitMatrix),bitMatrix,0) ;
        }
    }


    private static BufferedImage genBarcode( BufferedImage image,BitMatrix bitMatrix,int logoSize) throws WriterException,
            IOException {
        // 二维矩阵转为一维像素数组
        int[] pixels = new int[width * height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // 左上角颜色,根据自己需要调整颜色范围和颜色
                if (x > 0 && x < 92 && y > 0 && y < 92) {
                    Color color = new Color(231, 144, 56);
                    int colorInt = color.getRGB();
                    pixels[y * width + x] = bitMatrix.get(x, y) ? colorInt
                            : 16777215;
                }
            else {
                    // 二维码颜色（RGB）
                    int num1 = (int) (50 - (50.0 - 13.0) / height
                            * (y + 1));
                    int num2 = (int) (165 - (165.0 - 72.0) / height
                            * (y + 1));
                    int num3 = (int) (162 - (162.0 - 107.0)
                            / height * (y + 1));
                    Color color = new Color(num1, num2, num3);
                    int colorInt = color.getRGB();
                    // 此处可以修改二维码的颜色，可以分别制定二维码和背景的颜色；
                    pixels[y * width + x] = bitMatrix.get(x, y) ? colorInt
                            : 16777215;// 0x000000:0xffffff
                }
            }
        }
        image.getRaster().setDataElements(0, 0, width, height, pixels);
        return image;
    }

    /**
     * resize image
     *
     * @param logoImg image BufferedImage
     * @param width   resize width
     * @param height  resize height
     * @return resized image
     */
    private static BufferedImage resizeImg(BufferedImage logoImg, int width, int height) {
        BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        tag.getGraphics().drawImage(logoImg.getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING),
                0, 0, null);
        return tag;
    }

    public static void main(String[] args) throws Exception {
        //存放在二维码中的内容
        String text = "https://blog.csdn.net/jam_fanatic/article/details/82818857";
        //嵌入二维码的图片路径
        String imgPath = "F:/project/photo/cq1.jpg";
        //生成二维码的路径及名称
        String descPath = "F:/project/photo/code.jpg";
        //生成二维码
        BufferedImage image = QRCodeUtils.qrCode(text,FileUtils.readFileToByteArray(new File(imgPath)));
        ImageIO.write(image,"jpg",new File(descPath));
    }

}
