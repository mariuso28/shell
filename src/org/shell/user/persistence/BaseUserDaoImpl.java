package org.shell.user.persistence;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.shell.persistence.PersistenceRuntimeException;
import org.shell.user.BaseUser;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class BaseUserDaoImpl extends NamedParameterJdbcDaoSupport implements BaseUserDao {
	private static Logger log = Logger.getLogger(BaseUserDaoImpl.class);

	@Override
	public void store(final BaseUser bu) throws PersistenceRuntimeException {
		bu.setId(UUID.randomUUID());
		try
		{
			getJdbcTemplate().update("INSERT INTO baseuser (id,email,password,role,enabled) "
										+ "VALUES (?,?,?,?,?)"
			        , new PreparedStatementSetter() {
						public void setValues(PreparedStatement ps) throws SQLException {
			    	  	ps.setObject(1, bu.getId());
						ps.setString(2, bu.getEmail().toLowerCase());
						ps.setString(3, bu.getPassword());
						ps.setString(4, bu.getRole());
						ps.setBoolean(5, bu.isEnabled());
			      }
			    });
			
		}
		catch (DataAccessException e1)
		{
			log.error("Could not execute : " + e1.getMessage(),e1);
			throw new PersistenceRuntimeException("Could not execute store : " + e1.getMessage());
		}	
	}

	@Override
	public BaseUser getByEmail(final String email) throws PersistenceRuntimeException {
		try
		{
			final String sql = "SELECT * FROM baseUser WHERE email=?";
			List<BaseUser> bus = getJdbcTemplate().query(sql,new PreparedStatementSetter() {
				        public void setValues(PreparedStatement preparedStatement) throws SQLException {
				          preparedStatement.setString(1, email);
				        }
				      }, BeanPropertyRowMapper.newInstance(BaseUser.class));
			if (bus.isEmpty())
				return null;
			return bus.get(0);
		}
		catch (DataAccessException e)
		{
			log.error("Could not execute : " + e.getMessage(),e);
			throw new PersistenceRuntimeException("Could not execute getByEmail : " + e.getMessage());
		}
	}

	@Override
	public BaseUser getById(final UUID id) throws PersistenceRuntimeException {
		try
		{
			final String sql = "SELECT * FROM baseUser WHERE id=?";
			List<BaseUser> bus = getJdbcTemplate().query(sql,new PreparedStatementSetter() {
				        public void setValues(PreparedStatement preparedStatement) throws SQLException {
				          preparedStatement.setObject(1, id);
				        }
				      }, BeanPropertyRowMapper.newInstance(BaseUser.class));
			if (bus.isEmpty())
				return null;
			return bus.get(0);
		}
		catch (DataAccessException e)
		{
			log.error("Could not execute : " + e.getMessage(),e);
			throw new PersistenceRuntimeException("Could not execute getById : " + e.getMessage());
		}
	}

	
}
