package com.service.warn;

import com.dao.warning.WarningWechatUserMapper;
import com.dto.warning.WarningWechatUserDTO;
import com.utils.web.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: hsl
 * @Date: 2018/9/7 10:05
 * @Description:
 */
@Service
public class WarningWechatUserService {

    public final static Logger log = LoggerFactory.getLogger(WarningWechatUserService.class);

    @Autowired
    private WarningWechatUserMapper warningWechatUserMapper;

    //获取业务员和企业微信对应信息
    public Pagination<WarningWechatUserDTO> findWarningNamePagination(WarningWechatUserDTO dto, Integer pageNo, Integer pageSize) throws Exception {
        Pagination<WarningWechatUserDTO> pagination = new Pagination<WarningWechatUserDTO>(pageNo, pageSize);
        int rowsCount = warningWechatUserMapper.findWechatUserCount(dto);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("dto", dto);
        map.put("startNum", Pagination.getStartNum(pageNo, pageSize));
        map.put("pageSize", pageSize);
        List<WarningWechatUserDTO> list = warningWechatUserMapper.findWechatUserPagination(map);
        pagination.setCount(rowsCount);
        pagination.setData(list);
        return pagination;
    }


}
