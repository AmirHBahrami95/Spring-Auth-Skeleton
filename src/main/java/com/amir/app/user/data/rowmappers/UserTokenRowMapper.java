package com.amir.app.user.data.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import com.amir.app.user.data.DomainUser;
import com.amir.app.user.data.UserToken;

public class UserTokenRowMapper implements RowMapper<UserToken>{

	@Override
	public UserToken map(ResultSet rs, StatementContext ctx) throws SQLException {
		UserToken us=new UserToken();
		DomainUser du=new DomainUserRowMapper().map(rs,ctx);
		// du.setAuths(Arrays.asList("USER")); // if you unset this no errors, only bugs! :|
		us.setDomainUser(du);
		if(rs.getString("token")!=null)us.setToken(rs.getString("token"));
		return us;
	}
}