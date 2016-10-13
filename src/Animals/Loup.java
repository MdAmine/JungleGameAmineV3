package Animals;

import game.Board;
import game.Position;

public class Loup extends Animal {

	public Loup(Board board, int player, Position position, String img) {
		super(board, player, position, img);
	}

	public Loup(Long id, Board board, int player, Position position, String img) {
		super(id, board, player, position, img);
	}

	public int getRang() {
		return 6;
	}
}
