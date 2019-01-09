package model;

public class TileModel {
	private int value;
	
	public TileModel() {
		this(0);
	}
	
	public TileModel(int value) {
		setValue(value);
	}

	public int getValue() {
		return this.value;
	}
	
	public void setValue(int value) {
		if(value < 0) {
			value = 0;
		}
    	this.value = value;
    }
	
	/**
	 * check if a tile can merge with another tile
	 * @param tile
	 * @return
	 */
	public boolean canMerge(TileModel tile) {
		if(tile == null) {
			return false;
		}else if((value == 1 && tile.getValue() == 2) || (value == 2 && tile.getValue() == 1)) {
			return true;
		}
		else if((value > 2 && tile.getValue() > 2) && (value == tile.getValue())){
			return true;
		}
		return false;
	}

    public void merge(TileModel tile){
    	if(tile == null) {
			return;
		}
        this.setValue(value + tile.getValue());
    }

    public void clear(){
        this.setValue(0);
    }
}
