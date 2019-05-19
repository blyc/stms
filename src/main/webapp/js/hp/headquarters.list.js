var baseUrl = $("script[baseUrl]").attr('baseUrl');
$(document).ready(function () {
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

    var date = new Date();
    var d = date.getFullYear() + "-0" + (date.getMonth() + 1);
    $("#standardTime").val(d)

    //获取年级
    $("#grade,#ugrade").append($("<option>").val("").text("请选择"));
    var date = new Date();
    var year = date.getFullYear() - 2000;  //取后两位
    for (var i = year; i >= 13; i--) {
        var option = $("<option>").val(i).text(i + "级");
        $("#grade,#ugrade").append(option);
    }
    $('#grade, #ugrade option:first').attr("selected", true);

    //获取专业
    $.get(baseUrl + '/student/getmajor.shtml', function (result) {
        obj = eval(result);
        $("#mid,#umid").append($("<option>").val("").text("请选择"));
        if (obj != undefined) {
            for (var i = 0; i < obj.length; i++) {
                var option = $("<option>").val(result[i].key).text(result[i].value);
                $("#mid,#umid").append(option);
            }
        }
        // $("#majorType").val(result[i].obj.id);
        // $("#umajorType").val(result[i].obj.id);
        $('#mid,#umid option:first').attr("selected", true);
    });

    $.post(baseUrl + '/employee/selectCompany.shtml', function (result) {
        obj = eval(result);

        var strHtml = "";
        $("#cid").append($("<option>").val("").text("请选择"));
        if (obj != undefined) {
            for (var i = 0; i < obj.length; i++) {
                if (result[i].value == "总部") {
                    continue;
                }
                strHtml = strHtml + '<label class="checkbox-inline"><input type="checkbox" name="inlineCkb" value="' + result[i].key + '"> ' + result[i].value + '</label>';
            }
        }
        $('#cid').html(strHtml);
    });


    $("#weightExam").val(20);
    $("#weightProject").val(20);
    $("#weightAttendance").val(15);
    $("#weightSatisfaction").val(15);
    $("#weightSchool").val(15);
    $("#weightHead").val(15);

    $("#weightExam,#weightProject,#weightAttendance,#weightSatisfaction,#weightSchool,#weightHead,#standardExam,#standardProject,#standardAttendance,#standardSatisfaction,#standardSchool,#standardHead").bind("blur", function () {
        var weightExam = $("#weightExam").val() == "" ? 0 : $("#weightExam").val();
        var weightProject = $("#weightProject").val() == "" ? 0 : $("#weightProject").val();
        var weightAttendance = $("#weightAttendance").val() == "" ? 0 : $("#weightAttendance").val();
        var weightSatisfaction = $("#weightSatisfaction").val() == "" ? 0 : $("#weightSatisfaction").val();
        var weightSchool = $("#weightSchool").val() == "" ? 0 : $("#weightSchool").val();
        var weightHead = $("#weightHead").val() == "" ? 0 : $("#weightHead").val();
        var standardExam = $("#standardExam").val() == "" ? 0 : $("#standardExam").val()
        var standardProject = $("#standardProject").val() == "" ? 0 : $("#standardProject").val();
        var standardAttendance = $("#standardAttendance").val() == "" ? 0 : $("#standardAttendance").val();
        var standardSatisfaction = $("#standardSatisfaction").val() == "" ? 0 : $("#standardSatisfaction").val();
        var standardSchool = $("#standardSchool").val() == "" ? 0 : $("#standardSchool").val();
        var standardHead = $("#standardHead").val() == "" ? 0 : $("#standardHead").val();

        var result = parseFloat(weightExam) * (parseFloat(standardExam) / 100) + parseFloat(weightProject) * (parseFloat(standardProject) / 100)
            + parseFloat(weightAttendance) * (parseFloat(standardAttendance) / 100) + parseFloat(weightSatisfaction) * (parseFloat(standardSatisfaction) / 100)
            + parseFloat(weightSchool) * (parseFloat(standardSchool) / 100) + parseFloat(weightHead) * (parseFloat(standardHead) / 100)

        result = parseFloat(result).toFixed(2);
        $("#result").val(result);
        $("#base").val(Math.ceil(result));

    });


    $("#uweightExam,#uweightProject,#uweightAttendance,#uweightSatisfaction,#uweightSchool,#uweightHead,#ustandardExam,#ustandardProject,#ustandardAttendance,#ustandardSatisfaction,#ustandardSchool,#ustandardHead").bind("blur", function () {
        var weightExam = $("#uweightExam").val() == "" ? 0 : $("#uweightExam").val();
        var weightProject = $("#uweightProject").val() == "" ? 0 : $("#uweightProject").val();
        var weightAttendance = $("#uweightAttendance").val() == "" ? 0 : $("#uweightAttendance").val();
        var weightSatisfaction = $("#uweightSatisfaction").val() == "" ? 0 : $("#uweightSatisfaction").val();
        var weightSchool = $("#uweightSchool").val() == "" ? 0 : $("#uweightSchool").val();
        var weightHead = $("#uweightHead").val() == "" ? 0 : $("#uweightHead").val();
        var standardExam = $("#ustandardExam").val() == "" ? 0 : $("#ustandardExam").val()
        var standardProject = $("#ustandardProject").val() == "" ? 0 : $("#ustandardProject").val();
        var standardAttendance = $("#ustandardAttendance").val() == "" ? 0 : $("#ustandardAttendance").val();
        var standardSatisfaction = $("#ustandardSatisfaction").val() == "" ? 0 : $("#ustandardSatisfaction").val();
        var standardSchool = $("#ustandardSchool").val() == "" ? 0 : $("#ustandardSchool").val();
        var standardHead = $("#ustandardHead").val() == "" ? 0 : $("#ustandardHead").val();

        var result = parseFloat(weightExam) * (parseFloat(standardExam) / 100) + parseFloat(weightProject) * (parseFloat(standardProject) / 100)
            + parseFloat(weightAttendance) * (parseFloat(standardAttendance) / 100) + parseFloat(weightSatisfaction) * (parseFloat(standardSatisfaction) / 100)
            + parseFloat(weightSchool) * (parseFloat(standardSchool) / 100) + parseFloat(weightHead) * (parseFloat(standardHead) / 100)

        result = parseFloat(result).toFixed(2);
        $("#uresult").val(result);
        $("#ubase").val(Math.ceil(result));

    });

    $("#addBtn").click(function () {
        $('#addStandard').modal();
    });

    $("#saveBtn").click(function () {
        var standardTime = $("#standardTime").val();
        var grade = $('#grade').val();
        var mid = $('#mid').val();
        var weightExam = $("#weightExam").val() == "" ? 0 : $("#weightExam").val();
        var weightProject = $("#weightProject").val() == "" ? 0 : $("#weightProject").val();
        var weightAttendance = $("#weightAttendance").val() == "" ? 0 : $("#weightAttendance").val();
        var weightSatisfaction = $("#weightSatisfaction").val() == "" ? 0 : $("#weightSatisfaction").val();
        var weightSchool = $("#weightSchool").val() == "" ? 0 : $("#weightSchool").val();
        var weightHead = $("#weightHead").val() == "" ? 0 : $("#weightHead").val();
        var standardExam = $("#standardExam").val() == "" ? 0 : $("#standardExam").val()
        var standardProject = $("#standardProject").val() == "" ? 0 : $("#standardProject").val();
        var standardAttendance = $("#standardAttendance").val() == "" ? 0 : $("#standardAttendance").val();
        var standardSatisfaction = $("#standardSatisfaction").val() == "" ? 0 : $("#standardSatisfaction").val();
        var standardSchool = $("#standardSchool").val() == "" ? 0 : $("#standardSchool").val();
        var standardHead = $("#standardHead").val() == "" ? 0 : $("#standardHead").val();
        var result = $("#result").val() == "" ? 0 : $("#result").val();
        var base = $("#base").val() == "" ? 0 : $("#base").val();
        var scope = $("#scope").val();

        var chk_value = "";//定义一个数组
        $('input[name="inlineCkb"]:checked').each(function () {//遍历每一个名字为interest的复选框，其中选中的执行函数
            chk_value = chk_value + $(this).val() + ",";
        });


        if ($.trim(standardTime) == '') {
            return layer.msg('月份不能为空。'), !1;
        }
        if ($.trim(grade) == '') {
            return layer.msg('年级不能为空。'), !1;
        }
        if ($.trim(mid) == '') {
            return layer.msg('专业不能为空。'), !1;
        }

        if ($.trim(base) == '') {
            return layer.msg('考核基数不能为空。'), !1;
        }

        if ($.trim(chk_value) == '') {
            return layer.msg('请选择适用校区。'), !1;
        }

        return layer.confirm("确定保存", {
            icon: 3,
            title: "提示",
            btn: ['确定', '取消'],
        }, function () {
            var load = layer.load();
            $.post(baseUrl + '/quarter.shtml', {
                    standardTime: standardTime,
                    grade: grade,
                    mid: mid,
                    base: base,
                    weightExam: weightExam,
                    weightProject: weightProject,
                    weightAttendance: weightAttendance,
                    weightSatisfaction: weightSatisfaction,
                    weightSchool: weightSchool,
                    weightHead: weightHead,
                    standardExam: standardExam,
                    standardProject: standardProject,
                    standardAttendance: standardAttendance,
                    standardSatisfaction: standardSatisfaction,
                    standardSchool: standardSchool,
                    standardHead: standardHead,
                    scope: scope,
                    result: result,
                    cids: chk_value
                }
                , function (result) {
                    layer.close(load);
                    if (result.selectDate == 1) {
                        return layer.msg(result.message), !1;
                    }
                    else {
                        if (result && result.status != 200) {
                            return layer.msg(result.message), !1;
                        }
                        layer.msg('保存成功。');
                        setTimeout(function () {
                            $('#formId').submit();
                        }, 1000);
                    }
                }, 'json');
        });
    });

    $("#saveBtn1").click(function () {
        var sid = $("#usid").val();
        var standardTime = $("#ustandardTime").val();
        var grade = $('#ugrade').val();
        var mid = $('#umid').val();
        var weightExam = $("#uweightExam").val() == "" ? 0 : $("#uweightExam").val();
        var weightProject = $("#uweightProject").val() == "" ? 0 : $("#uweightProject").val();
        var weightAttendance = $("#uweightAttendance").val() == "" ? 0 : $("#uweightAttendance").val();
        var weightSatisfaction = $("#uweightSatisfaction").val() == "" ? 0 : $("#uweightSatisfaction").val();
        var weightSchool = $("#uweightSchool").val() == "" ? 0 : $("#uweightSchool").val();
        var weightHead = $("#uweightHead").val() == "" ? 0 : $("#uweightHead").val();
        var standardExam = $("#ustandardExam").val() == "" ? 0 : $("#ustandardExam").val()
        var standardProject = $("#ustandardProject").val() == "" ? 0 : $("#ustandardProject").val();
        var standardAttendance = $("#ustandardAttendance").val() == "" ? 0 : $("#ustandardAttendance").val();
        var standardSatisfaction = $("#ustandardSatisfaction").val() == "" ? 0 : $("#ustandardSatisfaction").val();
        var standardSchool = $("#ustandardSchool").val() == "" ? 0 : $("#ustandardSchool").val();
        var standardHead = $("#ustandardHead").val() == "" ? 0 : $("#ustandardHead").val();
        var result = $("#uresult").val() == "" ? 0 : $("#uresult").val();
        var base = $("#ubase").val() == "" ? 0 : $("#ubase").val();
        var scope = $("#uscope").val();

        if ($.trim(standardTime) == '') {
            return layer.msg('月份不能为空。'), !1;
        }
        if ($.trim(grade) == '') {
            return layer.msg('年级不能为空。'), !1;
        }
        if ($.trim(mid) == '') {
            return layer.msg('专业不能为空。'), !1;
        }
        if ($.trim(base) == '') {
            return layer.msg('考核基数不能为空。'), !1;
        }


        return layer.confirm("确定保存", {
            icon: 3,
            title: "提示",
            btn: ['确定', '取消'],
        }, function () {
            var load = layer.load();
            $.post(baseUrl + '/quarter/updateStandard.shtml', {
                    sid: sid,
                    standardTime: standardTime,
                    grade: grade,
                    mid: mid,
                    base: base,
                    weightExam: weightExam,
                    weightProject: weightProject,
                    weightAttendance: weightAttendance,
                    weightSatisfaction: weightSatisfaction,
                    weightSchool: weightSchool,
                    weightHead: weightHead,
                    standardExam: standardExam,
                    standardProject: standardProject,
                    standardAttendance: standardAttendance,
                    standardSatisfaction: standardSatisfaction,
                    standardSchool: standardSchool,
                    standardHead: standardHead,
                    scope: scope,
                    result: result
                }
                , function (result) {
                    layer.close(load);
                    if (result.selectDate == 1) {
                        return layer.msg(result.message), !1;
                    }
                    else {
                        if (result && result.status != 200) {
                            return layer.msg(result.message), !1;
                        }
                        layer.msg('保存成功。');
                        setTimeout(function () {
                            $('#formId').submit();
                        }, 1000);
                    }
                }, 'json');
        });
    });
    $("#btn3").click(function () {
        $("#importExam").modal();
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
        _initPopover();
    });
});

