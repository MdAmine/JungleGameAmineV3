package Animals;

import java.util.ArrayList;
import java.util.List;

import game.Board;
import game.Position;

public class Lion extends Animal {

	public Lion(Board board, int player, Position position, String img) {
		super(board, player, position, img);
	}

	public Lion(Long id, Board board, int player, Position position, String img) {
		super(id, board, player, position, img);
	}

	public List<Position> getPossibleMoves() {

		List<Position> positions = board.allPossibleMoves(position);

		List<Position> toRemove = new ArrayList<Position>();

		Animal p = null;

		for (int i = 0; i < positions.size(); i++) {

			if (board.InRiver(positions.get(i))) {
				if (position.getX() == 2 && (position.getY() == 1 || position.getY() == 2 || position.getY() == 4
						|| position.getY() == 5)) {

					positions.get(i).setX(positions.get(i).getX() + 3);
					if (board.getAnimalAt(new Position(position.getX() + 1, position.getY())) != null
							|| board.getAnimalAt(new Position(position.getX() + 2, position.getY())) != null
							|| board.getAnimalAt(new Position(position.getX() + 3, position.getY())) != null) {
						toRemove.add(positions.get(i));
						continue;
					}

				}

				if (position.getX() == 6 && (position.getY() == 1 || position.getY() == 2 || position.getY() == 4
						|| position.getY() == 5)) {
					positions.get(i).setX(positions.get(i).getX() - 3);

					if (board.getAnimalAt(new Position(position.getX() - 1, position.getY())) != null
							|| board.getAnimalAt(new Position(position.getX() - 2, position.getY())) != null
							|| board.getAnimalAt(new Position(position.getX() - 3, position.getY())) != null) {
						toRemove.add(positions.get(i));
						continue;
					}
				}

				if (position.getY() == 0 && (position.getX() == 3 || position.getX() == 4 || position.getX() == 5)) {
					positions.get(i).setY(positions.get(i).getY() + 2);

					if (board.getAnimalAt(new Position(position.getX(), position.getY() + 1)) != null
							|| board.getAnimalAt(new Position(position.getX(), position.getY() + 2)) != null) {
						toRemove.add(positions.get(i));
						continue;
					}
				}

				if (position.getY() == 6 && (position.getX() == 3 || position.getX() == 4 || position.getX() == 5)) {
					positions.get(i).setY(positions.get(i).getY() - 2);

					if (board.getAnimalAt(new Position(position.getX(), position.getY() - 1)) != null
							|| board.getAnimalAt(new Position(position.getX(), position.getY() - 2)) != null) {
						toRemove.add(positions.get(i));
						continue;
					}
				}

				if (position.getY() == 3 && (position.getX() == 3 || position.getX() == 4 || position.getX() == 5)) {

					if (positions.get(i).getY() < position.getY()) {
						positions.get(i).setY(positions.get(i).getY() - 2);

						if (board.getAnimalAt(new Position(position.getX(), position.getY() - 1)) != null
								|| board.getAnimalAt(new Position(position.getX(), position.getY() - 2)) != null) {
							toRemove.add(positions.get(i));
							continue;
						}
					} else {
						positions.get(i).setY(positions.get(i).getY() + 2);

						if (board.getAnimalAt(new Position(position.getX(), position.getY() + 1)) != null
								|| board.getAnimalAt(new Position(position.getX(), position.getY() + 2)) != null) {
							toRemove.add(positions.get(i));
							continue;
						}
					}

				}

			}

			p = board.getAnimalAt(positions.get(i));
			if (p != null) {

				if (p.getPlayer() == player) {
					toRemove.add(positions.get(i));
					continue;
				}
				if (p.getRang() < getRang()) {
					if (p.isTrapped()) {

					} else {
						toRemove.add(positions.get(i));
						continue;
					}
					;
				}
			}

		}
		for (Position pos : toRemove) {
			positions.remove(pos);
		}
		return positions;
	}

	public int getRang() {
		return 2;
	}
}
