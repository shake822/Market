package com.comtop.mobile.market

class Good {
	String name
	String description
	String type
	String state
	float price
	String code
	String recency
	boolean deleteFlag
	Date createTime
	Date updateTime
	static belongsTo = [user: User]
	static hasMany = [pictures:GoodPicture]
    static constraints = {
    }
}
