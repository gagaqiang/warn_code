package com.service.warn;

import com.dao.warning.*;
import com.ds.DS;
import com.dto.warning.WarningGroupDTO;
import com.dto.warning.WarningGroupReportDTO;
import com.dto.warning.WarningUserDTO;
import com.entity.erptc.ErrorMsg;
import com.entity.warning.*;
import com.pojo.WarningGroupPojo;
import com.service.redis.WarnHeadRedis;
import com.utils.*;
import com.utils.web.Pagination;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class WarningGroupService {

    public final static Logger _logger = LoggerFactory.getLogger(WarningGroupService.class);
    @Autowired
    private WarningGroupMapper warningGroupMapper;
    @Autowired
    private WarningDetailMapper warningDetailMapper;
    @Autowired
    private WarningShopMapper warningShopMapper;
    @Autowired
    private WarningHeaderService warningHeaderService;
    @Autowired
    private WarningJobDateLogMapper warningJobDateLogMapper;
    @Autowired
    private WarningHeaderMapper warningHeaderMapper;
    @Autowired
    private WarnHeadRedis warnHeadRedis;

    /**
     * 列表分页查询
     *
     * @param pageNo
     * @param pageSize
     * @param wp
     * @return
     */
    public Pagination<WarningGroupDTO> getListByParmes(Integer pageNo, Integer pageSize, WarningGroupPojo wp) {

        Pagination<WarningGroupDTO> pagination = new Pagination<WarningGroupDTO>(pageNo, pageSize);
        int count = warningGroupMapper.queryCount(wp);
        List<WarningGroupDTO> li = warningGroupMapper.queryListByParmes(wp, Pagination.getStartNum(pageNo, pageSize), pageSize);
        //获取已处理数，未处理数和总影响数
        if (li != null && !li.isEmpty()) {
            for (int i = 0; i < li.size(); i++) {
                WarningGroupDTO dto = li.get(i);
                if (dto.getGroupId() != null) {
                    WarningGroupDTO reDTO = warningDetailMapper.getNumByGroupId(dto.getGroupId());
                    dto.setAllNum(reDTO.getAllNum());
                    dto.setNuDealNum(reDTO.getNuDealNum());
                    dto.setDealNum(reDTO.getDealNum());
                }
            }
        }
        pagination.setCount(count);
        pagination.setData(li);
        return pagination;
    }

    /**
     * 获取全量数据列表
     *
     * @param pageNo
     * @param pageSize
     * @param wp
     * @return
     */
    public List<WarningGroupDTO> queryAllList(Integer pageNo, Integer pageSize, WarningGroupPojo wp) {
        return warningGroupMapper.queryListByParmes(wp, Pagination.getStartNum(pageNo, pageSize), pageSize);
    }


    /**
     * 新增预警明细
     *
     * @param wh
     * @param err
     * @param isDetailOnly
     */
    @DS(value = "tc_warning")
    @Transactional
    public void addWarningGroup(WarningHeader wh, ErrorMsg err, boolean isDetailOnly){
        //获取当前没有完成的明细组
        WarningGroup wg = warningGroupMapper.getWarningGroupByHeaderIdAndStatus(wh.getId(), 2);
        if (null == wg) {
            //新增
            addWarnGroupAndDetail(wh, wg, err, isDetailOnly, true);
        } else {
            addWarnGroupAndDetail(wh, wg, err, isDetailOnly, false);
        }
    }

    /**
     * 记录日志
     * @param err
     * @param wh
     */
    private void addJobDataLog(ErrorMsg err,WarningHeader wh){
        WarningJobDateLog wj = new WarningJobDateLog();
        wj.setCreateTime(new Date());
        wj.setDate(err.getErrorDetail());
        wj.setLastTime(new Date());
        wj.setVersion(1);
        wj.setWarningHeaderId(wh.getId());
        wj.setHeaderCode(wh.getCode());
        wj.setAttribuate1(err.getPlatformCode());
        wj.setAttribuate2(String.valueOf(err.getShopId()));
        wj.setAttribuate3(err.getFromInterface());

        warningJobDateLogMapper.insertSelective(wj);
    }

    /**
     * 保存预警明细、详情
     * @param wh
     * @param wgr
     * @param err
     * @param isDetailOnly 详情是否只一条
     * @param flag 是否新增
     */
    private void addWarnGroupAndDetail(WarningHeader wh, WarningGroup wgr
            ,  ErrorMsg err, boolean isDetailOnly, boolean flag) {
        String platfromCode = err.getPlatformCode();
        Integer shopId = err.getShopId();
        String shopName = err.getShopName();

        if (null == wgr) {
            addJobDataLog(err, wh);
            wgr = new WarningGroup();
            wgr.setWarningHeaderId(wh.getId());
            wgr.setDetailCode(ID.getRandomId());
            wgr.setStatus(2);
            wgr.setNum(isDetailOnly ? 0 : 1);
            wgr.setCreateTime(new Date());
            wgr.setVersion(1);

            warningGroupMapper.insertSelective(wgr);

            wh.setTotal(wh.getTotal() + 1);
            wh.setUnProcessedNum(1);
            wh.setTotalOrderNumber(wh.getTotalOrderNumber() + 1);
            wh.setCurrentOrderNumber(wh.getCurrentOrderNumber() + 1);
            wh.setLastTime(new Date());

            warningHeaderService.updateWarnHeader(wh);
        }

        //当前未完成是否存在
        List<WarningDetail> wdList = warningDetailMapper.getWarningDetailDto(wgr.getId(), 2, platfromCode);
        if (CollectionUtils.isEmpty(wdList)) {
            addJobDataLog(err, wh);
            if (!isDetailOnly && !flag) {
                //更新明细单量
                wgr.setNum(1 + wgr.getNum());
                wh.setLastTime(new Date());
                warningGroupMapper.updateByPrimaryKeySelective(wgr);
            }
            if(org.apache.commons.lang3.StringUtils.isNotBlank(platfromCode) && !flag){
                //更新项单量
                wh.setTotalOrderNumber(wh.getTotalOrderNumber() + 1);
                wh.setCurrentOrderNumber(wh.getCurrentOrderNumber() + 1);
                wh.setLastTime(new Date());
                warningHeaderService.updateWarnHeader(wh);
            }

            WarningDetail wd = new WarningDetail();

            wd = new WarningDetail();
            wd.setGroupId(wgr.getId());
            wd.setPlatfromCode(platfromCode);

            wd.setTouchTime(new Date());
            wd.setStatus(2);
            wd.setIsClose(1);
            wd.setCreateTime(new Date());

            WarningUserDTO warningUserDTO = new WarningUserDTO();
            warningUserDTO.setSourceType("OLD_TC");//旧TC
            if (shopId != null) {
                warningUserDTO.setShopId(shopId);
            } else {
                warningUserDTO.setShopId(-999);
            }
            List<WarningShop> warningShopLsit = warningShopMapper.findWarningShopByShopIdAndType(warningUserDTO);
            if (CollectionUtils.isNotEmpty(warningShopLsit)) {
                wd.setShopName(warningShopLsit.get(0).getShopName());
                wd.setShopId(warningShopLsit.get(0).getId());//主键
            } else {
                wd.setShopId(shopId);
                wd.setShopName(shopName);
            }
            warningDetailMapper.insertSelective(wd);
        }
    }


    /**
     * 处理预警项、订单
     *
     * @param err
     * @param wh
     * @param flag   true:error表数据，false:warn数据
     */
    @DS(value = "tc_warning")
    @Transactional
    public void updateHasPlatfromCodeStatus(ErrorMsg err, WarningHeader wh, boolean flag)  {
        String platfromCode = err.getPlatformCode();
        if (WarnOnceCodeUtil.ONLY_ONCE_CODE.contains(wh.getCode())) {//单一只有一个
            updateStatus(wh);
        } else {
            WarningGroup wgr =  warningGroupMapper.getWarningGroupByHeaderIdAndStatus(wh.getId(), 2);
            if(null != wgr){
                //未完成的detail
                List<WarningDetail> wdList = warningDetailMapper.getWarningDetailDto(wgr.getId(), 2, platfromCode);
                if (CollectionUtils.isNotEmpty(wdList)) {
                    if (flag){
                        addJobDataLog(err, wh);
                    }
                    _logger.info(platfromCode + "：对应有未处理的明细");
                    WarningDetail wd = wdList.get(0);
                    wd.setDelTime(new Date());
                    int mins = DateUtil.getDatePoor(new Date(), wd.getCreateTime()).intValue();
                    String minMsg = DateUtil.getHourAndMins(mins);
                    wd.setMins(mins);
                    wd.setTimeConsuming(minMsg);
                    wd.setStatus(1);
                    wd.setIsClose(2);
                    wd.setAttribuate1("系统自动关闭");

                    //更新详情
                    warningDetailMapper.updateByPrimaryKeySelective(wd);

                    int groupId = wd.getGroupId();
                    _logger.info("更新分组>: "+ groupId);
                    WarningGroup warningGroup = warningGroupMapper.selectByPrimaryKey(groupId);


                    //未处理数量
                    int undoNum = wh.getUnProcessedNum();
                    //已处理数量
                    int dealNum = wh.getProcessedNum();
                    //未处理订单
                    int undoOrders = wh.getCurrentOrderNumber();

                    //判断当前分组是否还有未处理数据
                    List<WarningDetail> li = warningDetailMapper.getWarningDetailDto(groupId, 2, null);
                    if (CollectionUtils.isEmpty(li)) {//全部完成
                        String msg = DateUtil.getHourAndMins(warningDetailMapper.getSumMinsById(groupId));
                        warningGroup.setStatus(1);
                        warningGroup.setDelTime(new Date());
                        warningGroup.setTimeConsuming(msg);

                        warningGroupMapper.updateByPrimaryKeySelective(warningGroup);

                        dealNum = dealNum + 1;
                        undoNum = undoNum - 1;
                    }
                    undoOrders = undoOrders - 1;

                    wh.setProcessedNum(dealNum);
                    wh.setUnProcessedNum(undoNum);
                    wh.setCurrentOrderNumber(undoOrders);
                    wh.setLastTime(new Date());
                    warningHeaderService.updateWarnHeader(wh);
                }
            }
        }
    }


    private void updateStatus(WarningHeader wh) {
        WarningGroup wg = warningGroupMapper.getWarningGroupByHeaderIdAndStatus(wh.getId(), 2);
        if (null != wg) {
            _logger.info(wh.getCode() + ": 有未处理");
            int groupId = wg.getId();
            //未处理
            List<WarningDetail> wdList = warningDetailMapper.getWarningDetailDto(groupId, 2, null);
            WarningDetail wd = wdList.get(0);
            wd.setDelTime(new Date());
            int mins = DateUtil.getDatePoor(new Date(), wd.getSendTime()).intValue();
            String minMsg = DateUtil.getHourAndMins(mins);
            wd.setMins(mins);
            wd.setTimeConsuming(minMsg);
            wd.setStatus(1);
            wd.setIsClose(2);
            wd.setAttribuate1("系统自动");

            //更新详情
            warningDetailMapper.updateByPrimaryKeySelective(wd);
            //更新分组
            wg.setStatus(1);
            wg.setDelTime(new Date());
            wg.setTimeConsuming(minMsg);

            warningGroupMapper.updateByPrimaryKeySelective(wg);


            wh.setUnProcessedNum(0);
            wh.setProcessedNum(wh.getProcessedNum() + 1);
            wh.setCurrentOrderNumber(0);
            wh.setLastTime(new Date());

            warningHeaderService.updateWarnHeader(wh);

        }else{
            _logger.info(wh.getCode() + ": 没有未处理");
        }
    }


    /**
     * 定时处理比对预警数量
     */
    @Transactional
    public void dealWarnReport(){
        List<WarningHeader> headerList = warningHeaderMapper.getAllWarning();

        for (WarningHeader wh: headerList){
            WarningGroupReportDTO dto = warningGroupMapper.getWarngroupReport(wh.getId());

            int totalNum = dto.getTotalNum();
            int total = wh.getTotal();

            int isdoNum = dto.getIsdoNum();
            int undoNum = dto.getUndoNum();
            int unProcessedNum = wh.getUnProcessedNum();
            int processedNum = wh.getProcessedNum();

            if (totalNum == total && isdoNum+undoNum == total
                    && unProcessedNum == undoNum && processedNum == isdoNum)
                continue;
            else
                wh.setTotal(totalNum);
                wh.setProcessedNum(isdoNum);
                wh.setUnProcessedNum(undoNum);
                warningHeaderMapper.updateByPrimaryKeySelective(wh);

                warnHeadRedis.delWarnHeaderrRedis(wh.getCode());
        }
    }



}
