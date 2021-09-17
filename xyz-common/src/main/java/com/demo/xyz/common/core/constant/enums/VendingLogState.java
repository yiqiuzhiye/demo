/*
 * Copyright (c) 2019-2020, 冷冷 (wangiegie@gmail.com). <p> Licensed under the GNU Lesser General Public License 3.0 (the
 * "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 * <p> https://www.gnu.org/licenses/lgpl.html <p> Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and limitations under the
 * License.
 */

package com.demo.xyz.common.core.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 购物，上货，下架 的货柜事件状态:1 处理中， 2:已完成
 *
 * @author alex.xu
 */
@Getter
@AllArgsConstructor
public enum VendingLogState {
    /**
     * 购物，上货，下架 的货柜事件状态:1:处理中;2:已完成;3:关门失败异常;4:开门超时;5:商品识别失败
     */
    INIT(0, "未开始"),
    PROCESS(1, "处理中"),
    FINISH(2, "已完成"),
    EXCEPTION(3, "关门失败异常"),
    OPEN_TIMEOUT(4, "用户未开门"),
    IDENTIFY_FAIL(5, "商品识别失败"),
    CLOSE_DOOR(6, "关门"),
    HARI_NOTIFY(7, "HARI通知成功"),
    DATA_IDENTIFY_FAIL(8,"自研识别失败"),
    HARIX_IDENTIFY_FAIL(9,"harix异常"),
    OPENDOOR_TIMEOUT(10,"开门超时")
    ;

    /**
     * 类型值
     */
    private final Integer value;
    /**
     * 类型名称
     */
    private final String name;

    // 通过 value 获取 name
    public static String getName(Integer value) {
        for (VendingLogState status : VendingLogState.values()) {
            if (status.getValue().equals(value)) {
                return status.name;
            }
        }
        return null;
    }
}
