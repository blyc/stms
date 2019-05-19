var baseUrl = $("script[baseUrl]").attr('baseUrl');
$(function () {
    _initPopover();
    $('input[required]').before('<span style="color:red">*</span>');
    $.get(baseUrl + '/satisfaction/teacher.shtml', function (result) {
        var obj = eval(result);
        var option = $("<option>").val("").text("请选择");
        $("#eid").append(option);
        if (obj != undefined) {
            for (var i = 0; i < obj.length; i++) {
                var option = $("<option>").val(result[i].key).text(result[i].value);
                $("#eid").append(option);
            }
        }
        $('#eid option:first').attr("selected", true);
    });

    $("#addBtn").click(function () {
        $('#satisfactiondlg').modal();
        $("#score").val("");
        $("#remark").val("");
    });

    $("#eid").bind('input propertychange', function () {
        var eid = $("#eid").val();
        $.get(baseUrl + '/satisfaction/class.shtml', {eid: eid}, function (result) {
            $("#ccid").empty();
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
    });

    $("#savebtn").click(function () {
        var ccid = $("#ccid").val();
        var eid = $("#eid").val();
        var score = $("#score").val();
        var remark = $("#remark").val();

        if ($.trim(eid) == '') {
            return layer.msg('讲师不能为空。'), !1;
        }
        if ($.trim(ccid) == '') {
            return layer.msg('班级不能为空。'), !1;
        }
        if ($.trim(score) == '') {
            return layer.msg('满意度不能为空。'), !1;
        }

        return layer.confirm("确定保存", {
            icon: 3,
            title: "提示",
            btn: ['确定', '取消'],
        }, function () {
            var load = layer.load();
            $.post(baseUrl + '/satisfaction.shtml',
                {ccid: ccid, eid: eid, score: score, remark: remark},
                function (result) {
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
});

//根据ID数组，删除
function _delete(ids) {
    var index = layer.confirm("确定这" + ids.length + "个信息？", function () {
        var load = layer.load();
        $.post(baseUrl + '/satisfaction/deleteUSatisfaction.shtml', {ids: ids.join(',')}, function (result) {
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
