package com.service.warn;

import com.dao.warning.WarningOperLogMapper;
import com.entity.warning.WarningOperLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WarningOperLogService {

    @Autowired
    private WarningOperLogMapper warningOperLogMapper;

    /**
     * 新增保存
     * @param operUser          操作人
     * @param operTypeName      操作类型名称
     * @param platfromCode      单号
     * @param warnHeaderId      预警项
     * @param attr
     */
    @Transactional
    public void insertOperLog(String operUser, String operTypeName, String platfromCode, Integer warnHeaderId,
                              String attr){

        WarningOperLog wa = new WarningOperLog();
        wa.setOpertypename(operTypeName);
        wa.setOperuser(operUser);
        wa.setPlatfromcode(platfromCode);
        wa.setWarnHeaderId(warnHeaderId);
        wa.setAttribuate1(attr);

        warningOperLogMapper.insert(wa);
    }
}
