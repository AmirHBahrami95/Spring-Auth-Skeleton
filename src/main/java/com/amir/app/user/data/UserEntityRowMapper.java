package com.amir.app.user.data;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

public class UserEntityRowMapper implements RowMapper<UserEntity>{

	@Override
	public UserEntity map(ResultSet rs, StatementContext ctx) throws SQLException {
		
		UserEntity ue=new UserEntity();
		
	// UserDto part 
    if(rs.getString("uname")!=null)ue.setUname(rs.getString("uname"));
    if(rs.getString("email")!=null)ue.setEmail(rs.getString("email"));
    if(rs.getString("fname")!=null)ue.setFname(rs.getString("fname"));
    if(rs.getString("lname")!=null)ue.setLname(rs.getString("lname"));
    if(rs.getString("phone_no")!=null)ue.setPhoneNo(rs.getString("phone_no"));
    if(rs.getString("is_enabled")!=null)ue.setEnabled(rs.getBoolean("is_enabled"));
        
    // UserEntity part
    if(rs.getString("pass")!=null)ue.setPass(rs.getString("pass"));
    if(rs.getString("nat_code")!=null)ue.setNatCode(rs.getString("nat_code"));
		
		return ue;
	}

}
