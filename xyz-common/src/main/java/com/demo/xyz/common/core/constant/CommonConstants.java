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

package com.demo.xyz.common.core.constant;

/**
 * 常量定义类
 *
 * @author admin
 */
public interface CommonConstants {

    public final String STATUS_INVALID = "0";

    public final String STATUS_VALID = "1";

    public final String STATUS_EXCEPTION = "2";

    public final String STATUS_DELETE = "9";

    public final int STATUS_INVALID_NUM = 0;

    public final int STATUS_VALID_NUM = 1;

    public final int STATUS_EXCEPTION_NUM = 2;

    public final int STATUS_DELETE_NUM = 9;

    /**
     * 子系统类型：0:达闼系统 1:租户
     */
    public final int SYSTEM_TYPE_DATA = 0;

    public final int SYSTEM_TYPE_TENANT = 1;

    /**
     * 开放，打开状态
     */
    public final String OPEN = "1";
    /**
     * 关闭，关闭状态
     */
    public final String CLOSE = "0";

    /**
     * 是
     */
    public final String YES = "1";
    /**
     * 否
     */
    public final String NO = "0";

    public final Integer NUM_YES = 1;

    public final Integer NUM_NO = 0;

    /**
     * 图片扩展名
     */

    public final String IMAGE_EXT_JPG = ".jpg";

    public final String IMAGE_EXT_PNG = ".png";

    public final String IMAGE_EXT_GIF = ".gif";

    /**
     * BOSS
     */
    public final int SYSTEM_BOSS = 1;
    /***
     * MCMS药柜管理系统
     */
    public final int SYSTEM_MCMS = 101;
    /**
     * 租户用户管理系统
     */
    public final int SYSTEM_TUMS = 100;

    /**
     * 租户端货柜管理系统
     */
    public final int SYSTEM_CVMS = 103;

    /**
     * 菜单
     */
    String MENU = "0";

    /**
     * 编码
     */
    String UTF8 = "UTF-8";

    /**
     * JSON 资源
     */
    String CONTENT_TYPE = "application/json; charset=utf-8";

    /**
     * 前端工程名
     */
    String FRONT_END_PROJECT = "crss-ui";

    /**
     * 后端工程名
     */
    String BACK_END_PROJECT = "crss";

    /**
     * 成功标记
     */
    Integer SUCCESS = 0;
    /**
     * 失败标记
     */
    Integer FAIL = 1;

    /**
     * 部分失败，部分成功
     */
    Integer PARTIAL_SUCCESS_PARTIAL_FAIL = 2;

    /**
     * 验证码前缀
     */
    String DEFAULT_CODE_KEY = "DEFAULT_CODE_KEY_";

    String BOSS_ADMIN = "admin";

    int BOSS_TENANT_ID = 1;

    /* =====用户订单状态===== */
    /**
     * 0-待付款
     */
    public final int ORDER_PAY_WAIT = 0;

    /**
     * 1-请求扣款成功
     */
    public final int ORDER_PAY_REQUEST_SUCCESS = 1;
    /**
     * 2-已付款成功
     */
    public final int ORDER_PAY_SUCCESS = 2;
    /**
     * 3-付款失败
     */
    public final int ORDER_PAY_FAIL = 3;
    /**
     * 4-待退款
     */
    public final int ORDER_REFUND_WAIT = 4;
    /**
     * 5-请求退款成功
     */
    public final int ORDER_REFUND_REQUEST_SUCCESS = 5;
    /**
     * 6-已退款
     */
    public final int ORDER_REFUND_SUCCESS = 6;
    /**
     * 7-退款失败
     */
    public final int ORDER_REFUND_FAIL = 7;

    /**
     * 8-坏账
     */
    public final int ORDER_BAD_DEBTS = 8;

    /**
     * SmartVision默认租户编码（用于货柜项目获取人脸集，需事先在ROC上开通并配置）
     */
    String CV_DEFAULT_TENANT_CODE = "crss_vending";

    public final String TOPIC_ELEVATOR2SERVER = "elevator2server/{hardkey}";

    public final String TOPIC_SERVER2ELEVATOR = "server2elevator/{hardkey}";


    public final String REG_MSG_NOTIFY_KEY = "crss_object:notify:{hardkey}_{msgId}";

    public final String REG_MSG_NOTIFY_DATA_KEY = "crss_object:notify:{hardkey}_{msgId}:data";

    public final String ELEVATOR_SERVER_LAST_MSG_KEY = "crss_elevator:server_last_msg";
    public final String ELEVATOR_CLIENT_LAST_MSG_KEY = "crss_elevator:client_last_msg";

    public final String ELEVATOR_ONLINES_KEY = "crss_elevator:onlines";

    public final String CRSS_GATEWAY_USER_DETAILS_KEY = "crss_gateway:user_details";

    /**
     * cms服务 提供给端侧的默认页面对应的文件服务器上的objectKey
     */
    public final String ROBOT_DEFAULT_PAGE_KEY = "default.html";

    /**
     * 手续费费率,千分之六
     */
    public final Double HANDLING_FEE_RATE = 0.006;

    /**
     * 针对扶贫商品手续费，百分之一
     */
    public final Double SHFP_HANDLING_FEE_RATE = 0.01;

    // 消息类型为告警
    public final Integer MESSAGE_TYPE_ALARM = 1;

    // 消息类型为事件
    public final Integer MESSAGE_TYPE_EVENT = 2;

    /**
     * 申请状态
     */
    // 审核中
    public final Integer APPLY_STATUS_DOING = 0;
    // 审核通过
    public final Integer APPLY_STATUS_SUCCESS = 1;
    // 审核未通过
    public final Integer APPLY_STATUS_FAIL = 2;

    /**
     * 业务子系统
     */
    public final String SYS_CXOS = "cxos";
    public final String SYS_CAOS = "caos";
    public final String SYS_CDOS = "cdos";

    /**
     * 工作中
     */
    public final Integer TASK_WORKING = 1;

    /**
     * CLIENT_CREDENTIALS
     */
    public final String CLIENT_CREDENTIALS = "client_credentials";

    /**
     * TOKEN_EXCHANGE_GRANT_TYPE
     */
    public final String TOKEN_EXCHANGE_GRANT_TYPE = "urn:ietf:params:oauth:grant-type:token-exchange";

    /**
     * REQUESTED_TOKEN_TYPE
     */
    public final String REQUESTED_TOKEN_TYPE = "urn:ietf:params:oauth:token-type:refresh_token";

    /**
     *
     */
    public final Integer TASKING_OFFLINE = 2100012;

}
