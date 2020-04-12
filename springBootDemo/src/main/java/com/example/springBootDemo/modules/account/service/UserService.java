package com.example.springBootDemo.modules.account.service;

import com.example.springBootDemo.modules.account.entity.User;
import com.example.springBootDemo.modules.common.vo.Result;

public interface UserService {

	Result insertUser(User user);

	User getUserByUserName(String userName);

	Result getUser(User user);
}
