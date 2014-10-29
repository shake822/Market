package com.comtop.mobile.market

class Favorites {
	Date createTime
	boolean deleteFlag
	static belongsTo = [user:User,good:Good]
    static constraints = {
    }
}
