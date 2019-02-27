package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.ranger.dao.IRangerDao;
import kr.or.ddit.ranger.service.IRangerService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-context-annotation.xml")
public class AnnotationScanTest {
	
	private Logger logger = LoggerFactory.getLogger(AnnotationScanTest.class);

	@Resource(name="rangerDaoImpl")
	private IRangerDao rangerDao;
	
	@Resource(name="rangerServiceImpl")
	private IRangerService rangerService;
	
	
	
	@Test
	public void testAnnotationTest() {
		/***Given***/
		
		/***When***/
		logger.debug("rangerDao {}" , rangerDao.getRangers());

		/***Then***/
		assertNotNull(rangerDao);	
		assertNotNull(rangerService);	
		
		}
	
	
	@Test
	public void testRangerDaoBean() {
		/***Given***/
		
		/***When***/

		/***Then***/
		assertNotNull(rangerDao);
		
	}
	
	@Test
	public void testRangerServiceBean() {
		/***Given***/
		
		/***When***/

		/***Then***/
		assertNotNull(rangerService);
	}

}
