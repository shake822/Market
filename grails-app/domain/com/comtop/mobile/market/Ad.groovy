package com.comtop.mobile.market

class Ad {
	String id
	String description
	int indexOrder
	String imgName
	static constraints = { description(nullable: true) }
}
