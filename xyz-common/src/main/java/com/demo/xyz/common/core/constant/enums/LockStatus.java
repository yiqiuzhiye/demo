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
 * 社交登录类型
 * 
 * @author alex.xu
 * 2019/08/13
 *
 */
@Getter
@AllArgsConstructor
public enum LockStatus {
	/**
	 * 锁打开
	 */
	OPEN(1, "打开"),

	/**
	 * 锁关闭
	 */
	CLOSE(0, "关闭");
	
	/**
	 * 类型
	 */
	private final Integer value;
	/**
	 * 描述
	 */
	private final String name;
	
	// 通过value获取name
	public static String getName(String value) {
		for (LockStatus status : LockStatus.values()) {
			if (status.getValue().equals(value)) {
				return status.name;
			}
		}
		return null;
	}


}