/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package name.myc.bgs.common.model;

import java.util.Date;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author mayic
 */
@Data
@Document(collection = "account")
public class Account {

    @Id
    private String id;

    private String weixin;
    private String name;
    private Date createTime;
    private Date modifyTime;
}
