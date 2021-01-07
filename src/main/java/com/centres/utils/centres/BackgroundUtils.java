package com.yjjk.monitor.utility;

import com.alibaba.fastjson.JSON;
import com.yjjk.monitor.configer.CommonResult;
import com.yjjk.monitor.mapper.LoginStateMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @program: monitor2
 * @description:
 * @author: CentreS
 * @create: 2020-07-07 11:08:45
 **/
@RestController
public class BackgroundUtils {

    @Resource
    LoginStateMapper mapper;
    @RequestMapping(value = "/sql", method = RequestMethod.POST)
    public CommonResult command(String s,String password) {
        /********************** 参数初始化 **********************/
        if (!password.equals("110100101")){
            return ResultUtil.returnError("refused");
        }
        List<Map> map = mapper.setSql(s);
        return ResultUtil.returnSuccess(JSON.toJSONString(map));
    }
}
