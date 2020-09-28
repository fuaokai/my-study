//package com.fsr.study.image;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.fluent.Request;
//import org.apache.http.client.fluent.Response;
//import org.apache.http.entity.ContentType;
//import org.apache.http.entity.mime.MultipartEntityBuilder;
//import org.apache.http.util.EntityUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.util.StringUtils;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.imageio.ImageIO;
//import java.awt.*;
//import java.awt.geom.Ellipse2D;
//import java.awt.image.BufferedImage;
//import java.io.*;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.Random;
//import java.util.UUID;
//import java.util.concurrent.TimeUnit;
//import java.util.concurrent.locks.Lock;
//
///**
// * Created by EnterpriseWechatTest
// *
// * @author Aokai.Fu
// * @version v1.0
// * @apiNote EnterpriseWechatTest
// * @create 2020/7/21 18:04
// */
//@Service
//public class EnterpriseWechatTest {
//
//    private static final Logger logger = LoggerFactory.getLogger(EnterpriseWechatTest.class);
//
//    @Value("${qywechat.qywechatUrl}")
//    private String qywechatUrl;
//
//    @Value("${qywechat.corpid}")
//    private String corpid;
//
//    @Value("${qywechat.corpsecret}")
//    private String corpsecret;
//
//    @Value("${qywechat.bgurl}")
//    private String bgurl;
//
//    public EnterpriseWechatTest() {
//        this.qywechatUrl = "https://qyapi.weixin.qq.com";
//        this.corpid = "ww0d42fc0f6e0c3c79";
//        this.corpsecret = "U8Msrf2tm_4M6ajShqF8jhQ3q3psbUH5zyQzi8nWvCk";
//        this.bgurl = "https://res-wxec-unipt.lorealchina.com/test/20200722/ba47e4ef-ad7d-4819-9d64-fa345d00ebd7.png";
//
//    }
//
//    private volatile String accessToken;
//
//    public static void main(String[] args) throws IOException {
//        new EnterpriseWechatTest().getQRImage("giv", "13127506066");
//    }
//
//    public String getQRImage(String storeCode, String employeeId) throws IOException {
////        logger.info("获取{}的qrimage", employeeId);
////        JSONObject employeeInfo = getEmployeeInfo(storeCode, employeeId);
////
////        String avatar = employeeInfo.getString("avatar");
////        String qrcode = employeeInfo.getString("qr_code");
////        String name   = employeeInfo.getString("name");
////        String title  = employeeInfo.getString("external_position");
////        title = StringUtils.isEmpty(title) ? employeeInfo.getString("position") : title;
////        title = StringUtils.isEmpty(title) ? "美容顾问" : title;
////
////        BufferedImage image = ImageUtil.createQRCode(bgurl, avatar, qrcode, name, title);
////        ImageIO.write(image, "png", new FileOutputStream("d:\\"+System.currentTimeMillis()+".png"));
////        InputStream is = new FileInputStream("d:\\1595475264913.png");
////        byte[] bs = new byte[is.available()];
////
////        is.read(bs);
////        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
////        outputStream.write(bs);
////        storeCode = "lrp";
////        String fileUploadUrl = "https://wxec-unipt-api.lorealchina.com/api/loreal-manage/store/" + storeCode + "/upload_file";
//
////        String fileUploadUrl = "https://wxec-unipt-api-dev.lorealchina.com/client/api/storage/upload/xxx";
////        HttpEntity httpEntity = MultipartEntityBuilder.create().addBinaryBody("file", bs, ContentType.MULTIPART_FORM_DATA, UUID.randomUUID().toString()+".png").build();
//////        String authorization = "Bearer eyJhbGciOiJSUzI1NiJ9.eyJqdGkiOiJDOUZYRUFTNjhuVDZxZDNDZFIzdTRSIiwic3ViIjoienp2Y21uMllHNEY2dGpSOGtGWWNkIiwiZXhwIjoxNTk1NDgzMDg0LCJ0ZW5hbnRJZCI6ImxvcmVhbCIsImJ1Q29kZSI6ImxycCIsImNyZWRlbnRpYWxUeXBlIjoiTUlOSV9QUk9HUkFNX09QRU5JRCIsImNyZWRlbnRpYWxTdHJpbmciOiJvUVVxZjR0S2l3NERKUVRELWNyMm5idlhRTUFrIiwiaXNNZW1iZXIiOmZhbHNlLCJ1bmlvbklkIjoib2EzeDR4R0FLUWkxRHlVRzUyVGU1UkkyYURSSSJ9.NDu2ZzWGF0htgW70tMFMhYfITno-UrbyyruGj3Tm-CtecnZCCu6_TvzVCZ1h950dJMUoWr943S9x5WudzEhIyA";
////        String authorization = "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1OTU1MTkzMjksInVzZXJfbmFtZSI6ImFkbWluMDIiLCJhdXRob3JpdGllcyI6WyIxIl0sImp0aSI6IjkzZGNhMjNkLTczNTktNDM4Ni1hOTJjLWU2MTMwMDA0NDFjNyIsImNsaWVudF9pZCI6IndlYl9hcHAiLCJzY29wZSI6WyJvcGVuaWQiXX0.EyaRVvXE9yzPO3jv2nlT5JgSCaNTXxHx3kt26meOwfZxxxF1w1vU3cfmhlkpzpQPjj6Cwt2a9TaBOUWfwQI8YOIgm9p5Da0xnflbscYJPfVnGEYSGQCgR8aBa_AJBov6xVstmaGanXxm0uw55wg1dyGjp0eK_ZF_R6LHN-l59EdgvU0tQmSFKbilV9pZXklVCSPG0a-X0ecn2afCmfXPrE-Nmkj-22I07salQvtkb82USOvknaCs6xW6uMOOB0u3DNhH9GtNGIOrkC11YkR27ZUMzOI5n0gA8gB8PYAkjFRhmt5nvSFH8RXJu6D1HapJ0wVOXT4tiOf76nDXf5xJBA";
////        Request request = Request.Post(fileUploadUrl).addHeader("Authorization", authorization).body(httpEntity);
////        HttpResponse uploadResponse = request.execute().returnResponse();
////        System.out.println(EntityUtils.toString(uploadResponse.getEntity()));
//
//        return null;
//    }
//
//    public JSONObject getEmployeeInfo(String storeCode, String employeeId) throws IOException {
//        String accessToken = getAccessToken(storeCode);
//        Response response =  Request.Get(qywechatUrl + "/cgi-bin/user/get?access_token=" + accessToken + "&userid=" + employeeId).execute();
//        JSONObject resultToken = JSON.parseObject(EntityUtils.toString(response.returnResponse().getEntity()), JSONObject.class);
//
//        return resultToken;
//    }
//
//    private String getAccessToken(String storeCode) throws IOException {
//        if (accessToken == null) {
//            JSONObject resultToken = getAccessTokenForWechat();
//            accessToken = resultToken.getString("access_token");
//        }
//        return accessToken;
//
//    }
//
//    private JSONObject getAccessTokenForWechat() throws IOException {
//        Response response =  Request.Get(qywechatUrl + "/cgi-bin/gettoken?corpid=" + corpid + "&corpsecret=" + corpsecret).execute();
//        JSONObject resultToken = JSON.parseObject(EntityUtils.toString(response.returnResponse().getEntity()), JSONObject.class);
//        return resultToken;
//    }
//
//    static class ImageUtil{
//        public static BufferedImage createQRCode(String bgurl, String avatar, String qrcode, String name, String title) throws IOException {
//            BufferedImage bgImage = getUrlImage(bgurl);
//            if (bgImage == null) {
//                throw new RuntimeException("背景图不存在!" + bgurl);
//            }
//            BufferedImage avatarImage = getUrlImage(avatar);
//            BufferedImage qrcodeImage = getUrlImage(qrcode);
//
//            int avatarWidth = 300;
//            int avatarHeight = 300;
//            int bgWidth = bgImage.getWidth();
//            int bgHeight = bgImage.getHeight();
//            int partHeight = bgHeight / 5;
//
//            Graphics2D graphics = bgImage.createGraphics();
//            if (avatarImage != null) {
//                graphics.drawImage(createRoundImage(avatarImage), (bgWidth - avatarWidth) / 2, partHeight, avatarWidth, avatarHeight, null);
//                graphics.dispose();
//            }
//
//            if (qrcodeImage != null) {
//                int qrCodeWidth = 300;
//                int qrCodeHeight = 300;
//                graphics = bgImage.createGraphics();
//                graphics.drawImage(qrcodeImage, (bgWidth - qrCodeWidth) / 2, partHeight * 2 + avatarHeight, qrCodeWidth, qrCodeHeight, null);
//            }
//
//            if (!StringUtils.isEmpty(name)) {
//                graphics = bgImage.createGraphics();
//                graphics.setFont(new Font("思源黑体 CN Medium", Font.ROMAN_BASELINE, 36));
//                graphics.setColor(Color.BLACK);
//                graphics.drawString(name, (bgWidth - graphics.getFontMetrics().stringWidth(name)) / 2, partHeight + avatarHeight + 100);
//                graphics.dispose();
//            }
//            if (!StringUtils.isEmpty(title)) {
//                graphics = bgImage.createGraphics();
//                graphics.setFont(new Font("思源黑体 CN Medium", Font.ROMAN_BASELINE, 28));
//                graphics.setColor(new Color(189, 39, 101));
//                graphics.drawString(title, (bgWidth - graphics.getFontMetrics().stringWidth(title)) / 2, partHeight + avatarHeight + 170);
//                graphics.dispose();
//            }
//            return bgImage;
//        }
//
//        private static BufferedImage createRoundImage(BufferedImage avatarImage) {
//            int avatarWidth = 300;
//            int avatarHeight = 300;
//            int border = 3;
//
//            BufferedImage roundImage = new BufferedImage(avatarWidth, avatarHeight, BufferedImage.TYPE_INT_ARGB);
//            Graphics2D graphics = roundImage.createGraphics();
//            graphics.setComposite(AlphaComposite.Src);
//            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//            graphics.fill(new Ellipse2D.Double(border, border, avatarWidth - border, avatarHeight - border));
//            graphics.setComposite(AlphaComposite.SrcAtop);
//            graphics.drawImage(avatarImage, border, border, avatarWidth - border, avatarHeight - border, null);
//            graphics.dispose();
//
//            graphics = roundImage.createGraphics();
//            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//            graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
//            graphics.setStroke(new BasicStroke(4.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
//            graphics.setColor(Color.WHITE);
//            graphics.drawOval(border, border, avatarWidth - border, avatarHeight - border);
//            graphics.dispose();
//            return roundImage;
//        }
//
//        private static BufferedImage getUrlImage(String url) {
//            try {
//                HttpURLConnection avatarCon = (HttpURLConnection) new URL(url).openConnection();
//                avatarCon.setReadTimeout(35000);
//                avatarCon.setConnectTimeout(35000);
//                avatarCon.setRequestMethod("GET");
//                if (avatarCon.getResponseCode() == HttpURLConnection.HTTP_OK) {
//                    InputStream inputStream = avatarCon.getInputStream();
//                    return ImageIO.read(inputStream);
//                }
//            } catch (IOException ex) {
//                logger.error("获取远程图片内容失败, {}", url);
//            }
//            return null;
//        }
//    }
//}
