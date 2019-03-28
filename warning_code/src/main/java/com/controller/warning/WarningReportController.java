package com.controller.warning;

import com.alibaba.fastjson.JSON;
import com.dto.warning.WarningHeaderDTO;
import com.dto.warning.WarningReportDTO;
import com.service.warn.WarningReportService;
import com.utils.*;
import com.utils.web.Pagination;
import com.utils.web.RetCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@Api("预警报表")
@CrossOrigin
@Controller
@RequestMapping(value="/report")
public class WarningReportController {

    public final static Logger log = LoggerFactory.getLogger(WarningReportController.class);

    @Autowired
    private WarningReportService warningReportService;


    @ResponseBody
    @ApiOperation("报表查询")
    @RequestMapping(value = "/findReportPagination", method = RequestMethod.POST, produces = "application/json")
    public RetCode<Pagination<WarningHeaderDTO>> getReport(@RequestBody WarningHeaderDTO dto,
                                                           @RequestParam("pageNo") Integer pageNo,
                                                           @RequestParam("pageSize") Integer pageSize) {
        log.info("WarningHeaderDTO==>>" + JSON.toJSONString(dto));
        Pagination<WarningHeaderDTO> pagination = null;
        try {
            pagination =  warningReportService.getWarnReportPagination(dto, pageNo, pageSize);
            return RetCode.success(pagination);
        } catch (Exception e) {
            log.error("报表查询失败！"+e);
            return RetCode.serverError(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation("报表总汇")
    @RequestMapping(value = "/findWarnReportList", method = RequestMethod.POST, produces = "application/json")
    public RetCode<WarningReportDTO> getWarnReportAll() {
        WarningReportDTO wr = null;
        try {
            wr =  warningReportService.getWarnReportAll();
            return RetCode.success(wr);
        } catch (Exception e) {
            log.error("报表总汇！"+e);
            return RetCode.serverError(e.getMessage());
        }
    }


    /**
     * 报表导出
     */
    @ApiOperation("报表导出")
    @RequestMapping(value = "/exportWarnList", method = RequestMethod.GET)
    public void exportUserInfoList(HttpServletResponse response, HttpServletRequest request) {

        String sheel = DateUtil.fmtDateToStr(Calendar.getInstance().getTime(), "yyyy-MM-dd HH:mm:ss").substring(0, 7);
        String fileName = "预警报表" + sheel + ".xls";
        // 导出数据集合
        Map<String, Object> m = warningReportService.getWarnReport(new WarningHeaderDTO(), 1, 99);
        if(null != m) {
            Pagination<WarningHeaderDTO> pagination = (Pagination<WarningHeaderDTO>) m.get("pagination");
            List<WarningHeaderDTO> li = pagination.getData();
            if(CollectionUtils.isNotEmpty(li)){
                String[] titles = { "预警项编号", "预警项名称", "预警对象", "预警数量", "已处理", "未处理", "累计影响单量", "当前影响单量", "平均处理耗时" };

                String[] fields = { "code", "name", "users", "total", "processedNum", "unProcessedNum",
                        "totalOrderNumber", "currentOrderNumber", "times" };
                try {
                    // 获取当前浏览器信息
                    String agent = request.getHeader("user-agent");
                    if (null != agent && -1 != agent.indexOf("MSIE")) { // IE浏览器
                        fileName = URLEncoder.encode(fileName, "utf-8"); // IE文件名编码
                    } else if (null != agent && -1 != agent.indexOf("Mozilla")) {// 火狐浏览器
                        fileName = new String(fileName.getBytes("utf-8"), "iso-8859-1");// 火狐文件名编码格式
                    }

                    OutputStream output = response.getOutputStream();
                    response.reset();
                    response.setContentType("application/octet-stream;charset=UTF-8");// 设置编码
                    response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

                    ExcelUtil.writeDataToExcel(sheel, sheel, fields, titles, li, output);

                } catch (IOException e) {
                }
            }
        }
    }

}
