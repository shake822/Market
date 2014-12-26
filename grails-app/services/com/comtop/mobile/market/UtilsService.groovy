package com.comtop.mobile.market

/**
 * Created by zhaoqunqi on 2014/12/3.
 */
class UtilsService {

    /**
     * 分页查询的SQL
     * @param pageSize
     * @param currentPage
     * @param query 查询条件
     * @param toJson 转Json
     * @return
     */
    def findAll(String pageSize, String currentPage,Closure query) {
        def returnMap = [:]
        int iPageSize  = pageSize as int
        int iCurrentPage  = currentPage as int
        returnMap.currentPage = iCurrentPage
        def params = [:]
        params.max = iPageSize + 1
        params.order = "desc"
        params.sort = "createTime"
        params.offset = (iCurrentPage - 1) * iPageSize

        def list = query.call(params)
        if (list.size() == iPageSize + 1) {
            returnMap.hasMore = true
            list.remove(iPageSize)
        } else {
            returnMap.hasMore = false
        }
        returnMap.data = list
        returnMap
    }

    def getCount(Closure query){
        def params = [:]
        query.call(params)
    }
}
