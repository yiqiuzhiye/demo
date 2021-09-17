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
 * 机器人工作状态
 * 
 * @author alex.xu
 * 2019/08/13
 *
 */
@Getter
@AllArgsConstructor
public enum RobotWorkingStatus {
	/**
	 * 执行任务中
	 */
	WORKING(1, "执行任务中"),

	/**
	 * 非执行任务中
	 */
	NOT_WORKING(0, "非执行任务中");
	
	/**
	 * 值
	 */
	private final Integer value;
	/**
	 * 描述
	 */
	private final String name;



}
