package com.comtop.mobile.market

class Classify {
	
	String id
	String code
	String name
	String description
	static  belongsTo =[parent:Classify]
    static constraints = {
		parent(nullable:true)
		description(nullable:true)
    }
}
