package com.comtop.mobile.market

class InitDataController {

	def index() {
		initClassify()
	}

	/**
	 * 初始化分类数据
	 * @return
	 */
	def initClassify(){
		Classify cl = new Classify()
		cl.code = "00"
		cl.name="数码产品"
		cl.description="手机 电脑 相机"
		cl.save   flush:true
		
		cl = new Classify()
		cl.code = "11"
		cl.name="食品饮料"
		cl.description="牛奶 零食 烟酒"
		cl.save  flush:true
		
		
		cl = new Classify()
		cl.code = "22"
		cl.name="票务卡卷"
		cl.description="电话卡 门票 优惠卷"
		cl.save flush:true
		
		
		cl = new Classify()
		cl.code = "33"
		cl.name="房屋出租"
		cl.description="合租 求租 出租"
		cl.save  flush:true
		
		
		cl = new Classify()
		cl.code = "44"
		cl.name="家具家电"
		cl.description="家具  冰箱 洗衣机"
		cl.save  flush:true
		
		
		cl = new Classify()
		cl.code = "55"
		cl.name="母婴育儿"
		cl.description="奶粉 童车 玩具"
		cl.save flush:true
		
		cl = new Classify()
		cl.code = "66"
		cl.name="其他"
		cl.description="新鲜玩意儿 各种零碎儿"
		cl.save flush:true
	}
}
