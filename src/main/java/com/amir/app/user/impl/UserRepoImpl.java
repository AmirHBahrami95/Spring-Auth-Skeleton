package com.amir.app.user.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.amir.app.user.User;
import com.amir.app.user.UserRepo;
import com.amir.app.user.data.DomainUser;
import com.amir.app.user.data.DomainUserDto;
import com.amir.app.user.data.DomainUserRowMapper;
import com.amir.app.utils.DbUtils;

@Transactional
public class UserRepoImpl implements UserRepo{
	
	final Logger logger = LoggerFactory.getLogger(UserRepoImpl.class);
	
	/* NOTE : I could've finalized column names too but I don't give enough shit to do that */
	private final static String USER_TABLE="usr";
	private final static String USER_AUTH_TABLE="usr_auth";
	private final static String USER_INFO_TABLE="usr_info";
	private final static String DOMAIN_USER_VIEW="domain_usr_view";
	
	@Autowired 
	private Jdbi db;
	
	//==================== GET
	
	@Override
	public Optional<DomainUser> getDomainUser(String uname) {
		return getDomainUserWithAuthority(uname);
	}

	@Override
	public List<String> getAuthorities(String uname) {
		return db.withHandle(h->h.createQuery("SELECT authority FROM "+USER_AUTH_TABLE+" WHERE usr_uname=:uname"))
			.bind("uname",uname)
			.map((rs, ctx) -> {return new String(rs.getString(1));})
			.list();
	}
	
	private Optional<DomainUser> getDomainUserWithAuthority(String uname){
		Optional<DomainUser> ueo=selectDomainUser(uname);
		if(ueo.isEmpty()) return Optional.empty();
		ueo.get().setAuths(getAuthorities(uname));
		return ueo;
	}
	
	private Optional<DomainUser> selectDomainUser(String uname){
		List<DomainUser> dus=db.withHandle(h->h.createQuery("SELECT * FROM "+DOMAIN_USER_VIEW+" WHERE uname=:uname"))
			.bind("uname",uname)
			.map(new DomainUserRowMapper())
			.list();
		return dus.size()>0?Optional.of(dus.get(0)):Optional.empty();
	}
	
	// ==================== ADD
	
	@Override
	@Transactional
	public boolean add(DomainUser u) {
		if(!insertUser(u))return false;
		if(!insertUserAuth(u))return false;
		if(!insertUserInfo(u))return false;
		return true;
	}

	@Transactional
	private boolean insertUser(DomainUser u) {
		return db.withHandle(h->h.createUpdate("INSERT INTO "+USER_TABLE+"(uname,pass) VALUES(:uname,:pass)"))
			.bind("uname", u.getUname())
			.bind("pass", u.getPass())
			.execute()>0;
	}
	
	@Transactional
	private boolean insertUserAuth(DomainUser u) {
		return db.withHandle(h->h.createUpdate("INSERT INTO usr_auth(usr_uname) VALUES(:uname)"))
			.bind("uname",u.getUname())
			.execute()>0;
	}
	
	@Transactional
	private boolean insertUserInfo(DomainUser ue) {
		Map<String,String> args=buildInsertArgs(ue);
		String q=buildInsertQuery(ue,args);
		// logger.error("insert-query:"+q);
		return db.withHandle(h->h.createUpdate(q)).bindMap(args).execute()>0;
	}
	
	private String buildInsertQuery(DomainUser ue,Map<String,String> args) {
		
		StringBuffer q=new StringBuffer();
		q.append("INSERT INTO "+USER_INFO_TABLE+"(usr_uname,");
		q.append(DbUtils.expandInsertQuery(args));
		
		StringBuffer v=new StringBuffer(" VALUES(:uname,");
		v.append(DbUtils.expandInsertValues(args));
		args.put("uname", ue.getUname());
		
		q.append(") ");
		v.append(")");
		q.append(v);
		
		return q.toString();
	}
	
	private Map<String,String> buildInsertArgs(DomainUser ue){
		Map<String,String> args=new HashMap<>();
		if(ue.getEmail()!=null && !ue.getEmail().isBlank())args.put("email",ue.getEmail());
		if(ue.getFname()!=null && !ue.getFname().isBlank())args.put("fname",ue.getFname());
		if(ue.getLname()!=null && !ue.getLname().isBlank())args.put("lname",ue.getLname());
		if(ue.getPhoneNo()!=null && !ue.getPhoneNo().isBlank())args.put("phone_no",ue.getPhoneNo());
		if(ue.getNatCode()!=null && !ue.getNatCode().isBlank())args.put("nat_code",ue.getNatCode());
		return args;
	}

	
	// ==================== UPDATE
	
	@Override
	public boolean update(DomainUserDto u) {
		Map<String,String> args=buildUpdateArgs(u);
		String q=buildUpdateQuery(u, args);
		return db.withHandle(h->h.createUpdate(q)).bindMap(args).execute()>0;
	}
	
	private String buildUpdateQuery(DomainUserDto ue,Map<String,String> args) {
		StringBuffer q=new StringBuffer("UPDATE "+USER_INFO_TABLE+" SET ");
		q.append(DbUtils.expandUpdateQuery(args));
		q.append(" WHERE uname=:uname");
		args.put("uname", ue.getUname()); // SHOULD "NOT" happen before this point 
		return q.toString();
	}
	
	private Map<String,String> buildUpdateArgs(DomainUserDto ue) {
		Map<String,String> args=new HashMap<>();
		if(ue.getEmail()!=null && !ue.getEmail().isBlank())args.put("email",ue.getEmail());
		if(ue.getFname()!=null && !ue.getFname().isBlank())args.put("fname",ue.getFname());
		if(ue.getLname()!=null && !ue.getLname().isBlank())args.put("lname",ue.getLname());
		if(ue.getPhoneNo()!=null && !ue.getPhoneNo().isBlank())args.put("phone_no",ue.getPhoneNo());
		return args;
	}
	
	//==================== DISABLE & CHANGE_PASS
	
	@Override
	public boolean disable(String uname) {
		return db.withHandle(h->h.createUpdate("UPDATE "+USER_TABLE+" SET is_enabled=FALSE WHERE uname=:uname"))
			.bind("uname",uname)
			.execute()>0;
	}

	@Override
	public boolean changePass(String uname, String newPass) {
		// TODO Auto-generated method stub
		return false;
	}

}
