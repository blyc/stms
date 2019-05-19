<#macro left1 index>
<div id="one" class="col-md-2">
    <ul data-spy="affix" class="nav nav-list nav-tabs nav-stacked bs-docs-sidenav dropdown affix"
        style="top: 100px; z-index: 100;">
        <li class="${(index==1)?string('active',' ')}">
            <a href="${basePath}/user/index.shtml">
                <i class="glyphicon glyphicon-chevron-right"></i>个人资料
            </a>
            <ul class="dropdown-menu" aria-labelledby="dLabel" style="margin-left: 160px; margin-top: -40px;">
                <li><a href="${basePath}/user/updateSelf.shtml">资料修改</a></li>
                <li><a href="${basePath}/user/updatePswd.shtml">密码修改</a></li>
            </ul>
        </li>
        <li class="${(index==2)?string('active',' ')} dropdown">
            <a href="${basePath}/role/mypermission.shtml">
                <i class="glyphicon glyphicon-chevron-right"></i>我的权限
            </a>
        </li>
    </ul>
</div>
</#macro>
<#macro left2 index>
    <@shiro.hasAnyRoles name='888888,100001'>
    <div id="one" class="col-md-2">
        <ul data-spy="affix" class="nav nav-list nav-tabs nav-stacked bs-docs-sidenav dropdown affix"
            style="top: 100px; z-index: 100;">
            <li class="${(index==1)?string('active',' ')}">
                <a href="${basePath}/member/list.shtml">
                    <i class="glyphicon glyphicon-chevron-right"></i>用户列表
                </a>
            </li>
            <li class="${(index==2)?string('active',' ')} dropdown">
                <a href="${basePath}/member/online.shtml">
                    <i class="glyphicon glyphicon-chevron-right"></i>在线用户
                </a>
            </li>
        </ul>
    </div>
    </@shiro.hasAnyRoles>
</#macro>
<#macro left8 index>
    <@shiro.hasAnyRoles name='888888,100001'>
    <div id="one" class="col-md-2">
        <ul data-spy="affix" class="nav nav-list nav-tabs nav-stacked bs-docs-sidenav dropdown affix"
            style="top: 100px; z-index: 100;">
            <@shiro.hasPermission name="/role/index.shtml">
                <li class="${(index==1)?string('active',' ')}">
                    <a href="${basePath}/role/index.shtml">
                        <i class="glyphicon glyphicon-chevron-right"></i>角色列表
                    </a>
                </li>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="/role/allocation.shtml">
                <li class="${(index==2)?string('active',' ')} dropdown">
                    <a href="${basePath}/role/allocation.shtml">
                        <i class="glyphicon glyphicon-chevron-right"></i>角色分配
                    </a>
                </li>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="/permission/index.shtml">
                <li class="${(index==3)?string('active',' ')} dropdown">
                    <a href="${basePath}/permission/index.shtml">
                        <i class="glyphicon glyphicon-chevron-right"></i>权限列表
                    </a>
                </li>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="/permission/allocation.shtml">
                <li class="${(index==4)?string('active',' ')} dropdown">
                    <a href="${basePath}/permission/allocation.shtml">
                        <i class="glyphicon glyphicon-chevron-right"></i>权限分配
                    </a>
                </li>
            </@shiro.hasPermission>
        </ul>
    </div>
    </@shiro.hasAnyRoles>
</#macro>
<#macro left3 index>
    <@shiro.hasAnyRoles name='888888,100001,100003'>
    <div id="one" class="col-md-2">
        <ul data-spy="affix" class="nav nav-list nav-tabs nav-stacked bs-docs-sidenav dropdown affix"
            style="top: 100px; z-index: 100;">
            <@shiro.hasPermission name="/company/list.shtml">
                <li class="${(index==1)?string('active',' ')} dropdown">
                    <a href="${basePath}/company/list.shtml">
                        <i class="glyphicon glyphicon-chevron-right"></i>校区列表
                    </a>
                </li>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="/department/list.shtml">
                <li class="${(index==2)?string('active',' ')} dropdown">
                    <a href="${basePath}/department/list.shtml">
                        <i class="glyphicon glyphicon-chevron-right"></i>部门列表
                    </a>
                </li>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="/position/list.shtml">
                <li class="${(index==3)?string('active',' ')} dropdown">
                    <a href="${basePath}/position/list.shtml">
                        <i class="glyphicon glyphicon-chevron-right"></i>职位列表
                    </a>
                </li>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="/employee/list.shtml">
                <li class="${(index==4)?string('active',' ')}">
                    <a href="${basePath}/employee/list.shtml">
                        <i class="glyphicon glyphicon-chevron-right"></i>员工列表
                    </a>
                </li>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="/major/list.shtml">
                <li class="${(index==5)?string('active',' ')}">
                    <a href="${basePath}/major/list.shtml">
                        <i class="glyphicon glyphicon-chevron-right"></i>专业列表
                    </a>
                </li>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="/rank/list.shtml">
                <li class="${(index==6)?string('active',' ')}">
                    <a href="${basePath}/rank/list.shtml">
                        <i class="glyphicon glyphicon-chevron-right"></i>薪资体系列表
                    </a>
                </li>
            </@shiro.hasPermission>
        </ul>
    </div>
    </@shiro.hasAnyRoles>
