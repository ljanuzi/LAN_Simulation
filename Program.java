package deployDestroy;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Game game = new Game();
		DeployDestroyGui gui = new DeployDestroyGui(game);
		gui.setVisible(true);
		gui.setBounds(100, 100, 1000, 250);
		/*Game game = new Game();
		game.deploy();
		/*
		 * game.deploy(); game.destroy();
		 */
	}
	
}
