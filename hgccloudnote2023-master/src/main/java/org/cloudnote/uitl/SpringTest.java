package org.cloudnote.uitl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {
    //使用ApplicationContext对象进行测试
    public ApplicationContext getContext(){
        String conf = "spring-mvc.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(conf);
        return ac;
    }

}
