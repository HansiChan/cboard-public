package com.dachen.cboardpublic.util;

import javax.servlet.http.HttpServletRequest;
import java.text.NumberFormat;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class StringUtils extends org.apache.commons.lang3.StringUtils{

    public final static String CRLF = "\r\n";
    public static final String BASE = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    public static final String BASE_2 = "abcdefghijklmnopqrstuvwxyz0123456789";
    public static final String BASE_3 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static String StringFilter(String str) {
        if (str == null) return str;
        Pattern p = Pattern.compile("[\1\2\3\4\6\7\u000B\u001C\u001D\u001E\u001F]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return m.replaceAll(" ").trim();
        } else {
            return str;
        }
    }
    
    public static boolean isNotEmpty(String str) {
    	return !isEmpty(str);
    }
    
    public static boolean isNumber(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    public static String IdToUpperCase(String s) {
        if (s == null)
            s = "";
        s = s.trim().toUpperCase();
        s = s.replaceAll(" ", "_");
        s = s.replaceAll("_+", "_");
        return s;
    }

    public static void append(StringBuilder buf, byte b, int base) {
        int bi = 0xff & b;
        int c = '0' + (bi / base) % base;
        if (c > '9')
            c = 'a' + (c - '0' - 10);
        buf.append((char) c);
        c = '0' + bi % base;
        if (c > '9')
            c = 'a' + (c - '0' - 10);
        buf.append((char) c);
    }

    public static String getRandomString(int length) {
        return randomString(length, BASE);
    }

    private static String randomString(int length, String base) {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public static String getRandomString2(int length) {
        return randomString(length, BASE_2);
    }

    public static String randomId() {
        return randomString(10, BASE_3);
    }
    
    public static String randomUUID() {
		UUID uuid = UUID.randomUUID();
		String uuidStr = uuid.toString().replace("-", "");

		return uuidStr;
	}
    
    public static boolean startsWith(final CharSequence str, final CharSequence prefix) {
        return org.apache.commons.lang3.StringUtils.startsWith(str, prefix);
    }
    
    public static boolean endsWith(final CharSequence str, final CharSequence suffix) {
        return org.apache.commons.lang3.StringUtils.endsWith(str, suffix);
    }
    
    public static String[] split(final String str, final String separatorChars) {
        return org.apache.commons.lang3.StringUtils.split(str, separatorChars);
    }
    public static String[] split(final String str, final String separatorChars, final int max) 
    {
    	 return org.apache.commons.lang3.StringUtils.split(str, separatorChars,max);
    }
    public static boolean equals(final CharSequence cs1, final CharSequence cs2)
    {
    	return org.apache.commons.lang3.StringUtils.equals(cs1, cs2);
    }
    public static String join(final Object[] array, final String separator) 
    {
    	return org.apache.commons.lang3.StringUtils.join(array,separator);
    }
  
    private static final char[] charArray = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f',
			'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

	public static String getExt(String filename) {
		return filename.substring(filename.lastIndexOf('.'));
	}

	public static boolean isEmpty(String s) {
		return isNullOrEmpty(s);
	}

	public static boolean isNullOrEmpty(String s) {
		return null == s || 0 == s.trim().length();
	}

	public static String randomCode() {
		return "" + (new Random().nextInt(899999) + 100000);
	}
	public static String random4Code() {
		return "" + (new Random().nextInt(8999) + 1000);
	}

	public static String randomPassword() {
		return randomString(6);
	}

	public static String randomString(int length) {
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < length; i++) {
			int index = new Random().nextInt(36);
			sb.append(charArray[index]);
		}

		return sb.toString();
	}

	public static String getFormatName(String fileName) {
		int index = fileName.lastIndexOf('.');

		return -1 == index ? "jpg" : fileName.substring(index + 1);
	}

	public static String format(long number,int length){
		NumberFormat nf=NumberFormat.getInstance();
		nf.setMaximumIntegerDigits(length);
		nf.setMinimumIntegerDigits(length);
		nf.setGroupingUsed(false);
		return nf.format(number);
	}
	
	public static String atKey(String telephone, Integer userType) {
        return String.format("uk_%1$s_%2$d", telephone, userType);
    }
	
	public static String atValue(String orderRecharegeNo, String refundRrice,String refundReason) {
        return String.format("%1$s^%2$s^%3$s", orderRecharegeNo, refundRrice,refundReason);
	}
	
	public static boolean isMobiPhoneNum(String telNum){
		String regex = "^((13[0-9])|(15[0-9])|(18[0-9]))\\d{8}$";
        Pattern p = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(telNum);
        return m.matches();
	}
	
	/**
     * 获取客服端ip
     * @param request
     * @return
     */
	public static String getClientIp(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if(StringUtils.isEmpty(ip)) return null;
		String[] ips = ip.split(",");
		if(Objects.nonNull(ips) && ips.length>0) return ips[0];
		return null;
	}	
}
