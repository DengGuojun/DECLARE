/*
 * author: Ming
 * date: 2016.08.19
 */
jQuery(function() {
	// 解决头部100%宽度 和主体固定宽度后自适应bug
	jQuery(window).resize(function() {
		var width = jQuery(window.body).width();
		jQuery('.header').css('width', width + 'px');
	});
	setWidth();
});

function setWidth() {
	var mainW = jQuery("#maindhead").width();
	var div = jQuery(".nav > div");
	var divW = -45;
	for (var i = 0; i < div.length; i++) {
		divW += jQuery(div[i]).width() + 45;
	}
	var sideW = (mainW - divW) / 2;
	jQuery(".nav").width(divW);
	jQuery(jQuery("#maindhead td")[0]).width(sideW);
	jQuery(jQuery("#maindhead td")[2]).width(sideW);
}
// 取消nav图标hover效果 改为样式实现 update at 161127
//
// var title;
// jQuery(function () {
// jQuery(".contents img").hover(function () {
// title = jQuery(this).attr("name");
// jQuery(this).attr("src", "../images/nav_hover_0" + title + ".png");
// }, function () {
// title = jQuery(this).attr("name");
// jQuery(this).attr("src", "../images/nav_0" + title + ".png");
// })
// })
jQuery(function() {
	// 左边栏
	jQuery(".detail_left_box").hover(
			function() {
				jQuery(this).removeClass("detail_box3").addClass("detail_box2")
						.children().addClass("detail_color");
			},
			function() {
				// if ($(this).attr("name") != 'checked') {
				jQuery(this).removeClass("detail_box2").addClass("detail_box3")
						.children().removeClass("detail_color");
				// }
			})
})

jQuery(function() {
	jQuery(".js-gvHead td").hover(function() {
		jQuery(this).parent('tr').css('background', '#fff0c8');
	}, function() {
		jQuery(this).parent('tr').css('background', '#ffffff');
	})
})

// 报名表
var def = [ "现代青年农场主培育对象申报表", "生产经营型职业农民培育对象申报表", "专业技能型职业农民培育对象申报表",
		"专业服务型职业农民培育对象申报表", "新型农业经营主体带头人培育对象申报表" ];

$('.js-fillForm').change(function() {
	var num = $(this).children('option[selected]').index();
	$('.js-title .table_title').html(def[num]);
	if (num == 0) {
		$('.js-tr-2,.js-tr-3,.js-tr-1-2,.js-tr-1-5').hide();
		$('.js-tr-1,.js-tr-1-1').show();
	} else if (num == 1) {
		$('.js-tr-2,.js-tr-3,.js-tr-1-1,.js-tr-1-5').hide();
		$('.js-tr-1,.js-tr-1-2').show();
	} else if (num == 2 || num == 3) {
		$('.js-tr-1,.js-tr-1-1,.js-tr-1-2,.js-tr-1-5').hide();
		$('.js-tr-2,.js-tr-3').show();
	} else {
		$('.js-tr-3,.js-tr-1-1,.js-tr-1-2').hide();
		$('.js-tr-2,.js-tr-1,.js-tr-1-5').show();
	}
});

;
(function() {
	function setHeight() {
		var winH = $(window).height();
		var body = $('.warpDefaultMgr'), slideL = $('#listItem'), slideR = $('.detail_right'), slideRcont = $('.detail_right .content_wrap');
		if (winH - 169 > 590) {
			body.css('height', winH - 169 + 'px');
			slideL.css('height', winH - 169 - 23 + 'px');
			slideR.css('height', winH - 169 - 5 + 'px');
			slideRcont.css('height', winH - 169 - 5 + 'px');
		} else {
			body.removeAttr('style');
			slideL.removeAttr('style');
			slideR.removeAttr('style');
			slideRcont.removeAttr('style');
		}
	}
	setHeight();
	$(window).resize(function() {
		setHeight();
	})
})();

var global_Html = "";
function printme() {
	global_Html = document.body.innerHTML;
	document.body.innerHTML = document.getElementById('printHtml').innerHTML;
	window.print();
	window.setTimeout(function() {
		document.body.innerHTML = global_Html;
	}, 1500);
}
