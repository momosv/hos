package cn.momosv.hos.service.impl;

import cn.momosv.hos.bean.TbTest;
import cn.momosv.hos.bean.base.BasicExample;
import cn.momosv.hos.service.ITestService;
import org.springframework.stereotype.Service;

@Service("testService")
public class TestService extends BasicServiceImpl<TbTest, BasicExample> implements ITestService{

}
