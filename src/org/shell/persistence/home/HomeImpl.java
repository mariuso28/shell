package org.shell.persistence.home;

import org.shell.user.persistence.BaseUserDao;

public class HomeImpl implements Home {

	private BaseUserDao baseUserDao;
	
	public HomeImpl()
	{
	}
	
	@Override
	public BaseUserDao getBaseUserDao() {
		return baseUserDao;
	}

	public void setBaseUserDao(BaseUserDao baseUserDao) {
		this.baseUserDao = baseUserDao;
	}

}
