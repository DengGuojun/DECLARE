//**************** Div折叠效果 需要样式表DIV.DivTitleOn支持*************************
//<div class="PageRoad" style="padding: 0 0 50px 0">
//    当前位置：一般列表查询模板

//</div>
//<div class="DivTitleOn" onclick="DivOnOff('Td3',event);" >种植信息</div>
//<div class="DivContent" id="Td3">
// 内容......
//</div>
//*****************************************************
function DivOnOff(objId, eventObj) {
    var e = eventObj || window.event;
    var srcElement = e.srcElement || e.target;
    var objDesc = document.getElementById(objId);
    if (objDesc.style.display == "none") {
        srcElement.className = "DivTitleOn";
        objDesc.style.display = "";
    }
    else {
        srcElement.className = "DivTitleOff";
        objDesc.style.display = "none";
    }
}

//**************** 按钮悬停高亮效果样式 *************************
// 用法：<style>.btn70highlight {color:black; background:red}</style>
// <form name="form2" onmouseover="highlightButton('btn70','btn70highlight',event)" onmouseout="highlightButton('btn70','btn70highlight',event)">
//*****************************************************
function highlightButton(oldClassName, newClassName, eventObj) {
    var e = eventObj || window.event;
    var srcElement = e.srcElement || e.target;
    if (srcElement != null) {
        srcElement = e.srcElement || e.target;
        if ("INPUT" == srcElement.tagName && srcElement.className == oldClassName) {
            srcElement.className = newClassName;
        }
    }
}
//字符长度（中文算两个）

String.prototype.realLength = function() {
    var cArr = this.match(/[^\u4e00-\u9fa5]/ig);
    var strLength = (cArr == null ) ? 0 : cArr.length;
    return this.length * 2 - (cArr == null ? 0 : strLength);
}
//是否中文字符
function isChinese(str) {
    var lst = /[u00-uFF]/;
    return !lst.test(str);
}
//取中英文混合字符串长度

function strlen(str) {
    var strlength = 0;
    for (i = 0; i < str.length; i++) {
        if (isChinese(str.charAt(i)) == true)
            strlength = strlength + 2;//1中文占2字符位

        else
            strlength = strlength + 1;
    }
    return strlength;
}

//Enter触发Tab事件
//ClientScript.RegisterClientScriptInclude("", "Include/EnterTab.js");//注册Enter移动焦点事件
function EnterClick(eventObj) {
    var e = eventObj || window.event;
    if (e.keyCode == 13) e.keyCode = 9;
}

function CheckBoxClick(obj) {
}

var ie = (navigator.appName.indexOf("Microsoft") != -1) ? true : false;

function $(objID) {
    return document.getElementById(objID);
}

//* text 模拟 link
function textMouseover(obj) {
    obj.textDecoration = "underline";
}
function textMouseout(obj) {
    obj.style.textDecoration = "none";
}

//判断GridView是否选择一个checkBox，适用于LinkButton控件，注意在控件上要设置href="#"
function ifSelectRow(obj, alt) {
    var chks = document.getElementsByTagName("input");
    if (chks == null) return false;

    if (chks.length) {
        for (i = 0; i < chks.length; i++) {
            if (chks[i].type == "checkbox") {
                if (chks[i].checked == true) {
                    __doPostBack(obj.replace(/_/ig, "$"), '');
                    return;
                }
            }
        }
    }
    if (alt != "") {
        alert(alt);
    }
}
//判断GridView是否选择一个checkBox，适用于Button控件
//参数：【alt：提示信息，如请勾选一条记录，可选项，默认为“请勾选需要操作的记录”】【divID：控件容器ID，可选项，默认为document】

