<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>


<script>
	function loadmenu() {
		var nav = $("ul#TopMenu").html("");
		nav.append('<li class="nav-item"><a class="nav-link" data-widget="pushmenu"href="#"><i class="fas fa-bars"></i></a></li>');
		
		$.ajax({
			url:"<%=request.getContextPath()%>/menu",
			type:"post",
			data:"",
			success:function(data){
				for(var menu of data){
					//alert(menu.name);
					var li=$('<li>').addClass('nav-item d-none d-sm-inline-block');
					var a=$('<a>').addClass('nav-link').attr({'data-url':'<%=request.getContextPath()%>'+menu.url,'href':'#','data-name':menu.menu_code}).text(menu.name);
					
					li.html(a);
					
					nav.append(li);
				}
					
			},
			error:function(){}
		});
		
	}/* end loadMenu */

	
	//팝업창 띄우기
	//새로운 window창을 open할 경우 사용되는 함수 (arg : 주소, 창이름, 넓이, 길이)
	function OpenWindow(UrlStr, WinTitle, WinWidth, WinHeight){
		winleft = (screen.width - WinWidth) / 2;
		wintop = (screen.height - WinHeight) / 2;
		var win = window.open(UrlStr, WinTitle, "scrollbars=yes, width="+ WinWidth
								+", height=" + WinHeight +", top=" + wintop + ",left="
								+ winleft + ", resizable=no, status=yes");
		
	}
		
</script>

<script>
loadmenu();
</script>

<script>
$('ul#TopMenu').on('click','li a',function(event){
	//alert('click')
	event.preventDefault();
	var url=$(this).attr('data-url');
	var menuCode = $(this).attr('data-name');
	
	//alert("url : " + url + "\n" + "Menu Code : " + menuCode);
	
	//self.location = url;
	
	var data={menu_code:menuCode};
	
	var asideMenu = $('ul#asideMenu').html("");
	
	$.ajax({
		url:"<%=request.getContextPath()%>/subMenu",
		type:"post",
		data:JSON.stringify(data),//data를 String형태로 server에 전달
		contentType:'application/json',
		success:function(subMenuList){
			for(var subMenu of subMenuList){
				//alert("name : "+subMenu.name);				
	          
	         var li =  $('<li>').addClass("<nav-item>");
	         var a = $('<a>').attr({href:"#","data-url" : subMenu.url}).addClass("nav-link");
	         var i = $('<i>').addClass("nav-icon fas fa-lightbulb-o");
	         var p = $('<p>').text(subMenu.name);
	        
	         a.append(i).append(p);
	         li.append(a);
	         //li.append(a.append(i).append(p)); 한줄로 표시
	         
	         asideMenu.append(li);
			}
		},
		error:function(error){
			alert("서비스가 준비중입ㄴ디ㅏ요")
		}
	});
});

$('ul#asideMenu').on('click','li a',function(event){
	event.preventDefault();
	
	alert($(this).attr("data-url"));
});	


</script>







