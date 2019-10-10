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
 * 宝贝. 也是宝贝账本。每个账号下可以有多个宝贝账本。 这是为了满足一个家庭可以有多个宝贝的需求。
 *
 * @author mayic
 */
@Data
@Document("baby")
public class Baby {

    /**
     * 主键
     */
    @Id
    private String id;
    /**
     * 主账号
     */
    private Account account;
    /**
     * 宝贝名称
     */
    private String name;
    /**
     * 星星的最后数量
     */
    private int starCount;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date modifyTime;
}
