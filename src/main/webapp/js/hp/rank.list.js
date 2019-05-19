var baseUrl = $("script[baseUrl]").attr('baseUrl');
$(function () {
    _initPopover();
    $('input[required]').before('<span style="color:red">*</span>');

    $("#addBtn").click(function () {
        $('#rankdlg').modal();
    });


    $("#savebtn").click(function () {
        var type = $("#type").val();
        var level = $("#level").val();
        var subsidy = $("#subsidy").val();

        if ($.trim(type) == '') {
            return layer.msg('岗位代码不能为空。'), !1;
        }
        if ($.trim(level) == '') {
            return layer.msg('级别不能为空。'), !1;
        }
        if ($.trim(subsidy) == '') {
            return layer.msg('薪资不能为空。'), !1;
        }

        return layer.confirm("确定保存", {
            icon: 3,
            title: "提示",
            btn: ['确定', '取消'],
        }, function () {
            var load = layer.load();
            $.post(baseUrl + '/rank.shtml',
                {type: type, level: level, subsidy: subsidy},
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
        var type = $("#utype").val();
        var level = $("#ulevel").val();
        var subsidy = $("#usubsidy").val();
        var lid = $("#ulid").val();

        if ($.trim(type) == '') {
            return layer.msg('岗位代码不能为空。'), !1;
        }
        if ($.trim(level) == '') {
            return layer.msg('级别不能为空。'), !1;
        }
        if ($.trim(subsidy) == '') {
            return layer.msg('薪资不能为空。'), !1;
        }

        return layer.confirm("确定保存", {
            icon: 3,
            title: "提示",
            btn: ['确定', '取消'],
        }, function () {
            var load = layer.load();
            $.ajax({
                url: baseUrl + "/rank.shtml",
                type: "post",
                dataType: "json",
                data: {
                    type: type, level: level, subsidy: subsidy,lid:lid,
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
    $("#btn3").click(function () {
        $("#importRank").modal();
    });
    $("#btnImport").click(function () {
            var f = $("#importFile").val();
            if (f === "") {
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
        });
});


function _openUpdateDlg(a) {
    var load = layer.load();
    $.post(baseUrl + '/rank/getUJsRank.shtml', {lid: a}
        , function (result) {
            layer.close(load);
            if (result && result.status != 200) {
                return layer.msg(result.message, so.default), !1;
            }

            var obj = result.entity;
            $('#ulid').val(obj.lid);
            $('#utype').val(obj.type);
            $('#ulevel').val(obj.level);
            $('#usubsidy').val(obj.subsidy);
            $('#updaterankdlg').modal();
        }, 'json');
}
