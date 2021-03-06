package com.splan.data.datacenter.bean;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.splan.base.annotation.ExcelAnnotation;
import com.splan.base.bean.BetOrderDetailBean;
import com.splan.base.bean.BetTopicsBean;
import com.splan.base.enums.OrderStatus;
import com.splan.base.enums.SyncStatus;
import com.splan.base.enums.WinLoseStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * bet_order
 */
@TableName(value = "bet_order")
@Data
@ApiModel
public class BetOrderBean implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)
    @JsonIgnore
    private Long id;
    /**
     * 竞猜选项id
     */
    @TableField(value = "bet_option_id")
    private Integer betOptionId;

    /**
     * 
     */
    @TableField(value = "check_odd")
    private BigDecimal checkOdd;

    /**
     * 下注时的赔率
     */
    @TableField(value = "odd")
    @ApiModelProperty(value = "赔率")
    @ExcelAnnotation(id=1,name={"赔率"},width = 5000)
    private BigDecimal odd;

    /**
     * 下注金额
     */
    @TableField(value = "amount")
    @ApiModelProperty(value = "下单金额")
    @ExcelAnnotation(id=1,name={"下单金额"},width = 5000)
    private Integer amount;


    /**
     * 影响金额
     */
    @TableField(value = "influence_amount")
    @ApiModelProperty(value = "影响金额")
    @JsonIgnore
    private Integer influenceAmount;

    /**
     * 预计获得
     */
    @TableField(value = "estimated_reward")
    @ApiModelProperty(value = "派彩金额")
    @ExcelAnnotation(id=1,name={"派彩金额"},width = 5000)
    private BigDecimal estimatedReward;

    /**
     * 下注时账户总金额
     */
    @TableField(value = "account_coin")
    private BigDecimal accountCoin;

    /**
     * 
     */
    @TableField(value = "rollback_lock_coin")
    @JsonIgnore
    private BigDecimal rollbackLockCoin;

    /**
     * 用户id
     */
    @TableField(value = "tenant_user_no")
    @JsonIgnore
    private Long tenantUserNo;

    /**
     * 订单流水
     */
    @TableField(value = "tenant_order_no")
    @ApiModelProperty(value = "注单ID")
    @ExcelAnnotation(id=1,name={"注单ID"},width = 5000)
    private String tenantOrderNo;

    /**
     * win,lose
     */
    @TableField(value = "win_lose")
    private WinLoseStatus winLose;

    /**
     * 是否对冲订单
     */
    @TableField(value = "hedge")
    @JsonIgnore
    private Boolean hedge;

    /**
     * 是否对冲订单
     */
    @TableField(value = "ip")
    @JsonIgnore
    private String ip;

    /**
     * 失败，未结算，已结算
     */
    @TableField(value = "status")
    private OrderStatus status;

    /**
     * dataId
     */
    @TableField(value = "data_id")
    @JsonIgnore
    private Integer dataId;

    @TableField(value = "cameo")
    @JsonIgnore
    private String cameo;

    /**
     * 是否串单
     */
    @TableField(value = "order_type")
    private Boolean orderType;

    @TableField(value = "fail_result")
    @JsonIgnore
    private String failResult;

    @TableField(value = "bonus_check")
    private Integer bonusCheck;

    @TableField(exist = false)
    @JsonIgnore
    private List<BetOrderDetailBean> betOrderDetails;

    @TableField(exist = false)
    @JsonIgnore
    private List<BetTopicsBean> betTopicsBean;

    @TableField(exist = false)
    @JsonIgnore
    private Integer gameNo;

    @TableField(exist = false)
    @JsonIgnore
    private String mobile;

    @TableField(exist = false,value = "real_name")
    @JsonIgnore
    private String realName;

    @TableField(exist = false,value = "user_name")
    @JsonIgnore
    private String userName;

    @TableField(exist = false,value = "invite_code")
    @JsonIgnore
    private String inviteCode;


    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @ApiModelProperty(value = "投注时间")
    @ExcelAnnotation(id=1,name={"投注时间"},width = 5000)
    private Date createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private Date updateTime;

    @TableField(value = "check_time")
    @ApiModelProperty(value = "结算时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private Date checkTime;

    @TableField(value = "rollback_time")
    @JsonIgnore
    private Date rollbackTime;



    @TableField(exist = false,value = "name_en")
    @JsonIgnore
    private String nameEn;

    @TableField(value = "sync_flag")
    @JsonIgnore
    private SyncStatus syncFlag;

    @TableField(exist = false,value = "api_id")
    @JsonIgnore
    private String apiId;

    @TableField(exist = false,value = "extra_id")
    private String extraId;

    @TableField(exist = false,value = "channel")
    @JsonIgnore
    private String channel="";

    @TableField(exist = false,value = "useracc")
    @JsonIgnore
    private String useracc;

    @TableField(exist = false,value = "gametype")
    @JsonIgnore
    private String gametype;


    @TableField(exist = false,value = "rollback_coin")
    @JsonIgnore
    private BigDecimal rollbackCoin;

    //xBet
//    @TableField(exist = false,value = "name_en")
//    private String gameName;

    @TableField(exist = false,value = "vs_detail")
    @JsonIgnore
    private String vsDetail;

    @TableField(exist = false,value = "vs")
    @JsonIgnore
    private String vs;

    @TableField(exist = false,value = "betId")
    @JsonIgnore
    private String betId;

    @ApiModelProperty(value = "比赛时间")
    @ExcelAnnotation(id=1,name={"比赛时间"},width = 5000)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @TableField(exist = false)
    private Date matchTime;

    @ApiModelProperty(value = "比赛")
    @ExcelAnnotation(id=1,name={"比赛"},width = 5000)
    @TableField(exist = false)
    private List<String> matchInfo;

    @ApiModelProperty(value = "盘口详情")
    @ExcelAnnotation(id=1,name={"盘口详情"},width = 5000)
    @TableField(exist = false)
    private List<String> betInfo;

    @ApiModelProperty(value = "投注账号")
    @ExcelAnnotation(id=1,name={"投注账号"},width = 5000)
    @TableField(exist = false)
    @JsonIgnore
    private String orderAccount;

    @ApiModelProperty(value = "状态")
    @ExcelAnnotation(id=1,name={"状态"},width = 5000)
    @TableField(exist = false)
    @JsonIgnore
    private String orderStatusCname;

    /*@JsonIgnore
    @TableField(exist = false)
    private BusinessConfigBean businessConfigBean;*/

    @TableField(exist = false)
    private List<String> play;

    @TableField(exist = false)
    private List<String> orderInfo;
}