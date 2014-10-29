package com.comtop.mobile.market.util

import org.codehaus.groovy.grails.commons.GrailsApplication
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
		println "sss $uuid"
		File saveFile = new File(grailsApplication.config.images.save.path,uuid)
		original.transferTo(saveFile)
	}
	/**
	 * 获取文件
	 * @param uuid 主键
	 * @return 文件
	 */
	File getFile(String uuid){
		new File(config.images.save.path,uuid)
	}
	/*
	 private String getFileType(String fileName){
	 int lastDot =fileName.lastIndexOf('.')
	 if( lastDot>0){
	 return fileName.substring(lastDot)
	 }
	 ".png"
	 }*/
}