package com.greedy.section02.service.run;

import com.greedy.section02.service.model.service.MenuService;

public class Application {

	public static void main(String[] args) {
		/* 메뉴 등록해주는 service 생성*/
		new MenuService().registNewMenu();
	}

}
