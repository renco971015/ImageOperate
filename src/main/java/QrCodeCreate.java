import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import org.junit.Test;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 名称：QrCodeCreate<名称>
 * 功能：二维码生成<功能描述>
 * 方法：<方法说明>
 * 版本：1.0
 * 作者：xurunhao
 * 日期：2019/11/27 13:55
 * 说明：<文档编写说明>
 */
public class QrCodeCreate {
    //todo:生成二维码信息
    @Test
    public void createQrCodeInfo() {
        //生成二维码
        BitMatrix bitMatrix = null;
        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
        hints.put(EncodeHintType.MARGIN, 1);
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        try {
            bitMatrix = new MultiFormatWriter().encode(("https://www.baidu.com/"), BarcodeFormat.QR_CODE, 300, 300,
                    hints);

            //生成BitMatrix成功
            int iw = bitMatrix.getWidth();
            int ih = bitMatrix.getHeight();
            BufferedImage image = new BufferedImage(iw, ih, BufferedImage.TYPE_INT_ARGB);
            //生成image成功
            for (int x = 0; x < iw; x++) {
                for (int y = 0; y < ih; y++) {
                    image.setRGB(x, y, bitMatrix.get(x, y) == true ? Color.black.getRGB() : Color.white.getRGB());
                }
            }
            //生成图片传入为文件
            String temp = "F:\\Renco\\course\\图片操作\\";
            File file = new File(temp + "123" + ".png");
            if (!file.exists()) {
                new File(temp).mkdirs();
                file.createNewFile();
                file.setReadable(true, false);
                file.setWritable(true, false);
            }

            //todo:获取二维码背景图片
            //获取二维码的背景图片流
            FileInputStream fis = new FileInputStream("F:\\Renco\\Ruimin\\微信图片_20191127172451.png");
            //转换成BufferedImage图像缓冲区
            BufferedImage backGroundImage = ImageIO.read(fis);
            //获取背景图的宽高
            int bw = backGroundImage.getWidth();
            int bh = backGroundImage.getHeight();
            //将二维码信息绘制到背景图中
            //将图像转换成可绘制的类型
            Graphics2D g = (Graphics2D) backGroundImage.getGraphics();
            Graphics2D f = (Graphics2D) backGroundImage.getGraphics();
            //绘制参数 ：绘制的图片，图片坐标轴（坐标轴默认为绘制图片的左上角）,通知对象.
            g.drawImage(image, (bw-iw)/2, 200, null);
            //设置字体
            Font font = new Font(Font.DIALOG, Font.BOLD, 42);
            f.setColor(Color.BLACK);
            f.setFont(font);
            String mchtSimpleName = "Renco的商户";
            FontMetrics fm = new JLabel().getFontMetrics(font);
            int a = fm.stringWidth("Renco的商户");
            //商户简称的字体x轴居中,y轴往下50
            f.drawString(mchtSimpleName,bw / 2 - a / 2, 80);

            //释放资源
            f.dispose();
            g.dispose();
            //将图像文件绘制到事先创建的文件中
            ImageIO.write(backGroundImage, "png",file);
            ImageIO.write(image, "png", file);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
