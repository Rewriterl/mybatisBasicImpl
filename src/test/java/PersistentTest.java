import com.stelpolvo.io.Resources;
import com.stelpolvo.sqlSession.SqlSession;
import com.stelpolvo.sqlSession.SqlSessionFactory;
import com.stelpolvo.sqlSession.SqlSessionFactoryBuilder;
import dao.UserDao;
import org.junit.jupiter.api.Test;
import pojo.User;

import java.io.InputStream;
import java.util.List;

public class PersistentTest {
    @Test
    public void test() throws Exception {
        InputStream resourcesAsStream = Resources.getResourcesAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourcesAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = new User(1, "张三");
//        User one = sqlSession.selectOne("user.selectOne", user);
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        List<User> all = userDao.findAll();
        System.out.println(all);
    }
}
