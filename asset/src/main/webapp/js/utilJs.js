/**
 * author: wang lin
 * date: 2015
 * @file
 */
//  for (var i = 0; i < elements.length; i++) {
// $(function () {    });

function msg(str) {
    alert(str);
}

// JSON.stringify is from json2.js
function printObject(param) {
    msg(JSON.stringify(param, null, 5));
}

function jsGet(id) {
    return document.getElementById(id);
}

function jqGet(id) {
    return $('#' + id);
}

function put(json, key, value) {
    json[key] = value;
}

function get(json, key) {
    return json[key];
}

function remove(json, key) {
    delete json[key];
}

function trim(st) {
    return $.trim(st);
}

function anotherTrim(st) {
    return st.replace(/(^\s*)|(\s*$)/g, '');   // return $.trim( st );
}

function submitPostWithoutParam(url) {
    var temp = document.createElement('form');
    temp.action = url;
    temp.method = 'post';
    temp.style.display = 'none';
    document.body.appendChild(temp);
    temp.submit();
    return temp;
}

function submitPost(url, param) {     // param is a json object
    var temp = document.createElement('form');
    temp.action = url;
    temp.method = 'post';
    temp.style.display = 'none';

    for (var st in param) {
        if (param.hasOwnProperty(st)) {
            var opt = document.createElement('textarea');
            opt.name = st;
            opt.value = param[st];
            temp.appendChild(opt);
        }
    }

    document.body.appendChild(temp);
    temp.submit();
    return temp;
}

function pageForwardWithoutParam(url) {
    window.location.href = url;
}

function resetForm(id) {
    jsGet(id).reset();
}

// param is a json object
function pageForward(url, param) { // alert( arguments.length )
    if (arguments.length !== 1) {
        var str = '?';

        for (var st in param) { // var key = param
            if (param.hasOwnProperty(st)) {
                str += st + '=' + param[st]; // alert( param.length )
                str += '&';
            }
        }
        str = str.substring(0, str.length - 1);   // alert( url + str )

        window.location.href = url + str;
    }
}

function theAjaxSubmit(url, param, callbackFunctionStr) {
    $.ajax({
        type: 'POST',
        url: url,
        data: param,    // param is a json object
        dataType: 'json',
        error: function (data) {
            if (data !== null && data !== undefined) {
                if (data.responseJSON !== undefined && data.responseJSON.msg !== undefined) {
                    alert(data.responseJSON.msg);
                } else {
                    if (data.status !== null && data.status !== undefined) {
                        if (Number(data.status) > 0 && data.msg !== null && data.msg !== undefined) {
                            alert(data.msg);
                        } else if (Number(data.status) === 0) {
                            // uuap超时跨域访问问题
                        } else {
                            alert('操作异常。');
                        }
                    }
                }
            } else {
                alert('操作异常。');
            }
           // alert('error: ' + textStatus + '\n' + errorThrown);
        },
        success: function (theData) {
            if (callbackFunctionStr != null && callbackFunctionStr !== '') {
                var funcName = callbackFunctionStr + '(' + JSON.stringify(theData) + ')';
                eval(funcName);
            }
        }
    });
}

// 将 form 里的所有 input 序列化，作为提交表单的参数
// serializeForm("searchForm"); returns a object of the serialized form
// jqGet("searchForm").serialize(); returns a string of a serialized form
// serialize the form into json object, similar to jqGet("theForm").serialize();
function serializeForm(formId) {
    var param = {};
    var elements = document.forms[formId]; // alert( elements == null )

    for (var i = 0; i < elements.length; i++) {
        if (elements[i].name !== '') {
            if (elements[i].id !== 'file') {   // do not submit input of 'file' type
                var trimmedValue = trim(elements[i].value);
                param[elements[i].name] = trimmedValue;  // trim the input value before submit
                elements[i].value = trimmedValue;  // set the trimmed value back into the input
            }
        }
    }
    return param;
}

function isNullOrEmpty(obj) { // OrUndefined
    if (obj == null || trim(obj) === '') {
        return true;
    } else {
        return false;
    }
}

function isUndefined(obj) {
    if (typeof (obj) === 'undefined') {
        return true;
    }
}

