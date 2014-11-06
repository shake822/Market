package com.comtop.mobile.market

import groovy.transform.ToString


@ToString
class Classify {

	String id
	String code
	String name
	String description
	static  belongsTo =[parent:Classify]
	static mapping={ parent cascade :"none" }
	static constraints = {
		code(maxSize:4)
		parent(nullable:true)
		description(nullable:true)
	}
}
