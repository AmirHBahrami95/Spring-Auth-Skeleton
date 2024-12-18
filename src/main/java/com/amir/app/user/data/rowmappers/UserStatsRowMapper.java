package com.amir.app.user.data.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import com.amir.app.user.data.UserStats;
import com.amir.app.utils.DateTimeUtils;

public class UserStatsRowMapper implements RowMapper<UserStats>{

	@Override
	public UserStats map(ResultSet rs, StatementContext ctx) throws SQLException {
		UserStats us=new UserStats();
		if(rs.getString("uname")!=null)us.setUname(rs.getString("uname"));
		if(rs.getString("joined_at")!=null)us.setJoinedAt(DateTimeUtils.parseDateStr(rs.getString("joined_at")));
		if(rs.getString("last_login")!=null)us.setLastLogin(DateTimeUtils.parseDateStr(rs.getString("last_login")));
		if(rs.getString("last_online")!=null)us.setLastOnline(DateTimeUtils.parseDateStr(rs.getString("last_online")));
		if(rs.getBoolean("validated"))us.setValidated(rs.getBoolean("validated"));
		return us;
	}
}