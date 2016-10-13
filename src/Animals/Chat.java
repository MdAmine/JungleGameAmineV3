package Animals;

import game.Board;
import game.Position;

public class Chat extends Animal {

	public Chat(Board board, int player, Position position, String img) {
		super(board, player, position, img);
	}

	public Chat(Long id, Board board, int player, Position position, String img) {
		super(id, board, player, position, img);
	}

	public int getRang() {
		return 7;
	}
}
