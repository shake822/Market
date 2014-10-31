package com.comtop.mobile.market.filter

class SessionFilterFilters {

	def filters = {
		loginCheck(controller:'*', action:'*' ) {
			before = {
				println "xxx"
				if (!session.user ) {
					println controllerName
					if(!(controllerName.equals('login') || controllerName.equals('assets'))){
						redirect(controller: "login", action: "index")
						return true
					}
				}
				return true
			}
			//            after = { Map model -> println "filter after" }
//			afterView = { Exception e -> println "filter afterView" }
		}
	}
}
