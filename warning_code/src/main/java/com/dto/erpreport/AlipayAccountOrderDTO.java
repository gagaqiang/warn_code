package com.dto.erpreport;

import java.util.Date;

public class AlipayAccountOrderDTO {
    private Integer page;
    private Integer rows;

    private Long id;
    private Integer version;
    private Long tenantId;
    private Date createDate;
    private Date modifyDate;
    private String created;
    private String type;
    private String businessType;
    private String balance;
    private String inAmount;
    private String outAmount;
    private String alipayOrderNo;
    private String merchantOrderNo;
    private String selfUserId;
    private String optUserId;
    private String memo;
    private String sign;
    private String sellerNick;
    private Long shopId;
    private Long firstSysId;
    private String firstSysName;
    private Long secondSysId;
    private String secondSysName;
    private String platFormNo; // 平台单号（订单号）
    private String fenxiaoId;   //分销id
    private Long newPlatformSubjectId = 0L; //对应平台科目id
    private String newPlatformSubjectName = "";//对应平台科目名称
    private Long newSysSubjectId = 0L;      //对应系统科目id
    private String newSysSubjectName = "";   //对应系统科目名称
    private int newType = 1;        // 1：收款；2：费用
    private int isSign = 0;         //是否记账，0否1是
    private String operator;    //记账人
    private Date signTime;      //记账时间
    private int isException = 0;    //是否异常，0否1是
    private String exceptionMsg = "";    //异常信息
    private String returnId;    //IMS返回id或批次号

    // 新增显示字段
    private String inOutStr;   // 支出/收入
    private String inOutAmount;  // 当前金额
    private String sysSubjectName; //系统科目名称（1级+2级）
    private String platFormSubjectName; //平台科目名称
    private String amount; //科目账单汇总统计 金额

    // 新增显示字段 交易订单汇总统计相关
    private String payDate;         //支付时间
    private String shipmentDate;    //发货时间
    private String shiFuAmount;     //实付金额
    private String tuiKuanAmount;   //退款金额
    private String yingShouAmount;  //应收金额
    private String realityInAmount; //实际收益
    private String orderStatus;     //订单状态
    private String returnStatus;    //退款状态

    // 查询条件
    private String beginTime;
    private String endTime;
    private String queryNumber;

    private String debtorCode;           //借方代码
    private String debtorSubject;           //借方科目
    private String debtorXNEGP;           //借方反记账
    private String debtorAccType;           //借方-账户类型
    private String debtorCashFlowCode;     //借方-现金流代码
    private String creditCode;           //贷方代码
    private String creditSubject;           //贷方科目
    private String creditXNEGP;           //贷方反记账
    private String creditAccType;           //贷方-账户类型
    private String creditCashFlowCode;     //贷方-现金流代码

    private Boolean isReconciled;
    private Date reconcileTime;

    private Long originalOrderId;
    private String originalOrderCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getInAmount() {
        return inAmount;
    }

    public void setInAmount(String inAmount) {
        this.inAmount = inAmount;
    }

    public String getOutAmount() {
        return outAmount;
    }

    public void setOutAmount(String outAmount) {
        this.outAmount = outAmount;
    }

    public String getAlipayOrderNo() {
        return alipayOrderNo;
    }

    public void setAlipayOrderNo(String alipayOrderNo) {
        this.alipayOrderNo = alipayOrderNo;
    }

    public String getMerchantOrderNo() {
        return merchantOrderNo;
    }

    public void setMerchantOrderNo(String merchantOrderNo) {
        this.merchantOrderNo = merchantOrderNo;
    }

    public String getSelfUserId() {
        return selfUserId;
    }

    public void setSelfUserId(String selfUserId) {
        this.selfUserId = selfUserId;
    }

    public String getOptUserId() {
        return optUserId;
    }

