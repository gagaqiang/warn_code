package com.dto.warning;

import io.swagger.annotations.ApiModel;

import java.util.List;

/**
 * @Auther: hsl
 * @Date: 2018/9/4 11:33
 * @Description:
 */
@ApiModel
public class WarningHeaderListDTO {

    private List<WarningHeaderDTO> warningHeaderDTOList;

    public List<WarningHeaderDTO> getWarningHeaderDTOList() {
        return warningHeaderDTOList;
    }

    public void setWarningHeaderDTOList(List<WarningHeaderDTO> warningHeaderDTOList) {
        this.warningHeaderDTOList = warningHeaderDTOList;
    }
}
