package com.ithaima.test;/*
@outhor shkstart
@date 2019/12/11-14:23
*/


import com.ithaima.dao.IUserDao;
import com.ithaima.domain.QueryVo;
import com.ithaima.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MybatisTest {
   /* public static void main(String[] args) throws  Exception {

    }*/
   private InputStream in;
   private SqlSession session;
   private IUserDao userDao;
   private SqlSessionFactory factory;
   @Before//用于在测试方法执行之前执行
   public void init() throws Exception {
       //1.读取配置文件
       in = Resources.getResourceAsStream("SqlMapConfig.xml");
       //2.创建SqlSessionFactory工厂
        factory=new SqlSessionFactoryBuilder().build(in);
       //3.使用工厂生产SqlSession对象
        session = factory.openSession();
       //4.使用SqlSession创建Dao接口的代理对象
        userDao = session.getMapper(IUserDao.class);
   }
   @After//用于在测试方法执行之后执行
   public void destroy() throws Exception {
       //提交事务
       session.commit();
       //6.释放资源
       session.close();
       in.close();
   }
   /**
    * 查询所有用户信息
    * */
    @Test
    public void testFindAll()  {
        //5.使用代理对象执行方法
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }

    }
    /**
     * 测试保存所有操作
     * */
    @Test
     public void testSave()  {
         User user = new User();
         user.setUserName("mybatis user property");
         user.setUserAddress("郑州八戒工场");
         user.setUserSex("男");
         user.setUserBirthday(new Date());
        System.out.println("保存所有操作之前"+user);
         //5.使用代理对象执行保存方法
          userDao.saveUser(user);
        System.out.println("保存所有操作之后"+user);
     }
    /**
     * 测试更新 操作
     * */
    @Test
    public void testUpdate() {
        User user = new User();
        user.setUserId(51);
        user.setUserName("mybatis Update user");
        user.setUserAddress("郑州八戒工场");
        user.setUserSex("女 ");
        user.setUserBirthday(new Date());

        //5.使用代理对象执行保存方法
        userDao.updateUser(user);
    }
    /**
     * 测试删除操作
     * */
    @Test
    public void testDelete() {

        //5.使用代理对象执行删除方法
        userDao.deleteUser(52 );
    }
    /**
     * 测试查询一个操作
     * */
    @Test
    public void testFindOne() {

        //5.使用代理对象执行查询一个方法
        User user = userDao.findById(51);
        System.out.println(user);
    }
    /**
     * 测试模糊查询操作
     * */
    @Test
    public void testFindByName() {

        //5.使用代理对象执行查询一个方法
        List<User> users = userDao.findByName("%王%");
       /* List<User> users = userDao.findByName("王");*/
        for (User user : users) {
            System.out.println(user);
        }
    }
    /**
     * 测试查询总记录条数
     * */
    @Test
    public void testFindTotal(){

        //5.使用代理对象执行查询总记录条数方法
         int count=userDao.findTotal();
        System.out.println(count);
    }
    /**
     * 测试使用QueryVo作为查询条件
     * */
    @Test
    public void testFindByVo() {
        QueryVo vo = new QueryVo();
        User user = new User();
        user.setUserName("%王%");
        vo.setUser(user);
        //5.使用代理对象执行查询一个方法
        List<User> users = userDao.findUserByVo(vo);
         for (User u : users) {
            System.out.println(u);
        }
    }
}
