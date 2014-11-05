package com.comtop.mobile.market.util

import org.springframework.web.multipart.commons.CommonsMultipartFile

class FileUtils {

	def grailsApplication

	/**
	 * 保存文件
	 * 
	 * @param original 原始文件
	 * @param uuid 主键
	 */
	void saveFile(CommonsMultipartFile  original,String uuid){
		File saveFile = new File(grailsApplication.config.images.save.path,uuid)
		original.transferTo(saveFile)
	}
	/**
	 * 获取文件
	 * @param uuid 主键
	 * @return 文件
	 */
	File getFile(String uuid){
		File file = new File(grailsApplication.config.images.save.path,uuid)
		if(!file.exists()){
			file = new File(grailsApplication.config.images.save.path,"default")
		}
		file
	}
}