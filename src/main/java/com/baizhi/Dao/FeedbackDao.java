package com.baizhi.Dao;

import com.baizhi.Entity.Feedback;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FeedbackDao {
    List<Feedback> queryForList(@Param("begin") Integer begin,
                                @Param("end") Integer end);

    Integer count();

    List<Feedback> QueryAll();

}
