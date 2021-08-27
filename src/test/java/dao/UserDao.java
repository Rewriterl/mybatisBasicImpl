package dao;

import org.dom4j.DocumentException;
import pojo.User;

import java.beans.PropertyVetoException;
import java.util.List;

public interface UserDao {
    public List<User> findAll() throws Exception;

    public User findByCondition(User user) throws PropertyVetoException, DocumentException, Exception;
}
