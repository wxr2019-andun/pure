package com.andun.controller;

import com.andun.common.MQSend;
import com.andun.platform.dao.DaoTest;
import com.andun.platform.pojo.po.DoctorDetailedInfo;
import com.andun.platform.pojo.vo.MQTestVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Author:wuxinrui
 * Date:2020-03-09  10:29
 * Description:
 */
@Controller
@RequestMapping("/doctor")
public class DoctorInfoController {
    @Resource
    DaoTest daoTest;
    @Resource
    MQSend mqSend;

    @GetMapping("/getDoctorDetailedInfo")
    @ResponseBody
    public List<DoctorDetailedInfo> getDoctorDetailedInfo(Integer userInfoVoId) {
        List<DoctorDetailedInfo> doctorDetailedInfos = daoTest.queryCertainDoctorDetailedInfo(userInfoVoId);
        return doctorDetailedInfos;
    }
    @GetMapping("/mqSend")
    @ResponseBody
    public String mqSend() {
        System.out.println("mqSend-send");
        mqSend.fanoutDelay(new MQTestVo("mqDelay","2020-04-27 14:45"), 1 * (60*1000));
        mqSend.directSend(new MQTestVo("mqDelay","2020-04-30 16:10"));
        //System.out.println("mqSend-succeed");
        return "mqSend-succeed";
    }

}
