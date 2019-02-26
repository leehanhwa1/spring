package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-context-placeholder.xml")
public class DbPropertiesTest {
	private Logger logger = LoggerFactory.getLogger(DbPropertiesTest.class);
	
	@Resource(name="dbProperties")
	private DbProperties DbProperties;

	@Test
	public void testPlaceholder() {
		/***Given***/
		
		
		/***When***/
		String className = DbProperties.getDriverClassName();
		String password  = DbProperties.getPassword();
		String url 		 = DbProperties.getUrl();
		String username  = DbProperties.getUsername();
		
		logger.debug("ClassName {}", className);
		logger.debug("password {}", password);
		logger.debug("url {}", url);
		logger.debug("username {}", username);
		

		/***Then***/
		assertEquals("oracle.jdbc.driver.OracleDriver", className);
		assertEquals("java", password);
		assertEquals("jdbc:oracle:thin:@localhost:1521:XE", url);
		assertEquals("PC09_PC", username);
		
	}

}