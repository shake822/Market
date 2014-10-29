package com.comtop.mobile.market.util

class FileUtils {
	//def config = new ConfigSlurper().parse(new File('grails-app/conf/market.groovy').toURL())
	
	private static FileUtils instance 
	
	public static FileUtils getInstance(){
		if(instance ==null){
			instance = new FileUtils()
		}
		instance
	}
	
	
	/**
	 * 保存文件
	 * 
	 * @param original 原始文件
	 * @param uuid 主键
	 */
	void saveFile(File original,String uuid){
		println "sss $uuid"
//		File saveFile = new File(config.images.save.path,uuid)
//		original.transferTo(saveFile)
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
