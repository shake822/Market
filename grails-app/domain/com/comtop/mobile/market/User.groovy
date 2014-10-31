package com.comtop.mobile.market

import groovy.transform.ToString;

@ToString
class User {
	
	String id
	
	/**用户名*/
	String username
	
	/**账号*/
	String account
	
	/**密码*/
	String password
	
	/**电话*/
	String phone
	
	/**部门*/
	String department
	
	/**图像地址*/
	String headImg
	
	/**地理位置*/
	String address
	
	static constraints = {
		phone(nullable: true)
		department(nullable: true)
		headImg(nullable: true)
		address(nullable: true)
	}
}
