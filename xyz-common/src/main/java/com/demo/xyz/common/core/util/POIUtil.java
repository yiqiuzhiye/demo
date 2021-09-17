package com.demo.xyz.common.core.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;

import java.util.Locale;

/**
 * 读取excel返回字符串的Cell
 * 
 * @author charles
 *
 */
public class POIUtil {

	private static DataFormatter formatter = new DataFormatter(Locale.SIMPLIFIED_CHINESE);

	public static String formatCellValue(Cell cell) {

		if (cell == null) {
			return null;
		}
		String cellval = formatter.formatCellValue(cell);

		return cellval.trim();

	}

}
