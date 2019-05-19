var baseUrl = $("script[baseUrl]").attr('baseUrl');


$(function () {
    $("#addBtn").click(function () {
        $('#addUClass').modal();
    });

    $("#saveBtn").click(function () {
        var classNumError = false;
        var newClassZeroError = true;
        var newClassOutOfError = false;
        var grade = $('#grade').val();
        var jsonMessage = {grade: grade,mids:mids};
        /*var grnum = Number($('#grnum').val()),
            origrnum = Number($('#t0').text()),
            ydnum = Number($('#ydnum').val()),
            oriydnum = Number($('#t1').text()),
            uinum = Number($('#uinum').val()),
            oriuinum = Number($('#t2').text()),
            wxnum = Number($('#wxnum').val()),
            oriwxnum = Number($('#t3').text()),
            webnum = Number($('#webnum').val()),
            oriwebnum = Number($('#t4').text());*/
        //动态创建变量 命名规则{新增即输入框值:t+tid+num 原有班级数量:ori+tid+num}[命名所用tid为专业id]
        for (var i = 0; i < mids.length; i++) {
            window['t'+mids[i]+'num']= Number($('#t'+mids[i]+'num').val());
            window['ori'+mids[i]+'num']= Number($('#t'+mids[i]).text());

            var tinum = eval('t'+mids[i]+'num');
            var oriinum = eval('ori'+mids[i]+'num');

            jsonMessage['t'+mids[i]+'num'] = tinum;
            jsonMessage['ori'+mids[i]+'num'] = oriinum;

            if (isNaN(tinum) || $.trim(tinum) < 0) {
                classNumError = true;
            }
            if($.trim(tinum) != '0'){
                newClassZeroError = false;
            }
            if(tinum+oriinum>15){
                newClassOutOfError = true;
            }
        }
        if ($.trim(grade) == '') {
            return layer.msg('年级不能为空。'), !1;
        }

        if (classNumError) {
            return layer.msg('班级数量错误。'), !1;
        }
        if (newClassZeroError) {
            return layer.msg('无班级数量添加。'), !1;
        }
        if (newClassOutOfError) {
            return layer.msg('超出创建班范围。'), !1;
        }

        /*if (isNaN(grnum) || isNaN(ydnum) || isNaN(uinum) || isNaN(wxnum) || isNaN(webnum)) {
            return layer.msg('班级数量错误。'), !1;
        }
        if ($.trim(grnum) < 0 || $.trim(ydnum) < 0 || $.trim(uinum) < 0 || $.trim(wxnum) < 0 || $.trim(webnum) < 0) {
            return layer.msg('班级数量错误。'), !1;
        }
        if ($.trim(grnum + ydnum + uinum + wxnum + webnum) == '0') {
            return layer.msg('无班级数量添加。'), !1;
        }
        if (grnum + origrnum > 15 || ydnum + oriydnum > 15 || uinum + oriuinum > 15 || wxnum + oriwxnum > 15 || webnum + oriwebnum > 15) {
            return layer.msg('超出创建班范围。'), !1;
        }*/

        return layer.confirm("确定保存", {
            icon: 3,
            title: "提示",
            btn: ['确定', '取消']
        }, function () {
            var load = layer.load();
            $.post(baseUrl + '/divide.shtml', jsonMessage
                , function (result) {
                    layer.close(load);
                    if (result && result.status != 200) {
                        return layer.msg(result.message), !1;
                    }
                    layer.msg('保存成功。');
                    setTimeout(function () {
                        $('#formId').submit();
                    }, 1000);
                }, 'json');
        });

    });


    $("#saveBtn1").click(function () {
        var js = $('#js').val(),
            ds = $('#ds').val(),
            bzr = $('#bzr').val();
        if ($.trim(js + ds + bzr) == '') {
            return layer.msg('请至少选择一位老师'), !1;
        }
        return layer.confirm("确定保存", {
            icon: 3,
            title: "提示",
            btn: ['确定', '取消'],
        }, function () {
            var load = layer.load();
            $.post(baseUrl + '/divide/addTeacherClass.shtml', {
                    ccid: $("#hccid").val(), js: js, ds: ds, bzr: bzr
                }
                , function (result) {
                    layer.close(load);
                    if (result && result.status != 200) {
                        return layer.msg(result.message), !1;
                    }
                    layer.msg('保存成功。');
                    setTimeout(function () {
                        $('#formId').submit();
                    }, 1000);
                }, 'json');
        });
    });

    //选择需要移出的学生
    $("#shiftoutBtn").click(function () {
        var chk_value = [];
        $('input[name="shiftoutname"]:checked').each(function () {
            chk_value.push($(this).val());
        });
        $("#hcsid").val(chk_value);
        if (chk_value == '') {
            return layer.msg('请至少选择一位学生'), !1;
        }
        return layer.confirm("确定保存", {
            icon: 3,
            title: "提示",
            btn: ['确定', '取消'],
        }, function () {
            var load = layer.load();
            $.post(baseUrl + '/divide/shiftoutStudent.shtml', {
                chk_value: $("#hcsid").val(), cid: $("#hccid").val()
            }, function (result) {
                layer.close(load);
                if (result && result.status != 200) {
                    return layer.msg(result.message), !1;
                }
                layer.msg('移出成功。');
                setTimeout(function () {
                    $('#formId').submit();
                }, 1000);
            }, 'json');
        });
    });

    $("#shiftinBtn").click(function () {
        var chk_value = [];
        $('input[name="shiftinname"]:checked').each(function () {
            chk_value.push($(this).val());
        });
        $("#hcsid").val(chk_value);
        if (chk_value == '') {
            return layer.msg('请至少选择一位学生'), !1;
        }

        return layer.confirm("确定保存", {
            icon: 3,
            title: "提示",
            btn: ['确定', '取消'],
        }, function () {
            var load = layer.load();
            $.post(baseUrl + '/divide/shiftinStudent.shtml', {
                chk_value: $("#hcsid").val(),
                cid: $("#hccid").val()
            }, function (result) {
                layer.close(load);
                if (result && result.status != 200) {
                    return layer.msg(result.message), !1;
                }
                layer.msg('移入成功。');
                setTimeout(function () {
                    $('#formId').submit();
                }, 1000);
            }, 'json');
        });
    });

    $.get(baseUrl + '/employee/selectEmployee.shtml', function (result) {
        obj = eval(result);
        $("#techerClass").append($("<option>").val("").text("请选择"));
        for (var i = 0; i < obj.length; i++) {
            var option = $("<option>").val(result[i].key).text(result[i].value);
            $(".techerClass").append(option);
        }
        $('#techerClass option:first').attr("selected", true);
    });

    //设置年级下拉框
    $.get(baseUrl + '/divide/getGrade.shtml', function (resultclassinfo) {
        obj1 = eval(resultclassinfo);
        $("#grade").append($("<option>").val("").text("请选择"));
        for (var i = 0; i < obj1.length; i++) {
            var option = $("<option>").val(resultclassinfo[i].key).text(resultclassinfo[i].value);
            $("#grade").append(option);
        }
        $('#grade option:first').attr("selected", true);
    });

    //动态获取专业显示
    //各框id命名规则 现有班级数量:t+id 新增班级数量:t+id+id 预计班级总数量:t+id+id+id 输入框:t+id+num [命名所用id为该专业id]
    //故为减少代码及工作量,将所有专业id储存在数组中,以待上传时快速遍历 [可能存在bug]
    var mids = [];
    $.get(baseUrl + '/major/getMajor.shtml', function (resultclassinfo) {
        obj1 = eval(resultclassinfo);
        for (var i = 0; i < obj1.length; i++) {
            var t = resultclassinfo[i].key;
            mids.push(t);
            var tr = $("<tr><td>"+resultclassinfo[i].value+"</td><td><span id=t"+t+"></span></td><td><span id=t"+t+t+"></span></td><td><span id=t"+t+t+t+"></span></td></tr>");
            $("#majorList").append(tr);
            if(i%2==0){
                $("<div class=\"row\"></div>").appendTo($("#major_classNum"));
            }
            var label = $("<div class=\"col-md-1\"></div><div class=\"col-md-5\"><div class=\"form-group\"><div class=\"form-group\">"
                +"<label class=\"control-label\">"+resultclassinfo[i].value+":</label>"
                +"<input type=\"text\" maxlength=\"2\" class=\"form-control\" name=\"t"+t+"num\" id=\"t"+t+"num\" placeholder=\"请输入班级数量\" onblur=\"javascript:changnum("+t+")\"/>"
                + "</div></div></div>");
            $("#major_classNum .row :last").append(label);
        }
    });

    //下拉框点击事件
    $("#grade").bind("change", function () {
        _showclassCount($("#grade").val());
    });

    //全选
    $("#shiftinckb").click(function () {

        if ($("#shiftinckb").attr("checked")) {
            $("input[name='shiftinname']").attr("checked", "true");
        }
        else {
            $("input[name='shiftinname']").removeAttr("checked");
        }
    });

    //全选
    $("#shiftinckb_add").click(function () {
        var start_id = $("#startin_id").val();
        var end_id = $("#endin_id").val();
        if (start_id == "" || end_id == "") {
            return layer.msg('不能为空'), !1;
        }

        if (start_id <= end_id && start_id > 0) {
            $("input[name='shiftinname']").each(function (index, element) {
                if ((index >= (start_id - 1)) && (index <= (end_id - 1))) {
                    if ($("#shiftinckb_add").attr("checked")) {
                        $(this).attr("checked", "true");
                    }
                    else {
                        $(this).removeAttr("checked");
                    }
                }
            });
        }
        else {
            return layer.msg('输入的范围有误'), !1;
        }
    });

    //全选
    $("#shiftoutckb").click(function () {
        if ($("#shiftoutckb").attr("checked")) {
            $("input[name='shiftoutname']").attr("checked", "true");
        }
        else {
            $("input[name='shiftoutname']").removeAttr("checked");
        }
    });

    $("#shiftoutckb_add").click(function () {
        var start_id = $("#startout_id").val();
        var end_id = $("#endout_id").val();
        if (start_id == "" || end_id == "") {
            return layer.msg('不能为空'), !1;
        }

        if (start_id <= end_id && start_id > 0) {
            $("input[name='shiftoutname']").each(function (index, element) {
                if ((index >= (start_id - 1)) && (index <= (end_id - 1))) {
                    if ($("#shiftoutckb_add").attr("checked")) {
                        $(this).attr("checked", "true");
                    }
                    else {
                        $(this).removeAttr("checked");
                    }
                }
            });
        }
        else {
            return layer.msg('输入的范围有误'), !1;
        }
    });
    $("#select_LikeName").change(function () {
        $('#haveClassStudnetname').empty();
        var ccid = $("#hccid").val();
        var name = $("#select_LikeName").val();
        //显示此班中的所有学生
        $.get(baseUrl + '/divide/fs.shtml', {ccid: ccid, name: name}, function (result) {
            obj = eval(result);
            var count = 0;
            var strHtml = "";
            if (obj.length === 0) {
                strHtml = strHtml + cname + '没有学生';
            }
            for (var i = 0; i < obj.length; i++) {
                if (i % 6 === 0) {
                    strHtml = strHtml + '<div class="row">';
                }
                strHtml = strHtml + '<div class="col-md-2"><div class="checkbox"><b>' + (i + 1) + '</b>&nbsp';
                strHtml = strHtml + '<label><input type="checkbox" name="shiftoutname" id="' + obj[i].sid + '" value="' + obj[i].sid + '">' + obj[i].name + '</label>';
                strHtml = strHtml + '</div></div>';
                count++;
                if (count === 6 || i === (obj.length - 1)) {
                    strHtml = strHtml + '</div>';
                    count = 0;
                }
            }
            $("#haveClassStudnetname").html(strHtml);
        });
    });
    $("#select_LikeName2").change(function () {
        $('#withoutClassStudnetname').empty();
        var mid = $("#mid").val();
        var name = $("#select_LikeName2").val();
        //显示没有班级的学生
        $.get(baseUrl + '/divide/fs.shtml', {mid: mid,name:name}, function (result) {
            obj = eval(result);
            var count = 0;
            var strHtml = "";

            if (obj.length === 0) {
                strHtml = strHtml + '没有待分班学生';
            }

            for (var i = 0; i < obj.length; i++) {
                if (i % 6 === 0) {
                    strHtml = strHtml + '<div class="row">';
                }
                strHtml = strHtml + '<div class="col-md-2"><div class="checkbox"><b>' + (i + 1) + '</b>&nbsp';
                strHtml = strHtml + '<label><input type="checkbox" name="shiftinname" id="' + obj[i].sid + '" value="' + obj[i].sid + '">' + obj[i].name + '</label>';
                strHtml = strHtml + '</div></div>';
                count++;
                if (count === 6 || i === (obj.length - 1)) {
                    strHtml = strHtml + '</div>';
                    count = 0;
                }
            }
            $("#withoutClassStudnetname").html(strHtml);
        });
    });
});

