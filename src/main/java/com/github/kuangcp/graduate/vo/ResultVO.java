package com.github.kuangcp.graduate.vo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-3-28  下午3:38
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ResultVO {

    Integer status;
    String message;
    Object result;

    public ResultVO(Integer status, String message, Object result) {
        this.status = status;
        this.message = message;
        this.result = result;
    }
}