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
	 * �����ļ�
	 * 
	 * @param original ԭʼ�ļ�
	 * @param uuid ����
	 */
	void saveFile(File original,String uuid){
		println "sss $uuid"
//		File saveFile = new File(config.images.save.path,uuid)
//		original.transferTo(saveFile)
	}

	/**
	 * ��ȡ�ļ�
	 * @param uuid ����
	 * @return �ļ�
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
