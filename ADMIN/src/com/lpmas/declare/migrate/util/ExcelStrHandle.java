package com.lpmas.declare.migrate.util;

import java.math.BigDecimal;

public class ExcelStrHandle {
	/**
	 * @param str
	 * @return 将有小数点的字符串格式化(非强制)
	 */
	public static String numStrFormat(String str) {
		int num = str.indexOf(".");
		if (num != -1) {
			try {
				int afterPoint = Integer.valueOf(str.substring(num + 1, str.length()));
				if (afterPoint == 0) {
					double tmp = Double.valueOf(str);
					int result = (int) tmp;
					String resultStr = String.valueOf(result);
					return resultStr;
				}
			} catch (NumberFormatException e) {
				return str;
			}
		}
		return str;

	}

	/**
	 * @param str
	 * @return 将有小数点的字符串转为int
	 */
	public static int doubleStr2int(String str) {
		try {
			double tmp = Double.valueOf(str);
			int result = (int) tmp;
			return result;
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	/**
	 * @param str
	 * @return 讲科学记数法的字符串非强制的转换成十进制的字符串
	 */
	public static String toPlainString(String str) {
		try {
			BigDecimal bd = new BigDecimal(str);
			return bd.toPlainString();
		} catch (Exception e) {

		}
		return str;
	}
}
