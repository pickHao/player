package com.performer.player.comment.pojo;


public class ResultUtil {
    /**
     * 请求成功返回
     * @param object
     * @return
     */
    public static ReturnMsg success(Object object){
    	ReturnMsg msg=new ReturnMsg();
        msg.setCode(200);
        msg.setMsg("请求成功");
        msg.setData(object);
        return msg;
    }
    public static ReturnMsg success(){
        return success(null);
    }

    public static ReturnMsg error(Integer code,String resultmsg){
    	ReturnMsg msg=new ReturnMsg();
        msg.setCode(code);
        msg.setMsg(resultmsg);
        return msg;
    }
}
