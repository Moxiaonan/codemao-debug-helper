package com.xy.debug.helper.util;

import java.util.StringJoiner;
import java.util.function.Supplier;

/**
 * TODO
 *
 * @author moxiaonan
 * @since 2021/5/24
 */
public class CommonUtil {
    public static <T> T calcCost(String msg ,Supplier<T> supplier){
        long beginTime = System.currentTimeMillis();
        T t = supplier.get();
        long endTime = System.currentTimeMillis();
        print(msg , beginTime ,endTime);
        return t;
    }
    public static void print(String msg ,long beginTime , long endTime){
        System.out.println(consoleHighlight(msg + " 耗时 : " + (endTime - beginTime)));
    }
    /**
     * 控制台高亮颜色显示
     *
     * @param content
     * @return
     */
    public static String consoleHighlight(String content){
        if (null != content && content.length() > 0) {
            StringJoiner colorJoiner = new StringJoiner("","\033[1;36m","\033[0m");
            return colorJoiner.add(content).toString();
        }
        return "";
    }
}
