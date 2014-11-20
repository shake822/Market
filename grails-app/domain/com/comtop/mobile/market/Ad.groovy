package com.comtop.mobile.market

import groovy.transform.ToString;

@ToString(excludes = "class")
class Ad {
    String id
    String description
    int indexOrder
    String imgName
    String imgAction
    static constraints = {
        description(nullable: true)
        imgAction(nullable: true)
    }
}
