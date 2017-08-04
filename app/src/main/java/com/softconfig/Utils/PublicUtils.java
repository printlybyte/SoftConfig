package com.softconfig.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ${王sir} on 2017/6/23.
 * application
 */

public class PublicUtils {

    /**
     * 验证是否是IP格式
     * @param ipaddr
     * @return
     */
    public static boolean isIPAddress(String ipaddr) {
        boolean flag = false;
        Pattern pattern = Pattern.compile("\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b");
        Matcher m = pattern.matcher(ipaddr);
        flag = m.matches();
        return flag;
    }
}
