package com.comtop.mobile.market

import groovy.transform.ToString;

@ToString
class Ad {
	String id
	String description
	int indexOrder
	String imgName
	static constraints = { description(nullable: true) }
}
