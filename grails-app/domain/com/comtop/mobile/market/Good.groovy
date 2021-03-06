package com.comtop.mobile.market

import groovy.transform.ToString

@ToString(excludes = "class")
class Good {
    String id
    String name
    String description
    String classify
    String status
    String transStatus
    int price
    String code
    String recency
    boolean deleteFlag
    Date createTime
    Date updateTime
    static belongsTo = [user: User]
    static hasMany = [pictures: GoodPicture]
    static constraints = {
        code(blank: true)
        pictures(blank: true)
    }
    static mapping = {
        pictures cascade: "all"
    }
}
