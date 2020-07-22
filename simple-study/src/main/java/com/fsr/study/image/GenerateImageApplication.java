package com.fsr.study.image;

import com.alibaba.fastjson.JSONObject;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.apache.http.HttpEntity;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.imageio.ImageIO;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by GenerateImageApplication
 *
 * @author Aokai.Fu
 * @version v1.0
 * @apiNote GenerateImageApplication
 * @create 2020/7/20 13:47
 */
public class GenerateImageApplication {

    private static final Logger logger = LoggerFactory.getLogger(GenerateImageApplication.class);

    private static final int QR_WIDTH = 400;
    private static final int QR_HEIGHT = 400;
    private static final int WORD_HEIGHT = 30;

    public static void main(String[] args) throws IOException {

//        String corpid = "ww0d42fc0f6e0c3c79";
//        String corpsecret = "U8Msrf2tm_4M6ajShqF8jhQ3q3psbUH5zyQzi8nWvCk";
//        Response response = Request.Get("https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=" + corpid + "&corpsecret=" + corpsecret).execute();
//        HttpEntity httpEntity = response.returnResponse().getEntity();
//        System.out.println(EntityUtils.toString(httpEntity));
//        {"errcode":0,"errmsg":"ok","access_token":"ppe-bbDK-KKjEl4MDpbSmEOyyw4qbbfA42D48dd5yH7JStNEdJ2v_cKrD7SoDsZ7gghuQNVJr2dNh1Ln4vJ2xsPiYfr7hFl5vBUKjTKRgumF81OwBeABnWenXwltIDokmiXTjKhKWW7t0djHR-KlURf_kJ4o4uhnzSfOR8buL6IxHwJpf72wtoIUk3f3pj9Bh7u4qPQHUJsSWurjapKZzA","expires_in":7200}
//        {"errcode":0,"errmsg":"ok","access_token":"X3CkRdYwM6zz_8iUuRS5GZzdrtgGY-cWC-MDi0_JzvYBo0pX2Jni7ffHUNpRkuu4-qnk7fQXQH0r7NAINFBVRKuuSZk1lOUDemrp0kfmPOkqv6-QMSQRkHdONDoHAgJnYjLQtFN-sywrIMzz2jiTwZORPpcfz5NUifg4ZllA3tT21RPI1oCC5XvLBiAhf6FpHN_7i45V9edMqj3nOGnJzQ","expires_in":7200}
        String token = "ppe-bbDK-KKjEl4MDpbSmEOyyw4qbbfA42D48dd5yH7JStNEdJ2v_cKrD7SoDsZ7gghuQNVJr2dNh1Ln4vJ2xsPiYfr7hFl5vBUKjTKRgumF81OwBeABnWenXwltIDokmiXTjKhKWW7t0djHR-KlURf_kJ4o4uhnzSfOR8buL6IxHwJpf72wtoIUk3f3pj9Bh7u4qPQHUJsSWurjapKZzA";
//
//
        Response response = Request.Get("https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token="+token+"&userid=13127506066").execute();
        HttpEntity httpEntity = response.returnResponse().getEntity();
        System.out.println(EntityUtils.toString(httpEntity));

//        createQRCode("d:\\bg.png", "d:\\logo.jpg", "d:\\qr.png", "张三", "兰蔻专柜·实习顾问 产品经理");
//        BufferedImage bim1 = createQRCode("http://www.baidu.com", "D:\\logo.jpg", "新图片嗷");
//        BufferedImage bim2 = createQRCode("http://www.baidu.com", "D:\\logo.jpg", null);
//        BufferedImage bim3 = createQRCode("http://www.baidu.com", null, null);
//        ImageIO.write(bim1, "png", new File("D:\\" + System.currentTimeMillis() + ".png"));
//        ImageIO.write(bim2, "png", new File("D:\\" + System.currentTimeMillis() + ".png"));
//        ImageIO.write(bim3, "png", new File("D:\\" + System.currentTimeMillis() + ".png"));
    }

