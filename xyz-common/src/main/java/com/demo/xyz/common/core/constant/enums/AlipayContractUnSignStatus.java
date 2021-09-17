
package com.demo.xyz.common.core.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 支付宝解约返回状态枚举值
 * 
 * @author charles
 *
 */
@Getter
@AllArgsConstructor
public enum AlipayContractUnSignStatus {

	UNSIGN("UNSIGN", "协议解约状态");

	private final String value;

	private final String name;

	// 通过value获取name
	public static String getName(Integer value) {
		for (AlipayContractUnSignStatus status : AlipayContractUnSignStatus.values()) {
			if (status.getValue().equals(value)) {
				return status.name;
			}
		}
		return null;
	}

}
