package com.demo.xyz.common.security.service;

import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

/**
 * 扩展用户信息
 *
 * @author admin
 */
@Data
public class CrssUserInfo implements Serializable {


	private static final long serialVersionUID = -2485962623814068748L;
	/**
	 * 用户ID
	 */

	private Integer id;
	/**
	 * 租户ID
	 */

	private Integer tenantId;
	/**
	 * 租户编码
	 */

	private String tenantCode;

	/*
	 * 客户名称
	 */
	@Getter
	private String tenantName;
	@Getter
	private String username;

	/**
	 * 虚拟运营商Id
	 */
	@Getter
	private Integer vopId;

	@Getter
	private Integer bizAdminFlag;
	@Getter
	private Integer bossAdminFlag;

	private Integer adminFlag;
	/**
	 * 用户姓名
	 */
	@Getter
	private String name;
	/**
	 * 分支机构ID
	 */
	@Getter
	private Integer branchId;

	@Getter
	private String logo;


	public Integer getAdminFlag() {
		Integer tempAdminFlag = -1;
		if (this.bizAdminFlag != null && this.bossAdminFlag != null) {
			tempAdminFlag = this.bizAdminFlag | this.bossAdminFlag;
		}
		return tempAdminFlag;
	}
}
