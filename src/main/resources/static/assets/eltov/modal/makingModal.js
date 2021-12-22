var Modal = {
	wrapElement: '',
	code : {
		PROGRAM: 'PROGRAM',
		CONTENTS: 'CONTENTS',
		DEVICE: 'DEVICE'
	},
	drawModal: function (result){
		isValid(result);
		if(result.code == this.code.PROGRAM){
			console.log('PROGRAM');
			this.set.programUpdModal(result.data);
		}else if(result.code == this.code.DEVICE){
			console.log('DEVICE');
		}else if(result.code == this.code.CONTENTS){
			console.log('CONTENTS');
		}
	},
	set: {
		programUpdModal: function(data){
			console.log(data);
			var form = document.getElementById('programUpdForm');
			var id = form.programId;
			var sects = form.programSect;
			var name = form.programName;
			var workPath = form.programWorkPath;
			var desc = form.programDesc;
			
			for(var i=0; i<sects.length; i++){
				var sect = sects[i];
				if(sect.value == data.programSect){
					sect.setAttribute('checked',true);
					sect.parentElement.setAttribute('class', 'btn btn-pill btn-outline-secondary active');
				}else{
					sect.setAttribute('checked',false);
					sect.parentElement.setAttribute('class', 'btn btn-pill btn-outline-secondary');
				}
			}
			
			name.value = data.programName;
			workPath.value = data.programWorkPath;
			desc.value = data.programDesc;
			id.value = data.programId;
			
			// 버전 목록 만들기
			var html = '';
			var fileList = data.programFileList;
			for(var i=0; i<fileList.length; i++){
				var file = fileList[i];
				var fileId = file.FILE_ID;
				var fileDesc = file.FILE_VER_DESC;
				var fileVer = file.FILE_VER;
				var filePath = file.FILE_PATH;
				var useYn = file.USE_YN;
				if(fileDesc == null || fileDesc == 'undefined') {fileDesc = 'first';}
				html += '<tr>';
				html += 	'<th scope="row" width="12%">';
//				html += 		'<div role="group" class="btn-group-lg btn-group btn-group-toggle" data-toggle="buttons">';
//				html += 			'<label class="btn btn-pill btn-outline-secondary active" onclick="javascript:chageVersion(' + fileId + ', ' + fileVer +');">';
				if(useYn == 'Y'){
					html += 		'<div role="group" class="btn-group-lg btn-group btn-group-toggle" data-toggle="buttons">';
					html += 			'<label class="btn btn-pill btn-outline-secondary active" onclick="javascript:chageVersion(' + fileId + ',' + data.programId + ');">';
					html += 				'<input type="radio" name="programVerUse" id="programVerUse" value="Y" checked>' + fileVer;
					html += 			'</label>';
					html += 		'</div>';
				}else{
					html += 		'<div role="group" class="btn-group-lg btn-group btn-group-toggle" data-toggle="buttons">';
					html += 			'<label class="btn btn-pill btn-outline-secondary" onclick="javascript:chageVersion(' + fileId + ',' + data.programId + ');">';
					html += 				'<input type="radio" name="programVerUse" id="programVerUse" value="N">' + fileVer;
					html += 			'</label>';
					html += 		'</div>';
				}
//				html += 				'<input type="radio" name="programVerUse" id="programVerUse" value="Y" checked>' + fileVer;
//				html += 			'</label>';
//				html += 		'</div>';
				html += 	'</th>';
				html += 	'<td width="80%">' + fileDesc + '</td>';
//				html += 	'<td width="30%">' + filePath + '</td>';
				html += 	'<td width="8%"><button type="button" class="btn btn-icon btn-light"><i class="pe-7s-download btn-icon-wrapper" style="margin-right: 0rem;"></i></button></td>';
				html += '</tr>';
			}
			
			var list = document.getElementById('programVerList');
			list.innerHTML = html;
		},
		programVerUpdModal: function(data){
			console.log(data);
		}
	}
}


///////////////////////////////
//Modal data validation
function isValid(__result){
	console.log('isValid');
	console.log(__result);
}