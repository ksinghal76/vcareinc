package com.vcareinc.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.vcareinc.utils.DateUtils;
import com.vcareinc.utils.SecureUtils;
import com.vcareinc.vo.Address;
import com.vcareinc.vo.Role;
import com.vcareinc.vo.User;

public class UserRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();

		return user;
	}

}
