package Animals;

import java.util.ArrayList;
import java.util.List;

import game.Board;
import game.Position;

public class Rat extends Animal {

	public Rat(Board board, int player, Position position, String img) {
		super(board, player, position, img);
	}

	public Rat(Long id, Board board, int player, Position position, String img) {
		super(id, board, player, position, img);
	}

	public List<Position> getPossibleMoves() {

		List<Position> posistions = board.allPossibleMoves(position);

		List<Position> toRemove = new ArrayList<Position>();

		Animal p = null;

		for (int i = 0; i < posistions.size(); i++) {
			p = board.getAnimalAt(posistions.get(i));

			if (p != null) {

				if (p.getRang() < getRang() && p.getRang() != 1) {
					if (p.isTrapped()) {

					} else {
						toRemove.add(posistions.get(i));
						continue;
					}

				}
				if (p.getPlayer() == player) {
					toRemove.add(posistions.get(i));
					continue;
				}
				if (board.InRiver(position)) {
					toRemove.add(posistions.get(i));
					continue;
				}
			}

		}

		for (Position pos : toRemove) {
			posistions.remove(pos);
		}

		return posistions;
	}

	public int getRang() {
		return 8;
	}
}
