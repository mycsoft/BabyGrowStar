/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package name.myc.bgs.star;

import name.myc.bgs.common.model.Account;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author mayic
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StarServiceTest {

    @Autowired
    StarService instance;

    /**
     * 测试一下与权限服务的联调.
     */
    @Test
    public void testFindAccountByWeixin() {
        System.out.println("findAccountByWeixin");
        String weixin = "1";
//        Account expResult = null;
        Account result = instance.findAccountByWeixin(weixin);
        assertNotNull("没有查询到账号信息,请确认权限服务已经启动且微信号正确.",result);
    }

    /**
     * Test of findDefaultBabyStarCount method, of class StarService.
     */
    @Test
    public void testFindDefaultBabyStarCount() {
        System.out.println("findDefaultBabyStarCount");
        Account account = null;
        StarService instance = new StarService();
        int expResult = 0;
        int result = instance.findDefaultBabyStarCount(account);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
