package com.stelpolvo.sqlSession;

import com.stelpolvo.pojo.Configuration;
import com.stelpolvo.pojo.MappedStatement;

import java.beans.IntrospectionException;
import java.lang.reflect.*;
import java.sql.SQLException;
import java.util.List;

public class DefaultSqlSession implements SqlSession {

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    Configuration configuration;

    @Override
    public <E> List<E> selectList(String statementId, Object... params) throws Exception {
        SimpleExecutor simpleExecutor = new SimpleExecutor();
        MappedStatement mappedStatement = configuration.getMappedStatementMap().get(statementId);
        List<Object> list = simpleExecutor.query(configuration, mappedStatement, params);
        return (List<E>) list;
    }

    @Override
    public <T> T selectOne(String statementId, Object... params) throws Exception {
        List<Object> objects = selectList(statementId, params);
        if (objects.size() == 1) {
            return (T) objects.get(0);
        } else {
            throw new RuntimeException("One Or More!");
        }
    }

    @Override
    public <T> T getMapper(Class<?> mapperClass) {
        Object proxyInstance = Proxy.newProxyInstance(DefaultSqlSession.class.getClassLoader(), new Class[]{mapperClass}, new InvocationHandler() {
            /**
             *
             * @param proxy 被代理对象的引用
             * @param method 被代理方法的引用
             * @param args 传递的参数
             * @return
             * @throws Throwable
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                String methodName = method.getName();
                String className = method.getDeclaringClass().getName();
                String statementId = className + "." + methodName;
                Type genericReturnType = method.getGenericReturnType();
                // 判断是否进行了泛型类型参数化(返回值类型是否有泛型,这里是偷懒行为)
                if (genericReturnType instanceof ParameterizedType){
                    List<Object> list = selectList(statementId, args);
                    return list;
                }
                return selectOne(statementId,args);
            }
        });
        return (T) proxyInstance;
    }
}
