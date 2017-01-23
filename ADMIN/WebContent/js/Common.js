//GridView多行编辑模型客户端脚本

//重写sting.indexOf(string)函数为sting.indexOf(string，bool)，加是否区分大小写参数
String.prototype._indexOf = String.prototype.indexOf;
String.prototype.indexOf = function () {
    if (typeof (arguments[arguments.length - 1]) != 'boolean') {

        return this._indexOf.apply(this, arguments);
    }
    else {
        var bi = arguments[arguments.length - 1];
        var thisObj = this;
        var idx = 0;
        if (typeof (arguments[arguments.length - 2]) == 'number') {
            idx = arguments[arguments.length - 2];

            thisObj = this.substr(idx);
        }

        var re = new RegExp(arguments[0], bi ? 'i' : '');
        var r = thisObj.match(re);
        return r == null ? -1 : r.index + idx;
    }
}

var TrCurrentRow; //当前行<tr></tr>
var GridRowMode; //GridViewRow模板
var TrID; //模板行ID(注意：当gridview没有RowDataBound事件时，行id为null)

//获取控件文本innerText
function getInnerText(obj) {
    return obj.innerText ? obj.innerText : obj.textContent;
}
//替换控件文本innerText
function setInnerText(obj, value) {
    if (obj.innerText != undefined)
        obj.innerText = value;
    else
        obj.textContent = value;
}

//设置GridView行模板，obj为行中任意cell的ClientID
function SetGridRowMode(obj) {
    //记录添加模板样式
    var currow = $(obj).parentNode;
    GridRowMode = currow.innerHTML;
    if (currow.id != "")
        TrID = currow.id + "_";
    else
        TrID = "";
    //删除模板行
    currow.parentNode.deleteRow(currow.rowIndex);
}

//combox控件onchange事件 in EditPage
function selectChange(ddl, value) {
    var textbox = $(ddl.id.replace('DropDownList', 'TextBox'));
    textbox.value = value;
}

//TextBox in EditGridView 的onfocus事件
function GetFocus(obj, hidden) {
    TrCurrentRow = obj.parentNode.parentNode; //记录操作行
    //            $(hidden).value = TrCurrentRow.cells[0].childNodes[0].innerText; //当前行号
    $(hidden).value = getInnerText(TrCurrentRow.cells[0].childNodes[0]); //当前行号
    //换背景
    obj.style.backgroundColor = "#f0ffff";
}

//TextBox in EditGridView 的onblur事件
function LostFocus(obj) {
    obj.style.backgroundColor = "#ffffff"; //换背景
}

//EditGridView页面删除当前行
function DeleteGridRow(HiddenFieldCurrentRowID) {
    var id = HiddenFieldCurrentRowID.replace("ButtonDelete", "HiddenFieldCurrentRow");
    var rowno = $(id).value;

    if (rowno == "") {
        alert("请首先单击选择你要删除的一行");
        return false;
    }
    if (confirm("是否真的要删除第" + rowno + "行吗？") == true) {
        //修改删除行后所有行号
        var _table = TrCurrentRow.parentNode;
        for (i = TrCurrentRow.rowIndex + 1; i < _table.rows.length; i++) {
            _table.rows[i].cells[0].innerHTML = _table.rows[i].cells[0].innerHTML.replace(">" + i + "<", ">" + (i - 1) + "<");
        }
        //删除当前行
        TrCurrentRow.parentNode.deleteRow(TrCurrentRow.rowIndex);
        return false;
    }
    else {
        return false;
    }
}

//EditGridView页面New行
function InsertGridRow(TableId) {
    var _table = $(TableId); //Gridview  

    //添加新行
    var newRow = _table.insertRow(-1);

    //用新行号替换行模板GridRowMode中的所有行号
    var nowno = "0" + newRow.rowIndex; //newRowIndex, 两位(如果一位，前添0)，从02开始，01为head
    nowno = nowno.substring(nowno.length - 2, nowno.length)
    if (TrID != "")
        TrID = TrID.replace(TableId, "");
    else
        TrID = "_ctl02_";
    var id = TableId + TrID;

    var reg = new RegExp(id, "g");
    var RowString;
    RowString = GridRowMode.replace(reg, TableId + "_ctl" + nowno + "_");
    id = id.replace(/_/g, "$");
    reg = new RegExp(id.replace(/\$/g, "\\$"), "g");
    var id2 = TableId + "_" + TrID.substring(4, 4) + nowno + "_";
    id2 = id2.replace(/_/g, "$");
    RowString = RowString.replace(reg, id2);

    //行样式
    newRow.className = "gv_Item";

    //添加Cell
    var index;
    index = RowString.indexOf("</td>", true);
    while (index != -1) {
        var oCell = newRow.insertCell(-1);
        oCell.innerHTML = RowString.substring(RowString.indexOf(">", true) + 1, index);
        RowString = RowString.substring(index + 5);
        index = RowString.indexOf("</td>", true);
    }

    //赋行号
    newRow.cells[0].innerHTML = newRow.cells[0].innerHTML.replace("\>\<", ">" + newRow.rowIndex + "<");
}