function update(id) {
    $.post(baseUrl + '/quarter/getStandard.shtml', {sid: id}, function (msg) {
        if (msg && msg.status != 200) {
            return;
        }
        $("#ustandardTime").val(msg.entity.standardTime);
        $('#ugrade').val(msg.entity.grade);
        $('#umid').val(msg.entity.mid);
        $("#uweightExam").val(msg.entity.weightExam);
        $("#uweightProject").val(msg.entity.weightProject);
        $("#uweightAttendance").val(msg.entity.weightAttendance);
        $("#uweightSatisfaction").val(msg.entity.weightSatisfaction);
        $("#uweightSchool").val(msg.entity.weightSchool);
        $("#uweightHead").val(msg.entity.weightHead);
        $("#ustandardExam").val(msg.entity.standardExam);
        $("#ustandardProject").val(msg.entity.standardProject);
        $("#ustandardAttendance").val(msg.entity.standardAttendance);
        $("#ustandardSatisfaction").val(msg.entity.standardSatisfaction);
        $("#ustandardSchool").val(msg.entity.standardSchool);
        $("#ustandardHead").val(msg.entity.standardHead);
        $("#uresult").val(msg.entity.result);
        $("#ubase").val(msg.entity.base);
        $("#uscope").val(msg.entity.scope);
        $("#usid").val(msg.entity.sid);
        $("#updateStandard").modal();
    });
}
