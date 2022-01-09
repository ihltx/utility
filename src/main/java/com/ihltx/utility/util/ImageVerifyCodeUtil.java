package com.ihltx.utility.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * ImageVerifyCodeUtil
 * Image verify code utility class
 * @author liulin 84611913@qq.com
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageVerifyCodeUtil {

    /**
     * current verify code  value
     */
    private String verifyCode;

    /**
     * verify code image backgroundImageFileName, if null not use
     */
    private String backgroundImageFileName;

    public static final Integer LOWERLETTER =0;
    public static final Integer UPPERLETTER =1;
    public static final Integer NUMBER =2;
    public static final Integer SYMBOLS =3;
    public static final Integer LOWERLETTER_UPPERLETTER =4;
    public static final Integer LOWERLETTER_NUMBER =5;
    public static final Integer LOWERLETTER_SYMBOLS =6;
    public static final Integer UPPERLETTER_NUMBER =7;
    public static final Integer UPPERLETTER_SYMBOLS =8;
    public static final Integer NUMBER_SYMBOLS =9;
    public static final Integer LOWERLETTER_UPPERLETTER_NUMBER =10;
    public static final Integer LOWERLETTER_UPPERLETTER_SYMBOLS =11;
    public static final Integer LOWERLETTER_NUMBER_SYMBOLS =12;
    public static final Integer UPPERLETTER_NUMBER_SYMBOLS =13;
    public static final Integer LOWERLETTER_UPPERLETTER_NUMBER_SYMBOLS =14;

    /**
     * VerifyCodeType
     */
    private Integer verifyCodeType = UPPERLETTER_NUMBER;

    /**
     * verifyCodeKey
     */
    private String verifyCodeKey = "DefaultTypeKey";

    /**
     *  verifyCode Image width, unit: px
     */
    private Integer width = 95;

    /**
     *  verifyCode Image height, unit: px
     */
    private Integer height = 25;

    /**
     *  disturb line number
     */
    private Integer disturbLineNumber = 40;

    /**
     * rand verify code length
     */
    private Integer verifyCodeLength = 4;

    /**
     * font size
     */
    private Integer fontSize = 18;

    /**
     * font name
     */
    private String fontName = "Fixedsys";

    /**
     * font style
     */
    private Integer fontStyle = Font.CENTER_BASELINE;

    /**
     * Specify verify code expires , unit: seconds.
     */
    private Integer verifyCodeExpires = 60;

    /**
     * Get random verify code by verifyCodeType
     * @return String
     */
    public String getRandVerifyCode(){
        String randVerifyCode = null;
        if(this.verifyCodeType == LOWERLETTER){
            randVerifyCode = StringUtil.getRandomString(this.verifyCodeLength,true,false,false,false);
        }else if(this.verifyCodeType == UPPERLETTER){
            randVerifyCode = StringUtil.getRandomString(this.verifyCodeLength,false,true,false,false);
        }else if(this.verifyCodeType == NUMBER){
            randVerifyCode = StringUtil.getRandomString(this.verifyCodeLength,false,false,true,false);
        }else if(this.verifyCodeType == SYMBOLS){
            randVerifyCode = StringUtil.getRandomString(this.verifyCodeLength,false,false,false,true);
        }else if(this.verifyCodeType == LOWERLETTER_UPPERLETTER){
            randVerifyCode = StringUtil.getRandomString(this.verifyCodeLength,true,true,false,false);
        }else if(this.verifyCodeType == LOWERLETTER_NUMBER){
            randVerifyCode = StringUtil.getRandomString(this.verifyCodeLength,true,false,true,false);
        }else if(this.verifyCodeType == LOWERLETTER_SYMBOLS){
            randVerifyCode = StringUtil.getRandomString(this.verifyCodeLength,true,false,false,true);
        }else if(this.verifyCodeType == UPPERLETTER_NUMBER){
            randVerifyCode = StringUtil.getRandomString(this.verifyCodeLength,false,true,true,false);
        }else if(this.verifyCodeType == UPPERLETTER_SYMBOLS){
            randVerifyCode = StringUtil.getRandomString(this.verifyCodeLength,false,true,false,true);
        }else if(this.verifyCodeType == NUMBER_SYMBOLS){
            randVerifyCode = StringUtil.getRandomString(this.verifyCodeLength,false,false,true,true);
        }else if(this.verifyCodeType == LOWERLETTER_UPPERLETTER_NUMBER){
            randVerifyCode = StringUtil.getRandomString(this.verifyCodeLength,true,true,true,false);
        }else if(this.verifyCodeType == LOWERLETTER_UPPERLETTER_SYMBOLS){
            randVerifyCode = StringUtil.getRandomString(this.verifyCodeLength,true,true,false,true);
        }else if(this.verifyCodeType == LOWERLETTER_NUMBER_SYMBOLS){
            randVerifyCode = StringUtil.getRandomString(this.verifyCodeLength,true,false,true,true);
        }else if(this.verifyCodeType == UPPERLETTER_NUMBER_SYMBOLS){
            randVerifyCode = StringUtil.getRandomString(this.verifyCodeLength,false,true,true,true);
        }else if(this.verifyCodeType == LOWERLETTER_UPPERLETTER_NUMBER_SYMBOLS){
            randVerifyCode = StringUtil.getRandomString(this.verifyCodeLength,true,true,true,true);
        }else{
            randVerifyCode = StringUtil.getRandomString(this.verifyCodeLength,true,true,true,false);
        }
        return  randVerifyCode;
    }


    private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc - 16);
        int g = fc + random.nextInt(bc - fc - 14);
        int b = fc + random.nextInt(bc - fc - 18);
        return new Color(r, g, b);
    }

    /**
     * save VerifyCode
     * @param verifyCode
     * @return Boolean
     *      true    --  success
     *      false   --  failure
     */
    private Boolean saveVerifyCode(String verifyCode,HttpServletRequest request){
        Boolean rs = false;
        //HTTP_SESSION
        if(request!=null){
            HttpSession session = request.getSession();
            if(session!=null){
                session.setAttribute(verifyCodeKey , verifyCode);
                session.setAttribute(verifyCodeKey + "__expires" , DateUtil.getTime() + this.verifyCodeExpires);
                rs = true;
            }
        }
        return  rs;
    }

    /**
     * get Save VerifyCode
     * @param request
     * @return String
     */
    public String getSaveVerifyCode(HttpServletRequest request){
        if(request!=null){
            HttpSession session = request.getSession();
            if(session!=null){
                Long time = session.getAttribute(verifyCodeKey + "__expires")!=null?Long.valueOf(session.getAttribute(verifyCodeKey + "__expires").toString()):0L;
                if(DateUtil.getTime()>time){
                    session.removeAttribute(verifyCodeKey);
                    return null;
                }
                return session.getAttribute(verifyCodeKey).toString();
            }
        }
        return  null;
    }

    /**
     * Generate verify code image to HttpResponse
     * @throws IOException
     */
    /**
     * Generate verify code image to OutputStream
     * @param request           HttpServletRequest
     * @param response              HttpServletResponse
     * @param outputStream          OutputStream
     * @throws IOException
     */
    public void outputImage2OutputStream(OutputStream outputStream,HttpServletRequest request , HttpServletResponse response) throws IOException {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        Graphics g = image.getGraphics();
        g.fillRect(0, 0, width, height);
        if(FileUtil.isFile(this.backgroundImageFileName)){
            Image bgImage = ImageIO.read(new File(this.backgroundImageFileName));
            g.drawImage(bgImage, 0, 0, bgImage.getWidth(null),bgImage.getHeight(null),null);
        }
        g.setColor(getRandColor(110, 133));
        for (int i = 0; i <= disturbLineNumber; i++) {
            drawDisturbLine(g);
        }
        this.verifyCode = getRandVerifyCode();
        for (int i = 0; i < this.verifyCode.length(); i++) {
            drawString(g, this.verifyCode, i);
        }
        saveVerifyCode(this.verifyCode,request);
        g.dispose();
        if(outputStream!=null){
            ImageIO.write(image, "JPEG", outputStream);
        }
    }

    /**
     * Generate verify code image to HttpResponse
     * @param request           HttpServletRequest
     * @param response              HttpServletResponse
     * @throws IOException
     */
    public void outputImage2Response(HttpServletRequest request , HttpServletResponse response) throws IOException {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        Graphics g = image.getGraphics();
        g.fillRect(0, 0, width, height);
        if(FileUtil.isFile(this.backgroundImageFileName)){
            Image bgImage = ImageIO.read(new File(this.backgroundImageFileName));
            g.drawImage(bgImage, 0, 0, bgImage.getWidth(null),bgImage.getHeight(null),null);
        }
        g.setColor(getRandColor(110, 133));
        for (int i = 0; i <= disturbLineNumber; i++) {
            drawDisturbLine(g);
        }
        this.verifyCode = getRandVerifyCode();
        for (int i = 0; i < this.verifyCode.length(); i++) {
            drawString(g, this.verifyCode, i);
        }
        saveVerifyCode(this.verifyCode,request);
        g.dispose();
        if(response!=null){
            ImageIO.write(image, "JPEG", response.getOutputStream());
        }
    }


    /**
     * Draw the specified characters of randomString to graphics of verify code image
     */
    private void drawString(Graphics g, String randomString, int i) {
        Random random = new Random();
        g.setFont(new Font(fontName, fontStyle, fontSize));
        FontMetrics metrics = g.getFontMetrics();
        int strWidth = metrics.stringWidth(randomString);
        int charWidth = metrics.stringWidth(randomString)/randomString.length();
        int strHeight = metrics.getHeight();
        int top = (this.height-strHeight)/2+metrics.getAscent();
        int left =  (this.width-strWidth)/2;

        g.setColor(new Color(random.nextInt(101), random.nextInt(111), random
                .nextInt(121)));
        String rand = String.valueOf(randomString.charAt(i));
        g.translate(random.nextInt(3), random.nextInt(3));
        g.drawString(rand, left + i * charWidth , top);
    }
 
    /**
     * draw disturb line on graphics of verify code image
     */
    private void drawDisturbLine(Graphics g) {
        Random random = new Random();
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        int xl = random.nextInt(23);
        int yl = random.nextInt(25);
        g.drawLine(x, y, x + xl, y + yl);
    }

}
