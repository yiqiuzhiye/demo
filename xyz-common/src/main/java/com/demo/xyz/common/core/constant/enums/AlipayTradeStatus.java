
package com.demo.xyz.common.core.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 支付宝交易状态枚举
 * 
 * @author charles
 *
 */
@Getter
@AllArgsConstructor
public enum AlipayTradeStatus {

	WAIT_BUYER_PAY("WAIT_BUYER_PAY", "交易创建"), TRADE_SUCCESS("TRADE_SUCCESS", "支付完成"),
	TRADE_FINISHED("TRADE_FINISHED ", "交易完结"), TRADE_CLOSED("TRADE_CLOSED", "交易关闭");

	private final String value;

	private final String name;

	// 通过value获取name
	public static String getName(Integer value) {
		for (AlipayTradeStatus status : AlipayTradeStatus.values()) {
			if (status.getValue().equals(value)) {
				return status.name;
			}
		}
		return null;
	}

}
