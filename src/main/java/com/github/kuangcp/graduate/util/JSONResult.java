package com.github.kuangcp.graduate.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.kuangcp.graduate.vo.ResultVO;

/**
 * Created by https://github.com/kuangcp
 *
 * @author kuangcp
 * @date 18-3-28  下午3:37
 */
public class JSONResult {

    public static String fillResultString(Integer status, String message, Object result) {
        ResultVO results = new ResultVO(status, message, result);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(results);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
