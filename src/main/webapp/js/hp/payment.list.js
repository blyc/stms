var baseUrl = $("script[baseUrl]").attr('baseUrl');

$(function () {
    $("#savepaybtn").click(function () {
        var ccid = $("#hideccid").val();
        var slen = $("#slen").val();
        var attendList = new Array();
        for (var i = 0; i < slen; i++) {
            attendList.push({sid: $("#stu_id_" + i).val(), record: $("#stu_atten_" + i).val()});
        }
        return layer.confirm("确定保存", {
            icon: 3,
            title: "提示",
            btn: ['确定', '取消'],
        }, function () {
            var load = layer.load();
            $.ajax({
                type: 'post',
                url: baseUrl + '/payment/do/' + ccid + '.shtml',
                data: JSON.stringify(attendList),
                dataType: 'json',
                contentType: 'application/json;charset=utf-8',
                success: function (result) {
                    layer.close(load);
                    if (result && result.status != 200) {
                        return layer.msg(result.message, so.default), !1;
                    }
                    layer.msg('添加成功。');
                    setTimeout(function () {
                        $('#formId').submit();
                    }, 1000);
                }
            });
        });
    });
});
function _open2(ccid) {
    $('#addPayment').modal();
    $.get(baseUrl + '/attendance/getClassStudent.shtml', {ccid: ccid}
        , function (result) {
            if (result && result.status != 200) {
                return layer.msg(result.message, so.default), !1;
            }
            var obj = result.entity;
            var strHtml = "";
            var opHtml = '<option value="1">升学</option><option value="2">退学</option><option value="3">转专业</option><option value="4">其他</option><option value="5">早退</option>';
            var count = 0;
            strHtml = strHtml + '<input type="hidden" id="slen" value="' + obj.length + '">';
            for (var i = 0; i < obj.length; i++) {
                if (i % 6 == 0) {
                    strHtml = strHtml + '<div class="row">';
                }
                strHtml = strHtml + '<div class="col-md-2"><div class="form-group">';
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
            $("#boxPaymentForm").html(strHtml);
            $("#hideccid").val(ccid);
        }, 'json');
}
