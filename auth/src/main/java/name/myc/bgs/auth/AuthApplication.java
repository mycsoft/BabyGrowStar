package name.myc.bgs.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

//@RestController
@SpringBootApplication
@EnableEurekaClient
public class AuthApplication {
//
//    @Autowired
//    AccountService service;

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }

//    @Value("${config}")
//    String text;
//
//    @RequestMapping(path = {"/hello","/public/hello"})
//    public String hello() {
//        return text;
//    }
//
//    @RequestMapping("/account")
//    public Account account(@RequestParam String weixin) {
//        return service.findByWeixin(weixin);
////        return text;
//    }
}
