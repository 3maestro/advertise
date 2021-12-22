var CommonAjax = {
		isProceeding : false,
		params : {
			get : {
				method : 'get',
				contentType : 'application/json',
				processData : true
			},
			post : {
				method : 'post',
				contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
				processData : true
			},
			file : {
				method : 'post',
				contentType : false,
				processData : false
			}
		},
		funcs : {
			ParentReload : function(){
		    	parent.document.location.reload();
		    },
		    Reload : function(){
		    	document.location.reload();
		    }
		},
		setMakeAjax : function(__url, __data, __type, __func, __progress, __errFunc){
			
			var params = this.params[__type];
			var func = typeof __func == 'function' ? __func : this.funcs[__func];
			var errFunc = typeof __errFunc == 'function' ? __errFunc : this.funcs[__errFunc];
			var ajaxObj = {
			                url: __url,
			                data: __data,
			                async: true,
			                type: params['method'],
			                contentType : params['contentType'],
			                processData : params['processData'],
			                dataType : 'json',
			                success: function(result){
			                	CommonAjax.isProceeding = false;
			                	$('#loading').hide();
			        			/*if (result.code == 'SUCC' ) {*/
			        				if(result.msg){
			        					alert(result.msg);
			        				}
			        				if(func){
			        					var goOnFlag = func(result);
			        					if(!goOnFlag){
			        						return false;
			        					}
			        				}
			        				if(result.red){
			        					if(result.red == 'RELOAD'){
			        						document.location.reload();
			        						return;
			        					}
			        					if(self != top){
			        						if(result.red == '/system/admin/list'){
			        							parent.document.location.href = result.red;
			        							return;
			        						}
			        						parent.document.location.href = parent.document.location.pathname + result.red;
			        						return;
			        					}
			        					location.href = result.red;
			        					return;
			        				}
			        			/*} else {
			        				alert(result.msg);
			        			}*/
			                },
			                error: function(err){
			                	CommonAjax.isProceeding = false;
			                	$('#loading').hide();
			                	
			                	if(errFunc){
		        					var goOnFlag = errFunc(err);
		        					if(!goOnFlag){
		        						return false;
		        					}
		        				}
			                	
			                	if(err.status == 0){
			                	}else if(err.status == 200){
			                		alert('로그인 후 다시 시도해주십시오.');
			                		location.href = "/stadmin";
			                	}else{
			                		if(err.responseJSON){
			                			alert(err.responseJSON.msg);
			                			if(err.responseJSON.msg.red){
			                				location.href = err.responseJSON.msg.red;
			                			}
			                		}else{
			                			alert('시스템 오류입니다. 관리자에게 문의하십시오.');
			                		}//TODO : ResponseEntity로 통일해서 이 로직도 수정해야함
			                	}
			                	
			                	/*else if(err.status == 400){
			                		alert('시스템 오류입니다. 관리자에게 문의하십시오.');
			                	}else if(err.status == 403){
			                		alert('권한이 없습니다. 로그인이 필요합니다.');
			                		location.href = "/stadmin";
			                	}else if(err.status == 404){
			                		alert('유효하지 않은 요청입니다.');
			                	}else if(err.status == 500){
			                		alert('시스템 오류입니다. 관리자에게 문의하십시오.');
			                	}else{
			                		console.log('Error : ' + err.responseText);
			                	}*/
			                }
            			};
			
			if(__type == 'file'){
				delete ajaxObj['dataType'];
			}
			
			if(__progress){
				ajaxObj['xhr'] = function() { //XMLHttpRequest 재정의 가능
					var xhr = $.ajaxSettings.xhr();
					xhr.upload.onprogress = function(evt) { //progress 이벤트 리스너 추가
						if(evt.lengthComputable) {
		                    var percent = 100 * evt.loaded / evt.total;
		                    percent = Math.floor(percent);
		                    $("#id_progress_bar").css("width",percent + "%");
		                    $("#id_progress_bar").html(percent + "%");
		                }
					};
					return xhr;
				}
			}
			
			return ajaxObj;
		},
		setSend : function(__url, __data, __type, __func, __progress, __allowRequestNesting, __errFunc){
			var ajaxObj = this.setMakeAjax(__url, __data, __type, __func, __progress, __errFunc);
			if(__allowRequestNesting || (!__allowRequestNesting && !this.isProceeding)){ //ajax 중복 요청 방지
				this.isProceeding = true;
				$.ajax(ajaxObj);
			}else{
				alert('요청이 진행중입니다. 잠시 후 다시 시도해주십시오.');
			}
		},
		getJSONData : function(__url, __data, __type, __succ, __error){
			$.ajax({
					url: __url,
	                data: __data,
	                type: __type,
	                contentType : 'application/json',
	                dataType : 'json',
	                success : __succ,
	                error : __error
	        });
		},
		setSendFileProgress : function(__url, __formData ){
			 $.ajax({
				url: __url,
				data : __formData,
				type : 'post',
				contentType : false,
				processData: false,
				xhr: function() { //XMLHttpRequest 재정의 가능
					var xhr = $.ajaxSettings.xhr();
					xhr.upload.onprogress = function(evt) { //progress 이벤트 리스너 추가
						if(evt.lengthComputable) {
		                    var percent = 100 * evt.loaded / evt.total;
		                    percent = Math.floor(percent);
		                    $("#id_progress_bar").css("width",percent + "%");
		                    $("#id_progress_bar").html(percent + "%");
		                }
					};
					return xhr;
				},
				success: function(result){
					CommonAjax.isProceeding = false;
        			if (result.code == 'SUCC' ) {
        				if(result.msg){
        					alert(result.msg);
        				}
        				if(result.red){
        					if(self != top){
        						parent.document.location.href = parent.document.location.pathname + result.red;
        						return;
        					}
        					location.href = result.red;                                    	
        				}
        			} else {
        				alert(result.msg);
        			}
                },
                error: function(err){
                	CommonAjax.isProceeding = false;
                	if(err.status == 0){
                	}else if(err.status == 200){
                		alert('시스템 오류입니다. 관리자에게 문의하십시오.');
                		location.href = "/";
                	}else if(err.status == 403){
                		alert('권한이 없습니다. 로그인이 필요합니다.');
                		location.href = "/";
                	}else if(err.status == 404){
                		alert('요청한 페이지는 존재하지 않습니다. 관리자에게 문의하십시오.');
                	}else if(err.status == 500){
                		alert('시스템 오류입니다. 관리자에게 문의하십시오.');
                	}else{
                		console.log('Error : ' + err.responseText);
                	}
                }
			}); 
		},
//    	setSend : function(url, data, async, method){
//    		$.ajax({
//                url: url,
//                data: data,
//                async: async,
//                type: method,
//                dataType : 'json',
//				contentType : 'application/json',
//				beforeSend : function(xhr){
//                	xhr.setRequestHeader("req-ajax","Y");
//                },
//                success: result => {
//                            if (result.code == 'SUCC' ) {
//                                alert(result.msg);
//                                if(result.red){
//                                	location.href = result.red;                                    	
//                                }
//                            } else {
//                                alert(result.msg);
//                            }
//                },
//                error: err => {
//                	if(err.status == 403){
//                		location.href = "/";
//                	}else{
//                		console.log(err.statusText);
//                	}
//                	
//                }
//            })
//    	},
    }

