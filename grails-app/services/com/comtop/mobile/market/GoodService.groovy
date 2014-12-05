package com.comtop.mobile.market

import com.comtop.mobile.market.util.ConstantUtils
import com.comtop.mobile.market.util.JsonHelper
import grails.converters.JSON
import grails.transaction.Transactional

@Transactional
class GoodService {

    def getGood(String uuid) {
        Good good = Good.get(uuid)
        if (good == null) {
            return null
        }
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
                updateTime : good.updateTime,
                deleteFlag : good.deleteFlag,
                userId     : good.user.id,
                status     : good.status,
                transStatus: good.transStatus,
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
    def findAll(int pageSize, int currentPage, def condition) {
        println "pageSize = ${pageSize}   ${currentPage} ${condition}"
        def returnMap = [:]
        returnMap.currentPage = currentPage
//        returnMap.status = status
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
//        println "status ${status}"
        def list = Good.findAll(params) {
//            if (status != -1) {
//                eq("status", "${status}")
//            }
            condition.each() {
                def keys = it.key.split(".")
                if (keys.length == 2) {
                    keys[0] {
                        eq(keys[1], it.value)
                    }
                } else {
                    if("name".equals(it.key)){
                        like(it.key,it.value)
                    }else{
                        eq(it.key, it.value)
                    }

                }

            }
            eq("deleteFlag", false)
            eq("transStatus", "0")
        }
        println "${list.size()}"
        if (list.size() == pageSize + 1) {
            returnMap.hasMore = true
            list.remove(pageSize)
        } else {
            returnMap.hasMore = false
        }
        returnMap.data = goodToJson(list)
        returnMap
    }

    def getSaleGood(int status, String id) {
        def returnMap = [:]
        def params = [:]

        def list = Good.findAll() {
            if (status != -1) {
                eq("status", "${status}")
            }
            user {
                eq("id", "${id}")
            }


            eq("deleteFlag", false)
        }

        returnMap.data = goodToJson(list)
        returnMap
    }


    private def goodToJson(def list) {
        list.collect()
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
                            id         : it.id,
                            classify   : it.classify,
                            userId     : it.userId,
                            description: it.description,
                            deleteFlag : it.deleteFlag,
                            name       : it.name,
                            price      : it.price,
                            recency    : it.recency,
                            createTime : it.createTime,
                            updateTime : it.updateTime,
                            status     : it.status,
                            transStatus: it.transStatus,
                            code       : it.code,
                            picture    : pictureId
                    ]
                }
    }

    def updateTransStatus(String goodId, String transStatus) {
        Good good = Good.get(goodId)
        if(good ==null){
            return "没有找到宝贝"
        }
        good.transStatus = transStatus
        good.save flush: true
        "修改成功"
    }

    def delete(String goodId) {
        Good good = Good.get(goodId)
        if(good ==null){
            return "没有找到宝贝"
        }
        good.deleteFlag=true
        good.save flush: true
        "删除成功"
    }
}