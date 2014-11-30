package com.comtop.mobile.market.util

/**
 * Created by zhaoqunqi on 14-11-21.
 */
class ConstantGroovyUtils {
    /**
     * 商品的类型信息
     */
    static def goodStatus =
            [0: '出售', 1: '交换', 2: '求购']


    static def goodRecency =
            [7: '八成以下', 8: '八成', 9: '九成', 10: '全新']

    static def goodTransStatus =
            [0: '交易中', 1: '已取消', 2: '已完成']

}
