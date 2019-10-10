/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package name.myc.bgs.star;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import name.myc.bgs.common.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 星星服务业务类
 *
 * @author mayic
 */
@Service
public class StarService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${bgs.service.auth.serviceId}")
    String authServiceId;

    /**
     * 根据微信号取出账号信息.
     *
     * @param weixin
     * @return
     */
    @HystrixCommand(fallbackMethod = "canntFindAccountByWeixin")
    public Account findAccountByWeixin(String weixin) {
        //从权限服务中查询微信号对应的账号.
        Account account = restTemplate.getForObject(
                "http://{1}/account?weixin={2}", Account.class, authServiceId, weixin);
        return account;
    }

    public Account canntFindAccountByWeixin(String weixin) {
//        return "Can't find account, may be auth service is down!"
        System.out.println("=======================> 启动熔断.");
        return null;
    }

    /**
     * 取出指定账号下默认账本的星星数量.
     *
     * @param account
     * @return
     */
    public int findDefaultBabyStarCount(Account account) {
        //FIXME 这是一段测试代码.
        return account == null ? -1 : 100;
    }

}