//显示班级数方法
function _showclassCount(a) {
    $.get(baseUrl + '/divide/getClassCount.shtml', {grade: a}, function (resultclasscount) {
        obj2 = eval(resultclasscount);
        for (var i = 0; i < obj2.length; i++) {
            var t = resultclasscount[i].key;
            $("#t" + t).text(resultclasscount[i].value);
            $("#t" + t + t).text(0);
            $("#t" + t + t + t).text(resultclasscount[i].value);
            $("#t"+t+"num").val("");
        }
    });


}

//班级学生对话框
function _showStudentDlg(ccid, cname) {
    $('#studentClass').modal();
    $("#hccid").val(ccid);
    $("#classname").html(cname);
    //显示此班中的所有学生
    $.get(baseUrl + '/divide/fs.shtml', {ccid: ccid}, function (result) {
        obj = eval(result);
        var count = 0;
        var strHtml = "";
        for (var i = 0; i < obj.length; i++) {
            if (i % 6 == 0) {
                strHtml = strHtml + '<div class="row">';
            }
            strHtml = strHtml + '<div class="col-md-2"><div class="checkbox"><b>' + (i + 1) + '</b>&nbsp';
            strHtml = strHtml + '<label>' + obj[i].name + '</label>';
            strHtml = strHtml + '</div></div>';
            count++;
            if (count == 6 || i == (obj.length - 1)) {
                strHtml = strHtml + '</div>';
                count = 0;
            }
        }
        $("#classStudnetname").html(strHtml);
    });
}

