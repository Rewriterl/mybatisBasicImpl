package com.stelpolvo.sqlSession;

import com.stelpolvo.pojo.Configuration;

public class DefaultSqlSessionFactory implements SqlSessionFactory {

    Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }
}
