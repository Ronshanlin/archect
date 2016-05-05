/**
 * 
 */
package com.shanlin.demo.ibatis;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author 13073457
 *
 */
public interface UserMapper {
    
    @Select("select * from user where id=#{userId}")
    public String getUserName(@Param("userId") String userId);
}
