var baseUrl = $("script[baseUrl]").attr('baseUrl');

$(function () {

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

    $.get(baseUrl + '/project/class.shtml', function (result) {
        var obj = eval(result);
        var option = $("<option>").val("").text("请选择");
        $("#ccid").append(option);

        if (obj != undefined) {
            for (var i = 0; i < obj.length; i++) {
                option = $("<option>").val(result[i].key).text(result[i].value);
                $("#ccid").append(option);
            }
        }
        $('#ccid option:first').attr("selected", true);
    });

    $("#addBtn").click(function () {
        $("#addProject").modal();
    });

    $("#saveBtn").click(function () {
        var cid = $("#cid").val(), /*校区id*/
            ccid = $("#ccid").val(), /*班级id*/
            name = $("#name").val();
        // description = $("#description").val();

        if ($.trim(name) == '') {
            return layer.msg('项目名字不能为空。'), !1;
        }

        // if ($.trim(description) == '') {
        //     return layer.msg('项目简介不能为空。'), !1;
        // }

        return layer.confirm("确定保存", {
            icon: 3,
            title: "提示",
            btn: ['确定', '取消'],
        }, function () {
            var load = layer.load();
            $.post(baseUrl + '/project.shtml', {
                    ccid: ccid,
                    cid: cid,
                    name: name
                    // description: description
                },
                function (result) {
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
        var len = $("#writtenid").val(),
            cpid = $("#writtentime").val();

        var gradeList = new Array();
        for (var i = 0; i < len; i++) {
            var vpass = 0;
            if ($("#vpass_" + i).attr("checked")) {
                vpass = 1;
            }
            else {
                vpass = 0;
            }
            var vsubmit = 0;
            if ($("#vsubmit_" + i).attr("checked")) {
                vsubmit = 1;
            }
            else {
                vsubmit = 0;
            }
            gradeList.push({
                spid: $("#sp_id_" + i).val(), rate: $("#rate_" + i).val(),
                vsubmit: vsubmit, vpass: vpass, vaddr: $("#vaddr_" + i).val(),
                vtime: $("#vtime_" + i).val(), remark: $("#remark_" + i).val()
            });
        }
        return layer.confirm("确定保存", {
            icon: 3,
            title: "提示",
            btn: ['确定', '取消'],
        }, function () {
            var load = layer.load();
            $.ajax({
                type: 'post',
                url: baseUrl + '/project/updateGrade/' + cpid + '.shtml',
                data: JSON.stringify(gradeList),
                dataType: 'json',
                contentType: 'application/json;charset=utf-8',
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

    $("#btnImport").click(function () {
            var f = $("#importFile").val();
            if (f == "") {
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
        }
    );

});
var qjcopid = 0;

function _getgrade(cpid) {
    $('#writtenGrade').modal();
    $("#writtentime").val(cpid);

    var load = layer.load();
    $.get(baseUrl + '/project/getstudentname.shtml', {cpid: cpid}
        , function (result) {
            if (result && result.status != 200) {
                layer.close(load);
            }
            var obj = eval(result.info);
            var str = "";
            var count = 0;
            str = str + '<input type="hidden" id="slen" value="' + obj.length + '">';

            str = str + '<div class="row"><div class="col-md-12"><div class="input-group">'
                + '<span class="input-group-addon" id="basic-addon3" style="width:55px;"></span>'
                + '<span class="input-group-addon" id="basic-addon3"><input type="checkbox" value="1" id="rate_all" onclick="javascript:rate_all_func();">完成率快捷方式</span>'
                + '<span class="input-group-addon" id="basic-addon3"><input type="checkbox" value="1" id="vsubmit_all" onclick="javascript:vsubmit_all_func();">视频提交全选</span>'
                + ' <input type="text" class="form-control" id="vaddr" placeholder="项目讲解提交视频地址" style="width:300px;">'
                + '<span class="input-group-addon" id="basic-addon3"><input type="checkbox" value="1" id="vaddr_all" onclick="javascript:vaddr_all_func();">视频地址一致</span>'
                + '<span class="input-group-addon" id="basic-addon3"><input type="checkbox"  value="1"  id="vpass_all" onclick="javascript:vpass_all_func();">视频合格全选</span>'
                + '<input type="text" class="form-control " disabled>'
                + '</div></div></div><br>';

            for (var i = 0; i < obj.length; i++) {
                str = str + '<div class="row"><div class="col-md-12"><div class="input-group">'
                    + '<span class="input-group-addon" id="basic-addon3" style="width:85px;"><b>' + (i + 1) + '</b>&nbsp' + obj[i].uStudent.name + '</span>'
                    + '<input type="hidden" id="sp_id_' + i + '" value="' + obj[i].spid + '" >'
                    + ' <input type="text" name="rate" id="rate_' + i + '" class="form-control" placeholder="项目视频提交率" style="width:70px;" maxlength="5">'
                    + '<span class="input-group-addon" id="basic-addon3">%</span>'
                    + '<span class="input-group-addon" id="basic-addon3"><input type="checkbox" name="chkvsubmit" value="1" id="vsubmit_' + i + '">视频是否提交</span>'
                    + ' <input type="text" class="form-control"name="vaddr_info" id="vaddr_' + i + '" placeholder="项目讲解提交视频地址" style="width:300px;" maxlength="256" >'
                    + '<span class="input-group-addon" id="basic-addon3"></span>'
                    + '<input type="text" class="form-control"id="vtime_' + i + '" placeholder="项目时长" style="width:100px;"  maxlength="10">'
                    + '<span class="input-group-addon" id="basic-addon3"><input type="checkbox"   name="chkvpass"  value="1"  id="vpass_' + i + '">视频是否合格</span>'
                    + '<input type="text" id="remark_' + i + '" class="form-control" placeholder="备注" maxlength="256">'
                    + '</div></div></div><br>';
            }
            $("#written").html(str);
            $("#writtenid").val(obj.length);
        }, 'json');
}
/* 查看项目成绩详情*/
function _getDetail(cpid) {
    qjcopid = cpid
    $("#projectDetails").modal();

    var load = layer.load();
    $.get(baseUrl + "/project/getprojectdetails.shtml", {cpid: cpid},
        function (result) {
            var obj = eval(result.info);
            var str = '<input type="hidden" id="slen" value="' + obj.length + '">';
            var count = 0;
            str = str + '<table class="table table-bordered">';
            str = str + '<tr><th>序号</th><th>姓名</th><th>项目完成率</th><th>视频是否提交</th><th>视频地址</th><th>时长</th><th>视频是否合格</th><th>项目代码提交视频地址</th><th>项目代码完成率</th><th>备注</th></tr>';
            for (var i = 0; i < obj.length; i++) {
                str = str + '<tr><td>' + (i + 1) + '</td><td>' + obj[i].uStudent.name + '</td><td>' + (obj[i].rate * 100) + '%</td>';
                if (obj[i].vsubmit == 1) {
                    str = str + '<td>是</td>';
                }
                else {
                    str = str + '<td>否</td>';
                }
                str = str + '<td>' + obj[i].vaddr + '</td><td>' + obj[i].vtime + '</td>';

                if (obj[i].vpass == 1) {
                    str = str + '<td>是</td>';
                }
                else {
                    str = str + '<td>否</td>';
                }
                if (obj[i].codeaddr != null) {
                    str = str + '<td>' + obj[i].codeaddr + '</td>';
                }
                else {
                    str = str + '<td></td>';
                }
                if (obj[i].coderate != null) {
                    str = str + '<td>' + (obj[i].coderate*100) + '%</td>';
                }
                else {
                    str = str + '<td></td>';
                }
                if (obj[i].remark != null) {
                    str = str + '<td>' + obj[i].remark + '</td></tr>';
                }
                else {
                    str = str + '<td></td></tr>';
                }
            }
            $("#projectwritten").html(str);
            layer.close(load);
        }, 'json');
}

function vsubmit_all_func() {
    if ($("#vsubmit_all").is(':checked')) {
        $("input[name='chkvsubmit']").attr("checked", "true");
    }
    else {
        $("input[name='chkvsubmit']").removeAttr("checked");
    }
}

function vpass_all_func() {
    if ($("#vpass_all").attr("checked")) {
        $("input[name='chkvpass']").attr("checked", "true");
    }
    else {
        $("input[name='chkvpass']").removeAttr("checked");
    }
}

function vaddr_all_func() {
    if ($("#vaddr_all").attr("checked")) {
        var strVaddr = $("#vaddr").val();
        if (strVaddr != "") {
            $("input[name='vaddr_info']").val(strVaddr);
        }
    }
    else {
        $("input[name='vaddr_info']").val("");
    }
}

function rate_all_func() {
    if ($("#rate_all").attr("checked")) {
        $("input[name='rate']").val("100");
    }
    else {
        $("input[name='rate']").val("");
    }

}

function _show_import_dlg(a) {
    $("#importcpid").val(a);
    $("#importProject").modal();
}

//根据ID数组，删除
function _delete(ids) {
    var index = layer.confirm("确定这" + ids.length + "个数据？", function () {
        var load = layer.load();
        $.post(baseUrl + '/project/deleteProject.shtml', {ids: ids.join(',')}, function (result) {
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