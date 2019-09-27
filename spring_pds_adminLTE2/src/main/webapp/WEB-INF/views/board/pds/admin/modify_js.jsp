<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<script>
$('#addFileBtn').on('click',function(event){

	var inputFile=$('input[name="uploadFile"]').length;
	var attachedFile=$('div.inputRow').length;
	var attachedCount = inputFile + attachedFile;
	
	if(attachedCount>=5){
		alert("최대 5개까지 업로드할 수 있습니다.");
		return;
	}
	
	var input=$('<input>').attr({"type":"file","name":"uploadFile"}).css("display","inline");
	
	var div=$('<div>').addClass("inputRow");
		div.append(input).append("<button style='border:0;outline:0;' class='badge bg-red' type='button'>x</button>");
		div.appendTo('.fileInput');
});

$('div.fileInput').on('click','div.inputRow > button',function(event){
	$(this).parent('div.inputRow').remove();
});

$('div.fileInput').on('change','input[type="file"]',function(event){
	if(this.files[0].size>1024*1024*40){
		alert("파일의 크기는 40mb를 초과할 수 없습니다.");
		this.value="";
		$(this).focus();
		return false;
	}
});
$('#modifyBtn').on('click',function(event){
	var form=document.registForm;
	
	var files=$('input[name="uploadFile"]');
		for(file of files){
		console.log(file.name+" : "+file.value);
			if(file.value==""){
				alert("파일을 선택하세요.");
				file.focus();
				return false;
			}
		}
		
		for(var i = 0; i<form.elements.length;i++){
			var input = form.elements[i];
			if(input.name=="title" || input.name=="content"){
				if(input.value==""){
					$(input).parent('div').addClass("is-invalid");
					alert(input.name + "은 필수로 입력해야 합니다.");
					return;
				}
			}
		}
		
		if(form.content.value.length>1000){
			alert("글자수는 1000글자를 초과할 수 없습니다.");
			return;
		}
		onSubmit("${category}",document.registForm,'modify','post');	
});

$("input").on('keyup',function(e){
	if($(this).val()!=""){
		$(this).removeClass("is-invalid");
	}
});

$('.attached div button').on('click',function(event){
	event.stopPropagation();
	
	var parent = $(this).parent('.info-box').parent('.inputRow');
	alert(parent.attr("attach-fileName")+"파일을 삭제합니다.");
	
	var ano = parent.attr("attach-no");
	var input = $('<input>').attr({"type":"hidden",
									"name":"deleteFile",
									"value":ano});
	$('form[role="form"]').prepend(input);
	
	parent.remove();
});

</script>