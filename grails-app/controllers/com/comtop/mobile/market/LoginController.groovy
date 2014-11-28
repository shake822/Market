package com.comtop.mobile.market

import com.comtop.mobile.market.util.JsonHelper
import grails.converters.JSON
import grails.transaction.Transactional

import com.comtop.mobile.utils.MD5Tools


@Transactional(readOnly = true)
class LoginController {

	def index() {
		render(view:"/login")
	}
	def mLogin(){
		String account = params["account"]
		String password = params["password"]
		println "${account} ==== ${password}"
		User user = User.findWhere(account:"$account",password:"$password")
		if(user ==null){
			render JsonHelper.onError("账号或密码错误")
		}else{
			session.user = user
			render JsonHelper.onSuccessBody("${user as JSON}")
		}
	}

	/**
	 * 登出
	 * @return
	 */
	def mLogout(){
		session.invalidate()
		render JsonHelper.onSuccessMessage("登出成功")
	}

	/**
	 * 获取用户信息
	 * @return
	 */
	def mGetUserInfo(){
		User user = session.user
		if(user != null){
			render JsonHelper.onSuccessBody("${user as JSON}")
		}else{
			render JsonHelper.onError("您还没有登录")
		}

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
