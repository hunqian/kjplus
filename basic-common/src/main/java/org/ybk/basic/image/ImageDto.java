package org.ybk.basic.image;

public class ImageDto {
	private String imageFileName;
	private int x = 0;
	private int y = 0;
	//目前只假设正方形，如果x、y大于了max，要缩小
	private int max = 200;

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public String toString(){
		StringBuffer buf = new StringBuffer();
		buf.append(imageFileName).append(",");
		buf.append("x=").append(x);
		buf.append(",y=").append(y);
		return buf.toString();
	}
}
