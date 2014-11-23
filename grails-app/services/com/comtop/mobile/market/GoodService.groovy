package com.comtop.mobile.market

import com.comtop.mobile.market.util.ConstantUtils
import grails.converters.JSON
import grails.transaction.Transactional

@Transactional
class GoodService {

    def getGood(String uuid) {
        Good good = Good.get(uuid)
        def pics = []
        good.pictures?.each {
            pics << [
                    indexOrder: it.indexOrder,
                    url       : ConstantUtils.IMAGE_URL + it.id
            ]
        }
        println pics
        def data = [
                id         : good.id,
                name       : good.name,
                classify   : good.classify,
                createTime : good.createTime,
                deleteFlag : good.deleteFlag,
                user       : [
                        id  : good.user.id,
                        name: good.user.username
                ],
                status     : good.status,
                recency    : good.recency,
                price      : good.price,
                code       : good.code,
                description: good.description,
                pictures   : pics
        ] as JSON
        data
    }
    /**
     * 列表查詢
     * @param pageSize 一页显示记录数
     * @param currentPage 当前页面
     * @param type 类型
     * @return true:还有更多记录;fasle:没有更多记录
     */
    def findAll(int pageSize, int currentPage, String status) {
        println "pageSize = ${pageSize}   ${currentPage}"
        def returnMap = [:]
        def params = [:]
        params.max = pageSize + 1
        params.order = "desc"
        params.sort = "createTime"
        params.offset = (currentPage - 1) * pageSize
//        params.fetch = [pictures: "join"]
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
        println "status ${status}"
        def list = Good.findAll(params) {
            if (status != null) {
                eq("status", "${status}")
            }
            eq("deleteFlag", false)
        }
        println "${list.size()}"
        if (list.size() == pageSize + 1) {
            returnMap.hasMore = true
            list.remove(pageSize)
        } else {
            returnMap.hasMore = false
        }
        returnMap.data = list.collect()
                {
                    def pictureId = null
                    def pictureIndex = 4
                    it.pictures?.each() {
                        if (pictureIndex > it.indexOrder) {
                            pictureId = ConstantUtils.IMAGE_URL + it.id
                            pictureIndex = it.indexOrder
                        }

                        if (it.indexOrder == 0) {
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