    public static BufferedImage createQRCode(String url) {
        return createQRCode(url, null, null);
    }

    public static BufferedImage createQRCode(String url, String icon) {
        return createQRCode(url, icon, null);
    }

    public static BufferedImage createQRCode(String url, String icon, String text) {
        try {
            Map<EncodeHintType, Object> hints = getDecodeHintType();
            // 参数顺序分别为：编码内容，编码类型，生成图片宽度，生成图片高度，设置参数
            BitMatrix bm =  new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE, QR_WIDTH, QR_HEIGHT, hints);
            int width =  bm.getWidth();
            int height = bm.getHeight();

            BufferedImage qrCodeImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
            // 开始利用二维码数据创建Bitmap图片，分别设为黑（0xFFFFFFFF）白（0xFF000000）两色
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    qrCodeImage.setRGB(x, y, bm.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
                }
            }

            BufferedImage image = new BufferedImage(width, StringUtils.isEmpty(text) ? height : height + WORD_HEIGHT, BufferedImage.TYPE_INT_BGR);
            Graphics2D graph = image.createGraphics();
            graph.drawImage(qrCodeImage, 0, 0, qrCodeImage.getWidth(), qrCodeImage.getHeight(), null);

            if (icon != null) {
                BufferedImage logoImage = ImageIO.read(new File(icon));
                int logoWidth = logoImage.getWidth(null) > 60 ? 60 : logoImage.getWidth(null);
                int logoHeight = logoImage.getHeight(null) > 60 ? 60 : logoImage.getHeight(null);

                int x = (QR_WIDTH - logoWidth) / 2;
                int y = (QR_HEIGHT - logoHeight) / 2;
                graph.drawImage(logoImage, x, y, logoWidth, logoHeight, null);
            }

