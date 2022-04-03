package com.purang.manifest.domain.deliver.entity.dp;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * 出库清单的单号原语，以"CKQD"开头 + "yyyyMMdd" + 4位(左侧0补齐)递增序号<br>
 * 1、如果当天首单，序号起始值为"0001"<br>
 * 2、如果非当天首单，序号值为在最近的一笔单子序号基础上加1<br>
 * 3、总共16位长度如"CKQD202112080001"
 */
@Data
@Builder
public class BusinessNoDP {
    /**
     * 约定出库清单的单号前缀
     */
    public static final String PREFIX = "CKQD";

    /**
     * 约定规则单号长度要求为16位
     */
    public static final byte REQUIRED_LENGTH = 16;

    /**
     * 每天首单的起始序号
     */
    private static final String INIT_NO = "0001";

    /**
     * 单号
     */
    private String value;

    public BusinessNoDP() {
    }

    public BusinessNoDP(String businessNo) {
        this.value = businessNo;
    }

    @Override
    public String toString() {
        return value;
    }

    /**
     * 创建下一个业务单号
     *
     * @return 返回下一个业务单号
     */
    public BusinessNoDP createNextBusinessNo() {
        String currentDate = DateUtil.format(new Date(), DatePattern.PURE_DATE_PATTERN);
        BusinessNoDP businessNoDP = new BusinessNoDP();
        if (StringUtils.isNotEmpty(value)) {
            businessNoDP.setValue(PREFIX + (Long.parseLong(value.substring(4)) + 1));
        } else {
            businessNoDP.setValue(PREFIX + currentDate + INIT_NO);
        }
        return businessNoDP;
    }
}