// 判断 obj 是否为 undefined 或 null 或 trim 后是否是空字符串
function isNull(obj) {   // var result1 = isNull(value);
    if (typeof (obj) === 'undefined' || obj == null || trim(obj) === '') {
        return true;
    } else {
        return false;
    }
}

function validateEmptyStr(value) {
    if (value === '') {
        // alert(stringName + '不能为空');
        return false;
    } else {
        return true; // 不过不返回 true 得到的返回值就是 undefined
    }
}

function validateMaxLength(value, maxSize) {   // var result1 = validateMaxLength(value);
    if (value.length > maxSize) {
        // alert(stringName + '的长度不能大于' + maxSize);
        return false;
    } else {
        return true; // 如果不返回 true  得到的方法的返回值就是 undefined
    }
}

// 验证 数字、26个英文字母 或者 下划线 组成的字符串
function validateChar(value) {
    var reg = /^\w+$/;
    if (reg.test(value)) {
        return true;
    } else {
        // alert(name + '必须是数字、26个英文字母或者下划线');
        return false;
    }
}

// 验证 0 和 正整数
function validateZeroAndNumber(value) {
    // alert( value ) //alert( parseInt(value) );
    // if ( isNaN( value ) || parseInt(value) <= 0 ) {
    var reg = /^(0|[1-9]\d*)$/;
    if (reg.test(value)) {
        return true;
    } else {
        // alert(name + '必须是大于等于零的整数');
        return false;
    }
}

// 验证 正整数
function validateNumber(value) {
    var reg = /^\+?[1-9][0-9]*$/;
    if (reg.test(value)) {
        return true;   // 是正整数
    } else {
        // alert(name + '必须是正整数');
        return false;   // 不是正整数
    }
}

