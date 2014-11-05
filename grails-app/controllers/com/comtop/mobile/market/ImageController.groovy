package com.comtop.mobile.market

import java.awt.Graphics
import java.awt.Image
import java.awt.image.BufferedImage

import javax.imageio.ImageIO

import com.comtop.mobile.market.util.FileUtils

class ImageController {

	FileUtils fileUtils

	def index() {
		String uuid =  params["uuid"]
		int width =( params["width"] == null ?200 :  params["width"] as int)
		File imgFile = fileUtils.getFile(uuid)
		if (imgFile.exists()) {
			try {
				BufferedImage src = ImageIO.read(imgFile); // 读入文件
				int sWidth = src.getWidth(); // 得到源图宽
				int sHeight = src.getHeight(); // 得到源图长
				int height = (int) ((float) width / (float) sWidth * sHeight);
				Image image = src.getScaledInstance(width, height, Image.SCALE_SMOOTH);
				BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
				Graphics g = tag.getGraphics();
				g.drawImage(image, 0, 0, null); // 绘制缩放后的图
				g.dispose();
				response.setContentType("image/png"); // 设置返回的文件类型
				OutputStream toClient = response.getOutputStream();
				ImageIO.write(tag, "JPEG", toClient);// 输出到文件流
			} catch (IOException e) {
				log.error "Error: ${e.message}", e
			}
		}
	}
}
