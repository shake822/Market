package com.comtop.mobile.market

import com.comtop.mobile.market.util.ConstantUtils
import com.comtop.mobile.market.util.JsonHelper
import com.comtop.mobile.market.util.StringUtils
import grails.converters.JSON

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class FeedbackController {

    UtilsService utilsService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    @Transactional
    def mSave(){
        Feedback feedback = new Feedback()
        feedback.content = params.content
        feedback.createTime = new Date()
        feedback.user = session.user
        if (feedback.hasErrors()) {
            JsonHelper.onError(feedback.errors)
            return
        }

        if(!StringUtils.isBlank(params.feedbackId)){
            Feedback parent = Feedback.get(params.feedbackId)
            if(parent ==null){
                JsonHelper.onError("回复的反馈已经不存在")
                return
            }
            feedback.isRepeat =true
            feedback.save flush: true
            parent.repeats.add(feedback)
            parent.save flush: true
        }else{
            feedback.isRepeat= false
            feedback.save flush: true
        }
        mFind("20","1")
    }

    def mFind(String pageSize, String currentPage){
        Date searchTime = StringUtils.isBlank(params.searchTime) ? new Date():new Date(params.searchTime as long)
        def data =  utilsService.findAll(pageSize,currentPage){
            queryParams ->
                def list =  Feedback.findAll(queryParams){
                    user{
                        eq("id",session.user.id)
                    }
                    eq("isRepeat",false)
                }
                def data = list.collect(){
                    def repeats=[]
                    it.repeats?.each{
                        repeats << [
                                id: it.id,
                                createTime:it.createTime,
                                content:it.content,
                                fromUserId:it.user.id,
                                fromUserName:it.user.username,
                                fromUserAddr:it.user.address,
                                fromUserImage: ConstantUtils.IMAGE_URL+ it.user.headImg
                        ]
                    }
                    [
                            id: it.id,
                            createTime:it.createTime,
                            content:it.content,
                            repeats:repeats
                    ]
                }
                data
        }
        data.searchTime = searchTime.getTime()
        render JsonHelper.onSuccessBody("${data as JSON}")
    }
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Feedback.findAll(params){
            eq("isRepeat",false)
        }, model: [feedbackInstanceCount: Feedback.countByIsRepeat(false)]
    }

    def show(Feedback feedbackInstance) {
        respond feedbackInstance
    }

    def create() {
        respond new Feedback(params)
    }

    @Transactional
    def save(Feedback feedbackInstance) {

        if (feedbackInstance == null) {
            notFound()
            return
        }
        feedbackInstance.createTime=new Date()
        if (feedbackInstance.hasErrors()) {
            respond feedbackInstance.errors, view: 'create'
            return
        }

        feedbackInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'feedback.label', default: 'Feedback'), feedbackInstance.id])
                redirect feedbackInstance
            }
            '*' { respond feedbackInstance, [status: CREATED] }
        }
    }

    def edit(Feedback feedbackInstance) {
        feedbackInstance.createTime=new Date()
        respond feedbackInstance
    }

    def saveRepeat(Feedback feedbackInstance){
        feedbackInstance.createTime=new Date()
        respond feedbackInstance
    }
    @Transactional
    def update(Feedback feedbackInstance) {
        if (feedbackInstance == null) {
            notFound()
            return
        }
        feedbackInstance.createTime=new Date()
        if (feedbackInstance.hasErrors()) {
            respond feedbackInstance.errors, view: 'edit'
            return
        }

        feedbackInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'feedback.label', default: 'Feedback'), feedbackInstance.id])
                redirect feedbackInstance
            }
            '*' { respond feedbackInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Feedback feedbackInstance) {

        if (feedbackInstance == null) {
            notFound()
            return
        }

        feedbackInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'feedback.label', default: 'Feedback'), feedbackInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'feedback.label', default: 'Feedback'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