// 验证 正浮点数
function validateDouble(value) {
    var reg = /^(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$/;  // 正浮点数

    if (reg.test(value)) {
        return true;
    } else {
        // alert(name + '必须是正浮点数');
        return false;
    }
}

// 生成分页条
function generatePager(count, page, pageSize, howManyPages) {
    if (page === 1) { // 当前页是 第一页
        jsGet('pageFirst').removeAttribute('href');
        jsGet('pageFirst').removeAttribute('onclick');
        jsGet('pagePrevious').removeAttribute('href');
        jsGet('pagePrevious').removeAttribute('onclick');
    } else {
        jsGet('pageFirst').href = 'javascript:void(0)';
        jsGet('pageFirst').setAttribute('onclick', 'goToPage("first")');
        jsGet('pagePrevious').href = 'javascript:void(0)';
        jsGet('pagePrevious').setAttribute('onclick', 'goToPage("previous")');
    }

    if (page === howManyPages) { // 当前页是 最后一页
        jsGet('pageLast').removeAttribute('href');
        jsGet('pageLast').removeAttribute('onclick');
        jsGet('pageNext').removeAttribute('href');
        jsGet('pageNext').removeAttribute('onclick');
    } else {
        jsGet('pageLast').href = 'javascript:void(0)';
        jsGet('pageLast').setAttribute('onclick', 'goToPage("last")');
        jsGet('pageNext').href = 'javascript:void(0)';
        jsGet('pageNext').setAttribute('onclick', 'goToPage("next")');
    }
}

// 设置翻页时的链接
function setupPagerLinks(whichPage) {
    var currentPage = eval(jsGet('page').value); // 将 字符串 转换为 数字(number)
    var howManyPages = eval(jsGet('howManyPages').value);

    if (whichPage === 'first') {
        jsGet('page').value = 1;
    } else if (whichPage === 'last') {
        jsGet('page').value = howManyPages;
    } else if (whichPage === 'next') {
        jsGet('page').value = currentPage + 1;
    } else if (whichPage === 'previous') {
        jsGet('page').value = currentPage - 1;
    } else {
        jsGet('page').value = whichPage;
    }
}

// 分页菜单的点击跳转
function pagerClick(pageNum) {
    $('.go_input').on('click', function () {
        $('.go_alert').css('display', 'block');
        $('.go_alert').css('left', '59px');
    });

    $('.go_alert').on('click', function () {
        if ($('.go_alert').css('display') === 'block') {
             //  提交数据之后上传页面里面的page等参数
            var enterNum = $('.go_input').val();

            if (enterNum > pageNum) {
                goToPage(pageNum);
            } else {
                goToPage(enterNum);
            }

            $('.go_alert').css('display', 'none');
            $('.go_alert').css('left', '0');
        }
    });

    $('.two-text').on('click', function () {
        return false;
    });

    $(document).on('click', function () {
        if ($('.go_alert').css('display') === 'block') {
            $('.go_alert').css('display', 'none');
            $('.go_alert').css('left', '0');
        }
    });
}

// 生成列表的表头
function generateTableHead(listTitleParam) {
    var html = '';

    for (var key in listTitleParam) {
        if (listTitleParam.hasOwnProperty(key)) {
            html += '    <th width="100" >' + listTitleParam[key] + '</th>';
        }
    }
    return html;
}

// 生成列表，并显示这个列表
function generateTable(className, divId, list, listTitleParam, formatterParam,
                    checkboxParam, opColumnParam, sumParam) {
    var count = sumParam.count;  // 总的查询结果数
    var page = sumParam.page;     // 总的查询结果页数
    var pageSize = sumParam.pageSize;   // 每页的行数
    var howManyPages = sumParam.howManyPages;  // 一共有多少页

    var html = '';

    // 列表上面的汇总信息
    html += '<p class="tip">一共有<span class="strip">' + count + '</span>条查询结果   ';

    var info = sumParam.info;
    if (!isNull(info)) {
        html += info;
    }
    html += '</p>';

    html += '<table align="center" border="1" cellpadding="0" cellspacing="0" class="list-table-style" >';

    // 生成列表的表头
    html += '   <tr>';

    var tempCheckboxArg = checkboxParam.checkboxArg;    // printObject(checkboxParam);
    var checkboxArgToUse = '';

    if (isUndefined(tempCheckboxArg) !== true && tempCheckboxArg !== null) {
        checkboxArgToUse = tempCheckboxArg;
    } else {
        checkboxArgToUse = 'id';
    }
    // alert( checkboxArgToUse )

    if (checkboxParam.needCheckbox === true) {   // printObject(checkboxParam   );
        html += '        <th align="center" width="50" >';
        html += '           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'
            + '<input type=\'checkbox\'  id=\'checkAll\' '
            + ' onclick=\'checkAll()\'  /> 全选';
        html += '        </th>';
    }

    // 是否显示 序号 列
    if (sumParam.needSn === true) {
        html += '        <th align="center" width="50" >序号</th>';
    }

    html += generateTableHead(listTitleParam);

    // 生成 功能 那一列的表头
    if (opColumnParam !== null) {
        html += '        <th width="100" >功能</th>';
    }
    html += '   </tr>';

    // 以下生成列表的内容
    for (var i = 0; i < list.length; i++) {   // printObject( list )
        // 隔行 变色 显示
        if (i % 2 === 0) {
            html += '<tr align="center" class="odd">';
        }

        // 是否需要在列表左侧显示多选按钮列
        if (checkboxParam.needCheckbox === true) {
            html += '    <td ><input type=\'checkbox\' name=\'checkbox\' value="' + list[i][checkboxArgToUse]
                     + '" id=\'checkbox' + i + '\' onclick=\'checkAllSwitch(this)\'/></td>';
        }

        // 是否显示 序号 列
        if (sumParam.needSn === true) {   // alert( ( page - 1 ) * pageSize )
            var startRow = (page - 1) * pageSize;
            html += '    <td >' + (startRow + i + 1) + '</td>';
        }

        html += formatListContent(formatterParam, list[i], listTitleParam);

        // 生成 操作 那一列
        if (opColumnParam !== null) {    // printObject(opColumnParam)
            html += '    <td>';
            for (var item in opColumnParam) {
                if (opColumnParam.hasOwnProperty(item)) {
                    html += '<div>';
                    html += eval(opColumnParam[item] + '(' + JSON.stringify(list[i]) + ')');
                    html += '</div>';
                }
            }
            html += '    </td>';
        }
        html += '</tr>';
    }
    html += '</table>';

    html += '<div class="page">';
    html += '<div class="kkpager clearfix">';
    html += '<a id="pageFirst"  href="javascript:void(0)" onclick="goToPage(\'first\')" >首页</a>';
    html += '<a id="pagePrevious"   href="javascript:void(0)"';
    html += 'onclick="goToPage(\'previous\')" >上一页</a>';
    html += '<span class="curr">' + page + '</span>';
    html += '<a id="pageNext" href="javascript:void(0)" onclick="goToPage(\'next\')" >下一页</a>';
    html += '<a id="pageLast" href="javascript:void(0)" onclick="goToPage(\'last\')" >尾页</a>';
    html += '<span class="disabled" >共' + howManyPages + '页</span>';
    html += '<span class="disabled">每页' + pageSize + '条数据</span>';
    html += '<div class="clear"></div>';
    html += '</div>';

    html += '<div class="mid-page">';
    html += '<span class="two-text">';
    html += '<input type="button" class="go_alert button" value="确认"/>';
    html += '转到<input type="text" class="go_input"/>页</span>';
    html += '</div>';
    html += '<div class="clear"></div>';
    html += '</div>';

    // 展示列表;
    jqGet(divId).html(html);
    pagerClick(howManyPages);
}

// 全选列表左侧的多选按钮
function checkAll() {   // alert( jsGet( 'checkAll' ).checked +' ' + jqGet('checkAll').prop('checked') );
    if (jqGet('checkAll').prop('checked') === true) {
        $('input[name="checkbox"]').each(function () { // input[type='checkbox']
            $(this).prop('checked', true);
        });
    } else {
        $('input[name="checkbox"]').each(function () {
            $(this).prop('checked', false);
        });
    }
}

// 判断全选按钮是否需要被选中，并进行相应的切换
function checkAllSwitch(obj) {
    var statusOfThis = obj.checked;

    if (statusOfThis === false) {
        jsGet('checkAll').checked = false;
    } else {
        var flag = true;

        var checkboxName = 'checkbox';
        var arr = document.getElementsByName(checkboxName);

        for (var i = 0; i < arr.length; i++) {
            if (arr[i].checked === false) {
                flag = false;
            }
        }

        if (flag === false) {
            jsGet('checkAll').checked = false;
        } else {
            jsGet('checkAll').checked = true;
        }
    }
}

// 生成添加或修改对象要用到的表单
function generateTrTd(param) {    // param is json object
    var html = '<div class="container">';

    for (var key in param) {
        if (param.hasOwnProperty(key)) {    // printObject( typeof param[key] )
            html += '<div class="tr-one"><span>';
            html += '<label for="111">' + param[key] + ':</label>';
            html += '<input type=\'text\' id=\'' + key + '\' name=\'' + key + '\' class="input" />';
            html += '</span></div>';
            html += '<div class="tr-one"><span>';
            html += '<label for="111">' + param[key] + ':</label>';
            html += '<input type=\'text\' id=\'' + key + '\' name=\'' + key + '\' class="input" />';
            html += '</span></div>';
        }
    }
    html += '</div>';

    jsGet('divOfForm').innerHTML = html;
}

// 生成 查看对象要用到的表单
function generateTrTdForViewPage(param) {   // printObject( param )  // param is json object
    var html = '';
    for (var key in param) {
        if (param.hasOwnProperty(key)) {
            html += '<div class="tr-one"><span>';
            html += '<label class="big-label"  for=\'' + key + '\'>' + param[key]  + ':</label>';
            html += '<input type="text" class="input" name=\'' + key + '\' id=\'' + key + '\' ></span></div>';
        }
    }
    jsGet('divOfForm').innerHTML = html;
}

// 格式化 列表中每个单元格的内容
function formatListContent(formatterParam, listContent, listTitleParam) { // printObject( listContent  )
    var html = '';

    for (var key in listTitleParam) {
        if (listTitleParam.hasOwnProperty(key)) {
            var value = listContent[key];      // printObject( listContent );
            var formatterName = formatterParam[key];

            // listTitleParam 的 key 对应的值，如果是 null，在 listContent 里，就没有对应的键值对
            // 这种情况要显示一个 "—"
            if (isUndefined(value)) {
                value = '—';
            }

            if (value === '') {
                value = '—';
            }

            if (isUndefined(formatterName)) {
                html += '    <td>' + value + '</td>';
            } else {
                var result = eval(formatterName + '( value )');
                html += '    <td>' + result + '</td>';
            }
        }
    }
    return html;
}

// 格式化 资产计划状态
function planStatusFormatter(value) {
    if (value === 0) {
        return '待发行';
    } else if (value === 1) {
        return '<font color=\"blue\">已发行</font>';
    } else if (value === 2) {
        return '<font color=\"red\">撤销</font>';
    }
}

// 格式化 空字符串 或者 undefined，显示 '—'
function nullFormatter(value) {   // printObject(  value )
    if (isNull(value)) {
        return '—';
    } else {
        return value;
    }
}

// 格式化 日期和时间      yyyy年mm月dd日 点:分:秒
function dateTimeFormatter(date) {   // printObject( date  )
    var flag = isUndefined(date);

    if (flag !== true && date !== null) {      //  alert( date.time )
        var dateInput = new Date(date.time);    // printObject( dateInput )

        var year = dateInput.getFullYear();
        var month = dateInput.getMonth() + 1;
        var date = dateInput.getDate();
        var hour = dateInput.getHours();
        var minute = dateInput.getMinutes();
        var second = dateInput.getSeconds();

        if (month < 10) {
            month = '0' + month;
        }

        if (date < 10) {
            date = '0' + date;
        }

        if (hour < 10) {
            hour = '0' + hour;
        }

        if (minute < 10) {
            minute = '0' + minute;
        }

        if (second < 10) {
            second = '0' + second;
        }

        return year + '-' + month + '-' + date + '  ' + hour + ':' + minute + ':' + second;
    } else {
        return '';
    }
}

// 格式化 日期
function dateFormatter(date) {   // alert( date )
    if (!isNull(date)) {      //  alert( date.time )
        var dateInput = new Date(date.time); // printObject( dateInput )

        var year = dateInput.getFullYear();
        var month = dateInput.getMonth() + 1;
        var date = dateInput.getDate();

        if (month < 10) {
            month = '0' + month;
        }

        if (date < 10) {
            date = '0' + date;
        }
        return year + '-' + month + '-' + date;
    } else {
        return '';
    }
}

// 格式化 产品类型
function productTypeFormatter(type) {
    type = type.substring(0, 3);    // alert(type);

    if (type === 'DXJ') {
        return '度学金';
    } else if (type === 'DLQ') {
        return '度零钱';
    } else if (type === 'QNR') {
        return '去哪儿';
    } else if (type === 'BTB') {
        return '度贴吧';
    } else {
        return '其他';
    }
}

// 格式化 还款状态 -- 资产明细
function repayStatusFormatter(status) {  // alert(status)
    if (status === 'N') {
        return '未结清';
    } else if (status === 'Y') {
        return '结清';
    } else if (status === 'O') {
        return '逾期';
    } else {
        return status;
    }
}

// 格式化 还款状态 -- 分期明细
function repaymentStatusFormatter(status) {
    return repayStatusFormatter(status);
}

// 格式化 还款计划是否变更
function repayPlanChangeFormatter(status) {
    if (status === 1) {
        return '是';
    } else if (status === 2) {
        return '否';
    } else {
        return '—';
    }
}

// 格式化 是否贴息
function isDiscountFormatter(status) {   //  alert( status)
    if (status === 1) {
        return '不贴息';
    } else if (status === 0) {
        return '贴息';
    } else {
        return '—';
    }
}

// 格式化 性别   0=女  1=男
function genderFormatter(value) {   //  alert(value)
    if (value === 1) {
        return '男';
    } else if (value === 0) {
        return '女';
    } else {
        return '—';
    }
}

// 格式化 日期
function emptyStrFormatter(str) {
    if (str === '') {
        return '—';
    } else {
        return str;
    }
}

// 格式化 transferStatus 状态
function transferStatusFormatter(status) {
    if (status === 0) {
        return '未转让';
    } else if (status === 1) {
        return '待转让';
    } else if (status === 2) {
        return '已转让';
    }
}

function compareTimeAWithTimeB(strA, strB) {   // alert(strA+"   "+strB ) // 格式 2015-5-1 22:20
    var dateA = new Date(strA.replace(/-/g, '/'));
    var dateATimestamp = dateA.getTime();     // 获得 timestamp

    var dateB = new Date(strB.replace(/-/g, '/'));
    var dateBTimestamp = dateB.getTime();   // 获得 timestamp

    if (dateATimestamp > dateBTimestamp) {
        // alert("strA 大于 strB");
        return 1;
    } else if (dateATimestamp === dateBTimestamp) {
        // alert("strA 等于 strB");
        return 0;
    } else if (dateATimestamp < dateBTimestamp) {
        // alert("strA 小于 strB");
        return -1;
    }
}

// 获得今天的日期的字符串
function getDateStrOfToday() {
    return jsDateObjectFormatter(new Date());
}

// 格式化 js 的日期对象
function jsDateObjectFormatter(date) {   // alert( typeof date )
    var flag = isUndefined(date);

    if (flag !== true && date !== null) {      //  alert( date.time )
        var dateInput = date;          // printObject( dateInput )

        var year = dateInput.getFullYear();
        var month = dateInput.getMonth() + 1;
        var date = dateInput.getDate();

        if (month < 10) {
            month = '0' + month;
        }

        if (date < 10) {
            date = '0' + date;
        }
        return year + '-' + month + '-' + date;
    } else {
        return '';
    }
}

// 格式化 还款方式
function repayTypeFormatter(value) {
    if (value === 'PAYMENT01') {
        return '等额本金';
    } else if (value === 'PAYMENT02') {
        return '等额本息';
    } else if (value === 'PAYMENT03') {
        return '每期还息费，到期还本';
    } else if (value === 'PAYMENT12') {
        return '延期2月等额本金';
    } else if (value === 'PAYMENT13') {
        return '延期3月等额本金';
    } else if (value === 'PAYMENT16') {
        return '延期6月等额本金';
    } else if (value === 'PAYMENT17') {
        return '延期4月等额本金';
    } else if (value === '4P12P00') {
        return '前4个月还4%的本金，后12个月还96%的本金';
    } else if (value === '6012P07') {
        return '前6个月还利息，后12个月还本金和利息';
    } else if (value === '6P12P00') {
        return '前6个月还6%的本金，后12个月还94%的本金';
    } else if (value === '6P12P1288') {
        return '前6个月还12%的本金，后12个月还88%的本金';
    } else if (value === '6PI12PI017') {
        return '前6个月还1.98%的本金和利息，后12个月还98.02%的本金和利息';
    } else if (value === '6PI12PI09') {
        return '前6个月还3.6%的本金和利息，后12个月还96.4%的本金和利息';
    } else if (value === '4P12P00') {
        return '前4个月还4%的本金，后12个月还96%的本金';
    } else if (value === '6012P07') {
        return '前6个月还利息，后12个月还本金和利息';
    } else if (value === '6P12P00') {
        return '前6个月还6%的本金，后12个月还94%的本金';
    } else if (value === '6P12P1288') {
        return '前6个月还12%的本金，后12个月还88%的本金';
    } else if (value === '6PI12PI017') {
        return '前6个月还1.98%的本金和利息，后12个月还98.02%的本金和利息';
    } else if (value === '6PI12PI09') {
        return '前6个月还3.6%的本金和利息，后12个月还96.4%的本金和利息';
    } else {
        return '其他';
    }
}


// 模拟select
    $(document).bind('click', function (e) {
        var target  = $(e.target);
        if (target.closest('.son-ul, .select-box cite').length === 0) {
            $('.son-ul').hide();
        }
        e.stopPropagation();
    });
// 初始ul隐藏
    $('.son-ul').hide();
// 鼠标移动函数
    $('.select-box cite').click(function () {
// 找到ul.son-ul显示
        $(this).parent().find('ul.son-ul').toggle();
        $(this).parent().find('li').hover(function () {
            $(this).addClass('select-hover');
        },
// li的hover效果
    function () {
        $(this).removeClass('select-hover');
    });
        $(this).parent().hover(function () {},
        function () {
            $(this).parent().find('ul.son-ul').slideUp();
        });
    });
    $('ul.son-ul li').click(function () {
        $(this).parents('li').find('cite').html($(this).html());
        $(this).parents().prev().val($(this).attr('value'));
        var name = $(this).attr('value') + '';
        $(this).parents('li').find('ul').slideUp();
    });