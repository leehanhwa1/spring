package kr.or.ddit.lprod;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.lprod.model.LprodVo;
import kr.or.ddit.lprod.service.ILprodService;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.util.model.PageVo;

@RequestMapping("/lprod")
@Controller
public class LprodController {
	
	@Resource(name="lprodService")
	private ILprodService lprodService;
	
	
	@RequestMapping("/lprodList")
	public String lprodAllList(Model model) {
		
		List<LprodVo> lprodList = lprodService.getAllLprod();
		
		model.addAttribute("lprodList" , lprodList);
		
		return "lprod/lprodList";
		
	}
	
	
	@RequestMapping("/lprodPagingList")
	public String lprodPagingList(PageVo pageVo , Model model) {
		

				// userService 객체를 이용 userPagingList 조회
				Map<String, Object> resultMap = lprodService
						.selectLprodPagingList(pageVo);
				
				model.addAllAttributes(resultMap);
				model.addAttribute("pageSize", pageVo.getPageSize());
				model.addAttribute("page", pageVo.getPage());
				
		
		return "lprod/lprodPagingList";
		
	}
	
	
	
}