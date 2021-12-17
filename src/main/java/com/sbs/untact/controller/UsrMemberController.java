package com.sbs.untact.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.untact.dto.Member;
import com.sbs.untact.dto.ResultData;
import com.sbs.untact.service.MemberService;

@Controller
public class UsrMemberController {
	@Autowired
	private MemberService memberService;
	
	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public ResultData doJoin(@RequestParam Map<String, Object> param) {
		if (param.get("loginId") == null) {
			return new ResultData("F-1", "로그인 아이디를 입력해주세요.");
		}
		
		Member existingMember = memberService.getMemberByLoginId((String) param.get("loginId"));
		
		if (existingMember != null) {
			return new ResultData("F-2", String.format("%s(은)는 사용할 수 없는 아이디 입니다.", param.get("loginId")));
		}
		if (param.get("loginPw") == null) {
			return new ResultData("F-1", "비밀번호를 입력해주세요.");
		}
		if (param.get("name") == null) {
			return new ResultData("F-1", "이름을 입력해주세요.");
		}
		if (param.get("nickname") == null) {
			return new ResultData("F-1", "닉네임을 입력해주세요.");
		}
		if (param.get("cellphoneNo") == null) {
			return new ResultData("F-1", "전화번호를 입력해주세요.");
		}
		if (param.get("email") == null) {
			return new ResultData("F-1", "이메일을 입력해주세요.");
		}
		ResultData rsData = memberService.join(param);

		return rsData;
	}
}
