var baseUrl = $("script[baseUrl]").attr('baseUrl');
$(document).ready(function () {
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
    $('input[required]').before('<span style="color:red">*</span>');

    $("#saveBtn").click(function () {
        var len = $("#writtenlen").val();
        var ceid = $("#writtenid").val();
        var reviewok = false;
        var gradeList = new Array();
        for (var i = 0; i < len; i++) {
            var id = $("#use_id_" + i).val();
            if ($("#ck_" + id).is(':checked')) {
                if ($("#ckok_" + id).is(':checked')) {
                    reviewok = true;
                }
                else {
                    reviewok = false;
                }
                gradeList.push({
                    useid: $("#use_id_" + i).val(),
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
                url: baseUrl + '/quarter/examlist/' + ceid + '.shtml',
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
function _addetail(a) {
    $('#examDetails').modal();
    $("#writtenid").val(a);

    var load = layer.load();
    $.get(baseUrl + '/studentexamlist/getExamDetails.shtml', {ceid: a}
        , function (result) {
            layer.close(load);
            var obj = eval(result.info);
            var str = "";
            for (var i = 0; i < obj.length; i++) {
                str = str + " <tr>";
                str = str + "<td style='width: 80px'>" + obj[i].uStudent.name + "</td> ";
                if (obj[i].examgrade < 60) {
                    str = str + "<td style='color: red;width: 50px'>" + obj[i].examgrade + "</td> ";
                }
                else {
                    str = str + "<td style='width: 50px'>" + obj[i].examgrade + "</td> ";
                }
                str = str + "<td>" + obj[i].eaddr + "</td> ";
                str = str + "<td style='width: 100px'><div class='checkbox'><label><input type='checkbox' class='checkbox' id='ck_" + obj[i].useid + "' onclick='javascript:chk1(" + obj[i].useid + ")'> 是否抽查</label></div></td>";
                str = str + "<td style='width: 100px'><div class='checkbox'><label><input type='checkbox' class='checkbox' id='ckok_" + obj[i].useid + "' disabled  onclick='javascript:chk2(" + obj[i].useid + "," + obj[i].examgrade + ")'> 是否合格</label></div></td>";
                str = str + "<td  style='width: 80px'><input type='text'  id='grade_" + obj[i].useid + "' class='form-control' disabled  placeholder='成绩'></td> ";
                str = str + "<td style='width: 250px'><input type='text'  id='remake_" + obj[i].useid + "' class='form-control' disabled placeholder='审核情况'><input type='hidden' id='use_id_" + i + "' value='" + obj[i].useid + "'></td></tr> ";
            }
            $("#showCount").html(str);
            $("#writtenlen").val(obj.length);
        }, 'json');
}


function _getdetail(a) {
    $("#showDetails").modal();
    var load = layer.load();
    $.get(baseUrl + '/quarter/getReviewExamDetails.shtml', {reid: a}
        , function (result) {
            layer.close(load);
            var obj = eval(result.info);
            var str = "";
            for (var i = 0; i < obj.length; i++) {
                str = str + " <tr>";
                str = str + "<td style='width: 100px'>" + obj[i].uStudent.name + "</td> ";
                if (obj[i].examgrade < 60) {
                    str = str + "<td style='color: red ; width: 110px'>" + obj[i].uStudentExam.examgrade + "</td> ";
                }
                else {
                    str = str + "<td style='width: 110px'>" + obj[i].uStudentExam.examgrade + "</td> ";
                }
                if (obj[i].reviewOk) {
                    str = str + "<td style='width: 110px'>合格</td>";
                }
                else {
                    str = str + "<td style='width: 110px'>不合格</td>";
                }
                str = str + "<td  style='width: 110px'>" + obj[i].reviewGrade + "</td> ";
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
        $("#grade_" + id).val(grade);
    }
    else {
        $("#grade_" + id).val("");
    }
}


$(function () {
    _initPopover();
})