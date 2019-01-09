package model;

public class TileModel {
	private int value;
	private int xwaarde;
	private int ywaarde;
	
	public TileModel(int value,int x, int y) {
		this.value = value;
		this.xwaarde = x;
		this.ywaarde = y;
	}

	public int getValue() {
		return this.value;
	}
	
	public int getX() {
		return this.xwaarde;
	}
	
	public int getY() {
		return this.ywaarde;
	}
	
	public boolean isEdgeTile() {
		return xwaarde == 0 || xwaarde == 3 || ywaarde == 0 || ywaarde == 3;
	}
}