</#macro>
<#macro left4 index>
    <@shiro.hasAnyRoles name='888888,100001,100002,100003'>
    <div id="one" class="col-md-2">
        <ul data-spy="affix" class="nav nav-list nav-tabs nav-stacked bs-docs-sidenav dropdown affix"
            style="top: 100px; z-index: 100;">
            <@shiro.hasPermission name="/student/list.shtml">
                <li class="${(index==1)?string('active',' ')}">
                    <a href="${basePath}/student/list.shtml">
                        <i class="glyphicon glyphicon-chevron-right"></i>学生档案
                    </a>
                </li>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="/divide/list.shtml">
                <li class="${(index==2)?string('active',' ')}">
                    <a href="${basePath}/divide/list.shtml">
                        <i class="glyphicon glyphicon-chevron-right"></i>分班管理
                    </a>
                </li>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="/payment/infolist.shtml">
                <li class="${(index==3)?string('active',' ')}">
                    <a href="${basePath}/payment/infolist.shtml">
                        <i class="glyphicon glyphicon-chevron-right"></i>升学记录
                    </a>
                </li>
            </@shiro.hasPermission>
        </ul>
    </div>
    </@shiro.hasAnyRoles>
</#macro>
<#macro left5 index>
    <@shiro.hasAnyRoles name='888888,100001,100002,100003'>
    <div id="one" class="col-md-2">
        <ul data-spy="affix" class="nav nav-list nav-tabs nav-stacked bs-docs-sidenav dropdown affix"
            style="top: 100px; z-index: 100;">
            <@shiro.hasPermission name="/attendance/list.shtml">
                <li class="${(index==1)?string('active',' ')}">
                    <a href="${basePath}/attendance/list.shtml">
                        <i class="glyphicon glyphicon-chevron-right"></i>出勤统计
                    </a>
                </li>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="/check/list.shtml">
                <li class="${(index==2)?string('active',' ')}">
                    <a href="${basePath}/check/list.shtml">
                        <i class="glyphicon glyphicon-chevron-right"></i>教务查课
                    </a>
                </li>
            </@shiro.hasPermission>
        </ul>
    </div>
    </@shiro.hasAnyRoles>
</#macro>
<#macro left6 index>
    <@shiro.hasAnyRoles name='888888,100001,100002,100003'>
    <div id="one" class="col-md-2">
        <ul data-spy="affix" class="nav nav-list nav-tabs nav-stacked bs-docs-sidenav dropdown affix"
            style="top: 100px; z-index: 100;">
            <@shiro.hasPermission name="/project/list.shtml">
                <li class="${(index==2)?string('active',' ')}">
                    <a href="${basePath}/project/list.shtml">
                        <i class="glyphicon glyphicon-chevron-right"></i>项目统计
                    </a>
                </li>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="/exam/list.shtml">
                <li class="${(index==3)?string('active',' ')}">
                    <a href="${basePath}/exam/list.shtml">
                        <i class="glyphicon glyphicon-chevron-right"></i>考试统计
                    </a>
                </li>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="/satisfaction/list.shtml">
                <li class="${(index==4)?string('active',' ')}">
                    <a href="${basePath}/satisfaction/list.shtml">
                        <i class="glyphicon glyphicon-chevron-right"></i>讲师满意度
                    </a>
                </li>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="/uclasshour/list.shtml">
                <li class="${(index==5)?string('active',' ')}">
                    <a href="${basePath}/uclasshour/list.shtml">
                        <i class="glyphicon glyphicon-chevron-right"></i>月课时统计
                    </a>
                </li>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="/quarter/companyquarteslist.shtml">
                <li class="${(index==6)?string('active',' ')}">
                    <a href="${basePath}/quarter/companyquarteslist.shtml">
                        <i class="glyphicon glyphicon-chevron-right"></i>讲师校区评估
                    </a>
                </li>
            </@shiro.hasPermission>
        </ul>
    </@shiro.hasAnyRoles>
