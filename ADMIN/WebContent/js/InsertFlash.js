function insertFlash(elm, url, w, h) { 
if (!document.getElementById(elm)) return; 
var str = ''; 
str += '<object width="'+ w +'" height="'+ h +'" classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,0,0">'; 
str += '<param name="movie" value="'+ url +'">'; 
str += '<param name="wmode" value="transparent">'; 
str += '<param name="quality" value="high"><param name="menu" value="false">'; 
str += '<embed width="'+ w +'" height="'+ h +'" src="'+ url +'" quality="autohigh" wmode="opaque" menu="false" type="application/x-shockwave-flash" plugspace="http://www.macromedia.com/shockwave/download/index.cgi?P1_Prod_Version=ShockwaveFlash"></embed>'; 
str += '</object>'; 
document.getElementById(elm).innerHTML = str; 
}

function insertFlashnews(elm, focus_width, focus_height, text_height, swf_height, pics, links, texts) { 
if (!document.getElementById(elm)) return; 
var str = ''; 
str += '<object width="'+ focus_width +'" height="'+ swf_height +'" classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,0,0">'; 
str += '<param name="movie" value="Images/picviewer.swf"><param name="wmode" value="opaque"><param name="quality" value="high"><param name="menu" value="false">'; 
str += '<param name="FlashVars" value="pics='+pics+'&links='+links+'&texts='+texts+'&borderwidth='+focus_width+'&borderheight='+focus_height+'&textheight='+text_height+'">'; 
str += '<embed width="'+ focus_width +'" height="'+ swf_height +'" src="Images/picviewer.swf" FlashVars="pics='+pics+'&links='+links+'&texts='+texts+'&borderwidth='+focus_width+'&borderheight='+focus_height+'&textheight='+text_height+'" menu="false" bgcolor="#F0F0F0" quality="autohigh" wmode="opaque" type="application/x-shockwave-flash" plugspace="http://www.macromedia.com/shockwave/download/index.cgi?P1_Prod_Version=ShockwaveFlash"></embed>'; 
str += '</object>'; 
document.getElementById(elm).innerHTML = str; 
}