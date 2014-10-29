package com.comtop.mobile.market

class Comment {
	String  content
	Date createTime
	int indexOrder
	static belongsTo = [good:Good,fromUser: User,toUser:User]
    static constraints = {
    }
}
