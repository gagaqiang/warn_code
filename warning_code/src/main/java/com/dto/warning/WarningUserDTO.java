package com.dto.warning;

import com.entity.warning.WarningUser;
import com.utils.SerializeUtil;
import io.swagger.annotations.ApiModel;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

/**
 * @Auther: hsl
 * @Date: 2018/9/3 14:41
 * @Description:
 */
@ApiModel
public class WarningUserDTO extends WarningUser implements Serializable {

    private String userName;
    private String wechatUserId;
    private String shopName;
    private Integer shopId;
    private String newFlag;
    private String sourceType;
    private String operUser;

    public String getOperUser() {
        if (StringUtils.isNotBlank(operUser)){
            try {
                operUser = java.net.URLDecoder.decode(operUser,"UTF-8");
            } catch (UnsupportedEncodingException e) {
            }
        }
        return operUser;
    }

    public void setOperUser(String operUser) {
        this.operUser = operUser;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getNewFlag() {
        return newFlag;
    }

    public void setNewFlag(String newFlag) {
        this.newFlag = newFlag;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getWechatUserId() {
        return wechatUserId;
    }

    public void setWechatUserId(String wechatUserId) {
        this.wechatUserId = wechatUserId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
}
