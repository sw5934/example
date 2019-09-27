<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>

<script
	src="<%=request.getContextPath()%>/resources/bootstrap/plugins/summernote/summernote-bs4.min.js"></script>

<script>
<!-- 글등록 내용 스마트에디터 적용 -->
$('#content').summernote({
	height:200,
	placeholder:"1000자는 넘기지 말자^^",
	tabsize:'1',
	fontNames:['궁서', 'Arial', 'Arial Black', 'Comic Sans MS', 'Courier New',],
	fontNamesIgnoreCheck : ['궁서'],
	focus: true,
	lang: 'ko-KR',

	callbacks:{
		onImageUpload : function(files, editor, welEditable){
			for (var i = files.length - 1; i >= 0; i--) {
				if(files[i].name.substring(files[i].name.lastIndexOf(".")+1).toLowerCase() != "jpg"){
					alert("jpg 확장자만 가능");
					return;
				}
				//독립된 if문은 or의 의미
				//alert(files[i].name);
				if(files[i].size > 1024*1024*1){
					alert("이미지는 1MB 미만입니다.");
					return;
				}
			
			}		
			//for(var i in files){
			for (var i = files.length - 1; i >= 0; i--) {
				sendFile(files[i], this);
			}
			
		},
		onMediaDelete : function(target) {
			//alert(target[0].src);
			deleteFile(target[0].src);
		}
	}
});
</script>


<script>
	function onCancel(){
		history.go(-1);
	}
	
	function onSubmit(category, form, url, method) {

		//유효성처리
		//validation();
		
		
<%-- 		열어서 주제를 선택하는 거라면 ("action","<%=request.getContextPath()%>/board/${category}/admin/regist") --%>
		form.action="<%=request.getContextPath()%>/board/"+category+"/admin/"+url;
		form.method=method;
		form.submit();
	}	

	function deleteFile(src){
		
		var splitSrc = src.split("/");
		var fileName = splitSrc[splitSrc.length-1];
		$.ajax({
			data: {fileName : fileName, id : '${loginUser.id}'},
			type: "POST",
			url: "<%=request.getContextPath() %>/deleteImg",
			cache: false,
			success: function(resp){
				console.log(resp);
			}
		});
	}

	function sendFile(file, el){
		var form_data = new FormData();
		form_data.append("file", file);
		form_data.append("id", "${loginUser.id}");
		$.ajax({
			data: form_data,
			type: "POST",
			url: '<%=request.getContextPath() %>/uploadImg',
			contentType: false,
			processData: false,
			success: function(img_url){
				$(el).summernote('editor.insertImage', img_url);
			}
		});
	}
	

	function searchList(category, searchType, keyword) {
		self.location="<%=request.getContextPath()%>/board/"+category
						+"/admin/list?searchType="+searchType
						+"&keyword="+keyword;
	}

	//팝업창 띄우기
	//새로운 window창을 open할 경우 사용되는 함수 (arg : 주소, 창이름, 넓이, 길이)
	function OpenWindow(UrlStr, WinTitle, WinWidth, WinHeight){
		winleft = (screen.width - WinWidth) / 2;
		wintop = (screen.height - WinHeight) / 2;
		var win = window.open(UrlStr, WinTitle, "scrollbars=yes, width="+ WinWidth
								+", height=" + WinHeight +", top=" + wintop + ",left="
								+ winleft + ", resizable=no, status=yes");
		win.focus();

	}
	
	function CloseWindow(){
		if(confirm("창을 닫으시려고요?ㅠ")){
			window.close();
		}
	}

$('button#searchBtn').click(function(){
	//alert("search btn click");
	var searchType=$('select#searchType').val();
	var keyword=$('input[name="keyword"]').val();

	//alert("searchType="+searchType+"\n"+"keyword="+keyword);

	searchList("${category}",searchType,keyword);

});
</script>