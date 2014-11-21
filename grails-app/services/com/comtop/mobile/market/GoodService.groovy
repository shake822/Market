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
    def findAll(int pageSize, int currentPage, String status) {
        println "${pageSize}   ${currentPage}"
        def returnMap = [:]
        def params = [:]
        params.max = pageSize + 1
        params.order = "desc"
        params.sort = "createTime"
        params.offset = (currentPage - 1) * pageSize
        params.fetch = [pictures: "join"]
//        params.pictures =[indexOrder:0]
//        def c = Good.createCriteria()
//        def list = c.list(params){
//            pictures{
//                sqlRestriction "id =xxxx"
//            }
//        }
//        def list = Good.withCriteria {
//            fetchMode "pictures", FetchMode.JOIN
//            pictures(CriteriaSpecification.) {
//                eq "id", "b"
//            }
//        }
//        def list = Good.findWhere(params)
//        def goodC = Good.createCriteria()
//        def list = goodC.list(params) {
//            eq("type","${type}")
//        }

        def list = Good.findAll(params) {
            eq("status", "${status}")
        }
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
                    def pictureId = null
                    it.pictures?.each() {
                        if (it.indexOrder == 0) {
                            pictureId = ConstantUtils.IMAGE_URL + it.id
                            return
                        }
                    }
                    [
                            classify   : it.classify,
                            userId     : it.userId,
                            description: it.description,
                            deleteFlag : it.deleteFlag,
                            name       : it.name,
                            price      : it.price,
                            recency    : it.recency,
                            createTime : it.createTime,
                            status     : it.status,
                            code       : it.code,
                            picture    : pictureId
                    ]
                }
        returnMap
    }
}