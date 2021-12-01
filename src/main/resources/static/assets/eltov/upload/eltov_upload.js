/**
 * 
 */
// 전송 할 파일 정보는 globalFileList객체에 담겨 있습니다.
var globalFileList = new Array();
var glovalFileConfig = {
		totalCnt: 5,							// 최대 업로그 갯수
		imgFileMaxSize: 10 * 1024 * 1024,		// 최대 이미지 사이즈
		movFileMaxSize: 500 * 1024 * 1024,		// 최대 동영상 사이즈
		fileExt: ['jpg', 'png', 'mp4'],			// 업로드 가능한 파일 확장자
		excpetFileMaxSize: 100 * 1024 * 1024,	// 예외 파일 사이즈
		excpetFileExt: ['pdf', 'pptx'],			// 예외 업로드 가능 확장자
		excpetFileExtStatus: 'Y'				// 예외 기능 사용 여부.
}

function uploadHtmlInit(){
	var html = '';
	var uploadWrapper = document.getElementById('uploadWrapper');
	html += '<div class="uploadBox" id="uploadBox">';
	html += 	'<input type="file" name="files" id="uploadInput" multiple>';
	html += 	'<p id="uploadTxt1">Drag & Drop or Click in here.</p>';
	html += 	'<p id="uploadTxt2">';
	html += 		'파일 업로드 최대 갯수 : <span style="color:chocolate;">' + glovalFileConfig.totalCnt + '개</span><br/>';
	html += 		'파일 업로드 최대 사이즈 : <span style="color:chocolate;">이미지 ' 
	html +=			(glovalFileConfig.imgFileMaxSize / 1024 / 1024) + 'MB / 동영상 ' + (glovalFileConfig.movFileMaxSize / 1024 / 1024) + 'MB</span><br/>';
	html += 		'파일 업로드 확장자 : <span style="color:chocolate;"> ' + glovalFileConfig.fileExt + ' </span><br/>';
	html += 		'<span style="color:chocolate;">** 각 하나의 컨텐츠에 해당합니다.**</span><br/>';
	html += 	'</p>';
	html += '</div>';
	html += '<div class="uploadTemp" id="uploadTempZone"></div>';
	uploadWrapper.innerHTML = html;
}

function uploadAddEventInit(){
	var uploadWrapper = document.querySelector('#uploadWrapper');
	var uploadBox = uploadWrapper.querySelector('#uploadBox');
	var uploadInput = uploadWrapper.querySelector('#uploadInput');

	// 이벤트 할당
	uploadInput.addEventListener('change', function(e){
		e.preventDefault();
	    console.log('change');
	    var files = this.files;
	    for(var i=0; i<files.length; i++){
	    	globalFileList.push(files[i]);
	    }
	    makeTempThumHtml();
	    console.log(globalFileList);
	})

	// 이벤트 할당(드롭)
	uploadBox.addEventListener('drop', function(e) {
	    e.preventDefault();
	    console.log('drop');
	    this.style.backgroundColor = 'white';
	    var files = e.dataTransfer.files;
	    for(var i=0; i<files.length; i++){
	    	globalFileList.push(files[i]);
	    }
	    makeTempThumHtml();
	    console.log(globalFileList);
	});
	
	/* 박스 안에 Drag 들어왔을 때 */
	uploadBox.addEventListener('dragenter', function(e) {
	    console.log('dragenter');
	});

	/* 박스 안에 Drag를 하고 있을 때 */
	uploadBox.addEventListener('dragover', function(e) {
	    e.preventDefault();
	    console.log('dragover');

	    var vaild = e.dataTransfer.types.indexOf('Files') >= 0;

	    if(!vaild){
	        this.style.backgroundColor = 'red';
	    }
	    else{
	        this.style.backgroundColor = '#fbffc0';
	    }
	});

	/* 박스 밖으로 Drag가 나갈 때 */
	uploadBox.addEventListener('dragleave', function(e) {
	    console.log('dragleave');

	    this.style.backgroundColor = 'white';
	});
}

