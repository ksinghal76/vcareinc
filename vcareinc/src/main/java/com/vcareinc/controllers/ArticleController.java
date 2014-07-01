package com.vcareinc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vcareinc.services.ArticleService;
import com.vcareinc.services.UserService;
import com.vcareinc.vo.Articles;
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
}
