
package com.demo.xyz.common.core.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 支付宝签约返回状态枚举值
 * 
 * @author charles
 *
 */
@Getter
@AllArgsConstructor
public enum AlipayContractSignStatus {

	TEMP("TEMP", "暂存，协议未生效过"), NORMAL("NORMAL", "正常"), STOP("STOP ", "暂停");

	private final String value;

	private final String name;

	// 通过value获取name
	public static String getName(Integer value) {
		for (AlipayContractSignStatus status : AlipayContractSignStatus.values()) {
			if (status.getValue().equals(value)) {
				return status.name;
			}
		}
		return null;
	}

}
