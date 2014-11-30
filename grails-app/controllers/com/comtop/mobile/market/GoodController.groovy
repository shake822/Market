package com.comtop.mobile.market

import com.comtop.mobile.market.util.ConstantGroovyUtils
import com.comtop.mobile.market.util.ConstantUtils
import com.comtop.mobile.market.util.FileUtils
import com.comtop.mobile.market.util.JsonHelper
import grails.converters.JSON
import grails.transaction.Transactional

import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
class GoodController {

    FileUtils fileUtils

    GoodService goodService

    static allowedMethods = [save: "POST", update: "POST", delete: "DELETE"]

    /**
     * 获取商品的信息
     * @param id 商品Id
     */
    def mGet(String id) {
        def data = goodService.getGood(id)
        if (data == null) {
            render JsonHelper.onError("没有找到宝贝")
            return
        } else {
            render JsonHelper.onSuccessBody("${data}")
        }

    }

    /**
     *
     * @param pageSize
     * @param currentPage
     * @param status
     */
    def mFind(String pageSize, String currentPage, String status) {
        def params = [:]
        if (!"-1".equals(status)) {
            params.put("status", status as int)
        }
        def data = goodService.findAll(pageSize as int, currentPage as int, params)
        data.status = status
        render JsonHelper.onSuccessBody("${data as JSON}")
    }

    /**
     * 获取自己宝贝
     * @param status
     */
    def mFindMyGood(String pageSize, String currentPage, String status) {
        def params = ["user.id": session.user.id]
        if (!"-1".equals(status)) {
            params.put("status", status as int)
        }
        def data = goodService.findAll(pageSize as int, currentPage as int, params)
        data.status = status
        render JsonHelper.onSuccessBody("${data as JSON}")
    }

    /**
     * 修改物品的交易状态
     * @param goodId
     * @param newTransStatus
     */
    @Transactional
    def mUpdateTransStatus(String goodId, String newTransStatus) {
        def data = goodService.updateTransStatus(goodId, newTransStatus)
        render JsonHelper.onSuccessMessage("${data}")
    }

    @Transactional
    def mSave() {
        Good good = new Good()
        good.name = params.name
        good.description = params.description
        good.classify = params.classify
        good.status = params.status
        good.price = params.price ?: 0
        good.code = params.code
        good.transStatus = "0"
        good.recency = params.recency
        good.user = session.user
        good.deleteFlag = params.deleteFlag ?: false
        good.createTime = new Date()
        good.updateTime = new Date()
        List<GoodPicture> pgList = []
        try {
            4.times {
                def f = request.getFile('imgFile' + it)
                if (!f.isEmpty()) {
                    GoodPicture gp = new GoodPicture()
                    gp.imgName = f.getOriginalFilename()
                    gp.indexOrder = it
                    gp.save flush: true
                    fileUtils.saveFile(f, gp.id)
                    pgList.add(gp)
                }
            }

            good.pictures = pgList

            def goodSaved = good.save flush: true
            if (goodSaved == null) {
                render JsonHelper.onError("参数有误")
                return
            }
        } catch (Exception e) {
            render JsonHelper.onError("服务器内部错误")
            return
        }
        render JsonHelper.onSuccessBody("添加成功")
    }

    def mUpdate() {

    }

    @Transactional
    def mDelete(String goodId) {
        def data = goodService.delete(goodId)
        render JsonHelper.onSuccessMessage("${data}")
    }


    def index(Integer max) {
        initGoodKeyValues()
        params.max = Math.min(max ?: 10, 100)
        respond Good.list(params), model: [goodInstanceCount: Good.count()]
    }

    /**
     * 初始化下拉菜单信息
     */
    private void initGoodKeyValues() {
        flash.goodStatus = ConstantGroovyUtils.goodStatus
        flash.goodRecency = ConstantGroovyUtils.goodRecency
        flash.goodTransStatus = ConstantGroovyUtils.goodTransStatus
    }

    def show(Good goodInstance) {
        goodInstance.recency = ConstantGroovyUtils.goodRecency.get(new Integer(goodInstance.recency))
        goodInstance.status = ConstantGroovyUtils.goodStatus.get(new Integer(goodInstance.status))
        goodInstance.transStatus = ConstantGroovyUtils.goodTransStatus.get(goodInstance.transStatus as int)
        respond goodInstance
    }

    def create() {
        initGoodKeyValues()
        respond new Good(params)
    }

    @Transactional
    def save(Good goodInstance) {
        if (goodInstance == null) {
            notFound()
            return
        }

        goodInstance.code = "xxx"
        goodInstance.createTime = new Date()
        goodInstance.updateTime = new Date()
//		if (goodInstance.hasErrors()) {
//			println goodInstance.errors
//			respond goodInstance.errors, view:'create'
//			return
//		}
        List<GoodPicture> pgList = []
        4.times {
            def f = request.getFile('imgFile' + it)
            if (!f.isEmpty()) {
                GoodPicture gp = new GoodPicture()
                gp.imgName = f.getOriginalFilename()
                gp.indexOrder = it
                gp.save flush: true
                fileUtils.saveFile(f, gp.id)
                pgList.add(gp)
            }
        }

        goodInstance.pictures = pgList
        goodInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [
                        message(code: 'good.label', default: 'Good'),
                        goodInstance.id
                ])
                redirect goodInstance
            }
            '*' { respond goodInstance, [status: CREATED] }
        }
    }

    def edit(Good goodInstance) {
        initGoodKeyValues()
        respond goodInstance
    }

    @Transactional
    def update(Good goodInstance) {
        if (goodInstance == null) {
            notFound()
            return
        }

        if (goodInstance.hasErrors()) {
            respond goodInstance.errors, view: 'edit'
            return
        }
        List<GoodPicture> pgList = []
        goodInstance.pictures?.each {
            pgList[it.indexOrder] = it
        }
        4.times {
            def f = request.getFile('imgFile' + it)
            if (!f.isEmpty()) {
                println "update ${it}"
                GoodPicture gp = pgList[it] ?: new GoodPicture()
                gp.imgName = f.getOriginalFilename()
                gp.indexOrder = it
                gp.save flush: true
                fileUtils.saveFile(f, gp.id)
                pgList[it] = gp
            }
        }
        if (pgList.size() > 0) {
            goodInstance.pictures = pgList
        }


        goodInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [
                        message(code: 'good.label', default: 'Good'),
                        goodInstance.id
                ])
                redirect goodInstance
            }
            '*' { respond goodInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Good goodInstance) {

        if (goodInstance == null) {
            notFound()
            return
        }

        goodInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [
                        message(code: 'good.label', default: 'Good'),
                        goodInstance.id
                ])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [
                        message(code: 'good.label', default: 'Good'),
                        params.id
                ])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
