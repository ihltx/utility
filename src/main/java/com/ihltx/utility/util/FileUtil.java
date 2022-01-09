package com.ihltx.utility.util;

import org.apache.logging.log4j.util.Strings;
import org.springframework.boot.system.ApplicationHome;

import java.io.*;
import java.nio.file.Files;

/**
 * FileUtil
 * File utility class
 * @author liulin 84611913@qq.com
 *
 */
public class FileUtil {


	/**
	 * Determine whether the current operating system is windows
	 * @return Boolean
	 * 		true	--	Windows
	 * 		false	--	not Windows
	 */
	public static Boolean isWindows(){
		return System.getProperties().getProperty("os.name").toUpperCase().contains("WINDOWS");
	}

	/**
	 * Create directory recursively
	 *
	 * @param directoryName 			Full path directory name
	 * @return Boolean
	 * 		true	--	success
	 * 		false	--	failure
	 */
	public static Boolean makeDirs(String directoryName) {
		if (Strings.isEmpty(directoryName))
			return false;
		File file = new File(directoryName);
		if(file.exists() && file.isDirectory()){
			return true;
		}
		return file.mkdirs();
	}

	/**
	 * Determine whether the file exists
	 * 
	 * @param fileName 				Full path file name
	 * @return Boolean
	 * 		true	--	exist
	 * 		false	--	not exist
	 */
	public static Boolean exists(String fileName) {
		if (Strings.isEmpty(fileName)){
			return false;
		}
		File file = new File(fileName);
		return file.exists();
	}

	/**
	 * Delete files or directories. Only empty directories can be deleted
	 *
	 * @param fileName 				The full path to the file or directory
	 * @return Boolean
	 * 		true	--	success
	 * 		false	--	failure
	 */
	public static Boolean deleteFile(String fileName) {
		if (exists(fileName)) {
			File file = new File(fileName);
			return file.delete();
		}
		return true;
	}

	/**
	 * Forced deletion of folder (the folder itself and all subordinate files and subfolders will be deleted)
	 *
	 * @param dirName				Full path directory name
	 * @return Boolean
	 * 		true	--	success
	 * 		false	--	failure
	 */
	public static Boolean deleteDir(String dirName) {
		Boolean rs = false;
		if (!Strings.isEmpty(dirName) && exists(dirName)) {
			File file = new File(dirName);
			if (file.isFile()) {
				rs = file.delete();
				System.gc();
				return rs;
			} else {
				File[] files = file.listFiles();
				if (null != files) {
					for (int i = 0; i < files.length; i++) {
						deleteDir(files[i].getPath());
					}
				}
				return file.delete();
			}
		} else {
			return true;
		}
	}

	/**
	 * Determine whether to file
	 *
	 * @param fileName 				Full path file name
	 * @return Boolean
	 * 		true	--	file
	 * 		false	--	not file
	 */
	public static Boolean isFile(String fileName) {
		if (Strings.isEmpty(fileName))
			return false;
		File file = new File(fileName);
		return file.isFile() && file.exists();
	}

	/**
	 * Determine whether the directory
	 *
	 * @param fileName				Full path file name
	 * @return Boolean
	 * 		true	--	directory
	 * 		false	--	not directory
	 */
	public static Boolean isDir(String fileName) {
		if (Strings.isEmpty(fileName))
			return false;
		File file = new File(fileName);
		return file.isDirectory() && file.exists();
	}


	/**
	 * Gets the parent path of the file name
	 *
	 * @param fileName				Full path file name
	 * @return String
	 * 		Return parent path
	 */
	public static String getFilePath(String fileName) {
		if (Strings.isEmpty(fileName))
			return "";
		File file = new File(fileName);
		return file.getParent();
	}

	/**
	 * Gets the base name of the file name
	 *
	 * @param fileName				Full path file name
	 * @return String
	 * 		Returns the base name of the file name
	 */
	public static String getFileBaseName(String fileName) {
		if (Strings.isEmpty(fileName))
			return "";
		File file = new File(fileName);
		return file.getName();
	}

	/**
	 * Gets the extension of the file
	 *
	 * @param fileName				Full path file name
	 * @return String
	 * 		Returns the extension of the file
	 */
	public static String getFileExtName(String fileName) {
		if (Strings.isEmpty(fileName))
			return "";
		int index = fileName.lastIndexOf(".");
		if (index == -1) {
			return "";
		}
		return fileName.substring(index + 1);
	}

