package com.comtop.mobile.market

class Classify {
	
	String id
	String code
	String name
	static  belongsTo =[parent:Classify]
    static constraints = {
		parent(nullable:true)
    }
}
