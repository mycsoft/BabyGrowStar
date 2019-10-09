/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package name.myc.bgs.auth.ctrl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 公开接口Api
 *
 * @author mayic
 */
@RestController
@RequestMapping("/public")
public class PublicApiControle {

    @Value("${config}")
    String text;

    @RequestMapping("/hello")
    public String hello() {
        return text;
    }

}
