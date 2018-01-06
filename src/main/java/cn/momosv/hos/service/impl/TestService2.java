package cn.momosv.hos.service.impl;

import cn.momosv.hos.bean.base.BasicExample;
import cn.momosv.hos.bean.TbTest;
import cn.momosv.hos.service.ITestService2;

import org.springframework.stereotype.Service;

@Service("testService2")
public class TestService2 extends BasicServiceImpl<TbTest, BasicExample> implements ITestService2{

}
