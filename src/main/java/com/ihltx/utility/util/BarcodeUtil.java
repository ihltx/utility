package com.ihltx.utility.util;

import java.awt.image.BufferedImage;
import java.io.*;

import org.krysalis.barcode4j.BarcodeException;
import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;

/**
 * BarcodeUtil
 * Barcode utility class
 * @author liulin 84611913@qq.com
 *
 */
@SuppressWarnings("all")
public class BarcodeUtil {

	/**
	 * Generate barcode to file
	 * Generation failed. Delete the files in the process of generation
	 *
	 * @param barCode				Barcode text content must be an integer
	 * @param path					Full path of generated barcode file
	 * @return File
	 * 		Returns the generated barcode file object
	 * 		null -- generated failed
	 * @throws IOException
	 * @throws BarcodeException
	 */
	public static File generate(String barCode, String path) throws IOException, BarcodeException {
		File file = new File(path);
		if(generate(barCode, new FileOutputStream(file))) {
			return file;
		}else {
			file.delete();
			return null;
		}
	}

	/**
	 * Generate barcode to byte array
	 *
	 * @param barCode				Barcode text content must be an integer
	 * @return byte[]
	 * 		Returns the generated byte array
	 * 		null -- generated failed
	 * @throws IOException
	 * @throws BarcodeException
	 */
	public static byte[] generate(String barCode) throws IOException, BarcodeException {
		ByteArrayOutputStream ous = new ByteArrayOutputStream();
		if(generate(barCode, ous)) {
			return ous.toByteArray();
		}else {
			return null;
		}
	}

	/**
	 * Generate barcode to output stream
	 *
	 * @param barCode				Barcode text content must be an integer
	 * @param ous					Receive output stream of barcode content
	 * @return Boolean
	 * 		true	--	success
	 * 		false	--	failure
	 * @throws IOException
	 * @throws BarcodeException
	 */
	public static Boolean generate(String barCode, OutputStream ous) throws IOException, BarcodeException {
		if (!StringUtil.isInteger(barCode)) {
			throw new BarcodeException("barCode must be an integer");
		}
		Code39Bean bean = new Code39Bean();
		final int dpi = 150;
		final double moduleWidth = UnitConv.in2mm(1.0f / dpi);
		bean.setModuleWidth(moduleWidth);
		bean.setWideFactor(3);
		bean.doQuietZone(false);
		String format = "image/png";
		BitmapCanvasProvider canvas = new BitmapCanvasProvider(ous, format, dpi, BufferedImage.TYPE_BYTE_BINARY,
				false, 0);
		bean.generateBarcode(canvas, barCode);
		canvas.finish();
		return true;
	}

}