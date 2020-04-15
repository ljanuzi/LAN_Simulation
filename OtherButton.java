package deployDestroy;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.border.Border;

public class OtherButton extends JButton implements Border{
	private int radius;
	//arditi
	private int position;
	//arditi
	public OtherButton(int position, String text) {
		super(text);
		this.position=position;
	}
	OtherButton(int radius) {
        this.radius = radius;
    }
	//arditi
	public int getPosition(){
		return position;
	}

	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		g.drawOval(x, y, width, height);
	}

	@Override
	public Insets getBorderInsets(Component c) {
		// TODO Auto-generated method stub
		return new Insets(this.radius, this.radius, this.radius, this.radius);
	}

	@Override
	public boolean isBorderOpaque() {
		// TODO Auto-generated method stub
		return true;
	}
}
