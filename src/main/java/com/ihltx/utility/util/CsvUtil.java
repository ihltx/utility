package com.ihltx.utility.util;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

/**
 * CsvUtil
 * Csv utility class
 * @author liulin 84611913@qq.com
 *
 */
public class CsvUtil {
	
	
	/**
     * Read the data of each row  from csv file (the header is included by default, the split character is comma and the character set is GBK)
     *
     * @param csvFileName               CSV full path file name
     * @return List<List<String>>
     *      Returns the list of data in the CSV file
	 * @throws IOException
     */
    public static List<List<String>> readCSV(String csvFileName) throws IOException {
    	return readCSV(csvFileName,true,',',Charset.forName(StringUtil.GBK));
    }
	
	/**
     * Read the data of each row from csv file
     *
     * @param csvFileName               CSV full path file name
     * @param hasHeader                 is include header
     * @param splitChar                 Split character
     * @param charset                   File character set
     * @return List<List<String>>
     *      Returns the list of data in the CSV file
     * @throws IOException
     */
    public static List<List<String>> readCSV(String csvFileName,Boolean hasHeader,char splitChar,Charset charset) throws IOException {
        List<List<String>> listData = new ArrayList<List<String>>();
        CsvReader csvReader = new CsvReader(csvFileName,splitChar ,charset);
        if(hasHeader) {
        	if(csvReader.readHeaders()) {
        		List<String> header = new ArrayList<String>();
        		for(String h : csvReader.getHeaders()) {
        			header.add(h);
        		}
        		listData.add(header);
        	}
        }
        
        while (csvReader.readRecord()) {
            String rawRecord = csvReader.getRawRecord();
            String[] arrs = rawRecord.split(String.valueOf(splitChar));
            List<String> row = new ArrayList<String>();
            for(String s : arrs) {
            	row.add(s);	
            }
            listData.add(row);
        }
        return listData;

    }

    /**
     * Read the data of each row from the reader (the header is included by default, and the split character is comma)
     *
     * @param reader                    Reader对象
     * @return List<List<String>>
     *      Returns the list of data in the CSV file
     * @throws IOException
     */
    public static List<List<String>> readCSVByReader(Reader reader) throws IOException {
    	return readCSVByReader(reader, true, ',');
    }
    
    /**
     *  Read the data of each row from the reader
     *
     * @param reader                    Reader对象
     * @param hasHeader                 is include header
     * @param splitChar                 Split character
     * @return List<List<String>>
     *      Returns the list of data in the CSV file
     * @throws IOException
     */
    public static List<List<String>> readCSVByReader(Reader reader,Boolean hasHeader,char splitChar) throws IOException {
        List<List<String>> listData = new ArrayList<List<String>>();
        CsvReader csvReader = new CsvReader(reader,splitChar);
        // 读表头
        if(hasHeader) {
        	if(csvReader.readHeaders()) {
        		List<String> header = new ArrayList<String>();
        		for(String h : csvReader.getHeaders()) {
        			header.add(h);
        		}
        		listData.add(header);
        	}
        }
        
        while (csvReader.readRecord()) {
            String rawRecord = csvReader.getRawRecord();
            String[] arrs = rawRecord.split(String.valueOf(splitChar));
            List<String> row = new ArrayList<String>();
            for(String s : arrs) {
            	row.add(s);	
            }
            listData.add(row);
        }
        return listData;

    }
	
    

    /**
     * Write to CSV file (including header by default, with comma split character and GBK character set)
     * @param csvFileName               CSV full path file name
     * @param datas                     Write datas , if there is a header, it must appear in line 1
     * @throws IOException
     */
    public static void writeCSV(String csvFileName, List<List<String>> datas) throws IOException {
    	writeCSV(csvFileName,datas,true,',' , Charset.forName(StringUtil.GBK));
    }
    
    /**
     * Write to CSV file
     * @param csvFileName               CSV full path file name
     * @param datas                     Write datas , if there is a header, it must appear in line 1
     * @param hasHeader                 is include header
     * @param splitChar                 Split character
     * @param charset                   File character set
     * @throws IOException
     */
    public static void writeCSV(String csvFileName, List<List<String>> datas,Boolean hasHeader,char splitChar,Charset charset) throws IOException {
        CsvWriter csvWriter = new CsvWriter(csvFileName, splitChar, charset);
        if(hasHeader) {
            List<String> header = datas.get(0);

            csvWriter.writeRecord((String[])header.toArray(new String[0]));
        }
        for(int i=1;i<datas.size();i++) {
            List<String> data = datas.get(i);
            csvWriter.writeRecord((String[])data.toArray(new String[0]));
        }

        csvWriter.close();
    }

}