//打开选择学生对话框
function _openoutStudentDlg(ccid, cname) {
    $('#addStudentClass').modal();
    $("#startout_id").val("");
    $("#endout_id").val("");
    $("#select_LikeName").val("");
    $("#shiftoutckb_add").removeAttr("checked");
    $("#shiftoutckb").removeAttr("checked");
    $("#hccid").val(ccid);
    $("#classname").html(cname);
    //显示此班中的所有学生
    $.get(baseUrl + '/divide/fs.shtml', {ccid: ccid}, function (result) {
        obj = eval(result);
        var count = 0;
        var strHtml = "";
        if (obj.length === 0) {
            strHtml = strHtml + cname + '没有学生';
        }
        for (var i = 0; i < obj.length; i++) {
            if (i % 6 == 0) {
                strHtml = strHtml + '<div class="row">';
            }
            strHtml = strHtml + '<div class="col-md-2"><div class="checkbox"><b>' + (i + 1) + '</b>&nbsp';
            strHtml = strHtml + '<label><input type="checkbox" name="shiftoutname" id="' + obj[i].sid + '" value="' + obj[i].sid + '">' + obj[i].name + '</label>';
            strHtml = strHtml + '</div></div>';
            count++;
            if (count == 6 || i == (obj.length - 1)) {
                strHtml = strHtml + '</div>';
                count = 0;
            }
        }
        $("#haveClassStudnetname").html(strHtml);
    });
}

