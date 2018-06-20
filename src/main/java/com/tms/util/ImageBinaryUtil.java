/**
* Project Name：phoenix-core
* File Name：Image.java
* @version 1.0
* @since JDK 1.7
*/
package com.tms.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * Package Name:com.ylpw.core.util ClassName: Image Description：
 */
public class ImageBinaryUtil {
    static BASE64Encoder encoder = new sun.misc.BASE64Encoder();

    static BASE64Decoder decoder = new sun.misc.BASE64Decoder();

    public static void main(String[] args) {
        System.out.println(getImageBinary("C:\\Users\\Allen\\Pictures\\timg.jpg"));

        // base64StringToImage(getImageBinary());
    }

    /**
     * 将图片转换成二进制
     * 
     * @return
     */
    static String getImageBinary(String path) {
        File f = new File(path);
        BufferedImage bi;
        try {
            bi = ImageIO.read(f);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bi, "jpg", baos);
            byte[] bytes = baos.toByteArray();

            return encoder.encodeBuffer(bytes).trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将二进制转换为图片
     * 
     * @param base64String
     */
    static void base64StringToImage(String base64String, String path) {
        try {
            byte[] bytes1 = decoder.decodeBuffer(base64String);

            ByteArrayInputStream bais = new ByteArrayInputStream(bytes1);
            BufferedImage bi1 = ImageIO.read(bais);
            File w2 = new File(path);// 可以是jpg,png,gif格式
            ImageIO.write(bi1, "jpg", w2);// 不管输出什么格式图片，此处不需改动
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
