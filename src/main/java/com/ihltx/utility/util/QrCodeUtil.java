package com.ihltx.utility.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
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

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * QrCodeUtil
 * QrCode utility class
 * @author liulin 84611913@qq.com
 *
 */
@SuppressWarnings("all")
public class QrCodeUtil {

	/**
	 * Picture format definition
	 * 
	 * @value Array
	 */
	private static String[] IMAGE_TYPE = { "BMP", "bmp", "jpg", "JPG", "wbmp", "jpeg", "png", "PNG", "JPEG", "WBMP",
			"GIF", "gif", "ICON", "icon" };

	/**
	 * QR code width
	 */
	public static final int WIDTH = 260;

	/**
	 * QR code height
	 */
	public static final int HEIGHT = 260;

	/**
	 * Icon width
	 */
	private static final int IMAGE_WIDTH = 68;
	/**
	 * Icon height
	 */
	private static final int IMAGE_HEIGHT = 68;
	/**
	 * Basemap size [square]
	 */
	private static final int IMAGE_HALF_WIDTH = IMAGE_WIDTH / 2;
	/**
	 * Basemap border
	 */
	private static final int FRAME_WIDTH = 5;

	/**
	 * QR code writer
	 */
	private static MultiFormatWriter mutiWriter = new MultiFormatWriter();