</div>
</#macro>
<#macro left9 index>
    <@shiro.hasAnyRoles name='888888,100001,100002,100003,100004'>
    <div id="one" class="col-md-2">
        <ul data-spy="affix" class="nav nav-list nav-tabs nav-stacked bs-docs-sidenav dropdown affix"
            style="top: 100px; z-index: 100;">
            <@shiro.hasPermission name="/summary/classlist.shtml">
                <li class="${(index==1)?string('active',' ')}">
                    <a href="${basePath}/summary/classlist.shtml">
                        <i class="glyphicon glyphicon-chevron-right"></i>数据汇总(班级)
                    </a>
                </li>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="/summary/teacherlist.shtml">
                <li class="${(index==2)?string('active',' ')}">
                    <a href="${basePath}/summary/teacherlist.shtml">
                        <i class="glyphicon glyphicon-chevron-right"></i>数据汇总(讲师)
                    </a>
                </li>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="/summary/majorlist.shtml">
                <li class="${(index==3)?string('active',' ')}">
                    <a href="${basePath}/summary/majorlist.shtml">
                        <i class="glyphicon glyphicon-chevron-right"></i>数据汇总(专业)
                    </a>
                </li>
            </@shiro.hasPermission>
        <@shiro.hasPermission name="/summary/studentlistt.shtml">
            <li class="${(index==4)?string('active',' ')}">
                <a href="${basePath}/summary/studentlist.shtml">
                    <i class="glyphicon glyphicon-chevron-right"></i>数据汇总(学生)
                </a>
            </li>
       </@shiro.hasPermission>
            <@shiro.hasPermission name="/summary/resultlist.shtml">
                <li class="${(index==5)?string('active',' ')}">
                    <a href="${basePath}/summary/resultlist.shtml">
                        <i class="glyphicon glyphicon-chevron-right"></i>月教学考核结果
                    </a>
                </li>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="/summary/subsidylist.shtml">
                <li class="${(index==6)?string('active',' ')}">
                    <a href="${basePath}/summary/subsidylist.shtml">
                        <i class="glyphicon glyphicon-chevron-right"></i>月讲师授课津贴汇总
                    </a>
                </li>
            </@shiro.hasPermission>
        </ul>
    </div>
    </@shiro.hasAnyRoles>
</#macro>

<#macro left11 index>
    <@shiro.hasAnyRoles name='888888,100001,100003,100004'>
    <div id="one" class="col-md-2">
        <ul data-spy="affix" class="nav nav-list nav-tabs nav-stacked bs-docs-sidenav dropdown affix"
            style="top: 100px; z-index: 100;">
            <@shiro.hasPermission name="/quarter/list.shtml">
                <li class="${(index==1)?string('active',' ')}">
                    <a href="${basePath}/quarter/list.shtml">
                        <i class="glyphicon glyphicon-chevron-right"></i>考核标准
                    </a>
                </li>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="/quarter/examlist.shtml">
                <li class="${(index==2)?string('active',' ')}">
                    <a href="${basePath}/quarter/examlist.shtml">
                        <i class="glyphicon glyphicon-chevron-right"></i>督查数据(考试)
                    </a>
                </li>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="/quarter/projectlist.shtml">
                <li class="${(index==3)?string('active',' ')}">
                    <a href="${basePath}/quarter/projectlist.shtml">
                        <i class="glyphicon glyphicon-chevron-right"></i>督查数据(项目)
                    </a>
                </li>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="/quarter/companylist.shtml">
                <li class="${(index==4)?string('active',' ')}">
                    <a href="${basePath}/quarter/companylist.shtml">
                        <i class="glyphicon glyphicon-chevron-right"></i>督查数据(校区)
                    </a>
                </li>
            </@shiro.hasPermission>
        </ul>
    </div>
    </@shiro.hasAnyRoles>
</#macro>
<#--------------------------------------------------------------------------------------------->
<#macro left12 index>
    <@shiro.hasAnyRoles name='888888,100001,100002,100003,100004'>
    <div id="one" class="col-md-2">
        <ul data-spy="affix" class="nav nav-list nav-tabs nav-stacked bs-docs-sidenav dropdown affix"
            style="top: 100px; z-index: 100;">
            <#--<@shiro.hasPermission name="/practice/list.shtml">-->
                <#--<li class="${(index==1)?string('active',' ')}">-->
                    <#--<a href="${basePath}/practice/list.shtml">-->
                        <#--<i class="glyphicon glyphicon-chevron-right"></i>题库管理-->
                    <#--</a>-->
                <#--</li>-->
            <#--</@shiro.hasPermission>-->
<#--            <@shiro.hasPermission name="/practice/teacherList.shtml">-->
                <li class="${(index==1)?string('active',' ')}">
                    <a href="${basePath}/practice/releaseList.shtml">
                        <i class="glyphicon glyphicon-chevron-right"></i>发布管理
                    </a>
                </li>
<#--            </@shiro.hasPermission>-->
        </ul>
    </div>
    </@shiro.hasAnyRoles>
</#macro>
<#--------------------------------------------------------------------------------------------->
