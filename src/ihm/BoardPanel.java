package ihm;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Animals.Animal;
import game.Board;
import game.Position;
import observer.Observateur;

public class BoardPanel extends JPanel {

	private Board board;
	private SquareBtn[][] squareBtn = new SquareBtn[9][7];

	public BoardPanel(Board board) {

		this.board = board;

		setLayout(new GridLayout(9, 7));
		initSquareBtn();

		SquareBtnListner sbListner = new SquareBtnListner();

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 7; j++) {
				add(squareBtn[i][j]);
				squareBtn[i][j].addActionListener(sbListner);
			}
		}
	}

	private void initSquareBtn() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 7; j++) {
				squareBtn[i][j] = new SquareBtn(new Position(i, j));
				squareBtn[i][j].setBackground(Color.WHITE);
			}
		}

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 7; j++) {
				squareBtn[i][j].setBackground(Color.WHITE);
			}
		}

		for (int i = 3; i < 6; i++) {
			for (int j = 1; j < 6; j++) {
				if (j == 3)
					continue;
				squareBtn[i][j].setBackground(Color.CYAN);
			}
		}

		squareBtn[0][2].setBackground(Color.GREEN);
		squareBtn[0][4].setBackground(Color.GREEN);
		squareBtn[1][3].setBackground(Color.GREEN);
		squareBtn[0][3].setBackground(Color.ORANGE);

		squareBtn[8][2].setBackground(Color.GREEN);
		squareBtn[8][4].setBackground(Color.GREEN);
		squareBtn[7][3].setBackground(Color.GREEN);
		squareBtn[8][3].setBackground(Color.ORANGE);

		setUpBoard();
	}

	public void setUpBoard() {
		for (Animal animal : board.getAnimals()) {
			int x = animal.getPosition().getX();
			int y = animal.getPosition().getY();

			squareBtn[x][y].setAnimal(animal);
		}

	}

	public class SquareBtnListner implements ActionListener {
		private SquareBtn btnClicked;

		private boolean click;
		private List<Position> possibleMoves;
		private Animal animal;

		public void actionPerformed(ActionEvent e) {
			btnClicked = (SquareBtn) e.getSource();
			if (!click) {
				Animal animal2 = btnClicked.getAnimal();
				if (animal2 != null && animal2.getPlayer() == board.getCurPlayer()) {
					animal = animal2;
					possibleMoves = animal2.getPossibleMoves();
					colorAccessibleSquare(possibleMoves, Color.GRAY);
					click = true;
				}
			} else {
				Animal animal2 = btnClicked.getAnimal();
				if (animal2 != null && animal2.getPlayer() == board.getCurPlayer()) {
					animal = animal2;
					colorAccessibleSquare(possibleMoves, Color.WHITE);
					colorAccessibleSquare(board.getTrap(), Color.GREEN);
					colorAccessibleSquare(board.getSanctuary(), Color.ORANGE);
					colorAccessibleSquare(board.getRiver(), Color.CYAN);

					possibleMoves = animal2.getPossibleMoves();

					colorAccessibleSquare(possibleMoves, Color.GRAY);

					click = true;
				} else {
					Position pos = btnClicked.getPosition();
					for (Position position : possibleMoves) {
						if (position.equals(pos)) {
							squareBtn[animal.getPosition().getX()][animal.getPosition().getY()].setAnimal(null);
							squareBtn[pos.getX()][pos.getY()].setAnimal(null);
							int a = board.move(animal, pos);

							setUpBoard();
							colorAccessibleSquare(possibleMoves, Color.WHITE);
							colorAccessibleSquare(board.getTrap(), Color.GREEN);
							colorAccessibleSquare(board.getSanctuary(), Color.ORANGE);
							colorAccessibleSquare(board.getRiver(), Color.CYAN);
							click = false;

							if (board.getCurPlayer() == 1)
								board.setCurPlayer(2);
							else
								board.setCurPlayer(1);

							if (a == 1) {
								
								board.setCompleted(true);
								board.saveBoard(board);
								JOptionPane.showMessageDialog(null, "Vous avez gagné, votre score est "+board.getScorePartie(), "Information",
										JOptionPane.INFORMATION_MESSAGE);

							} else if (a == 2) {
								JOptionPane.showMessageDialog(null, "player 2 gagné", "Information",
										JOptionPane.INFORMATION_MESSAGE);
							}
						}
					}

				}
			}
		}

	}

	public void colorAccessibleSquare(List<Position> positions, Color color) {
		for (Position position : positions) {
			int x = position.getX();
			int y = position.getY();

			squareBtn[x][y].setBackground(color);
		}
	}

	public void colorAccessibleSquare(Position[] positions, Color color) {
		for (Position position : positions) {
			int x = position.getX();
			int y = position.getY();

			squareBtn[x][y].setBackground(color);
		}
	}
}
