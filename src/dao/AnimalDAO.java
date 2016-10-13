package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Animals.*;
import game.Board;
import game.Position;

public class AnimalDAO extends DAO<Animal> {

	public Animal create(Animal animal) {

		try {
			PreparedStatement prepare = this.connect
					.prepareStatement("INSERT INTO animal (animal,player , trapped,posx,posy) VALUES( ?, ?, ?,?,?) ");

			prepare.setString(1, animal.getClass().getName());
			prepare.setInt(2, animal.getPlayer());
			prepare.setBoolean(3, animal.isTrapped());
			prepare.setInt(4, animal.getPosition().getX());
			prepare.setInt(5, animal.getPosition().getY());

			prepare.executeUpdate();

			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
					.executeQuery("SELECT * FROM animal");

			result.last();

			animal = this.find(result.getLong("id"),null);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return animal;
	}

	public Animal find(long id, Board board) {
		Animal animal = null;

		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM animal WHERE id = " + id);

			if (result.first()) {

				String a = result.getString("animal");
				if ("Animals.Chat".equals(a))
					animal = new Chat(id, board, result.getInt("player"),
							new Position(result.getInt("posX"), result.getInt("posY")), "chat.png");
				else if ("Animals.Chien".equals(a)) {
					animal = new Chien(id, board, result.getInt("player"),
							new Position(result.getInt("posX"), result.getInt("posY")), "chien.png");

				} else if ("Animals.Loup".equals(a)) {
					animal = new Loup(id, board, result.getInt("player"),
							new Position(result.getInt("posX"), result.getInt("posY")), "loup.png");

				} else if ("Animals.Tigre".equals(a)) {
					animal = new Tigre(id, board, result.getInt("player"),
							new Position(result.getInt("posX"), result.getInt("posY")), "tigre.png");

				} else if ("Animals.Panthere".equals(a)) {
					animal = new Panthere(id, board, result.getInt("player"),
							new Position(result.getInt("posX"), result.getInt("posY")), "panther.png");

				} else if ("Animals.Rat".equals(a)) {
					animal = new Rat(id, board, result.getInt("player"),
							new Position(result.getInt("posX"), result.getInt("posY")), "rat.png");

				} else if ("Animals.Elephant".equals(a)) {
					animal = new Elephant(id, board, result.getInt("player"),
							new Position(result.getInt("posX"), result.getInt("posY")), "elephant.png");

				} else if ("Animals.Lion".equals(a)) {
					animal = new Lion(id, board, result.getInt("player"),
							new Position(result.getInt("posX"), result.getInt("posY")), "lion.png");

				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return animal;
	}

	public Animal update(Animal obj) {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(Long id) {
		try {
			this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
					.executeUpdate("DELETE FROM animal WHERE animal.id = " + id);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete(Animal obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public Animal find(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
