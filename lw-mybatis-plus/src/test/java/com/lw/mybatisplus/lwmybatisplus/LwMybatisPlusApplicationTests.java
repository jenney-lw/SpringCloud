package com.lw.mybatisplus.lwmybatisplus;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LwMybatisPlusApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void testGenTable() {

    }

    @Test
    public void deployment() {
        //1.定义ProcessEngine对应
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        //2.得到RepositoryService实例
        RepositoryService repositoryService = processEngine.getRepositoryService();

        //3.进行部署
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("diagram/holiday.bpmn")
                .name("请假申请流程")
                .deploy();

        //4.输出部署信息
        System.out.println("流程id:" + deployment.getId());
        System.out.println("流程名称:" + deployment.getName());
    }

}
