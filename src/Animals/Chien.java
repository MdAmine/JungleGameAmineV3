package Animals;

import game.Board;
import game.Position;

public class Chien extends Animal {

	public Chien(Board board, int player, Position position, String img) {
		super(board, player, position, img);
	}

	public Chien(Long id, Board board, int player, Position position, String img) {
		super(id, board, player, position, img);
	}

	public int getRang() {
		return 5;
	}
}
