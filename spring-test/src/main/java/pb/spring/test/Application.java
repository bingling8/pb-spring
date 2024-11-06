package pb.spring.test;

import pb.springframwork.context.PbApplicationContext;

public class Application {
    public static void main(String[] args) {

        PbApplicationContext myApplicationContext = new PbApplicationContext(AppConfig.class);

        UserInterface userService =(UserInterface) myApplicationContext.getBean("userService");
//        System.out.println(myApplicationContext.getBean("userService"));
//        System.out.println(myApplicationContext.getBean("userService"));
//        System.out.println(myApplicationContext.getBean("userService"));
//        System.out.println(myApplicationContext.getBean("reportService"));

        userService.test();

    }
}
