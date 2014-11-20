package com.comtop.mobile.market

import groovy.transform.ToString

@ToString(excludes = "class")
class Good {
    String id
    String name
    String description
    String type
    String state
    float price
    String code
    String recency
    boolean deleteFlag
    Date createTime
    Date updateTime
    static belongsTo = [user: User]
    static hasMany = [pictures: GoodPicture]
    static constraints = {
        code(blank: true)
    }
}
