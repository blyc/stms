var baseUrl = $("script[baseUrl]").attr('baseUrl');

$(function () {
    $('input[required]').before('<span style="color:red">*</span>');

    $("#addBtn").click(function () {
        $('#addCompany').modal();
    });
    
    $("#savebtn").click(function () {
        var name = $('#name').val(),
            address = $('#address').val(),
            tel = $('#tel').val(),
            founded = $('#founded').val(),
            describtion = $('#describtion').val();

        if ($.trim(name) == '') {
            return layer.msg('校区名称不能为空。', so.default), !1;
        }

        // if ($.trim(founded) == '') {
        //     return layer.msg('校区成立时间不能为空。', so.default), !1;
        // }

        return layer.confirm("确定保存", {
            icon: 3,
            title: "提示",
            btn: ['确定', '取消'],
        }, function () {
            var load = layer.load();

            $.post(baseUrl+'/company.shtml'
                , {name: name, address: address, tel: tel, founded: founded, describtion: describtion}
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
})

//根据ID数组，删除
function _delete(ids) {
    var index = layer.confirm("确定这" + ids.length + "个校区？", function () {
        var load = layer.load();
        $.post(baseUrl+'/company/deleteCompanyById.shtml', {ids: ids.join(',')}, function (result) {
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




