"use strict";

$('.u-tab li').click(function(){
	$(this).addClass('selected').siblings().removeClass('selected');
	var index = $(this).index();
	$('.declareInfo-cont .tab-cont').eq(index).show().siblings().hide();
});

function ajaxAuthCheck(data){
	var code = data.code;
	if(code!='403'){
		return data;
	}else{
		//没有登陆
		window.location = data.message;
	}
}

//省市区选择
$(function(){
	showProvince();
});

function setAjax(id,elm,callback){
        if(id == 'province'){
            $.ajax({
                type: 'POST',
                dataType:'jsonp',
                url: 'http://www.lpmas.com/m/ProvinceList.action?jsoncallback='+callback,
                success: function(data){
                },
                error: function(){
                    return;
                }
            });
        }else if(id == 'city'){
        	var provinceId = $('#'+elm).val();
            $.ajax({
                type: 'POST',
                dataType:'jsonp',
                url: "http://www.lpmas.com/m/CityList.action?provinceId="+provinceId+"&jsoncallback="+callback,
                success: function(data){
                },
                error: function(){
                    return;
                }
            });  
        }else{
        	var cityId = $("#"+elm).val();
            $.ajax({
                type: 'POST',
                dataType:'jsonp',
                url: "http://www.lpmas.com/m/RegionList.action?cityId="+cityId+"&jsoncallback="+callback,
                success: function(data){
                },
                error: function(){
                    return;
                }
            });
        }
}


//show
function showProvince(){
    setAjax('province',null,'provinceData');
    setAjax('province',null,'provinceData1')
} 

function provinceData(data){
	var sel = $("#selectProvince");  
	var selProvince = $("#selProvince").val(); 
	sel.empty();  
	sel.append("<option value = '请选择'>请选择</option>");
	var items=data.content.provinceList;
    	if(items != null) {
	   	for(var i =0;i<items.length;i++) {
	      	var item=items[i];
	      	sel.append("<option value = '"+item.provinceId+"'>"+item.provinceName+"</option>");
	      	if(item.provinceName == selProvince){
	      		$("#selectProvince").val(item.provinceId);
	      	}
	    };
	    sel.change(function(){
	    	showCity();
	    })
	    if(selProvince != ""){
	    	showCity();
	    }
    } else{
   		sel.empty();  
    }
}

function provinceData1(data){
	var sel = $("#personProvince");  
	var perProvince = $("#perProvince").val();  
	sel.empty();  
	sel.append("<option value = '请选择'>请选择</option>");
	var items=data.content.provinceList;
    	if(items != null) {
	   	for(var i =0;i<items.length;i++) {
	      	var item=items[i];
	      	sel.append("<option value = '"+item.provinceId+"'>"+item.provinceName+"</option>");
	      	if(item.provinceName == perProvince){
	      		$("#personProvince").val(item.provinceId);
	      	}
	    };
	    sel.change(function(){
	    	showCity();
	    })
	    if(perProvince != ""){
	    	showCity();
	    }
    } else{
   		sel.empty();  
    }
}

//show
function showCity(){
 	setAjax('city','selectProvince','cityData');
 	setAjax('city','personProvince','cityData1');
} 

function cityData(data){
	var sel = $("#selectCity");  
	var selCity = $("#selCity").val(); 
	sel.empty();  
	sel.append("<option value = '全部'>全部</option>");
	var items=data.content.cityList;
    	if(items != null) {
	   	for(var i =0;i<items.length;i++) {
	      	var item=items[i];
	      	sel.append("<option value = '"+item.cityId+"'>"+item.cityName+"</option>");
	      	if(item.cityName == selCity){
	      		$("#selectCity").val(item.cityId);
	      	}
	    };
	    sel.change(function(){
	    	showRegion();
	    })
	    if(selCity != ""){
	    	showRegion();
	    }
    } else{
   		sel.empty();  
    }
}
function cityData1(data){
	var sel = $("#personCity");  
	var perCity = $("#perCity").val(); 
	sel.empty();  
	sel.append("<option value = '全部'>全部</option>");
	var items=data.content.cityList;
    	if(items != null) {
	   	for(var i =0;i<items.length;i++) {
	      	var item=items[i];
	      	sel.append("<option value = '"+item.cityId+"'>"+item.cityName+"</option>");
	    	if(item.cityName == perCity){
	      		$("#personCity").val(item.cityId);
	      	}
	    };
	    sel.change(function(){
	    	showRegion();
	    })
	    if(perCity != ""){
	    	showRegion();
	    }
    } else{
   		sel.empty();  
    }
}


//show
function showRegion(){
	var text = $("#selectCity").val();
	if(text == "全部"){
		$("#queryCity").val("");
	}else{
		$("#queryCity").val(text);
	}
	var text1 = $("#personCity").val();
	if(text1 == "全部"){
		$("#queryCity1").val("");
	}else{
		$("#queryCity1").val(text1);
	}
	setAjax('region','selectCity','regionData');

	setAjax('region','personCity','regionData1');
} 