//格式化字符串
String.prototype.format = function () {
    var args = arguments;
    return this.replace(/\{(\d+)\}/g,
        function (m, i) {
            return args[i];
        });
}

//触发按钮事件
function KeyDown(obj) {
    if (event.keyCode == 13
    && event.srcElement.type != "button"
    && event.srcElement.type != "submit"
    && event.srcElement.type != "reset"
    && event.srcElement.type != "textarea"
    && event.srcElement.type != "")//enter
    {
        event.keyCode = 9;
    }
    else {
        switch (window.event.keyCode) {
            case 37: //←
                if (obj.parentNode.cellIndex != 1) {
                    if (obj.parentNode.parentNode.cells[obj.parentNode.cellIndex - 1].childNodes[0].tabIndex != "-1")
                        obj.parentNode.parentNode.cells[obj.parentNode.cellIndex - 1].childNodes[0].focus();
                    else
                        obj.parentNode.parentNode.cells[obj.parentNode.cellIndex - 1].childNodes[1].focus();
                }
                else if (obj.parentNode.parentNode.rowIndex != 1) {
                    var row = obj.parentNode.parentNode.parentNode.rows[obj.parentNode.parentNode.rowIndex - 1];
                    if (row.cells[row.cells.length - 1].childNodes[0].tabIndex != "-1")
                        row.cells[row.cells.length - 1].childNodes[0].focus();
                    else
                        row.cells[row.cells.length - 1].childNodes[1].focus();
                }
                break;
            case 38: //↑
                if (event.srcElement.type != "select-one" && obj.parentNode.parentNode.rowIndex != 1) {
                    var row = obj.parentNode.parentNode.parentNode.rows[obj.parentNode.parentNode.rowIndex - 1];
                    row.cells[obj.parentNode.cellIndex].childNodes[0].focus();
                }
                break;
            case 39: //→
                event.keyCode = 9;
                break;
            case 40: //↓
                if (event.srcElement.type != "select-one" && obj.parentNode.parentNode.rowIndex != obj.parentNode.parentNode.parentNode.rows.length - 1) {
                    var row = obj.parentNode.parentNode.parentNode.rows[obj.parentNode.parentNode.rowIndex + 1];
                    row.cells[obj.parentNode.cellIndex].childNodes[0].focus();
                }
                break;
        }
    }
}

//DropDownList in EditGridView 的onfocus事件
function DropDownListGetFocus(obj, hidden) {
    TrCurrentRow = obj.parentNode.parentNode; //记录操作行
    //    $(hidden).value = TrCurrentRow.cells[0].childNodes[0].innerText; //当前行号
    $(hidden).value = getInnerText(TrCurrentRow.cells[0].childNodes[0])//当前行号

    //显示DropDownList并定为初始值，隐藏TextBox,
    var id = obj.id.substring(0, obj.id.indexOf("_"));
    id = id + obj.id.substring(obj.id.lastIndexOf("_"));
    var ddl = $(id);
    if (obj.value == "") {
        ddl.options.selectedIndex = 0;
    }
    else {
        for (var i = 0; i < ddl.options.length; i++) {
            if (ddl.options[i].text == obj.value) {
                ddl.options.selectedIndex = i;
                break;
            }
        }
    }
    ddl.style.display = "block";
    obj.parentNode.appendChild(ddl);
    ddl.style.width = obj.style.width;
    obj.style.display = "none";
    ddl.focus();

}
//DropDownList in EditGridView 的onblur事件
function DropDownLisLostFocus(obj) {
    var cell = obj.parentNode;
    cell.childNodes[0].style.display = "block";
    if (obj.options[obj.selectedIndex].text == "请选择")
        cell.childNodes[0].value = "";
    else
        cell.childNodes[0].value = obj.options[obj.selectedIndex].text;

    obj.style.display = "none";
}

//EditGridView提交时，DropDownList得Text和Value的转换,flag=true时,Text --> Value;flag=false时,Text <-- Value
function AcceptDropDownListValue(gridViewId, flag) {
    if (typeof (RefColumnIndexs) == "undefined" || RefColumnIndexs == null || RefColumnIndexs == "") return;
    var colomns = RefColumnIndexs.split(",");
    var gridView = $(gridViewId);
    if (flag == true) gridView.style.display = "none";
    if (gridView.rows.length == 1) return;
    var id;
    var _TextBox;
    var _DropDownList;
    for (j = 0; j < colomns.length; j++) {
        _TextBox = gridView.rows[1].cells[parseInt(colomns[j])].childNodes[0];

        id = _TextBox.id.substring(0, _TextBox.id.indexOf("_"));
        id = id + _TextBox.id.substring(_TextBox.id.lastIndexOf("_"));
        _DropDownList = $(id);

        for (i = 1; i < gridView.rows.length; i++) {
            _TextBox = gridView.rows[i].cells[parseInt(colomns[j])].childNodes[0];
            if (flag == true)
                _TextBox.value = GetValueByText(_DropDownList, _TextBox.value);
            else
                _TextBox.value = GetTextByValue(_DropDownList, _TextBox.value);
        }
    }
}

