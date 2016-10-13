package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import Animals.*;
import game.Board;
import game.User;

public class BoardDAO extends DAO<Board> {

	public Board create(Board board) {
		try {
			if (board.getUser().getId() == 0) {
				DAO<User> userDAO = new UserDAO();
				board.setUser(userDAO.create(board.getUser()));
			}

			PreparedStatement prepare;

			long time = System.currentTimeMillis();

			board.setDatePartie(new Date(time));
			board.setTimePartie(new Time(time));

			prepare = this.connect.prepareStatement(

					"INSERT INTO board (datepartie,timepartie, curplayer , scorepartie , id_user , completed) VALUES(?, ? , ? , ? ,?,?)");

			prepare.setDate(1, board.getDatePartie());
			prepare.setTime(2, board.getTimePartie());
			prepare.setInt(3, board.getCurPlayer());
			prepare.setInt(4, board.getScorePartie());

			prepare.setLong(5, board.getUser().getId());

			prepare.setBoolean(6, board.isCompleted());
			prepare.executeUpdate();

			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
					.executeQuery("SELECT * FROM board");

			result.last();
			long id = result.getLong("id");

			if (board.isCompleted()) {
				return null;
			} else {
				Animal animal = null;
				for (int i = 0; i < board.getAnimals().size(); i++) {
					animal = board.getAnimals().get(i);
					if (animal.getId() == 0) {
						DAO<Animal> animalDAO = new AnimalDAO();
						animal = animalDAO.create(animal);
					}

					PreparedStatement prepare2 = this.connect
							.prepareStatement("INSERT INTO board_animal (id_board, id_animal) " + " VALUES(? , ? ) ");
					prepare2.setLong(1, id);
					prepare2.setLong(2, animal.getId());
					prepare2.executeUpdate();
				}
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return null;
	}

	public Board find(long id) {
		Board board = new Board();

		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery(
							"SELECT * FROM board , board_animal ,animal " + "WHERE board.id = board_animal.id_board "
									+ "and board_animal.id_animal = animal.id " + "and board.id = " + id);

			if (result.first()) {
				AnimalDAO animalDAO = new AnimalDAO();
				BoardDAO boardDAO = new BoardDAO();

				List<Animal> animals = new ArrayList<Animal>();

				result.first();
				board = new Board(id, result.getInt("curplayer"), result.getDate("datepartie"),
						result.getTime("timepartie"), result.getInt("scorepartie"), result.getBoolean("completed"),
						new UserDAO().find(result.getLong("id_user")), animals);

				result.beforeFirst();
				while (result.next()) {
					animals.add(animalDAO.find(result.getLong("animal.id"), board));
				}

				board.setAnimals(animals);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return board;
	}

	public List<Board> findByUserId(long id, int x) {
		List<Board> board = new ArrayList<Board>();

		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
					.executeQuery("SELECT * FROM board " + "WHERE board.id_user = " + id);

			int i = 0;
			while (result.next()) {
				if (result.getBoolean("completed") == false && x == 1) {
					BoardDAO boardDAO = new BoardDAO();
					// result.beforeFirst();

					board.add(new Board(result.getLong("id"), result.getInt("curplayer"), result.getDate("datepartie"),
							result.getTime("timepartie"), result.getInt("scorepartie"), result.getBoolean("completed"),
							new UserDAO().find(result.getLong("id_user")), null));
					i++;
				}
				if (result.getBoolean("completed") == true && x == 2) {
					BoardDAO boardDAO = new BoardDAO();
					// result.beforeFirst();

					board.add(new Board(result.getLong("id"), result.getInt("curplayer"), result.getDate("datepartie"),
							result.getTime("timepartie"), result.getInt("scorepartie"), result.getBoolean("completed"),
							new UserDAO().find(result.getLong("id_user")), null));
					i++;
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return board;
	}

	public Board update(Board obj) {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(Board board) {
		try {

			AnimalDAO animalDAO = new AnimalDAO();
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
					.executeQuery("SELECT id_animal FROM board_animal WHERE id_board = " + board.getId());

			while (result.next()) {
				animalDAO.delete(result.getLong("id_animal"));
			}

			this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
					.executeUpdate("DELETE FROM board_animal WHERE id_board = " + board.getId());

			this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
					.executeUpdate("DELETE FROM board WHERE board.id = " + board.getId());

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