	/**
	 * Generate QR code picture(PNG) without middle Icon
	 *
	 * @param content            	QR code text content, usually URL
	 * @param width             	The width of the generated QR code picture, in pixels
	 * @param height				The height of the generated QR code picture, in pixels
	 * @param outputStream     	    out QR code to OutputStream. Fox example: response.getOutputStream()
	 * @throws WriterException
	 * @throws IOException
	 */
	public static void generate(String content, int width, int height, OutputStream outputStream) throws WriterException, IOException {
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix bitMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, width, height);
		MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);
	}

	/**
	 * Generate QR code picture(PNG) without middle Icon
	 * 
	 * @param content            	QR code text content, usually URL
	 * @param width             	The width of the generated QR code picture, in pixels
	 * @param height				The height of the generated QR code picture, in pixels
	 * @param qrcodeImagePath     	The full path of the generated QR code image file. If the file exists, it will be overwritten
	 * @return String
	 * 		Generated successfully, return the generated QR code picture file name
	 * 		null -- Generated failed
	 * @throws WriterException
	 * @throws IOException
	 */
	public static String generate(String content, int width, int height, String qrcodeImagePath) throws WriterException, IOException {
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix bitMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, width, height);
		Path path = FileSystems.getDefault().getPath(qrcodeImagePath);
		MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
		return qrcodeImagePath;

	}


	/**
	 * Generate QR code(PNG) with Icon
	 *
	 * @param content            	QR code text content, usually URL
	 * @param width             	The width of the generated QR code picture, in pixels
	 * @param height				The height of the generated QR code picture, in pixels
	 * @param iconImagePath        	Icon original path
	 * @param outputStream     	    out QR code to OutputStream. Fox example: response.getOutputStream()
	 * @param hasFiller          	Whether to fill in white space when the proportion is incorrect: true means fill in white space; False means no padding
	 * @return
	 */
	public static void generate(String content, int width, int height, String iconImagePath, OutputStream outputStream,
								  boolean hasFiller) throws IOException, WriterException {
		File icon = new File(iconImagePath);
		if (!icon.exists()) {
			throw new WriterException("Icon file (" + iconImagePath + ") not found!");
		}
		String iconFileName = icon.getName();
		String fileExtName = FileUtil.getFileExtName(iconImagePath);
		if (!checkIMGType(fileExtName)) {
			throw new WriterException("Icon file format error!");
		}

		if (width < QrCodeUtil.WIDTH || height < QrCodeUtil.HEIGHT) {
			width = QrCodeUtil.WIDTH;
			height = QrCodeUtil.HEIGHT;
		}
		ImageIO.write(genBarcode(content, width, height, iconImagePath, hasFiller), "PNG",
				outputStream);
	}

	/**
	 * Generate QR code(PNG) with Icon
	 *
	 * @param content            	QR code text content, usually URL
	 * @param width             	The width of the generated QR code picture, in pixels
	 * @param height				The height of the generated QR code picture, in pixels
	 * @param iconImagePath        	Icon original path
	 * @param qrcodeImagePath     	The full path of the generated QR code image file. If the file exists, it will be overwritten
	 * @param hasFiller          	Whether to fill in white space when the proportion is incorrect: true means fill in white space; False means no padding
	 * @return String
	 * 		Generated successfully, return the generated QR code picture file name
	 * 		null -- Generated failed
	 */
	public static String generate(String content, int width, int height, String iconImagePath, String qrcodeImagePath,
			boolean hasFiller) throws IOException, WriterException {
		File icon = new File(iconImagePath);
		if (!icon.exists()) {
			throw new WriterException("Icon file (" + iconImagePath + ") not found!");
		}
		String iconFileName = icon.getName();
		String fileExtName = FileUtil.getFileExtName(iconImagePath);
		if (!checkIMGType(fileExtName)) {
			throw new WriterException("Icon file format error!");
		}

		if (width < QrCodeUtil.WIDTH || height < QrCodeUtil.HEIGHT) {
			width = QrCodeUtil.WIDTH;
			height = QrCodeUtil.HEIGHT;
		}
		ImageIO.write(genBarcode(content, width, height, iconImagePath, hasFiller), "PNG",
				new File(qrcodeImagePath));

		return qrcodeImagePath;
	}

	/**
	 * Image processing method
	 *
	 * @param content            	QR code text content, usually URL
	 * @param width             	The width of the generated QR code picture, in pixels
	 * @param height				The height of the generated QR code picture, in pixels
	 * @param iconImagePath        	Icon original path
	 * @param hasFiller          	Whether to fill in white space when the proportion is incorrect: true means fill in white space; False means no padding
	 * @return	BufferedImage
	 *
	 * @throws WriterException
	 * @throws IOException
	 */
	private static BufferedImage genBarcode(String content, int width, int height, String iconImagePath,
			boolean hasFiller) throws WriterException, IOException {
		BufferedImage scaleImage = scale(iconImagePath, IMAGE_WIDTH, IMAGE_HEIGHT, hasFiller);
		int[][] srcPixels = new int[IMAGE_WIDTH][IMAGE_HEIGHT];
		for (int i = 0; i < scaleImage.getWidth(); i++) {
			for (int j = 0; j < scaleImage.getHeight(); j++) {
				srcPixels[i][j] = scaleImage.getRGB(i, j);
			}
		}

		Map<EncodeHintType, Object> hint = new HashMap<EncodeHintType, Object>();
		hint.put(EncodeHintType.CHARACTER_SET, StringUtil.UTF_8);
		hint.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		BitMatrix matrix = mutiWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hint);

		int halfW = matrix.getWidth() / 2;
		int halfH = matrix.getHeight() / 2;
		int[] pixels = new int[width * height];

		for (int y = 0; y < matrix.getHeight(); y++) {
			for (int x = 0; x < matrix.getWidth(); x++) {
				if (x > halfW - IMAGE_HALF_WIDTH && x < halfW + IMAGE_HALF_WIDTH && y > halfH - IMAGE_HALF_WIDTH
						&& y < halfH + IMAGE_HALF_WIDTH) {
					pixels[y * width + x] = srcPixels[x - halfW + IMAGE_HALF_WIDTH][y - halfH + IMAGE_HALF_WIDTH];
				}
				else if ((x > halfW - IMAGE_HALF_WIDTH - FRAME_WIDTH && x < halfW - IMAGE_HALF_WIDTH + FRAME_WIDTH
						&& y > halfH - IMAGE_HALF_WIDTH - FRAME_WIDTH && y < halfH + IMAGE_HALF_WIDTH + FRAME_WIDTH)
						|| (x > halfW + IMAGE_HALF_WIDTH - FRAME_WIDTH && x < halfW + IMAGE_HALF_WIDTH + FRAME_WIDTH
								&& y > halfH - IMAGE_HALF_WIDTH - FRAME_WIDTH
								&& y < halfH + IMAGE_HALF_WIDTH + FRAME_WIDTH)
						|| (x > halfW - IMAGE_HALF_WIDTH - FRAME_WIDTH && x < halfW + IMAGE_HALF_WIDTH + FRAME_WIDTH
								&& y > halfH - IMAGE_HALF_WIDTH - FRAME_WIDTH
								&& y < halfH - IMAGE_HALF_WIDTH + FRAME_WIDTH)
						|| (x > halfW - IMAGE_HALF_WIDTH - FRAME_WIDTH && x < halfW + IMAGE_HALF_WIDTH + FRAME_WIDTH
								&& y > halfH + IMAGE_HALF_WIDTH - FRAME_WIDTH
								&& y < halfH + IMAGE_HALF_WIDTH + FRAME_WIDTH)) {
					pixels[y * width + x] = 0xfffffff;
				} else {
					pixels[y * width + x] = matrix.get(x, y) ? 0xff000000 : 0xfffffff;
				}
			}
		}
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		image.getRaster().setDataElements(0, 0, width, height, pixels);
		return image;
	}

	/**
	 * The incoming original image is scaled by height and width to generate an icon that meets the requirements
	 * 
	 * @param iconImagePath     	Small icon source file address
	 * @param height                Target height
	 * @param width					Target width
	 * @param hasFiller          	Whether to fill in white space when the proportion is incorrect: true means fill in white space; False means no padding
	 * @return BufferedImage
	 * @throws IOException
	 */
	private static BufferedImage scale(String iconImagePath, int height, int width, boolean hasFiller)
			throws IOException {
		double ratio = 0.0;
		File file = new File(iconImagePath);
		BufferedImage srcImage = ImageIO.read(file);
		Image destImage = srcImage.getScaledInstance(width, height, BufferedImage.SCALE_SMOOTH);
		if ((srcImage.getHeight() > height) || (srcImage.getWidth() > width)) {
			if (srcImage.getHeight() > srcImage.getWidth()) {
				ratio = (height *1.0) / srcImage.getHeight();
			} else {
				ratio = (width * 1.0) / srcImage.getWidth();
			}
			AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(ratio, ratio), null);
			destImage = op.filter(srcImage, null);
		}
		if (hasFiller) {
			BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics2D graphic = image.createGraphics();
			graphic.setColor(Color.white);
			graphic.fillRect(0, 0, width, height);
			if (width == destImage.getWidth(null))
				graphic.drawImage(destImage, 0, (height - destImage.getHeight(null)) / 2, destImage.getWidth(null),
						destImage.getHeight(null), Color.white, null);
			else
				graphic.drawImage(destImage, (width - destImage.getWidth(null)) / 2, 0, destImage.getWidth(null),
						destImage.getHeight(null), Color.white, null);
			graphic.dispose();
			destImage = image;
		}
		return (BufferedImage) destImage;
	}

	/**
	 * Picture format verification
	 * 
	 * @param fileExtName      		File extension
	 * @return Boolean
	 * 		true	--	OK
	 * 		false	--	error
	 */
	private static Boolean checkIMGType(String fileExtName) {
		Boolean flag = false;
		for (String type : IMAGE_TYPE) {
			if (type.toLowerCase().equals(fileExtName.toLowerCase())) {
				flag = true;
				break;
			}
		}
		return flag;
	}

}
