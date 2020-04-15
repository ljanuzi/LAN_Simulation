package deployDestroy;

import java.util.Arrays;

public class Player {
	boolean isFirst;
	private Army[] army;
	private String color;
	private Board board;

	public Player(String color, Board board) {
		isFirst = true;
		this.color = color;
		army = new Army[8];
		for (int i = 0; i < 8; i++) {
			army[i] = new Army(this.color, i + 1);
		}
		this.board = board;
	}

	public void deploy(int army, int position) {
		board.deployToPosition(this.army[army], position);
	}

	public void destroy(int position, String color) {
		if((board.getArmyInPosition(position).getNumber()<board.getAdjacentSum(position)) && board.getArmyInPosition(position).getColor()!=color) {
			board.destroyArmy(position);
		}
	}
	
	public boolean isFirst() {
		return isFirst;
	}

	public Army getArmy(int i) {
		return army[i];
	}

	public Army[] getArmies() {
		return army;
	}

	public void setArmyNumber(Army army, int number) {
		army.setNumber(number);
	}

	public void setIsFirst(boolean isFirst) {
		this.isFirst = isFirst;
	}
	
	public String getColor() {
		return color;
	}
}
