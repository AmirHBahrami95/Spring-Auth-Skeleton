package com.amir.app.user.data.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import com.amir.app.user.data.DomainUser;

public class DomainUserRowMapper implements RowMapper<DomainUser>{

	@Override
	public DomainUser map(ResultSet rs, StatementContext ctx) throws SQLException {
		
		DomainUser ue=new DomainUser();
		
		// UserDto part 
    if(rs.getString("uname")!=null)ue.setUname(rs.getString("uname")); // XXX this is problematic if uname IS null !! 
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
