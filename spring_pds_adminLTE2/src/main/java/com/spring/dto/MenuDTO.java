package com.spring.dto;

public class MenuDTO {
	private String name;
	private String url;	
	private String menu_code;
	
	public MenuDTO() {}
	public MenuDTO(String name, String url) {
		super();
		this.name = name;
		this.url = url;
	}
		
	public MenuDTO(String name, String url, String menu_code) {
		super();
		this.name = name;
		this.url = url;
		this.menu_code = menu_code;		
	}
	
	public String getMenu_code() {
		return menu_code;
	}
	public void setMenu_code(String menu_code) {
		this.menu_code = menu_code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	@Override
	public String toString() {
		return "MenuDTO [name=" + name + ", url=" + url + ", menu_code=" + menu_code + "]";
	}
}