            if (text != null) {
                graph.setBackground(Color.WHITE);
                graph.drawRect(0, QR_HEIGHT, QR_WIDTH, WORD_HEIGHT);
                graph.fillRect(0, QR_HEIGHT, QR_WIDTH, WORD_HEIGHT);

                graph.setColor(Color.BLACK);
                graph.setFont(new Font("宋体", Font.BOLD, 30));
                int wordX = (image.getWidth() - graph.getFontMetrics().stringWidth(text)) / 2;
                graph.drawString(text, wordX, QR_HEIGHT + 8);

            }
            graph.dispose();
            return image;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 设置二维码的格式参数
     * @return
     */
    private static Map<EncodeHintType, Object> getDecodeHintType() {
        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.Q);
        hints.put(EncodeHintType.MARGIN, 1);
        return hints;
    }


    public static void createQRCode(String bgurl, String avatar, String qrcode, String name, String title) throws IOException {
        BufferedImage bgImage = getUrlImage(bgurl);
        if (bgImage == null) {
            throw new RuntimeException("背景图不存在!" + bgurl);
        }
        BufferedImage avatarImage = getUrlImage(avatar);
        BufferedImage qrcodeImage = getUrlImage(qrcode);

        int avatarWidth = 300;
        int avatarHeight = 300;
        int bgWidth = bgImage.getWidth();
        int bgHeight = bgImage.getHeight();
        int partHeight = bgHeight / 5;

        Graphics2D graphics = bgImage.createGraphics();
        if (avatarImage != null) {
            graphics.drawImage(createRoundImage(avatarImage), (bgWidth - avatarWidth) / 2, partHeight, avatarWidth, avatarHeight, null);
            graphics.dispose();
        }

        if (qrcodeImage != null) {
            int qrCodeWidth = 300;
            int qrCodeHeight = 300;
            graphics = bgImage.createGraphics();
            graphics.drawImage(qrcodeImage, (bgWidth - qrCodeWidth) / 2, partHeight * 2 + avatarHeight, qrCodeWidth, qrCodeHeight, null);
        }

        if (!StringUtils.isEmpty(name)) {
            graphics = bgImage.createGraphics();
            graphics.setFont(new Font("思源黑体 CN Medium", Font.ROMAN_BASELINE, 36));
            graphics.setColor(Color.BLACK);
            graphics.drawString(name, (bgWidth - graphics.getFontMetrics().stringWidth(name)) / 2, partHeight + avatarHeight + 100);
            graphics.dispose();
        }
        if (!StringUtils.isEmpty(title)) {
            graphics = bgImage.createGraphics();
            graphics.setFont(new Font("思源黑体 CN Medium", Font.ROMAN_BASELINE, 28));
            graphics.setColor(new Color(189, 39, 101));
            graphics.drawString(title, (bgWidth - graphics.getFontMetrics().stringWidth(title)) / 2, partHeight + avatarHeight + 170);
            graphics.dispose();
        }
        ImageIO.write(bgImage, "png", new File("D:\\" + System.currentTimeMillis() + ".png"));
    }

    public static BufferedImage createRoundImage(BufferedImage avatarImage) {
        int avatarWidth = 300;
        int avatarHeight = 300;
        int border = 3;

        BufferedImage roundImage = new BufferedImage(avatarWidth, avatarHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = roundImage.createGraphics();
        graphics.setComposite(AlphaComposite.Src);
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.fill(new Ellipse2D.Double(border, border, avatarWidth - border, avatarHeight - border));
        graphics.setComposite(AlphaComposite.SrcAtop);
        graphics.drawImage(avatarImage, border, border, avatarWidth - border, avatarHeight - border, null);
        graphics.dispose();

        graphics = roundImage.createGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics.setStroke(new BasicStroke(4.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        graphics.setColor(Color.WHITE);
        graphics.drawOval(border, border, avatarWidth - border, avatarHeight - border);
        graphics.dispose();
        return roundImage;
    }

    public static void createFont() {

    }

    private static BufferedImage getUrlImage(String url) {
        try {
//            HttpURLConnection avatarCon = (HttpURLConnection) new URL(url).openConnection();
//            avatarCon.setReadTimeout(35000);
//            avatarCon.setConnectTimeout(35000);
//            avatarCon.setRequestMethod("GET");
//            if (avatarCon.getResponseCode() == HttpURLConnection.HTTP_OK) {
//                InputStream inputStream = avatarCon.getInputStream();
//                return ImageIO.read(inputStream);
//            }
            return ImageIO.read(new File(url));
        } catch (IOException ex) {
            logger.error("获取远程图片内容失败, {}", url);
        }
        return null;
    }

//    public JSONObject getToken(String host, String corpid, String corpsecret) {
//        try {
//            logger.info("【获取企业微信token】url ->{}, 企业微信ID -->{}, 企业微信秘钥 -->{}", host, corpid, corpsecret);
//            corpid = "ww2fd36ee68e28f6fd";
//            corpsecret = "LSs78Cmb_C_rm9jOsWNHGc_qohrJcc55WMQLQsmbZtI";
//            Response response = Request.Get("https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=").execute();
//            HttpEntity httpEntity = response.returnResponse().getEntity();
//            System.out.println(EntityUtils.toString(httpEntity));
//
//            JSONObject jsonObject = restTemplate.getForObject(host + "/cgi-bin/gettoken?corpid=" + corpid + "&corpsecret=" + corpsecret, JSONObject.class);
//            boolean flag = null == jsonObject || !jsonObject.containsKey("errcode") || (jsonObject.containsKey("errcode") && jsonObject.getInteger("errcode") != 0);
//            if (flag) {
//                logger.error("【获取企业微信token】异常 response --> {}", jsonObject);
//                throw new RuntimeException(jsonObject.toJSONString());
//            }
//
//            return jsonObject;
//        } catch (Exception e) {
//            logger.error("【获取企业微信token】getToken error:", e);
//            throw new RuntimeException("getToken error:" + e.getMessage());
//        }
//    }


}
