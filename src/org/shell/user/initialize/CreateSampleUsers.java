package org.shell.user.initialize;

import org.apache.log4j.Logger;
import org.shell.persistence.home.Home;
import org.shell.user.BaseUser;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CreateSampleUsers 
{
	private static Logger log = Logger.getLogger(CreateSampleUsers.class);
	
	private static BaseUser createAdmin()
	{
		BaseUser bu = new BaseUser();
		bu.setEmail("admin@test.com");
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		bu.setPassword(encoder.encode("88888888"));
		bu.setRole(BaseUser.ROLE_ADMIN);
		bu.setEnabled(true);
		return bu;
	}
	
	private static BaseUser createPunter()
	{
		BaseUser bu = new BaseUser();
		bu.setEmail("punter@test.com");
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		bu.setPassword(encoder.encode("88888888"));
		bu.setRole(BaseUser.ROLE_PUNTER);
		bu.setEnabled(true);
		return bu;
	}
	
	public static void main(String[] args)
	{
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"shell-service.xml");

		Home home = (Home) context.getBean("home");
		BaseUser admin = createAdmin();
		BaseUser punter = createPunter();
		try
		{
			home.getBaseUserDao().store(admin);
			home.getBaseUserDao().store(punter);
		}
		catch (Exception e)
		{
			log.error(e.getMessage());
			System.exit(1);
		}
		admin = home.getBaseUserDao().getByEmail("admin@test.com");
		log.info("Admin : " + admin);
		
		punter = home.getBaseUserDao().getByEmail("punter@test.com");
		log.info("Punter : " + punter);
		
		log.info("Done");
	}
}
