package deployDestroy;

public class Army {
	private int number;
	private String color;
	private boolean isDeployed;

	public Army(String color, int number) {
		this.number = number;
		this.color = color;
		isDeployed = false;
	}

	public int getNumber() {
		if (number != 0) {
			return number;
		} else {
			return 0;
		}
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getColor() {
		return color;
	}

	public void setIsDeployed() {
		isDeployed = true;
	}

	public boolean getIsDeployed() {
		return isDeployed;
	}
}
