package HouDuan;

/*import com.baizhi.YxueApp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@SpringBootTest(classes = YxueApp.class)
@RunWith(SpringRunner.class)
public class TestRedisTemplate
{
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test0() {
        ValueOperations opsForValue = redisTemplate.opsForValue();

        *//*
         * 存用户登录手机验证码的key如何设计?
         *  登录用户名-phone-code
         *//*

        opsForValue.set("admin-key","123456",5, TimeUnit.MINUTES);
    }
}*/
