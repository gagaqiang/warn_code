package com.service.warn;

import com.dao.warning.WarningHeaderMapper;
import com.dao.warning.WarningJobDateLogMapper;
import com.ds.DS;
import com.entity.warning.WarningHeader;
import com.entity.warning.WarningJobDateLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class WarningJobDataLogService {

    @Autowired
    private WarningHeaderMapper warningHeaderMapper;
    @Autowired
    private WarningJobDateLogMapper warningJobDateLogMapper;

    private Integer getWarnHeaderId(String warnCode){
        WarningHeader wh = warningHeaderMapper.getWarningHeaderByCode(warnCode);
        if (null != wh ){
            return wh.getId();
        }else {
            return 0;
        }
    }

    /**
     * 新增抓取接口日志
     * @param data      抓取内容
     * @param warnCode  预警项CODE
     * @param platformCode
     * @param shopId
     * @param fromInterface
     */
    @DS(value = "tc_warning")
    @Transactional
    public void addJobDataLog(String data, String warnCode, String platformCode, String shopId, String fromInterface){
        WarningJobDateLog wj = new WarningJobDateLog();
        wj.setCreateTime(new Date());
        wj.setDate(data);
        wj.setLastTime(new Date());
        wj.setVersion(1);
        wj.setWarningHeaderId(getWarnHeaderId(warnCode));
        wj.setHeaderCode(warnCode);
        wj.setAttribuate1(platformCode);
        wj.setAttribuate2(shopId);
        wj.setAttribuate3(fromInterface);

        warningJobDateLogMapper.insertSelective(wj);
    }


}
