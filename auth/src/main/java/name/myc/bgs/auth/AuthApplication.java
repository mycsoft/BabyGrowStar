package name.myc.bgs.auth;

import name.myc.bgs.auth.service.AccountService;
import name.myc.bgs.common.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@EnableEurekaClient
public class AuthApplication {

    @Autowired
    AccountService service;

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }

    @Value("${config}")
    String text;

    @RequestMapping("/hello")
    public String hello() {
        return text;
    }

    @RequestMapping("/account")
    public Account account(@RequestParam String weixin) {
        return service.findByWeixin(weixin);
//        return text;
    }

}
