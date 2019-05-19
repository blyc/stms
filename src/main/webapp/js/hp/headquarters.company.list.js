var baseUrl = $("script[baseUrl]").attr('baseUrl');
$(document).ready(function () {
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


    $("#saveBtn").click(function () {
        var qualified = $("#qualified").val();
        var score1 = $("#score1").val();
        var score2 = $("#score2").val();
        var score3 = $("#score3").val();
        var eid = $("#eid").val();

        if ($.trim(eid) == '') {
            return layer.msg('讲师不能为空。'), !1;
        }

        if ($.trim(score1) == '') {
            return layer.msg('不能为空。'), !1;
        }
        if ($.trim(score2) == '') {
            return layer.msg('不能为空。'), !1;
        }
        if ($.trim(score3) == '') {
            return layer.msg('不能为空。'), !1;
        }


        if (score1 > 5 || score1 < 0 || score2 > 5 || score2 < 0 || score3 > 5 || score3 < 0) {
            return layer.msg('输入分数错误。'), !1;
        }

        if ($.trim(qualified) == '') {
            return layer.msg('考核不能为空。'), !1;
        }

        if (parseFloat(qualified) > 100 || parseFloat(qualified) < 0) {
            return layer.msg('考核格式错误。'), !1;
        }
        return layer.confirm("确定保存", {
            icon: 3,
            title: "提示",
            btn: ['确定', '取消'],
        }, function () {
            var load = layer.load();
            $.ajax({
                type: 'post',
                url: baseUrl + '/quarter/addReviewCompany.shtml',
                data: {eid: eid, qualified: qualified, score1: score1, score2: score2, score3: score3},
                dataType: 'json',
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

    $("#saveCompanyBtn2").click(function () {
        var qualified = $("#uqualified").val();
        var urcid = $("#urcid").val();
        var score1 = $("#uscore1").val();
        var score2 = $("#uscore2").val();
        var score3 = $("#uscore3").val();

        if ($.trim(qualified) == '') {
            return layer.msg('考核不能为空。'), !1;
        }

        if (parseFloat(qualified) > 100 || parseFloat(qualified) < 0) {
            return layer.msg('考核格式错误。'), !1;
        }

        return layer.confirm("确定保存", {
            icon: 3,
            title: "提示",
            btn: ['确定', '取消'],
        }, function () {
            var load = layer.load();
            $.ajax({
                type: 'post',
                url: baseUrl + '/quarter/updateReviewCompany1.shtml',
                data: {urcid: urcid, qualified: qualified, score1: score1, score2: score2, score3: score3},
                dataType: 'json',
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

    $("#saveHeadBtn2").click(function () {
        var qualified = $("#uqualified").val();
        var urcid = $("#urcid").val();
        var score1 = $("#uscore1").val();
        var score2 = $("#uscore2").val();
        var score3 = $("#uscore3").val();

        if ($.trim(qualified) == '') {
            return layer.msg('考核不能为空。'), !1;
        }

        if (parseFloat(qualified) > 100 || parseFloat(qualified) < 0) {
            return layer.msg('考核格式错误。'), !1;
        }

        return layer.confirm("确定保存", {
            icon: 3,
            title: "提示",
            btn: ['确定', '取消'],
        }, function () {
            var load = layer.load();
            $.ajax({
                type: 'post',
                url: baseUrl + '/quarter/updateReviewCompany2.shtml',
                data: {urcid: urcid, qualified: qualified, score1: score1, score2: score2, score3: score3},
                dataType: 'json',
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

    $("#score1,#score2,#score3").blur(function () {

        var score1 = $("#score1").val() == "" ? 0 : $("#score1").val();
        var score2 = $("#score2").val() == "" ? 0 : $("#score2").val();
        var score3 = $("#score3").val() == "" ? 0 : $("#score3").val();

        if (score1 > 5 || score1 < 0 || score2 > 5 || score2 < 0 || score3 > 5 || score3 < 0) {
            return layer.msg('输入分数错误。'), !1;
        }

        var qualified = parseFloat((parseInt(score1) + parseInt(score2) + parseInt(score3)) / 15 * 100).toFixed(2);

        $("#qualified").val(qualified);

    });

    $("#uscore1,#uscore2,#uscore3").blur(function () {


        var score1 = $("#uscore1").val() == "" ? 0 : $("#uscore1").val();
        var score2 = $("#uscore2").val() == "" ? 0 : $("#uscore2").val();
        var score3 = $("#uscore3").val() == "" ? 0 : $("#uscore3").val();

        if (score1 > 5 || score1 < 0 || score2 > 5 || score2 < 0 || score3 > 5 || score3 < 0) {
            return layer.msg('输入分数错误。'), !1;
        }

        var qualified = parseFloat((parseInt(score1) + parseInt(score2) + parseInt(score3)) / 15 * 100).toFixed(2);

        $("#uqualified").val(qualified);

    });
});


function _showAddDailog() {
    $('#addCompanyReview').modal();
}

function _showUpdateDailog(a, b) {

    var load = layer.load();
    $.get(baseUrl + '/quarter/getReviewCompany.shtml', {reviewTime: a, eid: b}, function (result) {
        layer.close(load);
        var obj = eval(result);
        if (obj != undefined) {
            $('#updateCompanyReview').modal();
            $("#urcid").val(obj.urcid);
            $("#uqualified").val(obj.qualified);
            $("#uscore1").val(obj.score1);
            $("#uscore2").val(obj.score2);
            $("#uscore3").val(obj.score3);


        }
    });
}
