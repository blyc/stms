var baseUrl = $("script[baseUrl]").attr('baseUrl');

$(function () {
    $('.form_date').datetimepicker({
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 3,
        minView: 3,
        forceParse: 0
    });
    $("#saveBtn").click(function () {
        var len = $("#writtenlen").val();
        var ceid = $("#writtenid").val();
        var reviewok = false;
        var gradeList = new Array();

        if(len==0){
            return layer.msg('没有数据，不能保存。'), !1;
        }


        for (var i = 0; i < len; i++) {
            var id = $("#spid_" + i).val();
            if ($("#ck_" + id).is(':checked')) {
                if ($("#ckok_" + id).is(':checked')) {
                    reviewok = true;
                }
                else {
                    reviewok = false;
                }
                gradeList.push({
                    spid: $("#spid_" + i).val(),
                    reviewGrade: $("#grade_" + id).val(),
                    reviewOk: reviewok,
                    remake: $("#remake_" + id).val()
                })
            }
        }
        return layer.confirm("确定保存", {
            icon: 3,
            title: "提示",
            btn: ['确定', '取消'],
        }, function () {
            var load = layer.load();
            $.ajax({
                type: 'post',
                url: baseUrl + '/quarter/projectlist/' + ceid + '.shtml',
                data: JSON.stringify(gradeList),
                dataType: 'json',
                contentType: 'application/json;charset=utf-8',
                success: function (result) {
                    layer.close(load);
                    if (result && result.status != 200) {
                        return layer.msg(result.message), !1;
                    }
                    layer.msg('保存成功。');
                    setTimeout(function () {
                        $('#formId').submit();
                    }, 1000);
                }
            });
        });
    });


});
/* 查看项目成绩详情*/
function _addetail(cpid) {
    $("#projectDetails").modal();
    $("#writtenid").val(cpid);
    var load = layer.load();
    $.get(baseUrl + "/project/getprojectdetails.shtml", {cpid: cpid},
        function (result) {
            var obj = eval(result.info);
            var count = 0;
            var str = "";
            for (var i = 0; i < obj.length; i++) {
                if (obj[i].uStudent != null) {
                    str = str + '<tr><td style="width: 70px">' + obj[i].uStudent.name + '</td>';
                }
                else{
                    str = str + '<tr><td style="width: 70px"></td>';
                }
                if (obj[i].vsubmit == 1) {
                    str = str + '<td style="width: 50px">是</td>';
                }
                else {
                    str = str + '<td style="width: 50px ; color: red">否</td>';
                }
                str = str + '<td>' + obj[i].vaddr + '</td><td>' + obj[i].vtime + '</td>';

                if (obj[i].vpass == 1) {
                    str = str + '<td style="width: 50px">是</td>';
                }
                else {
                    str = str + '<td style="width: 50px; color: red">否</td>';
                }
                if (obj[i].codeaddr != null) {
                    str = str + '<td>' + obj[i].codeaddr + '</td>';
                }
                else {
                    str = str + '<td></td>';
                }
                if (obj[i].coderate != null) {
                    str = str + '<td>' + (obj[i].coderate*100) + '%</td>';
                }
                else {
                    str = str + '<td></td>';
                }
                if (obj[i].remark != null) {
                    str = str + '<td style="width: 110px">' + obj[i].remark + '</td></tr>';
                }
                else {
                    str = str + '<td style="width: 110px"></td>';
                }
                str = str + "<td style='width: 80px'><div class='checkbox'><label><input type='checkbox' class='checkbox' id='ck_" + obj[i].spid + "' onclick='javascript:chk1(" + obj[i].spid + ")'> 抽查</label></div></td>";
                str = str + "<td style='width: 80px'><div class='checkbox'><label><input type='checkbox' class='checkbox' id='ckok_" + obj[i].spid + "' disabled  onclick='javascript:chk2(" + obj[i].spid + "," + obj[i].examgrade + ")'> 合格</label></div></td>";
                str = str + "<input type='hidden'  id='grade_" + obj[i].spid + "' class='form-control' disabled style='width:100px' placeholder='成绩'>";
                str = str + "<td><input type='text'  id='remake_" + obj[i].spid + "' class='form-control' disabled placeholder='审核情况'><input type='hidden' id='spid_" + i + "' value='" + obj[i].spid + "'></td></tr> ";
            }
            $("#showCount").html(str);
            $("#writtenlen").val(obj.length);
            layer.close(load);
        }, 'json');
}

function _getdetail(a) {
    $("#showDetails").modal();
    var load = layer.load();
    $.get(baseUrl + '/quarter/getReviewProejctDetails.shtml', {rpid: a}
        , function (result) {
            layer.close(load);
            var obj = eval(result.info);
            var str = "";
            for (var i = 0; i < obj.length; i++) {
                str = str + " <tr>";
                if (obj[i].uStudent != null) {
                    str = str + "<td style='width: 100px'>" + obj[i].uStudent.name + "</td> ";
                }
                else {
                    str = str + "<td style='width: 100px'></td> ";
                }

                if (obj[i].uStudentProject.vsubmit == 1) {
                    str = str + '<td style="width: 50px">是</td>';
                }
                else {
                    str = str + '<td style="width: 50px ; color: red">否</td>';
                }
                str = str + '<td>' + obj[i].uStudentProject.vtime + '</td>';

                if (obj[i].uStudentProject.vpass == 1) {
                    str = str + '<td style="width: 50px">是</td>';
                }
                else {
                    str = str + '<td style="width: 50px; color: red">否</td>';
                }
                if (obj[i].reviewOk) {
                    str = str + "<td style='width: 110px'>合格</td>";
                }
                else {
                    str = str + "<td style='width: 110px'>不合格</td>";
                }
                str = str + "<td>" + obj[i].remake + "</td> ";
            }
            $("#showDetailtb").html(str);

        }, 'json');

}

function chk1(id) {
    if ($("#ck_" + id).is(':checked')) {
        $("#ckok_" + id).removeAttr("disabled");
        $("#grade_" + id).removeAttr("disabled");
        $("#remake_" + id).removeAttr("disabled");
    }
    else {
        $("#ckok_" + id).attr("disabled", "disabled");
        $("#ckok_" + id).attr("checked", false);
        $("#grade_" + id).attr("disabled", "disabled");
        $("#grade_" + id).val("");
        $("#remake_" + id).attr("disabled", "disabled");
        $("#remake_" + id).val("");
    }
}

function chk2(id, grade) {
    if ($("#ckok_" + id).is(':checked')) {
        $("#grade_" + id).val(100);
    }
    else {
        $("#grade_" + id).val("");
    }
}

