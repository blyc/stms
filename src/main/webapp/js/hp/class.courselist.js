var baseUrl = $("script[baseUrl]").attr('baseUrl');
$(function () {
    _initPopover();
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
    $.get(baseUrl + '/satisfaction/teacher.shtml', function (result) {
        var obj = eval(result);
        var option = $("<option>").val("").text("请选择");
        $("#eid,#ueid").append(option);
        if (obj != undefined) {
            for (var i = 0; i < obj.length; i++) {
                var option = $("<option>").val(result[i].key).text(result[i].value);
                $("#eid,#ueid").append(option);
            }
        }
        $('#eid,#ueid option:first').attr("selected", true);
    });

    $("#addBtn").click(function () {
        $('#satisfactiondlg').modal();
        $("#score").val("");
        $("#remark").val("");
    });


    // $("#eid").bind('input propertychange', function () {
    //     var eid = $("#eid").val();
    //     $.get(baseUrl + '/satisfaction/class.shtml', {eid: eid}, function (result) {
    //         $("#ccid").empty();
    //         var obj = eval(result);
    //         var option = $("<option>").val("").text("请选择");
    //         $("#ccid").append(option);
    //
    //         if (obj != undefined) {
    //             for (var i = 0; i < obj.length; i++) {
    //                 var option = $("<option>").val(result[i].key).text(result[i].value);
    //                 $("#ccid").append(option);
    //             }
    //         }
    //         $('#ccid option:first').attr("selected", true);
    //     });
    // });

    $("#count").bind("blur", function () {
        var count = $("#count").val() == "" ? 0 : $("#count").val();
        var result = (parseFloat(count) / 6).toFixed(0);
        $("#dayCount").val(result);
    });

    $("#ucount").bind("blur", function () {
        var count = $("#ucount").val() == "" ? 0 : $("#ucount").val();
        var result = (parseFloat(count) / 6).toFixed(0);
        $("#udayCount").val(result);
    });

    $("#savebtn").click(function () {
        var eid = $("#eid").val();
        var count = $("#count").val();
        var dayCount = $("#dayCount").val();

        if ($.trim(eid) == '') {
            return layer.msg('讲师不能为空。'), !1;
        }
        if ($.trim(count) == '') {
            return layer.msg('课时不能为空。'), !1;
        }

        return layer.confirm("确定保存", {
            icon: 3,
            title: "提示",
            btn: ['确定', '取消'],
        }, function () {
            var load = layer.load();
            $.post(baseUrl + '/uclasshour.shtml',
                {eid: eid, count: count, dayCount: dayCount},
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

    $("#savebtn1").click(function () {

        var uchid = $("#uchid").val();
        var count = $("#ucount").val();
        var dayCount = $("#udayCount").val();

        if ($.trim(count) == '') {
            return layer.msg('课时不能为空。'), !1;
        }

        return layer.confirm("确定保存", {
            icon: 3,
            title: "提示",
            btn: ['确定', '取消'],
        }, function () {
            var load = layer.load();

            $.ajax({
                url: baseUrl + "/uclasshour.shtml",
                type: "post",
                dataType: "json",
                data: {
                    count: count, dayCount: dayCount, uchid: uchid,
                    _method: "PUT"
                },
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

function _openUpdateDlg(a) {
    var load = layer.load();
    $.post(baseUrl + '/uclasshour/getUClassHour.shtml', {uchid: a}
        , function (result) {
            layer.close(load);
            if (result && result.status != 200) {
                return layer.msg(result.message, so.default), !1;
            }

            var obj = result.entity;
            $('#uchid').val(obj.uchid);
            $('#ueid').val(obj.eid);
            $('#ucount').val(obj.count);
            $('#udayCount').val(obj.dayCount);
            $('#updatecourelistdlg').modal();
        }, 'json');
}

//根据ID数组，删除
function _delete(ids) {
    var index = layer.confirm("确定这" + ids.length + "个数据？", function () {
        var load = layer.load();
        $.post(baseUrl + '/uclasshour/deleteUclasshour.shtml', {ids: ids.join(',')}, function (result) {
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