function regionData(data){
	var sel = $("#selectRegion");  
	var selRegion = $("#selRegion").val();
	sel.empty();  
	sel.append("<option value = ''>全部</option>");
	var item=data.content;
    if(item != null) {
    	var items = item.regionList;
   		for(var i =0;i<items.length;i++) {
	      	var item=items[i];
	      	var queryRegion = "";
	      	sel.append("<option value = '"+item.regionId+"'>"+item.regionName+"</option>");
	      	if(item.regionName == selRegion){
	      		$("#selectRegion").val(item.regionId);
	      	}
	    }
	    sel.change(function(){
	    	setRegion();
	    })
    } else{
   		sel.empty();  
    }
}
function regionData1(data){
	var sel = $("#personRegion");  
	var perRegion = $("#perRegion").val(); 
	sel.empty();  
	sel.append("<option value = ''>全部</option>");
	var item=data.content;
    if(item != null) {
    	var items = item.regionList;
   		for(var i =0;i<items.length;i++) {
	      	var item=items[i];
	      	var queryRegion = "";
	      	sel.append("<option value = '"+item.regionId+"'>"+item.regionName+"</option>");
	      	if(item.regionName == perRegion){
	      		$("#personRegion").val(item.regionId);
	      	}
	    }
	    sel.change(function(){
	    	setRegion();
	    })
    } else{
   		sel.empty();  
    }
}

function setRegion(){
	var text = $("#selectRegion").val();
	if(text == "全部"){
		$("#queryRegion").val("");
	}else{
		$("#queryRegion").val(text);
	}
	var text1 = $("#personRegion").val();
	if(text1 == "全部"){
		$("#queryRegion1").val("");
	}else{
		$("#queryRegion1").val(text1);
	} 
}


//checkbox

$('.cc-checkbox').click(function(){
	if($(this).hasClass('selected')){
		$(this).removeClass('selected');
		$(this).find('input[type=checkbox]').attr('checked', false);
	}else{
		$(this).addClass('selected');
		$(this).find('input[type=checkbox]').attr('checked', true);
	}
})
$('.cd-checkbox').click(function(){
	if($(this).hasClass('selected')){
		$(this).removeClass('selected');
		$(this).find('input[type=checkbox]').attr('checked', false);
	}else{
		$(this).addClass('selected');
		$(this).find('input[type=checkbox]').attr('checked', true);
		$('.checkAllNone input[type=checkbox]').attr('checked', false);
		$('.checkAllNone').removeClass('selected');
	}
})
$('.checkAllNone').click(function(){
	if($(this).hasClass('selected')){
		$(this).removeClass('selected');
		$(this).find('input[type=checkbox]').attr('checked', false);
	}else{
		$(this).addClass('selected');
		$(this).find('input[type=checkbox]').attr('checked', true);
		$('.cd-checkbox input[type=checkbox]').attr('checked', false);
		$('.cd-checkbox').removeClass('selected');
	}
})



//图片上传
;(function (global) {
    var _defaultSetting = {
            loading: '.loading',
            url: '', //接收数据的api接口地址
            maxFileSize: 10 * 1024 * 1024,
            format: 'image',
            isCompress: true,
            compressNum: 0.6, // 0~1 设置为1可能最终结果比未压缩还大，请慎用1.
            beforeUpload: function () {},
            uploadStart: function () {},
            afterUpload: function () {},
            uploadProgress: function (v) {},
            uploadError: function () {},
            showThumbnail: function () {}
        },
        opt = {};

    function mobileUpload(options) {
        opt = $.extend(true, _defaultSetting, options);
        $(this).on('change', function () {
            startUpload(this);
        });
        return this;
    }

    function startUpload(el) {
        var files = el.files,
                len = files.length;
        for (var i = len - 1; i >= 0; i--) {
            (function (file) {
                if (file.size > opt.maxFileSize) {
                    console.log('您上传的' + file.name + ',图片尺寸过大，最大限制为10M');
                    return false;
                }
                //有些安卓手机无法获取文件类型
                if (new RegExp(opt.format,'i').test(file.type) || !file.type) {
                    if (opt.beforeUpload() === false) {
                        return false;
                    }
                    readFile(file);
                } else {
                    console.log("您上传的文件不符合上传的要求");
                }
            }(files[i]));
        }
    }

    function readFile(file) {
        var reader = new FileReader();
        reader.onload = function () {
            file=this.result;
            opt.uploadStart && opt.uploadStart(file);
            upLoadFile(file);
            this.result = null;
            reader.onload = null;
            reader = null;
        };
        reader.onprogress = function (e) {
            if (e.lengthComputable) {
                var percentLoaded = Math.round((e.loaded / e.total) * 100);
                opt.uploadProgress && opt.uploadProgress(percentLoaded);
            }
        };
        reader.onabort = function () {
            opt.uploadError && opt.uploadError();
        }
        if(opt.format='image'){
            reader.readAsDataURL(file);
        }else{
            reader.readAsBinaryString(file);
            opt.isCompress=false;
        }
    }

    function upLoadFile(file) {
        if(opt.format!='image'){
            ajaxUploadFile(file);
        }else {
            var _canvas = $('<canvas style="display: none"></canvas>')[0];
            var img = new Image();
            img.onload = function () {
                if (opt.isCompress) {
                    _canvas.width = img.naturalWidth;
                    _canvas.height = img.naturalHeight;
                    var context = _canvas.getContext('2d');
                    context.drawImage(img, 0, 0);
                    ajaxUploadFile(_canvas.toDataURL('image/jpeg', opt.compressNum));
                } else {
                    ajaxUploadFile(file);
                }
            };
            img.src = file;
        }
    }

    function ajaxUploadFile(file) {
        $(opt.loading).show();
        opt.showThumbnail && opt.showThumbnail(file);
        $.ajax({
            url: opt.url,
            type: 'post',
            data: {
                file: file
            },
            success: function (d) {
                $(opt.loading).hide();
                opt.afterUpload && opt.afterUpload(file, d);
            },
            error: function (d) {
                $(opt.loading).hide();
                opt.uploadError && opt.uploadError(d);
            }
        });
    }

    $.fn.mobileUpload = mobileUpload;
}(this));