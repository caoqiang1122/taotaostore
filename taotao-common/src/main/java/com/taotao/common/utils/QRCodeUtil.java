package com.taotao.common.utils;

import com.google.zxing.*;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.OutputStream;
import java.util.Hashtable;

/**
 * @Title TODO
 * @Description: TODO
 * @Author caoqiang
 * @Date 2019/8/29 11:25
 * @Version 1.0
 */
public class QRCodeUtil {

    private static Logger logger  = LoggerFactory.getLogger(QRCodeUtil.class);

    private static final String CHARSET = "utf-8";
    private static final String FORMAT_NAME = "JPG";
    //二维码尺寸
    private static final int QRCODE_SIZE = 300;
    //LOGO宽度
    private static final int WIDTH = 60;
    //LOGO高度
    private static final int HEIGHT = 60;

    private static BufferedImage createImage(String content, String imgPath, boolean needCompress) throws Exception{
        Hashtable<EncodeHintType,Object> hints = new Hashtable();
        hints.put(EncodeHintType.ERROR_CORRECTION,ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET,CHARSET);
        hints.put(EncodeHintType.MARGIN,1);

        BitMatrix bitMatrix = new MultiFormatWriter().encode(content,BarcodeFormat.QR_CODE,QRCODE_SIZE,QRCODE_SIZE,hints);
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        for (int x = 0;x < width;x++){
            for (int y = 0;y < height;y++){
                image.setRGB(x,y,bitMatrix.get(x,y)?0xFF000000 : 0xFFFFFFFF);
            }
        }
        if (imgPath == null || "".equals(imgPath)){
            return image;
        }
        //插入图片
        QRCodeUtil.insertImage(image,imgPath,needCompress);
        return image;
    }

    private static void insertImage(BufferedImage source, String imagePath, boolean needCompress) throws Exception{
        File file = new File(imagePath);
        if (!file.exists()){
            logger.error("文件路径：{}，该文件不存在！",imagePath);
        }
        Image src = ImageIO.read(new File(imagePath));
        int width = src.getWidth(null);
        int height = src.getHeight(null);
        //压缩logo
        if (needCompress){
            if (width > WIDTH){
                width = WIDTH;
            }
            if (height > HEIGHT){
                height = HEIGHT;
            }
            Image image = src.getScaledInstance(width,height,Image.SCALE_SMOOTH);
            BufferedImage tag = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
            Graphics g = tag.getGraphics();
            //绘制缩小后的图
            g.drawImage(image,0,0,null);
            g.dispose();
            src = image;
        }
        //插入LOGO
        Graphics2D graphics2D = source.createGraphics();
        int x = (QRCODE_SIZE - width) / 2;
        int y = (QRCODE_SIZE - height) / 2;
        graphics2D.drawImage(src,x,y,width,height,null);
        Shape shape = new RoundRectangle2D.Float(x,y,width,width,6,6);
        graphics2D.setStroke(new BasicStroke(3f));
        graphics2D.draw(shape);
        graphics2D.dispose();
    }

    public static void encode(String content,String imgPath,String descPath,boolean needCompress) throws Exception{
        BufferedImage image = QRCodeUtil.createImage(content,imgPath,needCompress);
        mkdirs(descPath);
        ImageIO.write(image,FORMAT_NAME,new File(descPath));
    }

    public static BufferedImage encode(String content,String imgPath,boolean needCompress) throws Exception{
        BufferedImage image = QRCodeUtil.createImage(content,imgPath,needCompress);
        return image;
    }

    public static void mkdirs(String descPath){
        File file = new File(descPath);
        if (!file.exists() && !file.isDirectory()){
            file.mkdirs();
        }
    }

    public static void encode(String content,String imgPath,String descPath) throws Exception{
        QRCodeUtil.encode(content, imgPath, descPath,false);
    }

    public static void encode(String content, String destPath) throws Exception {
        QRCodeUtil.encode(content, null, destPath, false);
    }

    public static void encode(String content, String imgPath, OutputStream output, boolean needCompress) throws Exception{
        BufferedImage image = QRCodeUtil.createImage(content, imgPath, needCompress);
        ImageIO.write(image, FORMAT_NAME, output);
    }

    public static void encode(String content, OutputStream output) throws Exception {
        QRCodeUtil.encode(content, null, output, false);
    }

    public static String decode(File file) throws Exception {
        BufferedImage image;
        image = ImageIO.read(file);
        if (image == null) {
            return null;
        }
        BufferImageLuminanceSource source = new BufferImageLuminanceSource(image);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        Result result;
        Hashtable hints = new Hashtable();
        hints.put(DecodeHintType.CHARACTER_SET, CHARSET);
        result = new MultiFormatReader().decode(bitmap, hints);
        String resultStr = result.getText();
        return resultStr;
    }

    public static String decode(String path) throws Exception {
        return QRCodeUtil.decode(new File(path));
    }

    public static void main(String[] args) throws  Exception {
        //存放在二维码中的内容
        String text = "https://blog.csdn.net/jam_fanatic/article/details/82818857";
        //嵌入二维码的图片路径
        String imgPath = "F:/project/photo/cq1.jpg";
        //生成二维码的路径及名称
        String descPath = "F:/project/photo/code.jpg";
        //生成二维码
        QRCodeUtil.encode(text,imgPath,descPath,true);
        //解析二维码
        String str = QRCodeUtil.decode(descPath);
        System.out.println(str);
    }
}
