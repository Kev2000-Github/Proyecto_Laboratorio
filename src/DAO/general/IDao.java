package DAO.general;

import java.sql.ResultSet;
import java.util.List;

public interface IDao<T> {
    T setEntity(ResultSet rs);

    T get(String id);
    
    List<T> getAll();
    
    void save(T t);
    
    void update(T t);
    
    void delete(T t);
}