function isSelectRow(alt, divID) {
    var chks;
    if (divID == undefined)
        chks = document.getElementsByTagName("input");
    else
        chks = $(divID).getElementsByTagName("input");

    if (chks == null) return false;

    if (chks.length) {
        for (i = 0; i < chks.length; i++) {
            if (chks[i].type == "checkbox") {
                if (chks[i].checked == true) {
                    //alert(chks[i].value);
                    return true;
                }
            }
        }
    }
    if (alt != undefined)
        alert(alt);
    else
        alert("请勾选需要操作的记录");
    return false;
}
function isDeleteRow(obj, alt) {
    var chks = document.getElementsByTagName("input");
    if (chks == null) return false;

    if (chks.length) {
        for (i = 0; i < chks.length; i++) {
            if (chks[i].type == "checkbox") {
                if (chks[i].checked == true) {
                    return confirm('确定删除所选数据吗？');
                }
            }
        }
    }
    if (alt != "") {
        alert(alt);
    }
    return false;
}
//设置GridView里的CheckBox单选

function selectOnlyOne(checkBoxId) {
    _table = $(checkBoxId).parentNode.parentNode.parentNode;
    var chks = document.getElementsByTagName("input");
    if (chks == null) return false;

    if (chks.length) {
        for (i = 0; i < chks.length; i++) {
            if (chks[i].type == "checkbox") {
                if (chks[i].checked == true && chks[i].id != checkBoxId) {
                    chks[i].checked = false;
                }
            }
        }
    }
}
//获取InnerText或textContent
function getInnerText(obj) {
    return obj.innerText ? obj.innerText : obj.textContent;
}

//设置InnerText或textContent
function setInnerText(obj, value) {
    if (obj.innerText != undefined)
        obj.innerText = value;
    else
        obj.textContent = value;
}
//ifram自适应高度
function SetCwinHeight() {
    var IfrmView = document.getElementById("IfrmView"); //iframe id
    if (document.getElementById) {
        if (IfrmView && !window.opera) {
            if (IfrmView.contentDocument && IfrmView.contentDocument.body.offsetHeight) {
                IfrmView.height = IfrmView.contentDocument.body.offsetHeight + 20;
            } else if (IfrmView.Document && IfrmView.Document.body.scrollHeight) {
                IfrmView.height = IfrmView.Document.body.scrollHeight + 20;
            }
        }
        if (IfrmView != null && IfrmView.src != "about:blank") {
            var divFram = $("divFram");
            divFram.style.top = "0px";
            divFram.style.left = "0px";
            divFram.style.zIndex = 99999;

            var top;
            if (IfrmView.contentDocument)
                top = IfrmView.contentDocument.getElementById("functionMenu");
            else
                top = IfrmView.Document.getElementById("functionMenu");
            if (top == null) {
                if (IfrmView.contentDocument)
                    top = IfrmView.contentDocument.getElementById("PageTop");
                else
                    top = IfrmView.Document.getElementById("PageTop");
                if (top != null) top.innerHTML += "<div id='functionMenu' class='right_t_menu' style='float: right; margin-right: 5px'>&nbsp;&nbsp;<a href='#' onclick='javascript:hideView();'><img src='Image/closewin.gif' alt='关闭窗口' style='border:0px;' /></a></div>";
            }
            else
                top.innerHTML += "&nbsp;&nbsp;<a href='#' onclick='javascript:hideView();'><img src='Image/closewin.gif' alt='关闭窗口' style='border:0px;' /></a>";
            //if (top != null) top.innerHTML += "<span onclick='hideView();' style=\"float: right; margin:0px 10px; background: transparent url(Images/gif/closewin.gif) no-repeat 0px 0px; width:18px; height:18px; vertical-align:middle; cursor:pointer\"><span>";
        }
    }
}
//隐藏Ifram中的页面
function hideView() {
    if (parent.document.getElementById('divFram') != null) {
        //alert("hide");
        parent.document.getElementById('divFram').style.top = "-99999px";
        parent.document.getElementById('divFram').style.display = "none";

        var IfrmView = document.getElementById("IfrmView");
        if (IfrmView != null) IfrmView.src = "about:blank";
        
        if (parent.document.getElementById('iframeGray') != null) {
            parent.document.getElementById('iframeGray').style.display = "none";
        }
        parent.document.body.style.backgroundColor = "#FFFFFF";
        //
//        var IfrmCalendar = parent.document.getElementById("IfrmCalendar"); //iframe id
//        if (IfrmCalendar == null) {//非首页才刷新
//            //alert("fresh");
//            if (parent != null) parent.location = parent.location; //.reload();
//        }
//        else {
//            //alert("nofresh");
//        }
    }
    else
        history.go(-1);
}
function hideView1() {
    if (parent.document.getElementById('divFram') != null) {
        //alert("hide");
        parent.document.getElementById('divFram').style.top = "-99999px";
        parent.document.getElementById('divFram').style.display = "none";

        var IfrmView = document.getElementById("IfrmView");
        if (IfrmView != null) IfrmView.src = "about:blank";
        
        if (parent.document.getElementById('iframeGray') != null) {
            parent.document.getElementById('iframeGray').style.display = "none";
        }
        parent.document.body.style.backgroundColor = "#FFFFFF";
        //
        var IfrmCalendar = parent.document.getElementById("IfrmCalendar"); //iframe id
        if (IfrmCalendar == null) {//非首页才刷新
            //alert("fresh");
            if (parent != null) parent.location = parent.location; //.reload();
        }
        else {
            //alert("nofresh");
        }
    }
    else
        history.go(-1);
}
//刷新父页面

