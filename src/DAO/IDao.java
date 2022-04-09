package DAO;

import java.util.List;

public interface IDao<T> {
    
    T get(String id);
    
    List<T> getAll();
    
    void save(T t);
    
    void update(T t, String[] params);
    
    void delete(T t);
}