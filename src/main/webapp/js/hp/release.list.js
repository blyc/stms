var baseUrl = $("script[baseUrl]").attr('baseUrl');

$('input[required]').before('<span style="color:red">*</span>');

//根据ID数组，删除
function _delete(ids) {
    var index = layer.confirm("确定这" + ids.length + "个用户？", function () {
        var load = layer.load();
        $.post('${basePath}/position/deletePositionById.shtml', {ids: ids.join(',')}, function (result) {
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

function _openDlg() {
    $('#addPosition').modal();
}

function _addPosition() {
    var name = $('#name').val();
    if ($.trim(name) == '') {
        return layer.msg('职位不能为空。', so.default), !1;
    }
    var load = layer.load();
    $.post('${basePath}/position/addPosition.shtml', {
            name: name
        }
        , function (result) {
            layer.close(load);
            if (result && result.status != 200) {
                return layer.msg(result.message, so.default), !1;
            }
            layer.msg('添加成功。');
            setTimeout(function () {
                $('#formId').submit();
            }, 1000);
        }, 'json');
}