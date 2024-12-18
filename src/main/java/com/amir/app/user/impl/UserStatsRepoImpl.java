package com.amir.app.user.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.jdbi.v3.core.Jdbi;
import org.springframework.beans.factory.annotation.Autowired;

import com.amir.app.user.UserStatsRepo;
import com.amir.app.user.data.UserStats;
import com.amir.app.user.data.rowmappers.UserStatsRowMapper;
import com.amir.app.utils.DateTimeUtils;
import com.amir.app.utils.DbUtils;

public class UserStatsRepoImpl implements UserStatsRepo{
	
	private final static String USER_STATS_TABLE="usr_stats";
	
	@Autowired
	private Jdbi db;

	@Override
	public Optional<UserStats> get(String uname) {
		List<UserStats> uss=db.withHandle(h->h.createQuery("SELECT * FROM "+USER_STATS_TABLE+" WHERE usr_uname=:uname"))
			.bind("uname",uname)
			.map(new UserStatsRowMapper())
			.list();
		return uss.size()>0?Optional.of(uss.get(0)):Optional.empty();
	}

	@Override
	public boolean put(UserStats us) {
		return insert(us)?true:update(us);
	}
	
	private boolean insert(UserStats us) {
		Map<String,String> args=buildArgs(us);
		String q=buildInsertQuery(us,args);
		return db.withHandle(h->h.createUpdate(q)).bindMap(args).execute()>0;
	}
	
	private boolean update(UserStats us) {
		Map<String,String> args=buildArgs(us);
		String q=buildUpdateQuery(us, args);
		return db.withHandle(h->h.createUpdate(q)).bindMap(args).execute()>0;
	}
	
	private String buildInsertQuery(UserStats ue,Map<String,String> args) {
		
		StringBuffer q=new StringBuffer();
		q.append("INSERT INTO "+USER_STATS_TABLE+"(usr_uname");
		if(args.size()>0)q.append(",");
		q.append(DbUtils.expandInsertQuery(args));
		
		StringBuffer v=new StringBuffer("VALUES(:usr_uname");
		if(args.size()>0)v.append(","); // don't ask
		v.append(DbUtils.expandInsertValues(args));
		args.put("usr_uname", ue.getUname());
		
		q.append(") ");
		v.append(")");
		q.append(v);
		
		return q.toString();
	}

	private String buildUpdateQuery(UserStats ue,Map<String,String> args) {
		StringBuffer q=new StringBuffer("UPDATE "+USER_STATS_TABLE+" SET ");
		q.append(DbUtils.expandUpdateQuery(args));
		q.append(" WHERE usr_uname=:usr_uname");
		args.put("usr_uname", ue.getUname()); // SHOULD "NOT" happen before this point 
		return q.toString();
	}
	
	private Map<String,String> buildArgs(UserStats ue){
		Map<String,String> args=new HashMap<>();
		if(ue.getJoinedAt()!=null)args.put("joined_at",DateTimeUtils.toDateStr(ue.getJoinedAt()));
		if(ue.getLastLogin()!=null)args.put("last_login",DateTimeUtils.toDateStr(ue.getLastLogin()));
		if(ue.getLastOnline()!=null)args.put("last_online",DateTimeUtils.toDateStr(ue.getLastOnline()));
		if(ue.isValidated())args.put("validated","1"); // mariadb/mysql syntax for 'true' :|
		return args;
	}
	
}
