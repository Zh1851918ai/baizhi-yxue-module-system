package HouDuan;

import com.baizhi.Dao.UserDao;
import com.baizhi.Entity.User;
import com.baizhi.YxueApp;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

//指定启动类的名字
@SpringBootTest(classes = YxueApp.class)
//整合的一个类
@RunWith(SpringRunner.class)
public class UserTest {

    @Autowired
    private UserDao userDao;

    /*@Test
    public void User1(){
        List<User> users = userDao.queryForList(0, 3);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void UserDao1(){
        List<User> users = userDao.queryForList(0, 3);
        for (User user : users) {
            System.out.println(user);
        }
    }*/

    /*private String id;//用户编号
    private String username;//账号名称
    private String mobile;//手机号
    private String sign;//签名
    private String head_show;//头像
    private String status;//账户状态
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date reg_time;//注册时间
    private Double score;//学分
    private String wechat;//绑定微信号*/

   /* @Test
    public void UserDao2(){
        userDao.add(new User("6","adsa","12312311","sadada","b750201f95cad1c825135c7c683e6709c83d51db.jpg","正常",
                new Date(),12.0,"qweqweqw"));
    }

    @Test
    public void UserDao3(){
        userDao.delete("4");
    }

    @Test
    public void UserDao4(){
        userDao.update(new User("4","sdsaa","1311","ada","b750201f95cad1c825135c7c683e6709c83d51db.jpg","冻结",new Date(),13.0,"345465676"));
    }*/

}
