var baseUrl = $("script[baseUrl]").attr('baseUrl');

$(function () {

    $('input[required]').before('<span style="color:red">*</span>');

    $.get(baseUrl + '/task/class.shtml', function (result) {
        var obj = eval(result);
        var option = $("<option>").val("").text("请选择");
        $("#ccid").append(option);

        if (obj != undefined) {
            for (var i = 0; i < obj.length; i++) {
                option = $("<option>").val(result[i].key).text(result[i].value);
                $("#ccid").append(option);
            }
        }
        $('#ccid option:first').attr("selected", true);
    });

    $("#addBtn").click(function () {
        $('#addtask').modal();
    });

    $("#saveBtn").click(function () {
        var ccid = $('#ccid').val(),
            taskproject = $('#taskproject').val(),
            homework = $('#homework').val();

        if ($.trim(ccid) == '') {
            return layer.msg('班级不能为空。'), !1;
        }


        if ($.trim(taskproject) == '') {
            return layer.msg('章节不能为空。'), !1;
        }

        if ($.trim(homework) == '') {
            return layer.msg('作业不能为空。'), !1;
        }

        return layer.confirm("确定保存", {
            icon: 3,
            title: "提示",
            btn: ['确定', '取消'],
        }, function () {
            var load = layer.load();
            $.post(baseUrl + '/task.shtml', {
                    ccid: ccid,
                    taskproject: taskproject,
                    homework: homework
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

    $("#saveBtn2").click(function () {
        var slen = $("#writtenid").val();
        var tid = $("#writtentime").val();

        var attendList = new Array();

        for (var i = 0; i < slen; i++) {
            attendList.push({utid: $("#stu_id_" + i).val(), taskgrade: $("#stu_atten_" + i).val()});
        }

        return layer.confirm("确定保存", {
            icon: 3,
            title: "提示",
            btn: ['确定', '取消'],
        }, function () {
            var load = layer.load();
            $.ajax({
                type: 'post',
                url: baseUrl + '/task/updateGrade/' + tid + '.shtml',
                data: JSON.stringify(attendList),
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

var qjcopid = 0;

function _openAddDlg(tid) {
    $('#addtaskaccomplish').modal();
    $("#writtentime").val(tid);

    var load = layer.load();
    $.get(baseUrl + '/task/getstudentname.shtml', {tid: tid }
        , function (result) {
            layer.close(load);
            var obj = eval(result.info);
            var strHtml = '<input type="hidden" id="slen" value="' + obj.length + '">';
            var opHtml = '<option value="1">优秀</option><option value="2">合格</option><option value="0">未交</option>';
            var count = 0;
            for (var i = 0; i < obj.length; i++) {
                if (i % 6 == 0) {
                    strHtml = strHtml + '<div class="row">';
                }
                strHtml = strHtml + '<div class="col-md-2"><div class="form-group">'
                    + '<label for="recipient-name" class="control-label">' + obj[i].uStudent.name + '</label>'
                    + '<input type="hidden" id="stu_id_' + i + '" value="' + obj[i].sid + '">'
                    + '<select class="form-control" id="stu_atten_' + i + '">' + opHtml + "</select>"
                    + '</div></div>';
                count++;
                if (count == 6 || i == (obj.length - 1)) {
                    strHtml = strHtml + '</div>';
                    count = 0;
                }
            }
            $("#boxRoleForm1").html(strHtml);
            $("#writtenid").val(obj.length);
        }, 'json');
}

function _getdetail(tid) {
    qjcopid = tid
    $('#taskDetails').modal();

    var format = function (time, format) {
        var t = new Date(time);
        var tf = function (i) {
            return (i < 10 ? '0' : '') + i
        };
        return format.replace(/yyyy|MM|dd|HH|mm|ss/g, function (tid) {
            switch (tid) {
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
    $.get(baseUrl + '/task/getstudenttask.shtml', {
            tid: tid
        }
        , function (result) {
            layer.close(load);
            var obj = eval(result.info);
            var strHtml = '';
            var count = 0;
            for (var i = 0; i < obj.length; i++) {
                if (count % 6 == 0) {
                    strHtml = strHtml + '<div class="row">';
                }
                strHtml = strHtml + '<div class="col-md-1"><div class="form-group">'
                    + '<label for="recipient-name" class="control-label">' + obj[i].uStudent.name + '</label>';
                if (obj[i].taskgrade == 1) {
                    strHtml = strHtml + '<span class="btn btn-success">优秀</span>';
                } else if (obj[i].taskgrade == 2) {
                    strHtml = strHtml + '<span class="	btn btn-warning">完成</span>';
                } else if (obj[i].taskgrade == 0) {
                    strHtml = strHtml + '<span class="btn btn-danger">未交</span>';
                }
                strHtml = strHtml + '</div></div>';

                count++;
                if (count == 6 || i == (obj.length - 1)) {
                    strHtml = strHtml + '</div>';
                    count = 0;
                }
            }
            var obj1 = eval(result.gradeinfo);
            var writtenstr = "<tr style='background-color: '><td><font size='4' color='#a52a2a'>完成人数</td> <td><font size='4' color='green'>" + obj1.qualified + "</td> <td><font size='4' color='#a52a2a'>合格率</td> <td><font size='4' color='green'>" + obj1.qualifiedrate * 100 + '%' + "</td><tr>";
            $("#showAttendanceForm").html(strHtml);
            $("#showAttendanceForm").append(writtenstr);

        }, 'json');

}

function _exportgrade() {
    /*alert($("#idtext").val());*/
    window.location.href = "${basePath}/task/exportgrade_" + qjcopid + ".shtml";
}

