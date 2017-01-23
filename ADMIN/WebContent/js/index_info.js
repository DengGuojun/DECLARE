//var pattern = '<span class="sub"></span>';
function getIndexInfoByQueryMethod(queryMethod,ele,callBack){
	var url = '/decalre/IndexInfoAjaxCount.do?queryMethod='+queryMethod;
	$.get(url,'',function(data){
		callBack(data,ele);
	});
}

function refreshDataAsync(){
	var elements = $('div');
	for(var i=0;i<elements.length;i++){
		var ele = $(elements[i]);
		var queryMethod = ele.attr('dataAsync');
		if(typeof(queryMethod)!='undefined'){
			getIndexInfoByQueryMethod(queryMethod,ele,function(data,e){
				if(data.code==1){
					e.html(data.message);
				}
			});
		}
	}
}

function init(){
	var elements = $('div');
	for(var i=0;i<elements.length;i++){
		var ele = $(elements[i]);
		var queryMethod = ele.attr('dataAsync');
		if(typeof(queryMethod)!='undefined'){
			ele.html('<span class="loading-tip">加载中...</span>');
		}
	}
}