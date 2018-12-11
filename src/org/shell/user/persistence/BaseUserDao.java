package org.shell.user.persistence;

import java.util.UUID;

import org.shell.persistence.PersistenceRuntimeException;
import org.shell.user.BaseUser;

public interface BaseUserDao {

	public void store(BaseUser bu) throws PersistenceRuntimeException;
	public BaseUser getByEmail(String email) throws PersistenceRuntimeException;
	public BaseUser getById(UUID id) throws PersistenceRuntimeException;
}
