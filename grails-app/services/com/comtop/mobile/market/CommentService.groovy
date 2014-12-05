package com.comtop.mobile.market

/**
 * Created by zhaoqunqi on 2014/12/2.
 */
class CommentService {

    def findAll(int pageSize, int currentPage, String goodId){
        def returnMap = [:]
        returnMap.currentPage = currentPage
//        returnMap.status = status
        def params = [:]
        params.max = pageSize + 1
        params.order = "desc"
        params.sort = "indexOrder"
        params.offset = (currentPage - 1) * pageSize
        def list = Comment.findAll(params) {
            good{
                eq("id", goodId)
            }
        }
        println "${list.size()}"
        if (list.size() == pageSize + 1) {
            returnMap.hasMore = true
            list.remove(pageSize)
        } else {
            returnMap.hasMore = false
        }
        returnMap.data = list
        returnMap
    }
}
