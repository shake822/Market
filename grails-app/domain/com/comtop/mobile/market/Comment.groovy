package com.comtop.mobile.market

class Comment {
    String id
    String content
    Date createTime
    boolean isRead
    static belongsTo = [good: Good, fromUser: User]
    static hasMany = [toUsers: User]
    static constraints = {
    }
}
