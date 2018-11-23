package com.performer.player.video.util;

public enum Const {

	VIDEO(1, "视频"), 
	IMAGE(2, "图片");

    private int value;
    
    private String name;

	Const(Integer value, String name) {
        this.value = value;
        this.name = name;
    }
	/**
     * 按照Value获得枚举�?
     */
    public static Const valueOf(Integer value) {
        if (value != null) {
            for (Const fsEnum : Const.values()) {
                if (fsEnum.getValue() == value) {
                    return fsEnum;
                }
            }
        }
        return null;
    }
 
    public int getValue() {
        return value;
    }
 
    public String getName() {
        return name;
    }
}
