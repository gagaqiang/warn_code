package com.service.warn;

import com.dao.warning.WarningHeaderMapper;
import com.dao.warning.WarningNameMapper;
import com.dao.warning.WarningShopMapper;
import com.dto.warning.WarningHeaderDTO;
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
 * @Date: 2018/9/4 16:03
 * @Description:
 */
@Service
public class WarningNameService {

    public final static Logger log = LoggerFactory.getLogger(WarningNameService.class);

    @Autowired
    private WarningNameMapper warningNameMapper;
    @Autowired
    private WarningShopMapper wrningShopMapper;
    @Autowired
    private WarningHeaderMapper warningHeaderMapper;


    //获取预警名称和编码列表
    public Pagination<WarningHeaderDTO> findWarningNamePagination(WarningHeaderDTO dto, Integer pageNo, Integer pageSize) throws Exception {
        Pagination<WarningHeaderDTO> pagination = new Pagination<WarningHeaderDTO>(pageNo, pageSize);
        StringBuffer sb = new StringBuffer();
        List<WarningHeaderDTO> warningHeaderDTOList = warningHeaderMapper.getAllCodeList();
        if (warningHeaderDTOList != null && !warningHeaderDTOList.isEmpty()) {
            for (int i = 0; i < warningHeaderDTOList.size(); i++) {
                WarningHeaderDTO warningHeaderDTO = warningHeaderDTOList.get(i);
                if (i == 0) {
                    sb.append("'" + warningHeaderDTO.getCode() + "'");
                } else {
                    sb.append("," + "'" + warningHeaderDTO.getCode() + "'");
                }
            }
        }
        dto.setCodeList(sb.toString());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("dto", dto);
        map.put("startNum", Pagination.getStartNum(pageNo, pageSize));
        map.put("pageSize", pageSize);
        List<WarningHeaderDTO> list = warningNameMapper.findWarningNamePagination(map);
        int rowsCount = warningNameMapper.findWarningNameCount(dto);
        pagination.setCount(rowsCount);
        pagination.setData(list);
        return pagination;
    }

    //获取店铺列表
    public Pagination<WarningUserDTO> findWarningShopPagination(WarningUserDTO dto, Integer pageNo, Integer pageSize) throws Exception {
        Pagination<WarningUserDTO> pagination = new Pagination<WarningUserDTO>(pageNo, pageSize);
        int rowsCount = wrningShopMapper.findWarningShopCount(dto);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("dto", dto);
        map.put("startNum", Pagination.getStartNum(pageNo, pageSize));
        map.put("pageSize", pageSize);
        List<WarningUserDTO> list = wrningShopMapper.findWarningShopPagination(map);
        pagination.setCount(rowsCount);
        pagination.setData(list);
        return pagination;
    }

}
