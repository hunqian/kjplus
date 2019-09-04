<script type="text/javascript">
        try {
                ace.settings.check('sidebar-shortcuts', 'fixed')
        } catch(e) {}
        
</script>

<!-- #sidebar-shortcuts -->
<!-- .nav-list -->
<ul class="nav nav-list">
        <#list funcs as f0>
                <li>
                        <a href="#" class="dropdown-toggle">
                                <img src="${base}/${f0.icon}" style="width:25px">
                                <span class="menu-text">
                                        ${f0.name}
                                </span>
                                <b class="arrow icon-angle-down">
                                </b>
                        </a>
                        <#if f0.id == pmenu>
                        <ul class="submenu" style="display: block;">
                        <#else>
                        <ul class="submenu">
                        </#if>
                                <#list f0.subs as f1>
                                        <li>
                                                <a href="${base}${f1.menu}">
                                                        <i class="icon-double-angle-right">
                                                        </i>
                                                        ${f1.name}
                                                </a>
                                        </li>
                                </#list>
                        </ul>
                </li>
        </#list>
</ul>
<!-- /.nav-list -->
<div class="sidebar-collapse" id="sidebar-collapse">
        <i class="icon-double-angle-left" data-icon1="icon-double-angle-left"
        data-icon2="icon-double-angle-right">
        </i>
</div>
<script type="text/javascript">
        try {
                ace.settings.check('sidebar-collapse', 'collapsed')
        } catch(e) {}
</script>