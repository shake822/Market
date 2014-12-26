package com.comtop.mobile.market

import com.comtop.mobile.market.util.ConstantUtils
import com.comtop.mobile.market.util.JsonHelper
import com.comtop.mobile.market.util.StringUtils
import grails.converters.JSON

import static org.springframework.http.HttpStatus.*

import com.comtop.mobile.market.Comment;

import grails.transaction.Transactional

@Transactional(readOnly = true)
class CommentController {

    CommentService commentService


    UtilsService utilsService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    /**
     * 获取评论信息
     * @param goodId
     */
    def mFind(String pageSize, String currentPage,String goodId){
        Date searchTime = StringUtils.isBlank(params.searchTime) ? new Date():new Date(params.searchTime as long)
        int count = params.count ==null ? -1:  params.count as int
        if(count == -1){
            count = utilsService.getCount {
                Comment.countByGood(Good.get(goodId))
            }
        }
        int iPageSize  = pageSize as int
        int iCurrentPage  = currentPage as int
        int startCount = count - (iCurrentPage - 1) * iPageSize
        def data = utilsService.findAll(pageSize,currentPage){
            queryParams ->
               def list =  Comment.findAll(queryParams){
                   good{
                       eq("id",goodId)
                   }
                }
                def data = list.collect(){
                        [
                                id: it.id,
                                index : startCount--,
                                createTime:it.createTime,
                                content:it.content,
                                fromUserId:it.fromUser.id,
                                fromUserName:it.fromUser.username,
                                fromUserAddr:it.fromUser.address,
                                fromUserImage: ConstantUtils.IMAGE_URL+ it.fromUser.headImg
                        ]
                }
                data
        }
        println data
        data.searchTime = searchTime.getTime()
        data.count  = count
        render JsonHelper.onSuccessBody("${data as JSON}")
    }


    /**
     * 保存评论信息
     */
    @Transactional
    def mSave(String content,String goodId,String toUserId){
        Good good =  Good.get(goodId)
        Comment comment = new Comment()
        comment.good = good
        comment.fromUser = session.user
        if(!StringUtils.isBlank(toUserId)){
            def users = []
            toUserId.split(';').each {
                 User tmpUser = User.get(it)
                 users << tmpUser
            }
            comment.toUsers = users
        }else{
            comment.toUsers = [good.user]
        }
        println comment.toUsers
        comment.createTime = new Date()
        comment.isRead = false
        comment.content = content
        println comment
        def commentSaved = comment.save flush:true
        if (commentSaved == null) {
            render JsonHelper.onError("保存失败")
            return
        }
        mFind("20","1",goodId)
    }


    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Comment.list(params), model:[commentInstanceCount: Comment.count()]
    }

    def show(Comment commentInstance) {
        respond commentInstance
    }

    def create() {
        respond new Comment(params)
    }

    @Transactional
    def save(Comment commentInstance) {
        if (commentInstance == null) {
            notFound()
            return
        }
        commentInstance.createTime = new Date()
//        if (commentInstance.hasErrors()) {
//            respond commentInstance.errors, view:'create'
//            return
//        }

        commentInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'comment.label', default: 'Comment'), commentInstance.id])
                redirect commentInstance
            }
            '*' { respond commentInstance, [status: CREATED] }
        }
    }

    def edit(Comment commentInstance) {
        respond commentInstance
    }

    @Transactional
    def update(Comment commentInstance) {
        if (commentInstance == null) {
            notFound()
            return
        }
        commentInstance.createTime = new Date()
        if (commentInstance.hasErrors()) {
            respond commentInstance.errors, view:'edit'
            return
        }

        commentInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Comment.label', default: 'Comment'), commentInstance.id])
                redirect commentInstance
            }
            '*'{ respond commentInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Comment commentInstance) {

        if (commentInstance == null) {
            notFound()
            return
        }

        commentInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Comment.label', default: 'Comment'), commentInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'comment.label', default: 'Comment'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
