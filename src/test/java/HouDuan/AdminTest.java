package HouDuan;

import com.baizhi.Dao.AdminDao;
import com.baizhi.Entity.Admin;
import com.baizhi.YxueApp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
//指定启动类的名字
@SpringBootTest(classes = YxueApp.class)
//整合的一个类
@RunWith(SpringRunner.class)
public class AdminTest {

    @Autowired
    private AdminDao adminDao;

    @Test
    public void selectBy(){
        Admin admin = adminDao.selectBy("bay","12312312");
        System.out.println(admin);
    }

    @Test
    public void add(){
        adminDao.add(new Admin("5","bay","12312312"));
    }
}
