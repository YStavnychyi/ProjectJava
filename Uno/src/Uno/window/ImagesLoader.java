package Uno.window;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImagesLoader {

	public static BufferedImage loadImage(String path)
	{
		try {
			return ImageIO.read(ImagesLoader.class.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Could not laod image from path:"+path);
		}
		return null;
		
	}
	
	public static BufferedImage cropOutImage(int x,int y,int w,int h,BufferedImage sheet)
	{
		return sheet.getSubimage(x, y, w, h);
	}
}
