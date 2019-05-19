var baseUrl = $("script[baseUrl]").attr('baseUrl');


$(function () {

    $("#addBtn").click(function () {
        $('#addMajor').modal();
    });

    $("#savebtn").click(function () {
        var name = $('#name').val();
         if ($.trim(name) == '') {
            return layer.msg('专业名称不能为空。', so.default), !1;
        }

        return layer.confirm("确定保存", {
            icon: 3,
            title: "提示",
            btn: ['确定', '取消'],
        }, function () {
            var load = layer.load();
            $.post(baseUrl + '/major.shtml'
                , {name: name}
                , function (result) {
                    layer.close(load);
                    if (result && result.status != 200) {
                        return layer.msg(result.message, so.default), !1;
                    }
                    layer.msg('保存成功。');
                    setTimeout(function () {
                        $('#formId').submit();
                    }, 1000);
                }, 'json');
        });
    });
});

//根据ID数组，删除
function _delete(ids) {
    var index = layer.confirm("确定这" + ids.length + "个用户？", function () {
        var load = layer.load();
        $.post(baseUrl + '/major/deleteMajorById.shtml', {ids: ids.join(',')}, function (result) {
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

