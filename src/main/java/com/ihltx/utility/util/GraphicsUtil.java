package com.ihltx.utility.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * GraphicsUtil
 * Graphics utility class
 * @author liulin 84611913@qq.com
 *
 */
@SuppressWarnings("all")
public class GraphicsUtil {

	/**
	 *  生成图片到输出流，允许指定图片背景，将在图片指定位置输出指定字体、颜色的文字
	 * @param width							宽度
	 * @param height						高度
	 * @param backgroundColor			    背景颜色
	 * @param backgroundImageFileName		图片背景文件全路径名，如果为null，表示没有背景图
	 * @param font							文字字体
	 * @param color							文字颜色
	 * @param message						文字信息
	 * @param x								x坐标
	 * @param y								y坐标
	 * @param outputStream					输出流
	 * @throws IOException
	 */
	public static void generateImage(int width,int  height, Color backgroundColor ,String backgroundImageFileName , Font font , Color color , String message , int x ,int y, OutputStream outputStream) throws IOException {
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
		Graphics g = image.getGraphics();
		g.setColor(backgroundColor);
		g.fillRect(0, 0, width, height);
		if(FileUtil.isFile(backgroundImageFileName)){
			Image bgImage = ImageIO.read(new File(backgroundImageFileName));
			g.drawImage(bgImage, 0, 0, bgImage.getWidth(null),bgImage.getHeight(null),null);
		}
		g.setFont(font);
		g.setColor(color);
		g.drawString(message, x , y);
		g.dispose();
		if(outputStream!=null){
			ImageIO.write(image, "JPEG",outputStream);
		}
	}

}
