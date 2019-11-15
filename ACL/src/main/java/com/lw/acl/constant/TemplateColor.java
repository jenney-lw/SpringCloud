package com.lw.acl.constant;

/**
 * 优惠券背景颜色
 */
public enum TemplateColor {

    RED(1, "红色"),
    GREEN(2, "绿色"),
    BLUE(3, "蓝色");

    /**
     * 背景颜色代码
     */
    private Integer code;
    /**
     * 颜色描述
     */
    private String color;

    TemplateColor(int code, String color) {
        this.code = code;
        this.color = color;
    }

    public Integer getCode() {
        return code;
    }

    public String getColor() {
        return color;
    }

}
