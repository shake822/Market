package com.comtop.mobile.market

class Favorites {
	String id
	Date createTime
	boolean deleteFlag
	static belongsTo = [user:User,good:Good]
    static constraints = {
    }
}
