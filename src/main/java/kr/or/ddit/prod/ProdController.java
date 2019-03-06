package kr.or.ddit.prod;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.prod.model.ProdVo;
import kr.or.ddit.prod.service.IProdService;


@Controller
public class ProdController {

	@Resource(name="prodService")
	private IProdService prodService;
	
	
	@RequestMapping(path="/prodList")
	public String prodList(Model model , @RequestParam("lgu")String lgu) {
		
		List<ProdVo> prodList = prodService.getProdByLgu(lgu);
		
		model.addAttribute("prodList", prodList);
		
		return "prod/prodList";
	}
	
	
	
	
	
	
	
}
