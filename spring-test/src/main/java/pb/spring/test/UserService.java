package pb.spring.test;

import pb.springframwork.annotation.Autowired;
import pb.springframwork.annotation.Component;
import pb.springframwork.annotation.Scope;
import pb.springframwork.context.BeanNameAware;
import pb.springframwork.context.InitializingBean;

@Component("userService")
@Scope("singleton")
public class UserService implements UserInterface, BeanNameAware, InitializingBean {
    @Autowired
    private ReportService reportService;

    private String beanName;

    private String companyName;

    @Override
    public void test(){
        System.out.println(reportService);
    }

    @Override
    public void setBeanName(String beanName) {
        this.beanName=beanName;
    }

    @Override
    public void afterPropertiesSet() {
        companyName="citi";
        System.out.println(companyName);
    }
}
