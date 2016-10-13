package ihm;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import Animals.Animal;
import game.Position;

public class SquareBtn extends JButton {

	private Animal animal;

	private Position position;

	public SquareBtn(Position position) {
		this.position = position;
	}

	public SquareBtn(Animal animal, Position position) {
		this.animal = animal;
		this.position = position;
	}

	public Animal getAnimal() {
		return animal;
	}

	public Position getPosition() {
		return position;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
		if (animal != null) {
			if (animal.getPlayer() == 1)
				setIcon(new ImageIcon("images/p1/" + animal.getImg()));
			else
				setIcon(new ImageIcon("images/p2/" + animal.getImg()));
		} else {
			setIcon(null);
		}
	}

}
