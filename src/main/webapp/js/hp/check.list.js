var baseUrl = $("script[baseUrl]").attr('baseUrl');

$(function () {
    $.get(baseUrl + '/check/class.shtml', function (result) {
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
        $('#addCheckClass').modal();
    });

    $("#saveBtn1").click(function () {
        var ccid = $('#ccid').val();

        if ($.trim(ccid) == '') {
            return layer.msg('班级不能为空。'), !1;
        }

        return layer.confirm("确定保存", {
            icon: 3,
            title: "提示",
            btn: ['确定', '取消'],
        }, function () {
            var load = layer.load();
            $.post(baseUrl + '/check.shtml', {ccid: ccid}
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

    $("#saveBtn2").click(function () {
        var chid = $("#chid").val(),
            due = $('#due').val(),
            actual = $('#actual').val(),
            attendancerate = $('#attendancerate').val().replace("%", "") / 100,
            jsname = $('#js').val(),
            bzrname = $('#bzr').val(),
            projector = $('#projector').val(),
            courseware = $('#courseware').val(),
            touchscreen = $('#touchscreen').val(),
            teapro = $('#teapro').val(),
            beforestate = "1";

        if ($.trim(actual) == 0) {
            return layer.msg('应到人数不能为空。'), !1;
        }
        if (!checkNumber(actual)) {
            return layer.msg('请输入数字。'), !1;
        }

        if (due < actual) {
            return layer.msg('实到人数不能大于应到人数。'), !1;
        }
        return layer.confirm("确定保存", {
            icon: 3,
            title: "提示",
            btn: ['确定', '取消'],
        }, function () {
            var load = layer.load();
            $.post(baseUrl + '/check/updateCheck.shtml', {
                    due: due,
                    actual: actual,
                    attendancerate: attendancerate,
                    jsname: jsname,
                    bzrname: bzrname,
                    projector: projector,
                    courseware: courseware,
                    touchscreen: touchscreen,
                    teapro: teapro,
                    beforestate: beforestate,
                    chid: chid
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
    $("#saveBtn3").click(function () {
        var chid = $("#chid").val(),
            jsstate = $('#jsstate').val(),
            stustate = $('#stustate').val(),
            teatourclass = $('#teatourclass').val(),
            middlestate = "1";

        return layer.confirm("确定保存", {
            icon: 3,
            title: "提示",
            btn: ['确定', '取消'],
        }, function () {
            var load = layer.load();
            $.post(baseUrl + '/check/updateCheck.shtml', {
                    jsstate: jsstate,
                    stustate: stustate,
                    teatourclass: teatourclass,
                    middlestate: middlestate,
                    chid: chid
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

    $("#saveBtn4").click(function () {
        var chid = $("#chid").val(),
            stunum = $('#stunum').val(),
            discipline = $('#discipline').val(),
            laterstate = "1";

        if ($.trim(stunum) == '') {
            return layer.msg('学生人数为空。'), !1;
        }

        if (!checkNumber(stunum)) {
            return layer.msg('请输入数字。'), !1;
        }

        return layer.confirm("确定保存", {
            icon: 3,
            title: "提示",
            btn: ['确定', '取消'],
        }, function () {
            var load = layer.load();
            $.post(baseUrl + '/check/updateCheck.shtml', {
                    stunum: stunum,
                    discipline: discipline,
                    laterstate: laterstate,
                    chid: chid
                }
                , function (result) {
                    layer.close(load);
                    if (result && result.status != 200) {
                        return layer.msg(result.message), !1;
                    }
                    layer.msg('添加成功。');
                    setTimeout(function () {
                        $('#formId').submit();
                    }, 1000);
                }, 'json');
        });
    });

    $("#saveBtn5").click(function () {
        var chid = $("#chid").val(),
            remark = $('#remark').val();

        if ($.trim(remark) == '') {
            return layer.msg('备注为空。'), !1;
        }

        return layer.confirm("确定保存", {
            icon: 3,
            title: "提示",
            btn: ['确定', '取消'],
        }, function () {
            var load = layer.load();
            $.post(baseUrl + '/check/updateCheck.shtml', {
                    remark: remark,
                    chid: chid
                }
                , function (result) {
                    layer.close(load);
                    if (result && result.status != 200) {
                        return layer.msg(result.message), !1;
                    }
                    layer.msg('添加成功。');
                    setTimeout(function () {
                        $('#formId').submit();
                    }, 1000);
                }, 'json');
        });
    });
    _initPopover();
});

// 打开添加课前对话框
function _openaddbeforeDlg(chid, ccid) {
    $('#addClassBefore').modal();
    $("#chid").val(chid);
    $.get(baseUrl + '/check/selectVClassByCcid.shtml', {ccid: ccid}, function (result) {
        var obj = eval(result);
        for (var i = 0; i < obj.length; i++) {
            $("#js").val(obj[i].jsname);
            $("#bzr").val(obj[i].bzrname);
            $("#due").val(obj[i].num);
        }
    });

    $("#actual").keyup(function () {
        var due = $('#due').val();
        var actual = $('#actual').val();
        if (due < actual) {
            return layer.msg('实到人数不能大于应到人数。'), !1;
        }
        $('#attendancerate').val((actual / due) * 100 + "%");
    });

}
//打开添加课中对话框
function _openaddmiddleDlg(chid) {
    $('#addClassMiddle').modal();
    $("#chid").val(chid);
}
//打开添加课末对话框
function _openaddlaterDlg(chid, actual) {
    $('#addClassLater').modal();
    $("#chid").val(chid);

    // $("#stunum").keyup(function () {
    //
    //     var stunum = $('#stunum').val();
    //     // if (actual < stunum) {
    //     //     return layer.msg('课后人数超出了课前实到人数。'), !1;
    //     // }
    // });
}
//打开添加备注对话框
function _openaddremarkDlg(chid) {
    $('#addClassRemark').modal();
    $("#chid").val(chid);
}
