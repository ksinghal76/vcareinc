package com.vcareinc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vcareinc.constants.NumberOfRecordPerPage;
import com.vcareinc.constants.SortingOrder;
import com.vcareinc.services.ClassifiedService;
import com.vcareinc.services.UserService;
import com.vcareinc.vo.Category;
import com.vcareinc.vo.Classified;
import com.vcareinc.vo.User;

@Controller
public class ClassifiedController extends BaseMultiActionController {

	@Autowired
	private UserService userService;

	@Autowired
	private ClassifiedService classifiedService;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public ClassifiedService getClassifiedService() {
		return classifiedService;
	}

	public void setClassifiedService(ClassifiedService classifiedService) {
		this.classifiedService = classifiedService;
	}

	@RequestMapping("/classifiedManage")
	public String classifiedManage(ModelMap modelMap) {
		User user = userService.getUser();
		List<Classified> classifiedList = classifiedService.getClassifiedByUser(user);
		modelMap.addAttribute("classifiedList", classifiedList);
		return "classifiedManage";
	}

	@RequestMapping("/classified")
	public String classified(ModelMap modelMap) {
		modelMap.addAttribute("classifiedLists", classifiedService.getTopClassifiedLists(6));
		return "classified";
	}

	@RequestMapping("/classifiedDescription")
	public String classifiedDescription(ModelMap modelMap, @RequestParam("id") Long id) {
		Classified classified = classifiedService.getClassifiedById(id);
		modelMap.addAttribute("classified", classified);
		modelMap.addAttribute("categories", classifiedService.getCategoriesById(id));
		return "classifiedDescription";
	}

	@RequestMapping("classifiedList")
	public String classifiedList(Pageable pageable, ModelMap map,
			@RequestParam(value="categoryId", required=false) Long categoryId,
			@RequestParam(value="optionType", required=false) String optionType,
			@RequestParam(value="orderBy", required=false) String orderBy,
			@RequestParam(value="pageNumber", required=false) Integer pageNumber,
			@RequestParam(value="numberPerPage", required=false) Integer numberPerPage) {

		SortingOrder sortingOrder = null;

		if(pageNumber == null || pageNumber == 0)
			pageNumber = 1;
		if(numberPerPage == null || numberPerPage == 0)
			numberPerPage = NumberOfRecordPerPage.TEN.getNumber();

		if(orderBy == null || orderBy.trim().length() <= 0) {
			orderBy = SortingOrder.LEVEL.toString();
		}

		if(orderBy != null && orderBy.trim().length() > 0) {
			sortingOrder = SortingOrder.valueOf(orderBy);
		}

		Page<Classified> classifiedList = null;


		if(SortingOrder.LEVEL.equals(sortingOrder))
			classifiedList = classifiedService.getClassifiedByCategoryOrderByPriceType(categoryId, pageNumber, numberPerPage);
		else if(SortingOrder.ALPHABETICALLY.equals(sortingOrder))
			classifiedList = classifiedService.getClassifiedByCategoryOrderByTitle(categoryId, pageNumber, numberPerPage);

		int current = classifiedList.getNumber() + 1;
		int begin = Math.max(1, current - 5);
		int end = Math.min(begin + 10, classifiedList.getTotalPages());

		if(classifiedList.getContent() != null && classifiedList.getContent().size() > 0) {
			Category category = classifiedList.getContent().get(0).getCategory().iterator().next();
			map.addAttribute("classifiedVal", classifiedList.getContent());
			map.addAttribute("pageable", classifiedList);
			map.addAttribute("categoryName", category.getName());
		}

		map.addAttribute("beginIndex", begin);
		map.addAttribute("endIndex", end);
		map.addAttribute("currentIndex", current);
		map.addAttribute("numberPerPage", numberPerPage);
		map.addAttribute("optionType", optionType);
		map.addAttribute("orderBy", orderBy);
		map.addAttribute("categoryId", categoryId);

		return "classifiedList";
	}

}
