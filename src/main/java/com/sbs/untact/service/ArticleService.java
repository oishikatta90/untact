package com.sbs.untact.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sbs.untact.dto.Article;
import com.sbs.untact.dto.ResultData;
import com.sbs.untact.util.Util;

@Service
public class ArticleService {

	private int articlesLastId;
	private List<Article> articles;

	public ArticleService() {
		// 멤버변수 초기화
		articlesLastId = 0;
		articles = new ArrayList<>();
		// 게시물 2개 생성
		articles.add(new Article(++articlesLastId, "2020-12-12 12:12:12", null, "제목1입니다.", "내용1입니다."));
		articles.add(new Article(++articlesLastId, "2020-12-12 12:12:12", null, "제목2", "내용2"));

	}

	public Article getArticle(int id) {
		for (Article article : articles) {
			if (article.getId() == id) {
				return article;
			}
		}
		return null;
	}

	public List<Article> getArticles(String searchKeywordType, String searchKeyword) {

		if (searchKeyword == null) {
			return articles;
		}

		List<Article> filtered = new ArrayList<>();

		for (Article article : articles) {
			boolean contains = false;

			if (searchKeywordType.equals("title")) {
				contains = article.getTitle().contains(searchKeyword);
			} else if (searchKeywordType.equals("body")) {
				contains = article.getBody().contains(searchKeyword);
			} else {
				contains = article.getTitle().contains(searchKeyword);
				if (contains == false) {
					contains = article.getBody().contains(searchKeyword);
				}
			}
			if (contains) {
				filtered.add(article);
			}
		}
		return filtered;
	}

	public ResultData add(String title, String body) {
		int id = ++articlesLastId;
		String regDate = Util.getNowDateStr();
		String updateDate = regDate;

		articles.add(new Article(id, regDate, updateDate, title, body));

		return new ResultData("S-1", "성공하였습니다.", "id", id);
	}

	public ResultData deleteArticle(int id) {
		for (Article article : articles) {
			if (article.getId() == id) {
				articles.remove(article);
				return new ResultData("S-1", "삭제에 성공하였습니다.", "id", id);
			}
		}
		return new ResultData("F-1", "삭제에 실패하였습니다.", "id", id);

	}

	public ResultData modify(int id, String title, String body) {
		Article article = getArticle(id);

		article.setTitle(title);
		article.setBody(body);
		article.setUpdateDate(Util.getNowDateStr());

		return new ResultData("S-1", "수정에 성공하였습니다.", "id", id);
	}
}
