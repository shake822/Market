package com.comtop.mobile.market

class SearchHistory {

	String keyWord
	Date searchTime
	static belongsTo = [user:User]
    static constraints = {
    }
}
