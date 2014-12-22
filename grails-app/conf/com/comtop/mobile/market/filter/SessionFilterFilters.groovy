package com.comtop.mobile.market.filter

import com.comtop.mobile.market.util.JsonHelper

class SessionFilterFilters {

    def noLogin = JsonHelper.onErrorNoLogin()

    def filters = {
        loginCheck(controller: '*', action: 'm*') {
            before = {
                response.setHeader("Access-Control-Allow-Origin", "null")
                response.setHeader("Access-Control-Allow-Credentials", "true")
                response.setHeader("P3P", "CP=CAO PSA OUR")
                request.getCookies()?.each {
                    println "cookie " + it.name + "  " + it.value
                }
                if (!session.user) {
                    if ("${"good".equals(controllerName)}" == "true") {
                        if ("${actionName in ["mFindMyGood", "mSave", "mUpdateTransStatus", "mDelete", "mUpdate"]}" == "true") {
                            render noLogin
                            return false
                        }
                        return true
                    }
                    if ("${"user".equals(controllerName)}" == "true") {
                        if ("${actionName in ["mUpdate"]}" == "true") {
                            render noLogin
                            return false
                        }
                        return true
                    }
                    if ("${"favorites".equals(controllerName)}" == "true") {
                        render noLogin
                        return false
                    }
                    if ("${"comment".equals(controllerName)}" == "true") {
                        if ("${actionName in ["mSave"]}" == "true") {
                            render noLogin
                            return false
                        }
                        return true
                    }
                }
            }
        }
    }
}
