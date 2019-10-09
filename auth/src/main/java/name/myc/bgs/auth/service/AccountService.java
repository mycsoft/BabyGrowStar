/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package name.myc.bgs.auth.service;

import name.myc.bgs.common.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

/**
 *
 * @author mayic
 */
@Service
public class AccountService {

    @Autowired
    MongoTemplate mongo;
    
    public Account findByWeixin(String weixin) {
        return mongo.findOne(Query.query(Criteria.where("weixin").is(weixin)), Account.class);
    }
    
}
