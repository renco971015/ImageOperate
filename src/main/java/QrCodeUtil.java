//package com.fcmsp.member.center.server.utils;
//
//
//
//import java.io.File;
//import java.awt.Font;
//
//import com.google.zxing.EncodeHintType;
//import com.ruimin.ifs.core.annotation.ActionResource;
//import com.ruimin.ifs.core.annotation.SnowDoc;
//import com.ruimin.ifs.core.exception.SnowExceptionUtil;
//import com.ruimin.ifs.core.iface.action.SnowAction;
//import com.ruimin.ifs.core.log.SnowLog;
//import com.ruimin.ifs.pmp.chnlMng.common.constants.MchtChnlRequestConstants;
//import com.ruimin.ifs.pmp.pubTool.util.SysParamUtil;
//import java.io.FileInputStream;
//import java.util.HashMap;
//import java.util.Map;
//import java.awt.Graphics2D;
//import java.awt.Color;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import java.awt.image.BufferedImage;
//import javax.imageio.ImageIO;
//import java.awt.FontMetrics;
//
//import javax.swing.JLabel;
///**
// * 二维码操作工具类
// * @author shrm_tyzf010
// *
// */
//@Slf4j
//public class QrCodeUtil{
//
//	public final static String PNG = "png";
//	//支付前置请求路径
//	static String tokenUrl = SysParamUtil.getParam(MchtChnlRequestConstants.PAY_PER_URL);
//
//
//
//	/**
//	 * 二维码图片生成(生成的二维码带白色边框版本,如果需要去除白色边框可以使用下面注释掉的方法)
//	 * @param qrCode 二维码token
//	 * @param tmpPath 二维码保存路径
//	 * @param picType 二维码图片生成类型的
//	 * @param mchtId 商户号
//	 * @param mchtSimpleName 商户简称
//	 * @param qrcBackGroundImageName
//	 * @throws Exception
//	 */
//	public  void build(String qrCode, String tmpPath, String picType, String mchtId, String mchtSimpleName, String qrcBackGroundImageName)
//			throws Exception {
//		try {
//			log.info("Set before===========java.awt.headless" + System.getProperty("java.awt.headless"));
//			System.setProperty("java.awt.headless", "true");
//			log.info("Set End===========java.awt.headless" + System.getProperty("java.awt.headless"));
//
//			log.info("商户二维码图片生成开始，商户号：" + mchtId + "，商户简称：" + mchtSimpleName);
//			log.info("tmpPath====:"+tmpPath);
//			String path = SysParamUtil.getParam(MchtChnlRequestConstants.QRC_BACKGROUND_PATH);
//			if("qrcBackground1.png".equals(qrcBackGroundImageName)){
//				path = path+"qrcBackground1.png";
//			}
//			if("qrcBackground2.png".equals(qrcBackGroundImageName)){
//				path = path+"qrcBackground2.png";
//			}
//			if("qrcBackground3.png".equals(qrcBackGroundImageName)){
//				path = path+"qrcBackground3.png";
//			}
//			if("qrcBackground4.png".equals(qrcBackGroundImageName)){
//				path = path+"qrcBackground4.png";
//			}
//			if("qrcBackground5.png".equals(qrcBackGroundImageName)){
//				path = path+"qrcBackground5.png";
//			}
//			if("qrcBackground6.png".equals(qrcBackGroundImageName)){
//				path = path+"qrcBackground6.png";
//			}
//			if("qrcBackground7.png".equals(qrcBackGroundImageName)){
//				path = path+"qrcBackground7.png";
//			}
//			log.info("获取二维码背景图片路径成功");
//			FileInputStream fis =new FileInputStream(path);
//			log.info("生成fileInputStream流成功");
//			BufferedImage bimg = ImageIO.read(fis);
//			log.info("生成BufferedImage成功");
//			int bw = bimg.getWidth();
//			int bh = bimg.getHeight();
//			log.info("bimg宽：" + bw + ",bimg长：" + bh);
//			Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
//			hints.put(EncodeHintType.MARGIN, 1);
//			BitMatrix bitMatrix = null;
//			//设置生生的二维码图片大小为背景图片的10分之8的大小
//			hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
////			bitMatrix = new MultiFormatWriter().encode((tokenUrl + qrCode), BarcodeFormat.QR_CODE,  bw * 8 / 10, bw * 8 / 10,
////					hints);
//			//设置生生的二维码图片大小为300x300
//			bitMatrix = new MultiFormatWriter().encode((tokenUrl + qrCode), BarcodeFormat.QR_CODE,  300, 300,
//			hints);
//
//
//			log.info("生成BitMatrix成功");
//			int iw = bitMatrix.getWidth();
//			int ih = bitMatrix.getHeight();
//			BufferedImage image = new BufferedImage(iw, ih, BufferedImage.TYPE_INT_ARGB);
//			log.info("生成image成功");
//			for (int x = 0; x < iw; x++) {
//				for (int y = 0; y < ih; y++) {
//					image.setRGB(x, y, bitMatrix.get(x, y) == true ? Color.black.getRGB() : Color.white.getRGB());
//				}
//			}
//			log.info("image.setRGB完成");
//			File file = new File(tmpPath + mchtId + "." + PNG);
//				if (!file.exists()) {
//					new File(tmpPath).mkdirs();
//					file.createNewFile();
//					file.setReadable(true, false);
//					file.setWritable(true, false);
//				}
//			log.info("file:"+file);
//			if(bimg != null){
//				log.info("bimg========="+bimg);
//				Graphics2D g = (Graphics2D) bimg.getGraphics();
//				Graphics2D g2 = (Graphics2D)bimg.getGraphics();
//				log.info("g2"+g2);
//				g.setColor(Color.WHITE);
//				log.info("g.setColor");
//				g2.setColor(Color.WHITE);
//				log.info("g2.setColor");
//				log.info("二维码图片生成开始，商户号：" + mchtId + "，商户简称：" + mchtSimpleName);
//				Font font = new Font(Font.DIALOG, Font.BOLD, 43);
//				Font font2 = new Font(Font.DIALOG, Font.BOLD, 25);//目前第二个参数不可为Font.PLAIN，否则某些中文字符显示异常，第三个参数不可超过31，否则导致显示不同行
//				g.setFont(font); // 设置字体
//				g2.setFont(font2); // 设置字体
//				if (StringUtils.isNotBlank(mchtSimpleName)) {
//					FontMetrics fm2 = new JLabel().getFontMetrics(font2);
//					int a = fm2.stringWidth(mchtSimpleName);
//					//商户简称的字体x轴居中,y轴往下50
//					g2.drawString(mchtSimpleName,bw / 2 - a / 2, 50);
//				}
//				//二维码图片的位置x轴居中,y轴往下60
//				g.drawImage(image, (bw - iw) / 2 , 80, null);
//				g2.dispose();
//				g.dispose();
//				log.info("二维码图片高:"+bimg.getHeight()+",二维码图片宽:"+bimg.getWidth());
//				ImageIO.write(bimg, "png", file);
//			}
//		} catch (Exception e) {
//			log.info("错误原因:"+e);
//		}
//
//	}
//
//	/**
//	 * 二维码图片生成(生成的二维码图片不带白色边框版本)
//	 * @param qrCode 二维码token
//	 * @param tmpPath 二维码保存路径
//	 * @param picType 二维码图片生成类型的
//	 * @param mchtId 商户号
//	 * @param mchtSimpleName 商户简称
//	 * @param qrcBackGroundImageName
//	 * @throws Exception
//	 */
////	public  void build(String qrCode, String tmpPath, String picType, String mchtId, String mchtSimpleName, String qrcBackGroundImageName)
////			throws Exception {
////		try {
////			log.info("Set before===========java.awt.headless" + System.getProperty("java.awt.headless"));
////			System.setProperty("java.awt.headless", "true");
////			log.info("Set End===========java.awt.headless" + System.getProperty("java.awt.headless"));
////
////			log.info("商户二维码图片生成开始，商户号：" + mchtId + "，商户简称：" + mchtSimpleName);
////			log.info("tmpPath====:"+tmpPath);
////			String path = SysParamUtil.getParam(MchtChnlRequestConstants.QRC_BACKGROUND_PATH);
////			if("qrcBackground1.png".equals(qrcBackGroundImageName)){
////				path = path+"qrcBackground1.png";
////			}
////			if("qrcBackground2.png".equals(qrcBackGroundImageName)){
////				path = path+"qrcBackground2.png";
////			}
////			if("qrcBackground3.png".equals(qrcBackGroundImageName)){
////				path = path+"qrcBackground3.png";
////			}
////			if("qrcBackground4.png".equals(qrcBackGroundImageName)){
////				path = path+"qrcBackground4.png";
////			}
////			if("qrcBackground5.png".equals(qrcBackGroundImageName)){
////				path = path+"qrcBackground5.png";
////			}
////			if("qrcBackground6.png".equals(qrcBackGroundImageName)){
////				path = path+"qrcBackground6.png";
////			}
////			if("qrcBackground7.png".equals(qrcBackGroundImageName)){
////				path = path+"qrcBackground7.png";
////			}
////			log.info("获取二维码背景图片路径成功");
////			FileInputStream fis =new FileInputStream(path);
////			log.info("生成fileInputStream流成功");
////			BufferedImage bimg = ImageIO.read(fis);
////			log.info("生成BufferedImage成功");
////			int bw = bimg.getWidth();
////			int bh = bimg.getHeight();
////			log.info("bimg宽：" + bw + ",bimg长：" + bh);
////			Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
////			hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
////			hints.put(EncodeHintType.MARGIN, 1);
////			BitMatrix bitMatrix = null;
////			bitMatrix = new MultiFormatWriter().encode((tokenUrl + qrCode), BarcodeFormat.QR_CODE,  bw * 8 / 10, bw * 8 / 10,
////					hints);
////			log.info("生成BitMatrix成功");
////			int iw = bitMatrix.getWidth();
////			int ih = bitMatrix.getHeight();
////			int[] rec = bitMatrix.getEnclosingRectangle();
////			int resWidth = rec[2] + 1;
////			int resHeight = rec[3] + 1;
////			BitMatrix resMatrix = new BitMatrix(resWidth, resHeight);//根据白色边框重新生成BitMatrix
////			log.info("重新生成BitMatrix成功");
////			resMatrix.clear();
////			for (int i = 0; i < resWidth; i++) {//循环，将二维码图案绘制到新的resMatrix中
////				for (int j = 0; j < resHeight; j++) {
////					if (bitMatrix.get(i + rec[0], j + rec[1])) {
////						resMatrix.set(i, j);
////					}
////				}
////			}
////			log.info("循环将二维码图片绘制到新的resMatrix中成功");
////			int width = resMatrix.getWidth();
////			int height = resMatrix.getHeight();
////			BufferedImage image = new BufferedImage(iw, ih, BufferedImage.TYPE_INT_ARGB);;
////			log.info("生成image成功");
////			for (int x = 0; x < width; x++) {
////				for (int y = 0; y < height; y++) {
////					image.setRGB(x, y, resMatrix.get(x, y) == true ? Color.black.getRGB() : Color.white.getRGB());
////				}
////			}
////			log.info("image.setRGB完成");
////			File file = new File(tmpPath + mchtId + "." + PNG);
////				if (!file.exists()) {
////					new File(tmpPath).mkdirs();
////					file.createNewFile();
////					file.setReadable(true, false);
////					file.setWritable(true, false);
////				}
////			log.info("file:"+file);
////			if(bimg != null){
////				log.info("bimg========="+bimg);
////				Graphics2D g = (Graphics2D) bimg.getGraphics();
////				Graphics2D g2 = (Graphics2D)bimg.getGraphics();
////				log.info("g2"+g2);
////				g.setColor(Color.WHITE);
////				log.info("g.setColor");
////				g2.setColor(Color.WHITE);
////				log.info("g2.setColor");
////				log.info("二维码图片生成开始，商户号：" + mchtId + "，商户简称：" + mchtSimpleName);
////				Font font = new Font(Font.DIALOG, Font.BOLD, 43);
////				Font font2 = new Font(Font.DIALOG, Font.BOLD, 25);//目前第二个参数不可为Font.PLAIN，否则某些中文字符显示异常，第三个参数不可超过31，否则导致显示不同行
////				g.setFont(font); // 设置字体
////				g2.setFont(font2); // 设置字体
////				if (StringUtil.isNotBlank(mchtSimpleName)) {
////					FontMetrics fm2 = new JLabel().getFontMetrics(font2);
////					int a = fm2.stringWidth(mchtSimpleName);
////					//System.err.println(a);
////					g2.drawString(mchtSimpleName,bw / 2 - a / 2, 35);
////				}
////				g.drawImage(image, (bw - iw) / 2 +10 , 60, null);
////				g2.dispose();
////				g.dispose();
////				log.info("二维码图片高:"+bimg.getHeight()+",二维码图片宽:"+bimg.getWidth());
////				ImageIO.write(bimg, picType, file);
////			}
////		} catch (Exception e) {
////			log.info("错误原因:"+e);
////			SnowExceptionUtil.throwWarnException("错误原因:"+e);
////			SnowExceptionUtil.throwErrorException(e.getMessage());
////		}
////
////	}
//	public static boolean deleteDir(File dir) throws Exception{
//		if(dir.isDirectory()){
//			String[] children = dir.list();
//			//递归删除目录中的子目录
//			for(int i=0;i<children.length;i++){
//				boolean success = deleteDir(new File(dir,children[i]));
//				if(!success){
//					return false;
//				}
//			}
//		}
//		//目录此时为空，可以删除
//		return dir.delete();
//	}
//	public static void main(String[] args) {
//		try {
//			new QrCodeUtil().build("11", SysParamUtil.getParam(MchtChnlRequestConstants.QRC_TMP_SAVE_PATH)+File.separator, "png", "123", "测试", "qrcBackground1.png");
//			System.out.println("生产二维码图片成功");
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
////		try {
////			boolean success = deleteDir(new File("W:/QRC/QRC_TMP"));
////			if(success){
////				System.out.println("删除成功");
////			}else{
////				System.out.println("失败");
////			}
////		} catch (Exception e) {
////			// TODO: handle exception
////		}
//
//	}
//}
