package com.comtop.mobile.market

import com.comtop.mobile.market.util.ConstantUtils
import grails.transaction.Transactional
import org.hibernate.FetchMode
import org.hibernate.criterion.CriteriaSpecification

@Transactional
class GoodService {

    /**
     * 列表查詢
     * @param pageSize 一页显示记录数
     * @param currentPage 当前页面
     * @param type 类型
     * @return true:还有更多记录;fasle:没有更多记录
     */
    def find(int pageSize, int currentPage, String type) {
        println "${pageSize}   ${currentPage}"
        def returnMap = [:]
        def params = [:]
        params.fetchSize = pageSize + 1
        params.order = "desc"
        params.sort = "createTime"
        params.offset = (currentPage - 1) * pageSize
        params.fetch = [pictures: "join"]
//        params.pictures =[indexOrder:0]
        println params
//        def c = Good.createCriteria()
//        def list = c.list(params){
//            pictures{
//                sqlRestriction "id =xxxx"
//            }
//        }
        println "xxxxx"
//        def list = Good.withCriteria {
//            fetchMode "pictures", FetchMode.JOIN
//            pictures(CriteriaSpecification.) {
//                eq "id", "b"
//            }
//        }
        def list = Good.list(params)
        println "${list.size()}"
        returnMap.hasMore = (list.size() == pageSize + 1)
        if (list.size() == pageSize + 1) {
            returnMap.hasMore = true
            list.remove(pageSize)
        } else {
            returnMap.hasMore = false
        }


        returnMap.data = list.collect()
                {
                    [
                            type       : it.type,
                            userId     : it.userId,
                            description: it.description,
                            deleteFlag : it.deleteFlag,
                            name       : it.name,
                            price      : it.price,
                            recency    : it.recency,
                            createTime : it.createTime,
                            state      : it.state,
                            code       : it.code,
                            pictures   : it.pictures?.size() > 0 ? it.pictures.each {
                                if (it.indexOrder == 0) {
                                    it.id
                                }
                            } : null
                    ]
                }
        returnMap
    }
}
