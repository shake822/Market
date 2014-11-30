package com.comtop.mobile.market

import com.comtop.mobile.market.util.JsonHelper
import com.comtop.mobile.market.util.StringUtils
import com.comtop.mobile.utils.EIPUser
import grails.converters.JSON
import grails.transaction.Transactional

import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
class UserController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    @Transactional
    def initFromEIP() {
        EIPUser users = new EIPUser()
        users.getAllUsers().each() { it.save flush: true }
    }

    /**
     * 获取用户信息
     * @param id 用户Id
     * @return
     */
    def mGet(String id) {
        def data = User.get(id)
        if (data == null) {
            render JsonHelper.onError("没有此用户")
            return
        }
        render JsonHelper.onSuccessBody("${data as JSON}")
    }

    @Transactional
    def mUpdate() {
        User user = session.user
        if (user == null) {
            render JsonHelper.onError("没有此用户")
            return
        }
        if (!StringUtils.isBlank(params.phone)) {
            user.phone = params.phone
        }
        if (!StringUtils.isBlank(params.password)) {
            user.password = params.password
        }
        if (!StringUtils.isBlank(params.department)) {
            user.department = params.department
        }
        if (!StringUtils.isBlank(params.address)) {
            user.address = params.address
        }
        user.save flush: true
        render JsonHelper.onSuccessMessage("修改成功")
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond User.list(params), model: [userInstanceCount: User.count()]
    }

    def show(User userInstance) {
        respond userInstance
    }

    def create() {
        respond new User(params)
    }

    @Transactional
    def save(User userInstance) {
        if (userInstance == null) {
            notFound()
            return
        }

        if (userInstance.hasErrors()) {
            respond userInstance.errors, view: 'create'
            return
        }

        userInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [
                        message(code: 'user.label', default: 'User'),
                        userInstance.id
                ])
                redirect userInstance
            }
            '*' { respond userInstance, [status: CREATED] }
        }
    }

    def edit(User userInstance) {
        respond userInstance
    }

    @Transactional
    def update(User userInstance) {
        if (userInstance == null) {
            notFound()
            return
        }

        if (userInstance.hasErrors()) {
            respond userInstance.errors, view: 'edit'
            return
        }

        userInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [
                        message(code: 'user.label', default: 'User'),
                        userInstance.id
                ])
                redirect userInstance
            }
            '*' { respond userInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(User userInstance) {

        if (userInstance == null) {
            notFound()
            return
        }

        userInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [
                        message(code: 'user.label', default: 'User'),
                        userInstance.id
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
                        message(code: 'user.label', default: 'User'),
                        params.id
                ])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
