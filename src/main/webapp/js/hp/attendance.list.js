var baseUrl = $("script[baseUrl]").attr('baseUrl');

$(function () {
    $('.form_date').datetimepicker({
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0
    });
    $.get(baseUrl + '/attendance/class.shtml', function (result) {
        var obj = eval(result);
        $("#ccid").append($("<option>").val("").text("请选择"));
        if (obj != undefined) {
            for (var i = 0; i < obj.length; i++) {
                var option = $("<option>").val(result[i].key).text(result[i].value);
                $("#ccid").append(option);
            }
        }
        $('#ccid option:first').attr("selected", true);
    });

    $("#addBtn").click(function () {
        $('#addClassAttendance').modal();
    });

    $("#saveBtn1").click(function () {
        var ccid = $("#ccid").val();
        var section = $("#section").val();
        if ($.trim(ccid) == '') {
            return layer.msg('班级不能为空。'), !1;
        }
        if ($.trim(section) == '') {
            return layer.msg('章节不能为空。'), !1;
        }
        return layer.confirm("确定保存", {
            icon: 3,
            title: "提示",
            btn: ['确定', '取消'],
        }, function () {
            var load = layer.load();
            $.post(baseUrl + '/attendance.shtml', {
                ccid: $("#ccid").val(), section: $("#section").val()
            }, function (result) {
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

    $("#saveBtn2").click(function () {
        var caid = $("#hidecaid").val();
        var slen = $("#slen").val();
        var attendList = new Array();
        for (var i = 0; i < slen; i++) {
            attendList.push({sid: $("#stu_id_" + i).val(), record: $("#stu_atten_" + i).val()});
        }

        if (slen == 0) {
            return layer.msg('该班级还没有学生。'), !1;
        }

        return layer.confirm("确定保存", {
            icon: 3,
            title: "提示",
            btn: ['确定', '取消'],
        }, function () {
            var load = layer.load();
            $.ajax({
                type: 'post',
                url: baseUrl + '/attendance/' + caid + '.shtml',
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

    $("#saveBtn3").click(function () {
        var hideremarkscaid = $("#hideremarkscaid").val();
        var remarksSlen = $("#remarksSlen").val();
        var remarksList = new Array();
        for (var i = 0; i < remarksSlen; i++) {
            remarksList.push({caid:hideremarkscaid,sid: $("#remarks_sid_" + i).val(),record:$("#remarks_record_" + i).val(), remarks: $("#remarks_detail_" + i).val()});
        }

        return layer.confirm("确定保存", {
            icon: 3,
            title: "提示",
            btn: ['确定', '取消'],
        }, function () {
            var load = layer.load();
            $.ajax({
                type: 'post',
                url: baseUrl + '/attendance/remarks' + hideremarkscaid + '.shtml',
                data: JSON.stringify(remarksList),
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


function _open2(ccid, caid) {
    $('#addAttendance').modal();
    $.get(baseUrl + '/attendance/getClassStudent.shtml', {ccid: ccid}
        , function (result) {
            if (result && result.status != 200) {
                return layer.msg(result.message), !1;
            }
            var obj = result.entity;
            var strHtml = "";
            var opHtml = '<option value="1">出勤</option><option value="2">迟到</option><option value="3">旷课</option><option value="4">请假</option><option value="5">早退</option>';
            var count = 0;
            strHtml = strHtml + '<input type="hidden" id="slen" value="' + obj.length + '">';
            for (var i = 0; i < obj.length; i++) {
                if (i % 6 == 0) {
                    strHtml = strHtml + '<div class="row">';
                }
                strHtml = strHtml + '<div class="col-md-2"><div class="form-group"><b>'+(i+1)+'</b>&nbsp';
                strHtml = strHtml + '<label for="recipient-name" class="control-label">' + obj[i].name + '</label>';
                strHtml = strHtml + '<input type="hidden" id="stu_id_' + i + '" value="' + obj[i].sid + '">';
                strHtml = strHtml + '<select class="form-control" id="stu_atten_' + i + '">' + opHtml + "</select>";
                strHtml = strHtml + '</div></div>';
                count++;
                if (count == 6 || i == (obj.length - 1)) {
                    strHtml = strHtml + '</div>';
                    count = 0;
                }
            }
            $("#boxRoleForm").html(strHtml);
            $("#hidecaid").val(caid);
        }, 'json');
}

function _get_detail(caid) {
    $('#showAttendance').modal();
    $.get(baseUrl + '/attendance/' + caid + '.shtml',
        function (result) {
            if (result && result.status != 200) {
                return layer.msg(result.message), !1;
            }
            var obj = result.entity;
            var strHtml = '';
            var opHtml = '';
            var count = 0;
            for (var i = 0; i < obj.length; i++) {
                if (count % 6 == 0) {
                    strHtml = strHtml + '<div class="row">';
                }
                strHtml = strHtml + '<div class="col-md-2"><div class="form-group">';
                strHtml = strHtml + '<label for="recipient-name" class="control-label"><b>'+(i+1)+'</b>&nbsp' + obj[i].name + '</label><br>';
                if (obj[i].record == 1) {
                    strHtml = strHtml + '<span class="btn btn-success">出勤</span>';
                } else if (obj[i].record == 2) {
                    strHtml = strHtml + '<span class="	btn btn-warning">迟到</span>';
                } else if (obj[i].record == 3) {
                    strHtml = strHtml + '<span class="btn btn-danger">旷课</span>';
                } else if (obj[i].record == 4) {
                    strHtml = strHtml + '<span class="btn btn-info">请假</span>';
                } else if (obj[i].record == 5) {
                    strHtml = strHtml + '<span class="	btn btn-danger">早退</span>';
                }
                strHtml = strHtml + '</div></div>';
                count++;
                if (count == 6 || i == (obj.length - 1)) {
                    strHtml = strHtml + '</div>';
                    count = 0;
                }
            }
            $("#showAttendanceForm").html(strHtml);
        }, 'json');
}

var detailMsg = ['','出勤','迟到','旷课','请假','早退'];
function _open3(caid) {
    $('#addRemarks').modal();
    $.get(baseUrl + '/attendance/' + caid + '.shtml',
        function (result) {
            if (result && result.status != 200) {
                return layer.msg(result.message), !1;
            }
            var obj = result.entity;
            var strHtml = '';
            var count = 0;
            for (var i = 0; i < obj.length; i++) {
                if (obj[i].record == 1){
                    continue;
                }
                strHtml = strHtml + '<div class="row">';
                strHtml = strHtml + '&nbsp;&nbsp;<label for="recipient-name" class="control-label" style="display: inline-block">' + obj[i].name + '</label>&nbsp;&nbsp;';
                strHtml = strHtml + '<input type="hidden" id="remarks_sid_'+count+'" value="'+obj[i].sid+'">';
                strHtml = strHtml + '<input type="hidden" id="remarks_record_'+count+'" value="'+obj[i].record+'">';
                strHtml = strHtml + '<input type="text" class="form-control" id="remarks_detail_'+count+'" name="remarks" style="display: inline-block;width: 70%" maxlength="10" placeholder="'+detailMsg[obj[i].record]+'">';
                strHtml = strHtml + '</div>';
                count++;
            }
            strHtml = strHtml + '<input type="hidden" id="remarksSlen" value="' + count + '">';
            $("#addRemarksForm").html(strHtml);
            $("#hideremarkscaid").val(caid);
        }, 'json');
}

function _get_remarks_detail(caid) {
    $('#showRemarks').modal();
    $.get(baseUrl + '/attendance/remarks' + caid + '.shtml',
        function (result) {
            if (result && result.status != 200) {
                return layer.msg(result.message), !1;
            }
            var obj = result.entity;
            var strHtml = '';
            for (var i = 0; i < obj.length; i++) {
                strHtml = strHtml + '<div class="row">';
                strHtml = strHtml + '&nbsp;&nbsp;<label for="recipient-name" class="control-label" style="display: inline-block">' + obj[i].name + '</label>&nbsp;&nbsp;';
                strHtml = strHtml + '<input type="text" class="form-control" name="remarks"  value="'+obj[i].remarks+'" style="display: inline-block;width: 70%" disabled>';
                strHtml = strHtml + '</div>';
            }
            $("#showRemarksForm").html(strHtml);
        }, 'json');
}
