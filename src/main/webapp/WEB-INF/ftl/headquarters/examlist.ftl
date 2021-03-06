<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8"/>
    <title>督查数据(考试)</title>
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport"/>
    <link rel="icon" href="${basePath}/favicon.ico" type="image/x-icon"/>
    <link rel="shortcut icon" href="${basePath}/favicon.ico"/>
    <link href="${basePath}/js/common/bootstrap/3.3.5/css/bootstrap.min.css?${_v}" rel="stylesheet"/>
    <link href="${basePath}/css/common/base.css?${_v}" rel="stylesheet"/>
    <link href="${basePath}/picker/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
    <script src="${basePath}/js/common/jquery/jquery1.8.3.min.js"></script>
    <script src="${basePath}/js/common/layer/layer.js"></script>
    <script src="${basePath}/js/common/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script src="${basePath}/picker/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
    <script src="${basePath}/picker/js/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
    <script src="${basePath}/js/util.js"></script>
    <script baseUrl="${basePath}" src="${basePath}/js/hp/headquarters.examlist.js"></script>

    <style>
        .popover {
            max-width: 500px;
        }

        .table th {
            text-align: center;
            vertical-align: middle !important;
        }
    </style>
</head>
<body data-target="#one" data-spy="scroll">

<@_top.top 11/>
<div class="container" style="padding-bottom: 15px;min-height: 300px; margin-top: 40px;">
    <div class="row">
    <@_left.left11 2/>
        <div class="col-md-10">
            <h2>督查数据(考试)</h2>
            <hr>
            <form method="post" action="" id="formId" class="form-inline">
                <div clss="well">
                    <div class="form-group">
                        <input type="text" class="form-control" style="width: 200px;" value="${findContent?default('')}"
                               name="findContent" id="findContent" placeholder="输入校区/班级/专业" maxlength="10">
                    </div>
                    <div class="form-group">
                        <div class="input-group date form_date col-md-10" data-date="" data-date-format="yyyy-mm" data-link-field="dtp_input2" data-link-format="yyyy-mm">
                            <input class="form-control" size="16" type="text"   value="${queryDate?default('')}" placeholder="输入月份" readonly>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                        <input type="hidden" name="queryDate"  id="dtp_input2" value="${queryDate?default('')}"/><br/>
                    </div>
                    <span class=""> <#--pull-right -->
                        <button type="submit" class="btn btn-primary">查询</button>
                    </span>
                </div>
                <hr>
                <table class="table table-bordered">
                    <tr>
                        <th>序号</th>
                        <th>校区</th>
                        <th>班级</th>
                        <th>专业</th>
                        <th>人数</th>
                        <th>科目</th>
                        <th>监考</th>
                        <th>地点</th>
                        <th>时间</th>
                        <th style="width: 50px">合格分数</th>
                        <th style="width: 50px">合格率(校区)</th>
                        <th style="width: 50px">合格率(总部)</th>
                        <th style="width: 50px">抽查个数</th>
                        <th>抽查合格率</th>
                        <th>操作</th>
                    </tr>
                <#if page?exists && page.list?size gt 0 >
                    <#list page.list as it>
                        <tr>
                            <td>  ${((page.pageNo-1)*page.pageSize)+it_index+1}</td>
                            <td>${it.vClass.companyname?default('')}</td>
                            <td>${it.vClass.classname?default('')}</td>
                            <td>${it.vClass.majorname?default('')}</td>
                            <td>${it.examnum?default('')}</td>
                            <td>${it.examproject?default('')}</td>
                            <td>${it.invigilator?default('')}</td>
                            <td>${it.examsite?default('')}</td>
                            <td>${(it.examtime?string("yyyy/MM/dd"))?default('')}</td>
                            <td>${(it.rateStandard)?string("#")}</td>
                            <td>${(it.qualifiedrate*100)?string("#.##")}%</td>
                            <#if it.uReviewExam?exists>
                                <td>${(it.uReviewExam.qualified*100)?string("#.##")}%</td>
                                <td>${(it.uReviewExam.reviewNum)?default(0)}</td>
                                <td>${(it.uReviewExam.reviewRate*100)?string("#.##")}%</td>
                            <#else>
                                <td>0%</td>
                                <td>0</td>
                                <td>0%</td>
                            </#if>
                            <td>
                                <#if it.uReviewExam?exists>
                                    <a href="javascript:_getdetail(${it.uReviewExam.reid})">查看详情</a>&nbsp;
                                <#else>
                                    <@shiro.hasAnyRoles name='888888,100001,100004'>
                                        <a href="javascript:_addetail(${it.ceid})">审核考试</a>&nbsp;
                                    </@shiro.hasAnyRoles>
                                </#if>
                            </td>
                        </tr>
                    </#list>
                <#else>
                    <tr>
                        <td class="text-center danger" colspan="12">没有考试成绩</td>
                    </tr>
                </#if>
                </table>
            <#if page?exists>
                <div class="pagination pull-right">
                ${page.pageHtml}
                </div>
            </#if>
            </form>
        </div>
    </div><#--/row-->

</div>

<#--录入抽查结果-->
<div class="modal fade" id="examDetails" tabindex="-1" role="dialog" aria-labelledby="examDetailsLabel">
    <div class="modal-dialog" role="document" style="width:1024px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="examDetailsLabel">学生考试抽查详情</h4>
            </div>
            <div class="modal-body">
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>姓名</th>
                        <th>成绩</th>
                        <th>试卷提交地址</th>
                        <th colspan="4">抽查结果</th>
                    </tr>
                    </thead>
                    <tbody id="showCount">
                    </tbody>
                </table>
                <input type="text" id="writtenlen" hidden="hidden">
                <input type="text" id="writtenid" hidden="hidden">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" id="saveBtn" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>

<#--查看抽查详细结果-->
<div class="modal fade" id="showDetails" tabindex="-1" role="dialog" aria-labelledby="showDetailsLabel">
    <div class="modal-dialog" role="document" style="width:1000px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="showDetailsLabel">学生考试抽查详情</h4>
            </div>
            <div class="modal-body">
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>姓名</th>
                        <th>成绩(校区)</th>
                        <th>抽查结果</th>
                        <th>成绩(总部)</th>
                        <th>审核情况</th>
                    </tr>
                    </thead>
                    <tbody id="showDetailtb">
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>