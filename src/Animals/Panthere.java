package Animals;

import game.Board;
import game.Position;

public class Panthere extends Animal {

	public Panthere(Board board, int player, Position position, String img) {
		super(board, player, position, img);
	}

	public Panthere(Long id, Board board, int player, Position position, String img) {
		super(id, board, player, position, img);
	}

	public int getRang() {
		return 4;
	}
}
