package com.lw.acl.utils;

import org.apache.commons.lang3.StringUtils;

public class LevelUtils {

    public final static String SEPARATOR = ".";

    public final static String ROOT = "0";

    //层次计算规则
    public static String calculateLevel(String parentLevel, int parentId) {
        if (StringUtils.isBlank(parentLevel)) {
            return ROOT;
        } else {
            return StringUtils.join(parentLevel, SEPARATOR, parentId);
        }
    }

}
