package Animals;

import game.Board;
import game.Position;

public class Elephant extends Animal {

	public Elephant(Board board, int player, Position position, String img) {
		super(board, player, position, img);
	}

	public Elephant(Long id, Board board, int player, Position position, String img) {
		super(id, board, player, position, img);
	}

	public int getRang() {
		return 1;
	}
}
