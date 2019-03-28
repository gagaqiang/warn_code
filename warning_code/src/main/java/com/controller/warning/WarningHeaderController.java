package com.controller.warning;

import com.alibaba.fastjson.JSON;
import com.dto.warning.WarningHeaderDTO;
import com.dto.warning.WarningHeaderListDTO;
import com.dto.warning.WarningUserDTO;
import com.dto.warning.WarningWechatUserDTO;
import com.service.warn.WarningHeaderService;
import com.service.warn.WarningNameService;
import com.service.warn.WarningUserService;
import com.service.warn.WarningWechatUserService;
import com.utils.web.Pagination;
import com.utils.web.RetCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Auther: hsl
 * @Date: 2018/8/23 17:16
 * @Description:
 */
@Api("预警设置相关api")
@CrossOrigin
@RestController
@RequestMapping(value="/setting")
public class WarningHeaderController {

    public final static Logger log = LoggerFactory.getLogger(WarningHeaderController.class);

    @Autowired
    private WarningHeaderService warningHeaderService;
    @Autowired
    private WarningUserService warningUserService;
    @Autowired
    private WarningNameService warningNameService;
    @Autowired
    private WarningWechatUserService warningWechatUserService;


    @ApiOperation(value = "获取预警设置列表")
    @RequestMapping(value = "/findWarningHeaderPagination", method = RequestMethod.POST, produces = "application/json")
    public RetCode<Pagination<WarningHeaderDTO>> findWarningHeaderPagination(@RequestBody WarningHeaderDTO dto,
                                                                             @RequestParam("pageNo") Integer pageNo,
                                                                             @RequestParam("pageSize") Integer pageSize) {
        log.info("WarningHeaderDTO==>>" + JSON.toJSONString(dto));
        Pagination<WarningHeaderDTO> pagination = null;
        try {
            pagination = warningHeaderService.findWarningHeaderPagination(dto, pageNo, pageSize);
            return RetCode.success(pagination);
        } catch (Exception e) {
            log.error("获取预警设置列表！"+e.getMessage());
            return RetCode.serverError(e.getMessage());
        }
    }

    @ApiOperation(value = "新增预警设置")
    @RequestMapping(value = "/addWarningHeader", method = RequestMethod.POST, produces = "application/json")
    public RetCode<Integer> addWarningHeader(@RequestBody WarningHeaderDTO dto) {
        log.info("WarningHeaderDTO==>>" + JSON.toJSONString(dto));
        try {
            Integer id = warningHeaderService.addWarningHeader(dto);
            return RetCode.success(id);
        } catch (Exception e) {
            log.error("新增预警设置！"+e.getMessage());
            return RetCode.serverError(e.getMessage());
        }
    }

    @ApiOperation(value = "获取预警设置明细列表")
    @RequestMapping(value = "/findWarningLinePagination", method = RequestMethod.POST, produces = "application/json")
    public RetCode<Pagination<WarningUserDTO>> findWarningLinePagination(@RequestBody WarningUserDTO dto,
                                                                         @RequestParam("pageNo") Integer pageNo,
                                                                         @RequestParam("pageSize") Integer pageSize) {
        log.info("WarningUserDTO==>>" + JSON.toJSONString(dto));
        Pagination<WarningUserDTO> pagination = null;
        try {
            if (dto.getWarningHeaderId() == null) {
                log.info("===warningHeaderId为空,新增===");
            } else {
                pagination = warningUserService.findWarningLinePagination(dto, pageNo, pageSize);
            }
            return RetCode.success(pagination);
        } catch (Exception e) {
            log.error("获取预警设置明细列表！"+e.getMessage());
            return RetCode.serverError(e.getMessage());
        }
    }

    @ApiOperation(value = "删除预警项设置(业务员+店铺数据)")
    @RequestMapping(value = "/deleteWarningLine", method = RequestMethod.POST, produces = "application/json")
    public RetCode<Integer> deleteWarningLine(@RequestBody WarningUserDTO dto) {
        log.info("-----------"+ dto.getOperUser());
        log.info("WarningUserDTO==>>" + JSON.toJSONString(dto));
        try {
            Integer id = warningUserService.deleteWarningLine(dto);
            return RetCode.success(id);
        } catch (Exception e) {
            log.error("删除预警项设置(业务员+店铺数据)失败！"+e.getMessage());
            return RetCode.serverError(e.getMessage());
        }
    }

    @ApiOperation(value = "设置预警项启用、停用", notes = "设置预警项启用、停用")
    @RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
    public RetCode<Boolean> updateStatus(@RequestBody WarningHeaderListDTO dto){
        log.info("WarningHeaderListDTO==>>" + JSON.toJSONString(dto));
        try {
            warningHeaderService.updataWarningStatus(dto);
            return RetCode.success(Boolean.TRUE);
        }catch (Exception e){
            log.error("更新状态失败！"+e.getMessage());
            return RetCode.success(Boolean.FALSE);
        }
    }

    @ApiOperation(value = "获取预警编码和预警名称列表", notes = "获取预警编码和预警名称列表")
    @RequestMapping(value = "/findWarnEnumPagination", method = RequestMethod.POST)
    public RetCode<Pagination<WarningHeaderDTO>> getWarnEnum(@RequestBody WarningHeaderDTO dto,
                                                             @RequestParam("pageNo") Integer pageNo,
                                                             @RequestParam("pageSize") Integer pageSize) {
        log.info("WarningHeaderDTO==>>" + JSON.toJSONString(dto));
        Pagination<WarningHeaderDTO> pagination = null;
        try {
            pagination = warningNameService.findWarningNamePagination(dto,pageNo,pageSize);
            return RetCode.success(pagination);
        }catch (Exception e){
            log.error("获取预警编码和预警名称列表失败！"+e.getMessage());
            return RetCode.serverError(e.getMessage());
        }
    }

    @ApiOperation(value = "获取店铺列表", notes = "获取店铺列表")
    @RequestMapping(value = "/findShopEnumPagination", method = RequestMethod.POST)
    public RetCode<Pagination<WarningUserDTO>> getShopEnum(@RequestBody WarningUserDTO dto,
                                                     @RequestParam("pageNo") Integer pageNo,
                                                     @RequestParam("pageSize") Integer pageSize) {
        log.info("WarningUserDTO==>>" + JSON.toJSONString(dto));
        Pagination<WarningUserDTO> pagination = null;
        try {
            pagination = warningNameService.findWarningShopPagination(dto,pageNo,pageSize);
            return RetCode.success(pagination);
        }catch (Exception e){
            log.error("获取店铺列表失败！"+e.getMessage());
            return RetCode.serverError(e.getMessage());
        }
    }

    @ApiOperation(value = "获取业务员和企业微信对应信息", notes = "获取业务员和企业微信对应信息")
    @RequestMapping(value = "/findWarningNamePagination", method = RequestMethod.POST)
    public RetCode<Pagination<WarningWechatUserDTO>> findWarningNamePagination(@RequestBody WarningWechatUserDTO dto,
                                                           @RequestParam("pageNo") Integer pageNo,
                                                           @RequestParam("pageSize") Integer pageSize) {
        log.info("WarningWechatUserDTO==>>" + JSON.toJSONString(dto));
        Pagination<WarningWechatUserDTO> pagination = null;
        try {
            pagination = warningWechatUserService.findWarningNamePagination(dto,pageNo,pageSize);
            return RetCode.success(pagination);
        }catch (Exception e){
            log.error("获取业务员和企业微信对应信息失败！"+e.getMessage());
            return RetCode.serverError(e.getMessage());
        }
    }

}
