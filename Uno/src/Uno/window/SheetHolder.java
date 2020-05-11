package Uno.window;

import java.awt.image.BufferedImage;

public class SheetHolder {
	
	private BufferedImage cardSheet;
	private BufferedImage buttonSheet;
	
	public BufferedImage getCardSheet() {
		return cardSheet;
	}
	
	public void setCardSheet(String path) {
		this.cardSheet = ImagesLoader.loadImage(path);
	}
	
	public BufferedImage getButtonSheet() {
		return buttonSheet;
	}
	
	public void setButtonSheet(String path) {
		this.buttonSheet = ImagesLoader.loadImage(path);
	}
	
	
	
}
