package deployDestroy;

import java.util.Random;
import java.util.Scanner;

public class Game {
	private Board board;
	private Player whitePlayer;
	private Player blackPlayer;
	Random rand = new Random();

	public Game() {
		board = new Board();
		whitePlayer = new Player("white", board);
		blackPlayer = new Player("black", board);
	}

	public boolean coinToss() {
		return (Math.random() < 0.5);
	}

	public void blackDeploy() {
		int armyNumber = rand.nextInt(8);
		int position = rand.nextInt(16);
		if (!blackPlayer.getArmy(armyNumber).getIsDeployed() && board.getPosition(position) == 0) {
			blackPlayer.deploy(armyNumber, position);
		} else {
			blackDeploy();
		}
	}

	public void blackDestroys() {
		for (int i = 0; i < 16; i++) {
			blackPlayer.destroy(i, "black");
		}
	}

	public Player getPlayer(String player) {
		if (player.equals(whitePlayer.getColor())) {
			return whitePlayer;
		}
		return blackPlayer;
	}

	public Board getBoard() {
		return board;
	}

	public String getWinner() {
		int whitetracker = 0;
		int blacktracker = 0;
		int whiteSum = 0;
		int blackSum = 0;
		for (int i = 0; i < 16; i++) {
			if (board.getArmyInPosition(i).getNumber() != 0) {
				if (board.getArmyInPosition(i).getColor().equals("white")) {
					whitetracker++;
					whiteSum += board.getArmyInPosition(i).getNumber();
				} else {
					blacktracker++;
					blackSum += board.getArmyInPosition(i).getNumber();
				}
			}
		}
		if (whitetracker == blacktracker) {
			if (whiteSum > blackSum) {
				return "White Player has: " + whiteSum + " tokens left.\n" + "Black Player has: " + blackSum
						+ " tokens left.\n" + " White Player Wins!";
			} else if (whiteSum < blackSum) {
				return "Black Player has: " + blackSum + " tokens left.\n" + " White Player has: " + whiteSum
						+ " tokens left.\n" + " Black Player Wins!";
			} else {
				return "White Player has: " + whiteSum + " tokens left.\n" + " Black Player has: " + blackSum
						+ " tokens left.\n" + " It is a tie!";
			}
		} else if (whitetracker > blacktracker) {
			return "White Player has: " + whitetracker + " tokens left.\n" + " Black Player has: " + blacktracker
					+ " tokens left.\n" + " White Player Wins!";
		} else {
			return "White Player has: " + whitetracker + " tokens left.\n" + " Black Player has: " + blacktracker
					+ " tokens left.\n" + " Black Player Wins!";
		}
	}
}