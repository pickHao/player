package com.performer.player.common.utils;

import java.util.HashMap;
import java.util.Map;

import static com.performer.player.common.utils.CodeConstants.*;

public class CodeMessageLibrary {

    public static Map<Integer,String> codeMessageMap = new HashMap<Integer,String>();

    static {
        codeMessageMap.put(TOKEN_ISERROR,"token传值有误");
        codeMessageMap.put(NOT_FOUND_DATA,"没有查询到数据");
        codeMessageMap.put(PARAM_ISERROR,"参数传值有误");
        codeMessageMap.put(LOGIN_FAILED,"账号或密码不正确");
    }


}
