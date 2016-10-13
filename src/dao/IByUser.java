package dao;

import java.util.List;

import game.Board;

public interface IByUser {

	public List<Board> findByUserId(long id);

}
