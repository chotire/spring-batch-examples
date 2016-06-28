package fcb.batch.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import fcb.batch.model.User;

public class UserRowMapper implements RowMapper<User> {
	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setSeq(rs.getInt("seq"));
		user.setId(rs.getString("id"));
		user.setEmail(rs.getString("email"));
		user.setHireDate(rs.getDate("hire_date"));
		return user;
	}
}