package com.controller.warning;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dto.warning.WarningDetailDTO;
import com.dto.warning.WarningDetailListDTO;
import com.dto.warning.WarningGroupDTO;
import com.pojo.WarningGroupPojo;
import com.service.redis.WarnHeadRedis;
import com.service.warn.TcErrorMsgService;
import com.service.warn.WarningDetailService;
import com.service.warn.WarningGroupService;
import com.utils.DateUtil;
import com.utils.ExcelUtil;
import com.utils.web.Pagination;
import com.utils.web.RetCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api("预警明细")
@Controller
@RequestMapping("/group")
@CrossOrigin
public class WarningGroupController {

    public final static Logger log = LoggerFactory.getLogger(WarningGroupController.class);

    @Autowired
    private WarningGroupService warningGroupService;
    @Autowired
    private WarningDetailService warningDetailService;
    @Autowired
    private TcErrorMsgService tcErrorMsgService;
    @Autowired
    private WarnHeadRedis warnHeadRedis;


    @ResponseBody
    @ApiOperation(value = "获取列表")
    @RequestMapping(value = "/findGroupPagination", method = RequestMethod.POST, produces = "application/json")
    public RetCode<Pagination<WarningGroupDTO>> queryList(@RequestBody WarningGroupPojo wp,
                                                          @RequestParam("pageNo") Integer pageNo,
                                                          @RequestParam("pageSize") Integer pageSize) {
        log.info("WarningGroupPojo >>>>" + JSONObject.toJSONString(wp));

        Pagination<WarningGroupDTO> pa = null;
        try {
            pa = warningGroupService.getListByParmes(pageNo, pageSize, wp);
            return RetCode.success(pa);
        } catch (Exception e) {
            log.error("error >>" + e);
            return RetCode.serverError(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "关闭", notes = "关闭")
    @RequestMapping(value = "/updateClose", method = RequestMethod.POST)
    public RetCode<Boolean> updateClose(@RequestBody WarningDetailDTO dto) {
        log.info("WarningDetailDTO==>>" + JSON.toJSONString(dto));
        try {
            Map<String, Object> m = warningDetailService.updateClose(dto);
            if (null != m && m.containsKey("code")) {
                tcErrorMsgService.updateDeal(String.valueOf(m.get("code")), String.valueOf(m.get("platformCode")));
                return RetCode.success(Boolean.TRUE);
            } else {
                return RetCode.serverError("当前预警项预警关闭");
            }
        } catch (Exception e) {
            log.error("更新关闭状态失败！" + e);
            return RetCode.serverError("更新关闭状态失败:" + e);
        }
    }


    @ResponseBody
    @ApiOperation(value = "批量关闭", notes = "批量关闭")
    @RequestMapping(value = "/updateCloseAll", method = RequestMethod.POST)
    public RetCode<Boolean> updateCloseAll(@RequestBody WarningDetailListDTO dto) {
        log.info("WarningDetailDTO==>>" + JSON.toJSONString(dto));
        try {
            List<Map<String, Object>> mList = warningDetailService.updateCloseAll(dto);
            if (null != mList && mList.get(0).containsKey("code")) {
                for (int i = 0; i < mList.size(); i++) {
                    Map<String, Object> m = mList.get(i);
                    tcErrorMsgService.updateDeal(String.valueOf(m.get("code")), String.valueOf(m.get("platformCode")));
                }
                return RetCode.success(Boolean.TRUE);
            } else {
                return RetCode.serverError("当前预警项预警关闭");
            }
        } catch (Exception e) {
            log.error("更新关闭状态失败！" + e);
            return RetCode.serverError("当前预警项预警关闭");
        }
    }

    @ResponseBody
    @ApiOperation(value = "导入批量关闭", notes = "导入批量关闭")
    @RequestMapping(value = "/updateImportCloseAll", method = RequestMethod.POST, headers = "content-type=multipart/form-data")
    public Map updateImportCloseAll(@RequestParam("file") MultipartFile file,
                                    @RequestParam("detailCode") String detailCode,
                                    @RequestParam("user") String user) {
        log.info("detailCode==>>" + JSON.toJSONString(detailCode));
        log.info("user==>>" + JSON.toJSONString(user));

        Map retMap = new HashMap();
        retMap.put("success", "true");
        retMap.put("message", "导入批量关闭成功！");
        try {
            InputStream is = file.getInputStream();
            ExcelUtil excelUtil = new ExcelUtil();
            List<Row> li = excelUtil.getExcelRead(file.getOriginalFilename(), is, true);
            if (CollectionUtils.isNotEmpty(li)) {
                for (Row r : li) {
                    String platfromCode = ExcelUtil.getValue(r.getCell(0));
                    List<WarningDetailDTO> warningDetailDTOList = warningDetailService.getDetailIdByDetailCodeAndPlatfromCode(detailCode, platfromCode);
                    if (CollectionUtils.isNotEmpty(li)) {
                        for (WarningDetailDTO lii : warningDetailDTOList) {
                            lii.setOperUser(user);
                            Map<String, Object> m = warningDetailService.updateClose(lii);
                            if (null != m && m.containsKey("code")) {
                                tcErrorMsgService.updateDeal(String.valueOf(m.get("code")), String.valueOf(m.get("platformCode")));
                            }
                            if (null != m && m.containsKey("error")) {
                                retMap.put("success", "false");
                                retMap.put("message", m.get("error") + ",不能关闭预警信息！");
                                return retMap;
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error("error >>" + e);
            retMap.put("success", "false");
            retMap.put("message", "导入Excel文件异常,请检查导入模板格式，或者联系程序猿！");
        }
        return retMap;
    }


    @ResponseBody
    @ApiOperation(value = "获取预警明细订单列表")
    @RequestMapping(value = "/findWarningDetailPagination", method = RequestMethod.POST, produces = "application/json")
    public RetCode<Pagination<WarningDetailDTO>> findWarningDetailPagination(@RequestBody WarningDetailDTO dto,
                                                                             @RequestParam("pageNo") Integer pageNo,
                                                                             @RequestParam("pageSize") Integer pageSize) {
        log.info("WarningDetailDTO >>>>" + JSONObject.toJSONString(dto));

        Pagination<WarningDetailDTO> pa = null;
        try {
            pa = warningDetailService.findWarningDetailPagination(dto, pageNo, pageSize);
            return RetCode.success(pa);
        } catch (Exception e) {
            log.error("error >>" + e);
            return RetCode.serverError(e.getMessage());
        }
    }

    /**
     * 预警明细导出(详情)
     */
    @ApiOperation("预警明细导出(详情)")
    @RequestMapping(value = "/exportWarnDetailList", method = RequestMethod.GET)
    public void exportWarnDetailList(@ModelAttribute WarningDetailDTO dto, HttpServletResponse response, HttpServletRequest request) {

        log.info("WarningDetailDTO >>>>" + JSONObject.toJSONString(dto));
        log.info("WarningDetailDTO >>>starttime>" + DateUtil.fmtDateToStr(Calendar.getInstance().getTime(), "yyyy-MM-dd HH:mm:ss"));
        warnHeadRedis.setValue("key:exportFlag:test", "0");//导出中

        String sheel = DateUtil.fmtDateToStr(Calendar.getInstance().getTime(), "yyyy-MM-dd HH:mm:ss").substring(0, 7);
        String fileName = "预警明细报表(详情)" + sheel + ".xls";
        // 导出数据集合
        List<WarningDetailDTO> list = null;
        try {
            list = warningDetailService.getDetailList(dto, 1, 9999);
        } catch (Exception e) {
            log.error("error >>" + e);
        }
        if (CollectionUtils.isNotEmpty(list)) {
            String[] titles = {"平台单号", "预警触发时间", "预警发送时间", "处理状态", "店铺名称", "预警对象", "处理完成时间", "处理耗时", "关闭预警", "操作人"};
            String[] fields = {"platfromCode", "touchTime", "sendTime", "statusMean", "shopName", "users", "delTime", "timeConsuming", "isCloseMean", "attribuate1"};
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
                ExcelUtil.writeDataToExcel(sheel, sheel, fields, titles, list, output);
                warnHeadRedis.setValue("key:exportFlag:test", "1");//导出完成
            } catch (IOException e) {
                warnHeadRedis.setValue("key:exportFlag:test", "-1");//导出失败
                log.error("error >>" + e);
            }
        }
        log.info("WarningDetailDTO >>>endtime>" + DateUtil.fmtDateToStr(Calendar.getInstance().getTime(), "yyyy-MM-dd HH:mm:ss"));

    }

    /**
     * 检查预警明细导出(详情)是否完成
     */
    @ResponseBody
    @ApiOperation("检查预警明细导出(详情)是否完成")
    @RequestMapping(value = "/checkExportWarnDetailList", method = RequestMethod.GET)
    public JSONObject checkExportWarnDetailList(@ModelAttribute WarningDetailDTO dto) {
        String exportFlag = warnHeadRedis.getObjectStr("key:exportFlag:test");
        JSONObject jo = new JSONObject();
        if (exportFlag.equals("0")) {
            jo.put("resultCode", 0);//导出中
        } else if (exportFlag.equals("1")) {
            jo.put("resultCode", 1);//导出完成
        } else {
            jo.put("resultCode", -1);//导出失败
        }
        return jo;
    }


    /**
     * 预警明细导出
     */
    @ApiOperation("预警明细导出")
    @RequestMapping(value = "/exportWarnGroupList", method = RequestMethod.GET)
    public void exportUserInfoList(@ModelAttribute WarningGroupPojo wp, HttpServletResponse response, HttpServletRequest request) {

        String sheel = DateUtil.fmtDateToStr(Calendar.getInstance().getTime(), "yyyy-MM-dd HH:mm:ss").substring(0, 7);
        String fileName = "预警明细报表" + sheel + ".xls";
        // 导出数据集合
        List<WarningGroupDTO> li = warningGroupService.queryAllList(1, 100000, wp);
        if (CollectionUtils.isNotEmpty(li)) {
            String[] titles = {"预警项编号", "预警发送时间", "预警项编号", "预警项名称", "预警对象", "预警状态", "当前影响单量", "处理完成时间", "处理耗时"};
            String[] fields = {"groupCode", "sendTime", "code", "name", "users", "statusName",
                    "num", "delTime", "timeCon"};
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
