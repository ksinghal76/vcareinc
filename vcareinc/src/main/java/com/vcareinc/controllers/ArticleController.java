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
import com.vcareinc.services.ArticleService;
import com.vcareinc.services.UserService;
import com.vcareinc.vo.Articles;
import com.vcareinc.vo.Category;
import com.vcareinc.vo.User;

@Controller
public class ArticleController extends BaseMultiActionController {

	@Autowired
	private UserService userService;

	@Autowired
	private ArticleService articleService;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public ArticleService getArticleService() {
		return articleService;
	}

	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}

	@RequestMapping("/articleManage")
	public String articleManage(ModelMap modelMap) {
		User user = userService.getUser();
		List<Articles> articlesList = articleService.getArticlesByUser(user);
		modelMap.addAttribute("articlesList", articlesList);
		return "articleManage";
	}

	@RequestMapping("/article")
	public String article(ModelMap modelMap) {
		modelMap.addAttribute("articlesLists", articleService.getTopArticlesLists(5));
		modelMap.addAttribute("articlesCategory", articleService.getAllCategories());
		return "article";
	}

	@RequestMapping("/allArticlesCategories")
	public String allArticlesCategories(ModelMap modelMap) {
		modelMap.addAttribute("articlesCategory", articleService.getAllCategories());
		return "allArticlesCategories";
	}

	@RequestMapping("/articlesDescription")
	public String articlesDescription(ModelMap modelMap, @RequestParam("id") Long id) {
		Articles articles = articleService.getArticlesById(id);
		modelMap.addAttribute("articles", articles);
		modelMap.addAttribute("categories", articleService.getCategoriesById(id));
		return "articlesDescription";
	}

	@RequestMapping("articlesList")
	public String articlesList(Pageable pageable, ModelMap map,
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

		Page<Articles> articlesList = null;


		if(SortingOrder.LEVEL.equals(sortingOrder))
			articlesList = articleService.getArticlesByCategoryOrderByPriceType(categoryId, pageNumber, numberPerPage);
		else if(SortingOrder.ALPHABETICALLY.equals(sortingOrder))
			articlesList = articleService.getArticlesByCategoryOrderByTitle(categoryId, pageNumber, numberPerPage);

		int current = articlesList.getNumber() + 1;
		int begin = Math.max(1, current - 5);
		int end = Math.min(begin + 10, articlesList.getTotalPages());

		if(articlesList.getContent() != null && articlesList.getContent().size() > 0) {
			Category category = articlesList.getContent().get(0).getCategory().iterator().next();
			map.addAttribute("articlesVal", articlesList.getContent());
			map.addAttribute("pageable", articlesList);
			map.addAttribute("categoryName", category.getName());
		}

		map.addAttribute("beginIndex", begin);
		map.addAttribute("endIndex", end);
		map.addAttribute("currentIndex", current);
		map.addAttribute("numberPerPage", numberPerPage);
		map.addAttribute("optionType", optionType);
		map.addAttribute("orderBy", orderBy);
		map.addAttribute("categoryId", categoryId);

		return "articlesList";
	}
}
