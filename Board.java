package deployDestroy;

import java.util.Arrays;

public class Board {
	private Army[] battlefield;

	public Board() {
		battlefield = new Army[16];

	}

	public void deployToPosition(Army army, int position) {
		battlefield[position] = army;
		army.setIsDeployed();
	}

	
	
	public boolean boardIsFull() {
		for(Army member:battlefield) {
			if(member == null) {
				return false;
			}
		}
		return true;
	}
	
	public boolean playerCanDestroy(String color) {
		for (int i = 0; i < battlefield.length; i++) {
			if (!battlefield[i].getColor().equals(color)) {
				if (battlefield[i].getNumber() < getAdjacentSum(i) && battlefield[i].getNumber() != 0) {
					return true;
				}
			}
		}
		return false;
	}

	public int getAdjacentSum(int i) {
		int sum = 0;
		if (i == 0) {
			if (!sameColor(battlefield[i], battlefield[i + 1])) {
				sum += battlefield[i + 1].getNumber();
			}
		} else if (i == 15) {
			if (!sameColor(battlefield[i], battlefield[i - 1])) {
				sum += battlefield[i - 1].getNumber();
			}
		} else {
			if (!sameColor(battlefield[i], battlefield[i + 1])) {
				sum += battlefield[i + 1].getNumber();
			}
			if (!sameColor(battlefield[i], battlefield[i - 1])) {
				sum += battlefield[i - 1].getNumber();
			}
		}
		return sum;
	}

	public boolean sameColor(Army army, Army army1) {
		return army.getColor().equals(army1.getColor());
	}

	public Army[] getBattlefield() {
		return battlefield;
	}

	public int getPosition(int i) {
		if (battlefield[i] == null) {
			return 0;
		} else {
			return battlefield[i].getNumber();
		}
	}

	public Army getArmyInPosition(int i) {
		if (battlefield[i] != null) {
			return battlefield[i];
		} else {
			return null;
		}
	}

	public void destroyArmy(int position) {
		battlefield[position].setNumber(0);
	}
}
