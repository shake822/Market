package com.comtop.mobile.market


class Classify {

	String id
	String code
	String name
	String description
	static  belongsTo =[parent:Classify]
	static mapping={ parent cascade :"none" }
	static constraints = {
		code(maxLength:4)
		parent(nullable:true)
		description(nullable:true)
	}
	String toString(){
		"$id:$code"
	}
}