	/**
	 * Writes / creates a text file with the specified character set
	 *
	 * @param fileName				Full path file name
	 * @param content               File content
	 * @param encoding              Character set, such as UTF-8
	 * @param isAppend              Is append write
	 * @return Boolean
	 * 		true	--	success
	 * 		false	--	failure
	 * @throws IOException
	 */
	public static boolean writeFile(String fileName, String content, String encoding, boolean isAppend) throws IOException {
		if (Strings.isEmpty(fileName)) {
			return false;
		}
		OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(fileName, isAppend), encoding);
		BufferedWriter bw = new BufferedWriter(osw);
		bw.write(content);
		bw.close();
		return true;
	}

	/**
	 * Write / create a text file with the specified UTF-8 character set and overwrite it if it exists
	 *
	 * @param fileName				Full path file name
	 * @param content				File content
	 * @return Boolean
	 * 		true	--	success
	 * 		false	--	failure
	 * @throws IOException
	 */
	public static boolean writeFile(String fileName, String content) throws IOException {
		return writeFile(fileName, content, StringUtil.UTF_8, false);
	}


	/**
	 * Append write the text file with the specified UTF-8 character set. If the file does not exist, it will be created
	 *
	 * @param fileName				Full path file name
	 * @param content				File content
	 * @return Boolean
	 * 		true	--	success
	 * 		false	--	failure
	 * @throws IOException
	 */
	public static Boolean appendFile(String fileName, String content) throws IOException {
		return writeFile(fileName, content, StringUtil.UTF_8, true);
	}


	/**
	 * Reads all the contents of the text file with the specified character set
	 *
	 * @param fileName				Full path file name
	 * @param encoding				Character set, such as UTF-8
	 * @return String
	 * 		Returns all text contents of the file
	 * 		null -- Indicates that the file does not exist
	 * @throws IOException
	 */
	public static String readFile(String fileName, String encoding) throws IOException {
		File file = new File(fileName);
		if (!file.isFile())
			return null;
		Long length = file.length();
		byte[] byteContents = new byte[length.intValue()];
		FileInputStream in = new FileInputStream(file);
		in.read(byteContents);
		in.close();
		return new String(byteContents, encoding);
	}

	/**
	 * Read the text file and read the file content based on the UTF-8 character set
	 *
	 * @param fileName				Full path file name
	 * @return String
	 * 		Returns all text contents of the file
	 * 		null -- Indicates that the file does not exist
	 * @throws IOException
	 */
	public static String readFile(String fileName) throws IOException {
		return readFile(fileName, StringUtil.UTF_8);
	}


	/**
	 * Modifying the file or folder name will fail if the source file does not exist or the target file already exists
	 *
	 * @param sourceName			Full name of source file
	 * @param targetName            Full name of the target file
	 * @return Boolean
	 * 		true	--	success
	 * 		false	--	failure
	 */
	public static Boolean rename(String sourceName, String targetName) {
		if (Strings.isEmpty(sourceName) || !exists(sourceName)) {
			return false;
		}
		if (Strings.isEmpty(targetName) || exists(targetName)) {
			return false;
		}
		File file = new File(sourceName);
		return file.renameTo(new File(targetName));
	}

	/**
	 * Move the source file or source folder to the target location and modify the name.
	 * If the source file does not exist or the target file already exists, it will fail
	 *
	 * @param sourceName			Full name of source file
	 * @param targetName			Full name of the target file
	 * @return Boolean
	 * 		true	--	success
	 * 		false	--	failure
	 */
	public static Boolean move(String sourceName, String targetName) {
		return rename(sourceName, targetName);
	}

	/**
	 * Copy the file to the target location and modify the name.
	 * If the source file does not exist, it will fail.
	 * If the target file already exists and is a folder, it will also fail
	 *
	 * @param sourceName			Full name of source file
	 * @param targetName			Full name of the target file
	 * @param isOverlay             Is overwrite target file
	 * @return Boolean
	 * 		true	--	success
	 * 		false	--	failure
	 * @throws IOException
	 */
	public static boolean copy(String sourceName, String targetName, Boolean isOverlay) throws IOException {
		if (Strings.isEmpty(sourceName) || !exists(sourceName) || !isFile(sourceName)) {
			return false;
		}
		File srcFile = new File(sourceName);
		File targetFile = new File(targetName);
		if (targetFile.exists()) {
			if (targetFile.isDirectory()) {
				return false;
			}
			if (isOverlay) {
				targetFile.delete();
			} else {
				return false;
			}
		}
		Files.copy(srcFile.toPath(), targetFile.toPath());
		return true;
	}

	/**
	 * Copy the file to the destination location and modify the name.
	 * If the source file does not exist, it will fail, and if the destination file exists, it will fail
	 *
	 * @param sourceName			Full name of source file
	 * @param targetName			Full name of the target file
	 * @return Boolean
	 * 		true	--	success
	 * 		false	--	failure
	 * @throws IOException
	 */
	public static boolean copy(String sourceName, String targetName) throws IOException {
		return copy(sourceName, targetName, false);
	}

	/**
	 * Replace text file content
	 *
	 * @param fileName				Full file name
	 * @param regex					The search content must be a regular expression
	 * @param replacement			Replace content
	 * @param charset				Character set, such as UTF-8
	 * @return String
	 * 		Returns the content after replacement
	 * 		null -- Indicates that the file does not exist
	 * @throws IOException
	 */
	public static String replace(String fileName, String regex, String replacement, String charset) throws IOException {
		if (Strings.isEmpty(charset)) {
			charset = StringUtil.UTF_8;
		}
		String content = readFile(fileName, charset);
		if (Strings.isEmpty(content)) {
			return null;
		}
		return content.replaceAll(regex, replacement);
	}

	/**
	 * Replace text file content based on UTF-8 character set
	 *
	 * @param fileName				Full file name
	 * @param regex					The search content must be a regular expression
	 * @param replacement			Replace content
	 * @return String
	 * 		Returns the content after replacement
	 * 		null -- Indicates that the file does not exist
	 * @throws IOException
	 */
	public static String replace(String fileName, String regex, String replacement) throws IOException {
		return replace(fileName, regex, replacement, StringUtil.UTF_8);
	}

	/**
	 * 获取当前运行jar文件所在文件夹
	 * @return
	 */
	public static String getJarDir(){
		ApplicationHome home = new ApplicationHome(FileUtil.class);
		return home.getDir().getAbsolutePath();
	}

}