function delThumImg(seq){
	var thumImg = document.getElementById('thumImg'+seq);
	thumImg.remove();
	//파일 리스트에서 제거
	var removeIndex = globalFileList.indexOf(globalFileList[seq]);
	if (removeIndex > -1) {
    	globalFileList.splice(removeIndex,1);
    	makeTempThumHtml();
	}
	console.log(globalFileList);
}

function makeTempThumHtml(){
	
	isValid(globalFileList);
	$("#uploadTempZone").children().remove();
	
	var html = '';
	var fileLength = globalFileList.length;
	
	// 파일 정보를 가지고 html 그리기
	for(var i=0; i<fileLength; i++){
		var file = globalFileList[i];
		//파일 경로.
    	var filePath = URL.createObjectURL(file);
		
    	// making html
    	html += '<div class="" id="thumImg' + i + '" style="float: left;">';
    	html += 	'<div style="padding: 5px;">';
    	html += 		'<span class="thumDiv">';
    	html += 			'<img src="' + filePath + '" style="height: 80px;width: 100%;"><br>';
		html += 			'<span class="thumInfo">' + file.name + '</span>';
    	html += 		'</span> ';
    	html += 		'<span class="delThumImg" onclick="javascript:delThumImg(' + i + ');"><i class="pe-7s-close"></i></span>';
    	html += 	'</div>';
    	html += '</div>';
	}
	$("#uploadTempZone").append(html);
	
	var fileTxt = document.getElementById('uploadTxt1');
	fileTxt.textContent = fileLength + ' file(s) selected';
}

// 그려진 파일 정보를 server로 보내기
function uploadFileProcess(){
	
}

///////////////////////////////
// File Util
///////////////////////////////
// 파일 업로드 유효성 검사
function isValid(files){
	
	var fileLength = files.length;
	for(var i=0; i<fileLength; i++){
		var file = files[i];
		
		//이미지인지 유효성 검사
		if(file.type.indexOf('image') < 0 && file.type.indexOf('video') < 0){
			alert('이미지 또는 동영상 파일만 업로드 가능합니다.');
			globalFileList.splice(i);
			return false;
		}
		
		//파일의 개수는 5개씩만 가능하도록 유효성 검사
		if(fileLength > glovalFileConfig.totalCnt){
			alert('파일은 ' + glovalFileConfig.totalCnt + '개 까지 전송이 가능합니다.');
			globalFileList.splice(glovalFileConfig.totalCnt, fileLength);
			return false;
		}
		
		//파일의 사이즈
		if(file.type.indexOf('image') > -1){
			if(file.size >= glovalFileConfig.imgFileMaxSize){
				alert((glovalFileConfig.imgFileMaxSize / 1024 / 1024) + 'MB' + '이상인 파일은 업로드할 수 없습니다.');
				globalFileList.splice(i);
				return false;
			}
		}else if(file.type.indexOf('video') > -1){
			if(file.size >= glovalFileConfig.movFileMaxSize){
				alert((glovalFileConfig.movFileMaxSize / 1024 / 1024) + 'MB' + '이상인 파일은 업로드할 수 없습니다.');
				globalFileList.splice(i);
				return false;
			}
		}
		
		//파일 확장자
		if(glovalFileConfig.fileExt.indexOf(extractFileExtension(file)) < 0){
			alert('허용 되는 파일의 확장자는 ' + glovalFileConfig.fileExt + ' 입니다.');	    
			globalFileList.splice(i);
			return false;
		}
	}
	
	return true;
}

// 파일 확장자 추출
function extractFileExtension(file){
	var ext = '';
	var fileNameLength = file.name.length;
	var fileDot = file.name.lastIndexOf('.');
	ext = file.name.substring(fileDot+1, fileNameLength).toLowerCase();
	console.log(ext);
	
	return ext;
}
