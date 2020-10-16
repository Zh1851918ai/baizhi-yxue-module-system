package com.baizhi.Log.service.impl;


import com.baizhi.Log.dao.LogMapper;
import com.baizhi.Log.entity.Log;
import com.baizhi.Log.service.LogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaozhi
 * @since 2020-09-28
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {

}
