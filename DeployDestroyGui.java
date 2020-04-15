package deployDestroy;

import javax.swing.*;

import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;


public class DeployDestroyGui extends JFrame implements ActionListener {
	private JPanel contentPanel;
	private JPanel boardPanel;
	private JPanel armyPanel;
	private OtherButton[] boardBt;
	private JButton[] armyBt;
	private int armyToDeploy;
	private OtherButton placeholder;
	private Game game;
	private JButton startButton;
	private JPanel startPanel;
	private JLabel winner;
	private JButton infoButton;
	private JPanel first;
	private JLabel dplDstr;
	
	public DeployDestroyGui(Game game) {

		this.game = game;
		/*
		 * this.board = board; this.whitePlayer = whitePlayer; this.blackPlayer =
		 * blackPlayer;
		 */

		// title
		this.setTitle("Deploy Destroy");

		// border layout and panels
		contentPanel = (JPanel) this.getContentPane();
		boardPanel = new JPanel();// (new GridLayout(16,1));
		armyPanel = new JPanel();
		startPanel = new JPanel();
		contentPanel.add(boardPanel/*, BorderLayout.NORTH*/);
		contentPanel.add(startPanel, BorderLayout.CENTER);
		contentPanel.add(armyPanel, BorderLayout.SOUTH);
		contentPanel.setBounds(100, 100, 500, 400);
		boardPanel.setBounds(70, 63, 800, 47);
		//armyPanel.setBounds(105, 176, 511, 41);
		// button values
		boardBt = new OtherButton[16];
		for (int i = 0; i < 16; i++) {
			boardBt[i] = new OtherButton(i, " ");
			boardPanel.add(boardBt[i]);
			boardBt[i].setVisible(false);
			boardBt[i].setBackground(new Color(255, 255, 153));
			boardBt[i].setFont(new Font("Javanese Text", Font.BOLD, 12));
		}
		armyBt = new JButton[8];
		for (int i = 0; i < 8; i++) {
			armyBt[i] = new JButton(String.valueOf(i + 1));
			armyBt[i].setBackground(SystemColor.textHighlightText);
			armyPanel.add(armyBt[i]);
			armyBt[i].addActionListener(this);
			armyBt[i].setVisible(false);
			armyBt[i].setBorder(new OtherButton(10));
		}
		startButton = new JButton("Start");
		startButton.addActionListener(this);
		startButton.setFocusable(false);
		startButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		startButton.setForeground(Color.BLACK);
		startButton.setBackground(Color.WHITE);
		startPanel.add(startButton, BorderLayout.CENTER);
		
		infoButton = new JButton("Info");
		infoButton.addActionListener(this);
		infoButton.setFocusable(false);
		infoButton.setForeground(Color.BLACK);
		infoButton.setBackground(Color.WHITE);
		infoButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		startPanel.add(infoButton);
		
		first = new JPanel();
		contentPanel.add(first, BorderLayout.NORTH);
		dplDstr = new JLabel ("Deploy Destroy");
		dplDstr.setBackground(SystemColor.controlShadow);
		dplDstr.setFont(new Font("Pristina", Font.BOLD, 30));
		dplDstr.setForeground(Color.ORANGE);
		first.add(dplDstr);
	}

	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		if (!game.getBoard().boardIsFull()) {
			if (str == "Start") { 
				startButton.setVisible(false);
				infoButton.setVisible(false);
				dplDstr.setVisible(false);
				for (int i = 0; i < 16; i++) {
					boardBt[i].setVisible(true);
				}
				for (int i = 0; i < 8; i++) {
					armyBt[i].setVisible(true);
				}
				armyToDeploy = 0;
				blackWentFirst();
			}else if(str == "Info") {
				JOptionPane.showMessageDialog(null, "Deplo Destroy is a very fun game, played by one player against the computer.\n"
						+ " When You click Start a coin is tossed to decide who plays first.\n"
						+ " You have an army of tokens from 1 to 8 on your possession. \n"
						+ " You will need to place each White Token on any position you like on the board. Of course as long as the position is not used.\n"
						+ " After every token is placed, you enter the second phase of the game, the DESTROY PHASE\n"
						+ " Destruction of number N of the opponent is possible only if in the surrounding squares (immediate left and right) the player has numbers whose sum exceeds N\n"
						+ " When there is no more tokes left to destroy, The other opponent starts the phase\n"
						+ " Whoever has the largest number of tokes wins.\n "
						+ " If the number of tokes is the same, the player that has the largest sum of corresponding tokens wins.\n"
						+ " \n                                          ENJOY :)");
			}
			else if (armyToDeploy == 0) {

				for (int i = 0; i < 16; i++) {
					boardBt[i].addActionListener(this);
				}
				for (int i = 0; i < 8; i++) {
					armyBt[i].removeActionListener(this);
				}
				armyToDeploy = Integer.parseInt(str);
			} else if (str == " ") {
				placeholder = (OtherButton) e.getSource();
				armyBt[armyToDeploy - 1].setVisible(false);

				game.getPlayer("white").deploy(armyToDeploy - 1, placeholder.getPosition());
				if (!game.getBoard().boardIsFull()) {
					game.blackDeploy();
				}
				updateBoard();
				for (int i = 0; i < 16; i++) {
					if (!game.getBoard().boardIsFull())
						boardBt[i].removeActionListener(this);
				}
				for (int i = 0; i < 8; i++) {
					if (!game.getBoard().boardIsFull())
						armyBt[i].addActionListener(this);
				}
				armyToDeploy = 0;
				if (game.getBoard().boardIsFull() && !game.getPlayer("white").isFirst()) {
					game.blackDestroys();
					updateBoard();
				}
			}
		} else {
			if (!game.getPlayer("white").isFirst()) {
				game.getPlayer("white").destroy(((OtherButton) e.getSource()).getPosition(), "white");
				updateBoard();
				if (!game.getBoard().playerCanDestroy("white")) {
					boardPanel.setVisible(false);
					winner = new JLabel(game.getWinner());
					startPanel.add(winner);
				}

			} else {
				game.getPlayer("white").destroy(((OtherButton) e.getSource()).getPosition(), "white");
				updateBoard();
				if (!game.getBoard().playerCanDestroy("white")) {
					game.blackDestroys();
					updateBoard();
					if (!game.getBoard().playerCanDestroy("black")) {
						boardPanel.setVisible(false);
						winner = new JLabel(game.getWinner());
						winner.setFont(new Font("Tahoma", Font.ITALIC, 26));
						winner.setBackground(new Color(255, 255, 153));
						startPanel.add(winner);
					}
				}
			}
		}
	}

	public void updateBoard() {
		for (int i = 0; i < 16; i++) {
			Army testReceive = game.getBoard().getArmyInPosition(i);
			int testIntReceive = 0;
			if (testReceive != null) {
				testIntReceive = testReceive.getNumber();
				boardBt[i].setText(Integer.toString(testIntReceive));
				if (testReceive.getColor().equals("white")) {
					boardBt[i].setBackground(SystemColor.textHighlightText);
					if (testIntReceive == 0) {
						boardBt[i].setBackground(SystemColor.inactiveCaption); //When black player destroys white tokens
					}
				}
				else {
					boardBt[i].setBackground(Color.black);
					boardBt[i].setForeground(Color.WHITE);
				}
			}
		}
	}

	public void blackWentFirst() {
		game.getPlayer("white").setIsFirst(game.coinToss());
		if (!game.getPlayer("white").isFirst()) {
			game.blackDeploy();
			updateBoard();
		}
	}
}