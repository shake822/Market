package com.comtop.mobile.market

class Feedback {

    String id
    String content
    Date createTime
    boolean isRepeat = false
    static belongsTo = [user: User]
    static hasMany = [repeats:Feedback]
    static constraints = {
        repeats(blank: true)
    }
    static mapping = {
        repeats fetch: 'join'
    }
}
