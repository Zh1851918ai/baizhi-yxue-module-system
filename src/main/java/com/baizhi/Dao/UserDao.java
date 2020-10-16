package com.baizhi.Dao;

import com.baizhi.Entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface UserDao extends BaseMapper<User> {

    List<User> queryForList(@Param("begin") Integer begin,
                            @Param("end") Integer end);

    Integer count();

    void add(User user);

    void delete(String id);

    void update(User user);

    void updateImg(User user);


    List<User>QueryAll();
}
