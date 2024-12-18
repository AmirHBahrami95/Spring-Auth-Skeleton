package com.amir.app.user;

import java.util.Optional;

import com.amir.app.user.data.UserStats;

public interface UserStatsRepo {
	public Optional<UserStats> get(String uname);
	public boolean put(UserStats us); // update + insert
}
