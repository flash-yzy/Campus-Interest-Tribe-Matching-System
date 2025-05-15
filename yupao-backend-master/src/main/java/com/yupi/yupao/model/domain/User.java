package com.yupi.yupao.model.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户实体
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
@TableName(value = "user")
@Data
public class User implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    // @TableField(updateStrategy = FieldStrategy.NOT_EMPTY)
    private long id;

    /**
     * 用户昵称
     */
    // @TableField(updateStrategy = FieldStrategy.NOT_EMPTY)
    private String username;

    /**
     * 账号
     */
    // @TableField(updateStrategy = FieldStrategy.NOT_EMPTY)
    private String userAccount;

    /**
     * 用户头像
     */
    // @TableField(updateStrategy = FieldStrategy.NOT_EMPTY)
    private String avatarUrl;

    /**
     * 性别
     */
    // @TableField(updateStrategy = FieldStrategy.NOT_EMPTY)
    private Integer gender;

    /**
     * 密码
     */
    // @TableField(updateStrategy = FieldStrategy.NOT_EMPTY)
    private String userPassword;

    /**
     * 电话
     */
    // @TableField(updateStrategy = FieldStrategy.NOT_EMPTY)
    private String phone;

    /**
     * 邮箱
     */
    // @TableField(updateStrategy = FieldStrategy.NOT_EMPTY)
    private String email;

    /**
     * 标签列表 json
     */
    // @TableField(updateStrategy = FieldStrategy.NOT_EMPTY)
    private String tags;

    /**
     * 状态 0 - 正常
     */
    // @TableField(updateStrategy = FieldStrategy.NOT_EMPTY)
    private Integer userStatus;

    /**
     * 创建时间
     */
    // @TableField(updateStrategy = FieldStrategy.NOT_EMPTY)
    private Date createTime;

    /**
     *
     */
    // @TableField(updateStrategy = FieldStrategy.NOT_EMPTY)
    private Date updateTime;

    /**
     * 是否删除
     */
    @TableLogic
    // @TableField(updateStrategy = FieldStrategy.NOT_EMPTY)
    private Integer isDelete;

    /**
     * 用户角色 0 - 普通用户 1 - 管理员
     */
    // @TableField(updateStrategy = FieldStrategy.NOT_EMPTY)
    private Integer userRole;

    /**
     * 星球编号
     */
    // @TableField(updateStrategy = FieldStrategy.NOT_EMPTY)
    private String planetCode;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}