//返回DropDownList中Text对应的Value
function GetValueByText(ddl, text) {
    if (ddl == null || text == "") return "";
    for (var i = 0; i < ddl.options.length; i++) {
        if (ddl.options[i].text == text) {
            return ddl.options[i].value;
        }
    }
}
//返回DropDownList中Value对应的Text
function GetTextByValue(ddl, value) {
    if (value == "") return "";
    for (var i = 0; i < ddl.options.length; i++) {
        if (ddl.options[i].value == value) {
            return ddl.options[i].text;
        }
    }
}

//EditGridView提交时,验证必添项
function CheckNull(gridViewID, cloumnIndex) {
    var gridView = $(gridViewID);
    //    var columnName = gridView.rows[0].cells[cloumnIndex].innerText;
    var columnName = getInnerText(gridView.rows[0].cells[cloumnIndex]);

    for (i = 1; i < gridView.rows.length; i++) {
        if (gridView.rows[i].cells[cloumnIndex].childNodes[0].value != undefined || gridView.rows[i].cells[cloumnIndex].childNodes[0].value != null) {

            if (gridView.rows[i].cells[cloumnIndex].childNodes[0].value == "") {
                alert("第" + i + "行“" + columnName + "”列为必添项！");
                return false;
            }
        }
    }
    return true;
}

//EditGridView提交时,自定义验证项
//function CheckCustom(gridViewID, cloumnIndex, reg, message) {
//    var regExp = new RegExp(reg);
//    var gridView = $(gridViewID);
//    gridView = gridViewID[0];
//    for (i = 1; i < gridView.rows.length; i++) {
//        if (gridView.rows[i].cells[cloumnIndex].childNodes[0] != null || gridView.rows[i].cells[cloumnIndex].childNodes[0] != undefined) {
//            if (!regExp.test(gridView.rows[i].cells[cloumnIndex].childNodes[0].value)) {
//                alert("第" + i + "行“" + message);
//                return false;
//            }
//        }
//    }
//    return true;
//}

//Combox列 in EditGridView 的onfocus事件
function ComboxGetFocus(obj, hidden) {
    TrCurrentRow = obj.parentNode.parentNode; //记录操作行

    if (hidden != "")//textbox获得焦点
    {
        //        $(hidden).value = TrCurrentRow.cells[0].childNodes[0].innerText; //当前行号
        $(hidden).value = getInnerText(TrCurrentRow.cells[0].childNodes[0]); //当前行号

        //显示DropDownList并定为初始值，隐藏TextBox,
        var id = obj.id.substring(0, obj.id.indexOf("_"));
        id = id + obj.id.substring(obj.id.lastIndexOf("_"));
        var ddl = $(id);
        obj.style.paddingTop = "0";
        ddl.style.marginTop = "1px";
        obj.parentNode.insertBefore(ddl, obj);
        ddl.style.display = "block";
        obj.style.borderStyle = "inset";
        obj.style.borderWidth = "2px";
        ddl.style.clip = "rect(0px " + (parseInt(obj.parentNode.style.width) + 19) + "px auto " + (parseInt(obj.parentNode.style.width) - 2) + "px)";
        ddl.tabIndex = "-1";
    }
    else//dropdownlist获得焦点
    {
        var textBox = obj.parentNode.childNodes[1];
        textBox.style.borderStyle = "inset";
        textBox.style.paddingTop = "0";
        textBox.style.borderWidth = "2px";
        obj.style.display = "block";
        obj.style.marginTop = "1px";
        obj.style.clip = "rect(0px " + (parseInt(textBox.parentNode.style.width) + 19) + "px auto " + (parseInt(textBox.parentNode.style.width) - 2) + "px)";
    }
}

//DropDownList in EditGridView 的onblur事件
function ComboxLostFocus(obj) {
    var cell = obj.parentNode;
    cell.childNodes[0].style.clip = "rect(0px " + (parseInt(cell.style.width) + 19) + "px auto " + (parseInt(cell.style.width) + 19) + "px)";
    cell.childNodes[1].style.border = "none";
}

//combox控件onchange事件 in EditGridView
function ComboxSelectChange(ddl) {
    var textbox = ddl.parentNode.childNodes[1];
    textbox.value = ddl.options(ddl.selectedIndex).value;
    textbox.focus();
}