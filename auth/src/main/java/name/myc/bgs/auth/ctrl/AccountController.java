/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package name.myc.bgs.auth.ctrl;

import name.myc.bgs.auth.service.AccountService;
import name.myc.bgs.common.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 账号相关服务接口。
 *
 * @author mayic
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService service;

    @RequestMapping("")
    public Account find(@RequestParam String weixin) {
        return service.findByWeixin(weixin);
//        return text;
    }
}
