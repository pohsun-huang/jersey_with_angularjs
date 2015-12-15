package dao;

import java.util.List;

import model.Model;

public interface Dao<T extends Model, I> {

	List<T> findAll();

	T find(I id);

	T save(T newsEntry);

	void delete(I id);

}
