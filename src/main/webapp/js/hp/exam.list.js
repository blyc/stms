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

    $.get(baseUrl + '/exam/class.shtml', function (result) {
        var obj = eval(result);
        var option = $("<option>").val("").text("请选择");
        $("#ccid").append(option);

        if (obj != undefined) {
            for (var i = 0; i < obj.length; i++) {
                var option = $("<option>").val(result[i].key).text(result[i].value);
                $("#ccid").append(option);
            }
        }
        $('#ccid option:first').attr("selected", true);
    });

    $("#addBtn").click(function () {
        $('#addClassExam').modal();
    });

    $("#saveBtn").click(function () {
        var ccid = $('#ccid').val(),
            examnum = $('#examnum').val(),
            rateStandard = $('#rateStandard').val(),
            // examtype = $('#examtype').val(),
            examsite = $('#examsite').val(),
            examtime = $('#examtime').val(),
            examproject = $('#examproject').val(),
            invigilator = $('#invigilator').val();

        if ($.trim(examsite) == '') {
            return layer.msg('考试地点不能为空。'), !1;
        }
        if ($.trim(examproject) == '') {
            return layer.msg('考试科目不能为空。'), !1;
        }
        if ($.trim(invigilator) == '') {
            return layer.msg('监考人不能为空。'), !1;
        }

        if ($.trim(examtime) == '') {
            return layer.msg('考试时间不能为空。'), !1;
        }

        if ($.trim(rateStandard) == '') {
            return layer.msg('考试合格分数不能为空。'), !1;
        }

        if (rateStandard < 0 || rateStandard > 100) {
            return layer.msg('考试合格分数范围不对。'), !1;
        }

        return layer.confirm("确定保存", {
            icon: 3,
            title: "提示",
            btn: ['确定', '取消'],
        }, function () {
            var load = layer.load();
            $.post(baseUrl + '/exam.shtml', {
                    ccid: ccid,
                    examnum: examnum,
                    // examtype: examtype,
                    examtime: examtime,
                    examsite: examsite,
                    invigilator: invigilator,
                    examproject: examproject,
                    rateStandard: rateStandard
                }
                , function (result) {
                    layer.close(load);
                    if (result.selectDate == 1) {
                        return layer.msg(result.message), !1;
                    }
                    else {
                        if (result && result.status != 200) {
                            return layer.msg(result.message), !1;
                        }
                        layer.msg('保存成功。');
                        setTimeout(function () {
                            $('#formId').submit();
                        }, 1000);
                    }
                }, 'json');
        });
    });

    $("#saveBtn1").click(function () {
        var len = $("#writtenid").val(),
            ceid = $("#writtentime").val();
        var gradeList = new Array();
        for (var i = 0; i < len; i++) {
            gradeList.push({
                useid: $("#use_id_" + i).val(),
                examgrade: $("#grade_id_" + i).val(),
                eaddr: $("#eaddr_" + i).val()
            })
        }

        return layer.confirm("确定保存", {
            icon: 3,
            title: "提示",
            btn: ['确定', '取消'],
        }, function () {
            var load = layer.load();
            $.ajax({
                type: 'post',
                url: baseUrl + '/studentexamlist/' + ceid + '.shtml',
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

    $("#saveBtn2").click(function () {
        var count = $("#examevent").val();

        return layer.confirm("确定保存", {
            icon: 3,
            title: "提示",
            btn: ['确定', '取消'],
        }, function () {
            var load = layer.load();
            $.post(baseUrl + '/exam/addExamEvent.shtml', {ceid: $("#ceid").val(), examevent: $("#examevent").val()}
                , function (result) {
                    layer.close(load);
                    if (result && result.status != 200) {
                        return layer.msg(result.message), !1;
                    }
                    layer.msg('保存成功。');
                    setTimeout(function () {
                        $('#formId').submit();
                    }, 1000);
                });
        });
    });
    $("#btnImport").click(function () {
            var f = $("#importFile").val();
            if (f == "") {
                return layer.msg('导入文件不能为空。'), !1;
            }
            return layer.confirm("确定保存", {
                icon: 3,
                title: "提示",
                btn: ['确定', '取消'],
            }, function () {
                var load = layer.load();
                $("#importForm").submit();
                $("#btnImport").attr('disabled', true);
                return true;
            });
        }
    );
});

function _show_import_dlg(a) {
    $("#importceid").val(a);
    $("#importExam").modal();
}

function _show_class_num() {
    var ccid = $("#ccid  option:selected").val();
    if (ccid != null && ccid != "") {
        $.get(baseUrl + '/exam/classinfo.shtml', {id: ccid}, function (result) {
            var obj = eval(result);
            $("#examnum").val(obj.num);
            $("#umajorname").val(obj.uMajor.name);
        });
    }
}

function _getgrade(ceid) {
    $('#writtenGrade').modal();
    $("#writtentime").val(ceid);
    var load = layer.load();
    $.get(baseUrl + '/studentexamlist/getStudentName.shtml', {ceid: ceid}
        , function (result) {
            layer.close(load);
            var obj = eval(result.info);
            var str = "";
            var count = 0;

            for (var i = 0; i < obj.length; i++) {
                str = str + '<div class="row"><div class="col-md-12"><div class="input-group">'
                    + '<span class="input-group-addon" id="basic-addon3" style="width:85px;"><b>' + (i + 1) + '</b>&nbsp' + obj[i].uStudent.name + '</span>'
                    + '<input type="hidden" id="use_id_' + i + '" value="' + obj[i].useid + '">'
                    + ' <input type="text" class="form-control" id="grade_id_' + i + '" placeholder="考试成绩" style="width: 100px">'
                    + ' <input type="text" class="form-control"name="eaddr_info" id="eaddr_' + i + '" placeholder="试卷提交地址" style="width:500px;" maxlength="256" >'
                    + '</div></div></div><br>';
            }

            $("#written").html(str);
            $("#writtenid").val(obj.length);
        }, 'json');
}

function _getdetail(a) {
    $('#examDetails').modal();
    var format = function (time, format) {
        var t = new Date(time);
        var tf = function (i) {
            return (i < 10 ? '0' : '') + i
        };
        return format.replace(/yyyy|MM|dd|HH|mm|ss/g, function (a) {
            switch (a) {
                case 'yyyy':
                    return tf(t.getFullYear());
                    break;
                case 'MM':
                    return tf(t.getMonth() + 1);
                    break;
                case 'mm':
                    return tf(t.getMinutes());
                    break;
                case 'dd':
                    return tf(t.getDate());
                    break;
                case 'HH':
                    return tf(t.getHours());
                    break;
                case 'ss':
                    return tf(t.getSeconds());
                    break;
            }
        })
    }
    var load = layer.load();
    $.get(baseUrl + '/studentexamlist/getExamDetails.shtml', {ceid: a}
        , function (result) {
            layer.close(load);
            var obj = eval(result.info);
            var str = "";
            for (var i = 0; i < obj.length; i++) {
                str = str + " <tr>";
                str = str + "<td colspan='2'>" + obj[i].uStudent.name + "</td> ";
                if (obj[i].examgrade < obj[i].uClassExam.rateStandard) {
                    str = str + "<td colspan='2' style='color: red'>" + obj[i].examgrade + "</td> ";
                }
                else {
                    str = str + "<td colspan='2'>" + obj[i].examgrade + "</td> ";
                }
                str = str + "<td colspan='4'>" + obj[i].eaddr + "</td> ";
                if (format(obj[i].registerexam, 'yyyy/MM/dd') == "1970/01/01") {
                    str = str + "<td colspan='2'>未设置</td> </tr>";
                }
                else {
                    str = str + "<td colspan='2'>" + format(obj[i].registerexam, 'yyyy/MM/dd') + "</td> </tr>";
                }

                $("#idtext").val(obj[i].ceid);
            }
            var obj1 = eval(result.gradeinfo);
            var writtenstr = "<tr style='background-color: lightcyan'><td>最高分</td><td>" + obj1.hightgrade + "</td> <td>最低分</td> <td>" + obj1.lowgrade + "</td> <td>合格人数</td> <td>" + obj1.qualified + "</td>  <td>平均分</td> <td>" + obj1.avggrade + "</td>< <td>合格率</td> <td>" + obj1.qualifiedrate + '%' + "</td><tr>";
            $("#showCount").html(str);
            $("#showCount").append(writtenstr);

        }, 'json');

}
function _getevent(ceid) {
    $('#writeEvent').modal();
    $('#ceid').val(ceid);
}

$(function () {
    _initPopover();
})

//根据ID数组，删除
function _delete(ids) {
    var index = layer.confirm("确定这" + ids.length + "个数据？", function () {
        var load = layer.load();
        $.post(baseUrl + '/exam/deleteExam.shtml', {ids: ids.join(',')}, function (result) {
            layer.close(load);
            if (result && result.status != 200) {
                return layer.msg(result.message, so.default), !0;
            } else {
                layer.msg('删除成功');
                setTimeout(function () {
                    $('#formId').submit();
                }, 1000);
            }
        }, 'json');
        layer.close(index);
    });
}