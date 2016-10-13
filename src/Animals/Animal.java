package Animals;

import java.util.ArrayList;
import java.util.List;

import game.Board;
import game.Position;

public abstract class Animal {

	protected int player;
	protected boolean trapped;
	protected Position position;
	protected String img;
	protected Long id = new Long(0);

	protected Board board;

	public Animal(Board board, int player, Position position, String img) {
		this.board = board;
		this.player = player;
		this.position = position;
		this.img = img;
	}

	public Animal(Long id, Board board, int player, Position position, String img) {
		this.id = id;
		this.board = board;
		this.player = player;
		this.position = position;
		this.img = img;
	}

	public List<Position> getPossibleMoves() {

		List<Position> positions = board.allPossibleMoves(position);

		List<Position> toRemove = new ArrayList<Position>();

		Animal p = null;

		for (int i = 0; i < positions.size(); i++) {
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

				}
			}

			if (board.InRiver(positions.get(i))) {
				toRemove.add(positions.get(i));
				continue;
			}
		}
		for (Position pos : toRemove) {
			positions.remove(pos);
		}
		return positions;
	}

	public int getPlayer() {
		return player;
	}

	public void setPlayer(int player) {
		this.player = player;
	}

	public boolean isTrapped() {
		return trapped;
	}

	public void setTrapped(boolean trapped) {
		this.trapped = trapped;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImg() {
		return img;
	}

	public int getRang() {
		return 0;
	}

}