var Validator = {
		getRequiredCheck : function(){
			var res = true;
			var eleArr = $('.cus_required');
			for(var i =0; i<eleArr.length; i++){
				var data = eleArr[i].value;
				var pureData = data? data.trim() : false;
			
				if(!pureData){
					alert($(eleArr[i]).data("name") + '를 입력해주세요');
					$(eleArr[i]).focus();
					res = false;
					break;
			    }
			}
			return res;
		},
		setDatePickerValid : function(sday, eday){
			sdayStr = $(sday).val();
			edayStr = $(eday).val();
			if(!sdayStr || !edayStr){
				return false;
			}
			var diff = CustomDate.getDiffDay(sdayStr, edayStr);
			if (diff > 0){
				alert('종료일은 시작일보다 빠른 날짜일 수 없습니다.');
				$(eday).val('');
			}
		},
		setNumberOnly : function(){
			$("input:text[numberOnly]").on("keyup", function() {
			    $(this).val($(this).val().replace(/[^0-9]/g,""));
			});
		},
		setDecimalOnly : function(){
			$("input:text[decimalOnly]").on("keyup", function() {
			    $(this).val($(this).val().replace(/[^0-9|\\.]/g,""));
			});
		},
		setPhoneOnly : function(){
			$("input:text[phoneOnly]").on("keyup", function() {
			    $(this).val($(this).val().replace(/[^0-9|\\.]/g, ""));
			});
		},
		setTimeOnly : function(){
			$("input:text[timeOnly]").on("keyup", function() {
				$(this).val($(this).val().replace(/^([01][0-9]|2[0-3]):([0-5][0-9])$/,""));
			});
		},
		checkAllowedFileExtension : function(fileList, fileExtenstionList){
			for(var i=0; i < fileList.length; i++){
				var f = fileList.item(i);
				var fileName = f.name;
				var extension = fileName.substring(fileName.lastIndexOf('.') +1);
				if(!fileExtenstionList.includes(extension))
					return false;
			}
			return true;
		},
		checkFileSize : function(fileList, maxSize){
			for(var i=0; i < fileList.length; i++){
				var f = fileList.item(i);
				if(f.size > maxSize)
					return false;
			}
			return true;
		}
}

