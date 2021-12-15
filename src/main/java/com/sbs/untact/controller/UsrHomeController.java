package com.sbs.untact.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.untact.dto.Article;

@Controller
public class UsrHomeController {
	private List<Article> articles;

	public UsrHomeController() {
		articles = new ArrayList<>();
		articles.add(new Article(1, "2020-12-12 12:12:12", "제목1", "내용1"));
		articles.add(new Article(2, "2020-12-12 12:12:12", "제목2", "내용2"));

	}

	@RequestMapping("/usr/article/detail")
	@ResponseBody
	public Article showDetail(int id) {
		return articles.get(id - 1);
	}

	@RequestMapping("/usr/article/list")
	@ResponseBody
	public List<Article> showList() {
		return articles;
	}
}
