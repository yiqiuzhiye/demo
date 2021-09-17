/*
 *  Copyright (c) 2019-2020, 冷冷 (wangiegie@gmail.com).
 *  <p>
 *  Licensed under the GNU Lesser General Public License 3.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  <p>
 * https://www.gnu.org/licenses/lgpl.html
 *  <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.demo.xyz.common.core.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 注册类型, 0:未注册  1:已注册
 * 
 * @author alex.xu
 * 
 */
@Getter
@AllArgsConstructor
public enum RegistrationType {
	/**
	 * App授权来源, 0:微信 小程序 1:支付宝小程序
	 */
	REGISTERED(1, "已注册"),
	UNREGISTERED(0, "未注册");
	/**
	 * 类型值
	 */
	private final Integer value;
	/**
	 * 类型名称
	 */
	private final String name;
	
	// 通过value获取name
	public static String getName(Integer value) {
		for (RegistrationType status : RegistrationType.values()) {
			if (status.getValue().equals(value)) {
				return status.name;
			}
		}
		return null;
	}

	
}