var ImageChecker = {
		getReplaceImageError : function(){
			var images = document.getElementsByTagName('img');
			for(var i=0; i<images.length; i++){
			    images[i].onerror = function(){
			        this.src = '/images/icon.gif';
			        this.parentElement.href='#';
			    }
			}
		}
}


var CustomPattern = {
		// yyyy-mm-dd
	    date : function(){
	        return new RegExp('^(19|20)\\d{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[0-1])$');
	    },
		number : function(){
			return new RegExp('/[^0-9]/g');
		},
		decimal : function(){
			return new RegExp('/[^0-9|\\.]/g');
		},
		phone : function(){
			return new RegExp('(^01[016789])-?(\\d{3}|\\d{4})-?(\\d{4})$');
		},
		time : function(){
			return new RegExp('/^([01][0-9]|2[0-3]):([0-5][0-9])$/');
		}
	}


var CustomDate = {
		getDiffDay : function(sdateStr, edateStr){
			sdate = CustomDate.getDate(sdateStr);
			edate = CustomDate.getDate(edateStr);
			
		    etime = sdate.getTime();
		    stime = edate.getTime();
		    count = (etime - stime);

		    count = Math.floor(count/(24*3600*1000));
		    return count;
		},
		getDate : function(dateStr){
			year = dateStr.substring(0,4);
		    month = dateStr.substring(5,7);
		    day = dateStr.substring(8,10);
		    date = new Date(year, month-1, day);
		    return date;
		},
		getNextDay : function(targetDate){
			var date;
			if(typeof targetDate === 'string' && CustomPattern.date().test(targetDate)){
				date = CustomDate.getDate(targetDate);
				nextDate = date.setDate(date.getDate() + 1);
			}
			
			return CustomDate.getDateStr(date);
		},
		getDateStr : function(date){
			if(date != null){
				yyyy = date.getFullYear();
				mm = date.getMonth() + 1 < 10 ? '0' + (date.getMonth() +1) : '' + (date.getMonth() + 1);
				dd = date.getDate() < 10 ? '0' + date.getDate() : '' + date.getDate();
			}else{
				console.log('date is not null');
			}
			return yyyy + '-' + mm + '-' + dd;
		}
}




var CustomUtil = {
		getAbbreviate : function(str, limitLength){
			if(str.length <= limitLength){
				return str;
			}else{
				return str.substr(0, limitLength) + '...';
			}
		}
}


function getJsonStr2Array(key, jsonStr){
	if(!key || !jsonStr){
		console.log('invalid arguments');
		return false;
	}
	jobj = JSON.parse(jsonStr.replace(/'/gi,"\""));
    jArr = jobj[key];
    return jArr;
}


function getJsonStrParse(jsonStr){
	if(!jsonStr) return null;
	return JSON.parse(jsonStr.replace(/'/gi,"\""));
}



