package com.spring.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.spring.dto.MenuDTO;

public class ListUpMenu {

	public static List<MenuDTO> listMenu(HttpServletRequest request) throws Exception {
		String menuFilePath = request.getSession().getServletContext().getRealPath("/WEB-INF/menu/menu.csv");
		FileReader reader = new FileReader(menuFilePath);
		BufferedReader in = new BufferedReader(reader);

		String textLine = null;
		List<MenuDTO> menuList = new ArrayList<MenuDTO>();
		while ((textLine = in.readLine()) != null) {
			String[] menuData = textLine.split(",");
			if (menuData.length >= 3) {
				menuList.add(new MenuDTO(menuData[0], menuData[1], menuData[2]));
			}

		}
		return menuList;
	}
	
	public static List<MenuDTO> listMenu(HttpServletRequest request, String menu_code) throws Exception {
		String menuFilePath = request.getSession().getServletContext().getRealPath("/WEB-INF/menu/"+menu_code+".csv");
		FileReader reader = new FileReader(menuFilePath);
		BufferedReader in = new BufferedReader(reader);

		String textLine = null;
		List<MenuDTO> menuList = new ArrayList<MenuDTO>();
		while ((textLine = in.readLine()) != null) {
			String[] menuData = textLine.split(",");
			if (menuData.length >= 3) {
				menuList.add(new MenuDTO(menuData[0], menuData[1], menuData[2]));
			}

		}
		return menuList;
	}

}