    public void setOptUserId(String optUserId) {
        this.optUserId = optUserId;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSellerNick() {
        return sellerNick;
    }

    public void setSellerNick(String sellerNick) {
        this.sellerNick = sellerNick;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getFirstSysId() {
        return firstSysId;
    }

    public void setFirstSysId(Long firstSysId) {
        this.firstSysId = firstSysId;
    }

    public String getFirstSysName() {
        return firstSysName;
    }

    public void setFirstSysName(String firstSysName) {
        this.firstSysName = firstSysName;
    }

    public Long getSecondSysId() {
        return secondSysId;
    }

    public void setSecondSysId(Long secondSysId) {
        this.secondSysId = secondSysId;
    }

    public String getSecondSysName() {
        return secondSysName;
    }

    public void setSecondSysName(String secondSysName) {
        this.secondSysName = secondSysName;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getPlatFormNo() {
        return platFormNo;
    }

    public void setPlatFormNo(String platFormNo) {
        this.platFormNo = platFormNo;
    }

    public String getFenxiaoId() {
        return fenxiaoId;
    }

    public void setFenxiaoId(String fenxiaoId) {
        if (null == fenxiaoId) {
            fenxiaoId = "0";
        }
        this.fenxiaoId = fenxiaoId;
    }

    public String getInOutStr() {
        return inOutStr;
    }

    public void setInOutStr(String inOutStr) {
        this.inOutStr = inOutStr;
    }

    public String getInOutAmount() {
        return inOutAmount;
    }

    public void setInOutAmount(String inOutAmount) {
        this.inOutAmount = inOutAmount;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public String getSysSubjectName() {
        return sysSubjectName;
    }

    public void setSysSubjectName(String sysSubjectName) {
        this.sysSubjectName = sysSubjectName;
    }

    public String getPlatFormSubjectName() {
        return platFormSubjectName;
    }

    public void setPlatFormSubjectName(String platFormSubjectName) {
        this.platFormSubjectName = platFormSubjectName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getQueryNumber() {
        return queryNumber;
    }

    public void setQueryNumber(String queryNumber) {
        this.queryNumber = queryNumber;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    public String getShipmentDate() {
        return shipmentDate;
    }

    public void setShipmentDate(String shipmentDate) {
        this.shipmentDate = shipmentDate;
    }

    public String getShiFuAmount() {
        return shiFuAmount;
    }

    public void setShiFuAmount(String shiFuAmount) {
        this.shiFuAmount = shiFuAmount;
    }

    public String getTuiKuanAmount() {
        return tuiKuanAmount;
    }

    public void setTuiKuanAmount(String tuiKuanAmount) {
        this.tuiKuanAmount = tuiKuanAmount;
    }

    public String getYingShouAmount() {
        return yingShouAmount;
    }

    public void setYingShouAmount(String yingShouAmount) {
        this.yingShouAmount = yingShouAmount;
    }

    public String getRealityInAmount() {
        return realityInAmount;
    }

    public void setRealityInAmount(String realityInAmount) {
        this.realityInAmount = realityInAmount;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(String returnStatus) {
        this.returnStatus = returnStatus;
    }

    public Long getNewPlatformSubjectId() {
        return newPlatformSubjectId;
    }

    public void setNewPlatformSubjectId(Long newPlatformSubjectId) {
        if (null == newPlatformSubjectId) {
            newPlatformSubjectId = 0L;
        }
        this.newPlatformSubjectId = newPlatformSubjectId;
    }

    public String getNewPlatformSubjectName() {
        return newPlatformSubjectName;
    }

    public void setNewPlatformSubjectName(String newPlatformSubjectName) {
        if (null == newPlatformSubjectName) {
            newPlatformSubjectName = "";
        }
        this.newPlatformSubjectName = newPlatformSubjectName;
    }

    public Long getNewSysSubjectId() {
        return newSysSubjectId;
    }

    public void setNewSysSubjectId(Long newSysSubjectId) {
        if (null == newSysSubjectId) {
            newSysSubjectId = 0L;
        }
        this.newSysSubjectId = newSysSubjectId;
    }

    public String getNewSysSubjectName() {
        return newSysSubjectName;
    }

    public void setNewSysSubjectName(String newSysSubjectName) {
        if (null == newSysSubjectName) {
            newSysSubjectName = "";
        }
        this.newSysSubjectName = newSysSubjectName;
    }

    public int getNewType() {
        return newType;
    }

    public void setNewType(int newType) {
        this.newType = newType;
    }

    public int getIsSign() {
        return isSign;
    }

    public void setIsSign(int isSign) {
        this.isSign = isSign;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getSignTime() {
        return signTime;
    }

    public void setSignTime(Date signTime) {
        this.signTime = signTime;
    }

    public int getIsException() {
        return isException;
    }

    public void setIsException(int isException) {
        this.isException = isException;
    }

    public String getExceptionMsg() {
        return exceptionMsg;
    }

    public void setExceptionMsg(String exceptionMsg) {
        if (null == exceptionMsg) {
            exceptionMsg = "";
        }
        this.exceptionMsg = exceptionMsg;
    }

    public String getReturnId() {
        return returnId;
    }

    public void setReturnId(String returnId) {
        if (null == returnId) {
            returnId = "";
        }
        this.returnId = returnId;
    }

    public String getDebtorCode() {
        return debtorCode;
    }

    public void setDebtorCode(String debtorCode) {
        if (null == debtorCode) {
            debtorCode = "";
        }
        this.debtorCode = debtorCode;
    }

    public String getDebtorSubject() {
        return debtorSubject;
    }

    public void setDebtorSubject(String debtorSubject) {
        if (null == debtorSubject) {
            debtorSubject = "";
        }
        this.debtorSubject = debtorSubject;
    }

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        if (null == creditCode) {
            creditCode = "";
        }
        this.creditCode = creditCode;
    }

    public String getCreditSubject() {
        return creditSubject;
    }

    public void setCreditSubject(String creditSubject) {
        if (null == creditSubject) {
            creditSubject = "";
        }
        this.creditSubject = creditSubject;
    }

    public String getDebtorXNEGP() {
        return debtorXNEGP;
    }

    public void setDebtorXNEGP(String debtorXNEGP) {
        if (null == debtorXNEGP) {
            debtorXNEGP = "";
        }
        this.debtorXNEGP = debtorXNEGP;
    }

    public String getDebtorAccType() {
        return debtorAccType;
    }

    public void setDebtorAccType(String debtorAccType) {
        if (null == debtorAccType) {
            debtorAccType = "";
        }
        this.debtorAccType = debtorAccType;
    }

    public String getDebtorCashFlowCode() {
        return debtorCashFlowCode;
    }

    public void setDebtorCashFlowCode(String debtorCashFlowCode) {
        if (null == debtorCashFlowCode) {
            debtorCashFlowCode = "";
        }
        this.debtorCashFlowCode = debtorCashFlowCode;
    }

    public String getCreditXNEGP() {
        return creditXNEGP;
    }

    public void setCreditXNEGP(String creditXNEGP) {
        if (null == creditXNEGP) {
            creditXNEGP = "";
        }
        this.creditXNEGP = creditXNEGP;
    }

    public String getCreditAccType() {
        return creditAccType;
    }

    public void setCreditAccType(String creditAccType) {
        if (null == creditAccType) {
            creditAccType = "";
        }
        this.creditAccType = creditAccType;
    }

    public String getCreditCashFlowCode() {
        return creditCashFlowCode;
    }

    public void setCreditCashFlowCode(String creditCashFlowCode) {
        if (null == creditCashFlowCode) {
            creditCashFlowCode = "";
        }
        this.creditCashFlowCode = creditCashFlowCode;
    }

    public Boolean getIsReconciled() {
        return isReconciled;
    }

    public void setIsReconciled(Boolean isReconciled) {
        this.isReconciled = isReconciled;
    }

    public Date getReconcileTime() {
        return reconcileTime;
    }

    public void setReconcileTime(Date reconcileTime) {
        this.reconcileTime = reconcileTime;
    }

    public Long getOriginalOrderId() {
        return originalOrderId;
    }

    public void setOriginalOrderId(Long originalOrderId) {
        this.originalOrderId = originalOrderId;
    }

    public String getOriginalOrderCode() {
        return originalOrderCode;
    }

    public void setOriginalOrderCode(String originalOrderCode) {
        this.originalOrderCode = originalOrderCode;
    }
}