function freshPage(url) {
    var IfrmCalendar = parent.document.getElementById("IfrmCalendar"); //iframe id
    if (IfrmCalendar != null) {
        var d = new Date();
        if (url.length > 30) IfrmCalendar.src = url + "&s=" + d.getSeconds();
    }
    else {
        //alert(url);
        //if (parent != null) parent.location = url; // parent.location.reload();
    }
}
//获取ifram的document
function getDocument(ifram) {
    if (ifram.contentDocument)
        return ifram.contentDocument;
    else
        return ifram.document;
}
//刷新GridView当前页数据

function bindGridView() {
    if (parent.main.$("tablePageBar") != null && parent.main.$("tablePageBar").style.display == "none") {
        parent.main.location.href = parent.main.location.href;
        //hideView();
    }
    else if (parent.main.$("PageBarGo") != null) {
        parent.main.$("PageBarGo").onclick();
        //hideView();
    }
    else if ($("tablePageBar") != null && $("tablePageBar").style.display == "none")
        location.href = location.href;
    else if ($("PageBarGo") != null)
        $("PageBarGo").onclick();
}
//GridView列单击排序

function setSort(gridID, columnIndex, img) {
    //var grid = $(gridID);
    var grid = $("#" + gridID + "")[0];
    var link = grid.rows[0].cells[columnIndex].getElementsByTagName('a');
    link[0].innerHTML = link[0].innerHTML + "<img border=\"0\" src=\"" + img + "\" style=\"vertical-align:text-bottom\"/> ";
}
//移动div-----------------------
function fDragging(obj, e, limit) {
    if (!e) e = window.event;
    var x = parseInt((obj.style.left == '') ? '0' : obj.style.left);
    var y = parseInt((obj.style.top == '') ? '0' : obj.style.top);

    var x_ = e.clientX - x;
    var y_ = e.clientY - y;

    if (document.addEventListener) {
        document.addEventListener('mousemove', inFmove, true);
        document.addEventListener('mouseup', inFup, true);
        document.addEventListener('mouseout', inFstop, true);
    }
    else if (document.attachEvent) {
        document.attachEvent('onmousemove', inFmove);
        document.attachEvent('onmouseup', inFup);
        document.attachEvent('mouseout', inFstop);
    }

    inFstop(e);
    inFabort(e)

    function inFmove(e) {
        var evt;
        if (!e) e = window.event;

        if (limit) {
            var op = obj.parentNode;
            var opX = parseInt(op.style.left);
            var opY = parseInt(op.style.top);

            if ((e.clientX - x_) < 0) return false;
            else if ((e.clientX - x_ + obj.offsetWidth + opX) > (opX + op.offsetWidth)) return false;

            if (e.clientY - y_ < 0) return false;
            else if ((e.clientY - y_ + obj.offsetHeight + opY) > (opY + op.offsetHeight)) return false;
            //status=e.clientY-y_; 
        }
        obj.style.left = e.clientX - x_ + 'px';
        obj.style.top = e.clientY - y_ + 'px';

        inFstop(e);
    }
    function inFup(e) {
        var evt;
        if (!e) e = window.event;

        if (document.removeEventListener) {
            document.removeEventListener('mousemove', inFmove, true);
            document.removeEventListener('mouseup', inFup, true);
        }
        else if (document.detachEvent) {
            document.detachEvent('onmousemove', inFmove);
            document.detachEvent('onmouseup', inFup);
        }

        inFstop(e);
    }

    function inFstop(e) {
        if (e.stopPropagation)
            return e.stopPropagation();
        else
            return e.cancelBubble = true;
    }
    function inFabort(e) {
        if (e.preventDefault)
            return e.preventDefault();
        else
            return e.returnValue = false;
    }
}
//移动div--^^^^^^^^---------------

