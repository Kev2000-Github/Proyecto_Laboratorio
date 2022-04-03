package DAO;

import java.util.List;
import java.util.Optional;
import java.util.Vector;

public interface IDao<T> {
    
    T get(long id);
    
    Vector<T> getAll();
    
    void save(T t);
    
    void update(T t, String[] params);
    
    void delete(T t);
}