//删除班级
function _deleteUClass(ccid) {
    return layer.confirm("将同时删除该班级下的所有学生", {
        icon: 3,
        title: "提示",
        btn: ['确定', '取消']
    }, function () {
        layer.confirm("将同时删除该班级及其所有学生", {
            icon: 0,
            title: "警告",
            btn: ['确定', '取消']
        }, function () {
            var load = layer.load();
            $.post(baseUrl + '/divide/delUClass.shtml', {
                ccid:ccid
            }, function (result) {
                layer.close(load);
                if (result && result.status != 200) {
                    return layer.msg(result.message), !1;
                }
                layer.msg('删除成功。');
                setTimeout(function () {
                    $('#formId').submit();
                }, 1000);
            }, 'json');
        });
    });
}

function _openinStudentDlg(ccid, mid) {
    $('#withoutStudentClass').modal();
    $("#startin_id").val("");
    $("#endin_id").val("");
    $("#select_LikeName2").val("");
    $("#shiftinckb_add").removeAttr("checked");
    $("#shiftinckb").removeAttr("checked");

    $("#hccid").val(ccid);
    $("#mid").val(mid);
    //显示没有班级的学生
    $.get(baseUrl + '/divide/fs.shtml', {mid: mid}, function (result) {
        obj = eval(result);
        var count = 0;
        var strHtml = "";

        if (obj.length == 0) {
            strHtml = strHtml + '没有待分班学生';
        }

        for (var i = 0; i < obj.length; i++) {
            if (i % 6 == 0) {
                strHtml = strHtml + '<div class="row">';
            }
            strHtml = strHtml + '<div class="col-md-2"><div class="checkbox"><b>' + (i + 1) + '</b>&nbsp';
            strHtml = strHtml + '<label><input type="checkbox" name="shiftinname" id="' + obj[i].sid + '" value="' + obj[i].sid + '">' + obj[i].name + '</label>';
            strHtml = strHtml + '</div></div>';
            count++;
            if (count == 6 || i == (obj.length - 1)) {
                strHtml = strHtml + '</div>';
                count = 0;
            }
        }
        $("#withoutClassStudnetname").html(strHtml);

    });
};