//
function checkBoxColumnHeadClick(checkType,ctlID) {
    var Tab = $(ctlID);
    if (Tab == null)
        return;
    var ifSelect;
    if (getInnerText(checkType) == '√') {
        ifSelect = true;
        checkType.title = "取消全选";
        setInnerText(checkType, '×');
    }
    else {
        ifSelect = false;
        checkType.title = "全选";
        setInnerText(checkType ,'√');
    }
    var Chks = Tab.getElementsByTagName("input");   
    if (Chks.length) {
        for (i = 0; i < Chks.length; i++) {
            if (Chks[i].type == "checkbox") {
                Chks[i].checked = ifSelect;
            }
        }
    }
    else if (Chks) {
        if (Chks.type == "checkbox") {
            Chks.checked = ifSelect;
        }
    }
}

function HTMLEncode(input) {
    var converter = document.createElement("DIV");    
    setInnerText(converter,input);
    var output = converter.innerHTML;
    converter = null;
    return output.replace(/</g, "&lt;").replace(/>/g, "&gt;");
    s = s.replace(/</g, "&lt;");
    s = s.replace(/>/g, "&gt;");   
}

function HTMLDecode(input) {
    var converter = document.createElement("DIV");
    converter.innerHTML = input;
    var output = getInnerText(converter);
    converter = null;
    return output.replace(/&lt;/g, "<").replace(/&gt;/g, ">");
}

//设置页面url
function postToUrl(obj) {
    location.href = urlRandom(obj.getAttribute("url"));
}

//为链接添加随机参数，迫使ifram每次刷新
function urlRandom(url) {
    if (url.indexOf("?") != -1)
        return url + "&" + Math.random();
    else
        return url + "?" + Math.random();
}
//隐藏Ifram中的页面
function hideView() {
    if (parent.document.getElementById('divFram') != null) {
        parent.document.getElementById('divFram').style.top = "-99999px";
        if (parent.document.getElementById('iframeGray') != null) {
            parent.document.getElementById('iframeGray').style.display = "none";
        }
        parent.document.body.style.backgroundColor = "#FFFFFF";

    }
    else
        history.go(-1);
}
function hideViewandfresh() {
    if (parent.document.getElementById('divFram') != null) {
        parent.document.getElementById('divFram').style.top = "-99999px";
        if (parent.document.getElementById('iframeGray') != null) {
            parent.document.getElementById('iframeGray').style.display = "none";
        }
        parent.document.body.style.backgroundColor = "#FFFFFF";
        if (parent != null) parent.location = parent.location;
    }
    else
        history.go(-1);
}
function hideViewandgoto(url) {
    if (parent.document.getElementById('divFram') != null) {
        parent.document.getElementById('divFram').style.top = "-99999px";
        if (parent.document.getElementById('iframeGray') != null) {
            parent.document.getElementById('iframeGray').style.display = "none";
        }
        parent.document.body.style.backgroundColor = "#FFFFFF";
        if (parent != null) parent.location = url;
    }
    else
        history.go(-1);
}

   
