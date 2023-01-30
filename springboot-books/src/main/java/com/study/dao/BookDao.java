package com.study.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.study.domain.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author
 * @create
 */
@Mapper
public interface BookDao extends BaseMapper<Book> {
//    @Select("select * from tbl_book where id = #{id}")
//    Book getById(Integer id);
}
