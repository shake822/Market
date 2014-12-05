package com.comtop.mobile.market

import grails.converters.JSON

/**
 * Created by zhaoqunqi on 2014/12/2.
 */
class FavoritesService {

    def findAll(int pageSize, int currentPage, String userId) {
        def returnMap = [:]
        returnMap.currentPage = currentPage
//        returnMap.status = status
        def params = [:]
        params.max = pageSize + 1
        params.order = "desc"
        params.sort = "createTime"
        params.offset = (currentPage - 1) * pageSize
        def list = Favorites.findAll(params) {
            user {
                eq("id", userId)
            }
            eq("deleteFlag", false)
        }
        if (list.size() == pageSize + 1) {
            returnMap.hasMore = true
            list.remove(pageSize)
        } else {
            returnMap.hasMore = false
        }
        returnMap.data = list.collect()
                {
                    [
                            id        : it.id,
                            createTime: it.createTime,
                            good   : it.good,
                            userId    : it.userId,
                            deleteFlag: it.deleteFlag
                    ]
                }
        returnMap
    }
}
