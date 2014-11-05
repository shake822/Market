package com.comtop.mobile.market

import grails.converters.JSON
import grails.transaction.Transactional

import com.comtop.mobile.utils.MD5Tools


@Transactional(readOnly = true)
class LoginController {

	def index() {
		render(view:"/login")
	}
	def login(){
		String account = params["account"]
		String password = params["password"]
		String md5Password = MD5Tools.toMD5(password)
		println md5Password
		User user = User.findWhere(account:"$account",password:"$md5Password")
		if(user ==null){
			flash.message = "用户名密码错误"
			render(view:"/login")
			return 
		}
		session.user = user
		redirect(uri: "/")
		return
//		render user as JSON
	}

	def logout(){
		session.invalidate()
		render(view:"/login")
	}
}
