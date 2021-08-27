package com.stelpolvo.sqlSession;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface SqlSession {
    public <E> List<E> selectList(String statementId, Object... params) throws Exception;

    public <T> T selectOne(String statementId, Object... params) throws Exception;

    public <T> T getMapper(Class<?> mapperClass);
}
