/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package name.myc.bgs.star.controller;

import java.util.HashMap;
import java.util.Map;
import name.myc.bgs.common.model.Account;
import name.myc.bgs.star.StarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mayic
 */
@RestController
public class StarController {

    /**
     * Service中实现查询指定用户的星星数量。
     */
    @Autowired
    private StarService service;

    @RequestMapping("/star/count")
    public Object count(String weixin) {
        //根据微信号查询出用户。
        Account account = service.findAccountByWeixin(weixin);
        //根据用户取出默认账本。
        //根据账本取出对应的星星数量。
        int c = service.findDefaultBabyStarCount(account);
        //返回信息
        //TODO 这里需要封装一个统一的返回对象，统一所有返回的格式。
        Map<String, Object> map = new HashMap<>();
        map.put("count", c);
        return map;

    }
}
