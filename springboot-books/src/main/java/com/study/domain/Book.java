package com.study.domain;
//lombok

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
//IDEA Ctrl+F12 生成方法
//Alt +7 查看代码结构
@Data
@TableName("tbl_book")
public class Book {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String type;
    private String name;
    private String description;

}
