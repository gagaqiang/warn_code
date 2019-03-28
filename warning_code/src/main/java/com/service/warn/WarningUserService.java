package com.service.warn;

import com.dao.warning.WarningUserMapper;
import com.dao.warning.WarningUserShopMapper;
import com.dto.warning.WarningUserDTO;
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
 * @Date: 2018/9/3 15:16
 * @Description:
 */
@Service
public class WarningUserService {

    public final static Logger log = LoggerFactory.getLogger(WarningUserService.class);

    @Autowired
    private WarningUserMapper warningUserMapper;
    @Autowired
    private WarningUserShopMapper warningUserShopMapper;

    @Autowired
    private WarningOperLogService warningOperLogService;

    //获取预警设置明细列表
    public Pagination<WarningUserDTO> findWarningLinePagination(WarningUserDTO dto, Integer pageNo, Integer pageSize) throws Exception {
        Pagination<WarningUserDTO> pagination = new Pagination<WarningUserDTO>(pageNo, pageSize);
        int rowsCount = warningUserMapper.findWarningLineCount(dto);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("dto", dto);
        map.put("startNum", Pagination.getStartNum(pageNo, pageSize));
        map.put("pageSize", pageSize);
        List<WarningUserDTO> list = warningUserMapper.findWarningLinePagination(map);
        pagination.setCount(rowsCount);
        pagination.setData(list);
        return pagination;
    }

    //删除预警项设置(业务员+店铺数据)
    public Integer deleteWarningLine(WarningUserDTO dto) throws Exception {
        warningUserMapper.deleteByPrimaryKey(Integer.parseInt(dto.getUserId()));
        warningUserShopMapper.deleteByPrimaryKey(dto.getId());

        //记录日志
        warningOperLogService.insertOperLog(dto.getOperUser(), "删除预警项设置(业务员+店铺数据)"
                , (String) null, dto.getWarningHeaderId(), null);

        return dto.getWarningHeaderId();
    }


}
