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
 * boss状态枚举值
 * 
 * @author admin
 *
 */
@Getter
@AllArgsConstructor
public enum BossStatusEnum {

	/**
	 * 停用
	 */
	INVALID(9, "停用"),
	/**
	 * 有效
	 */
	VALID(1, "有效");

	/**
	 * 类型
	 */
	private final int value;
	/**
	 * 描述
	 */
	private final String name;

	// 通过value获取name
	public static String getName(int value) {
		for (BossStatusEnum status : BossStatusEnum.values()) {
			if (status.getValue() == value) {
				return status.name;
			}
		}
		return null;
	}

}