//打开选择老师对话框
function _openTeacherDlg(ccid) {

    $('#addTeacherClass').modal();
    $("#hccid").val(ccid);
    //回显
    $.get(baseUrl + '/divide/ft.shtml', {ccid: ccid}, function (result) {
        obj = eval(result);
        if (obj.js != "") {
            var all_select = $("#js > option");
            for (var i = 0; i < all_select.length; i++) {

                var svalue = all_select[i].value;
                if (obj.js == svalue) {  //取select中所有的option的值与其进行对比，相等则令这个option添加上selected属性
                    $("#js option[value=" + obj.js + "]").attr("selected", "selected");
                }
            }
        }
        if (obj.ds != "") {
            var all_select = $("#ds > option");
            for (var i = 0; i < all_select.length; i++) {

                var svalue = all_select[i].value;
                if (obj.ds == svalue) {  //取select中所有的option的值与其进行对比，相等则令这个option添加上selected属性
                    $("#ds option[value=" + obj.ds + "]").attr("selected", "selected");
                }
            }
        }
        if (obj.bzr != "") {
            var all_select = $("#bzr > option");
            for (var i = 0; i < all_select.length; i++) {

                var svalue = all_select[i].value;
                if (obj.bzr == svalue) {  //取select中所有的option的值与其进行对比，相等则令这个option添加上selected属性
                    $("#bzr option[value=" + obj.bzr + "]").attr("selected", "selected");
                }
            }
        }
    });

}
function changnum(t) {
    var num = Number($("#t"+t+"num").val());
    if (num < 0 || isNaN(num)) {
        return layer.msg('班级数量错误。'), !1;
    }
    var oldNum = $("#t" + t).html();
    $("#t" + t + t).html(num);
    var sum = parseInt(num) + parseInt(oldNum);
    $("#t" + t + t + t).html(sum);
}
