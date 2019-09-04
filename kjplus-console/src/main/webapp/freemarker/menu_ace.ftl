<script type="text/javascript">
        try {
                ace.settings.check('sidebar', 'fixed')
        } catch(e) {}
</script>
<div class="sidebar-shortcuts" id="sidebar-shortcuts">
        <div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
                <button class="btn btn-success">
                        <i class="icon-signal">
                        </i>
                </button>
                <button class="btn btn-info">
                        <i class="icon-pencil">
                        </i>
                </button>
                <button class="btn btn-warning">
                        <i class="icon-group">
                        </i>
                </button>
                <button class="btn btn-danger">
                        <i class="icon-cogs">
                        </i>
                </button>
        </div>
        <div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
                <span class="btn btn-success">
                </span>
                <span class="btn btn-info">
                </span>
                <span class="btn btn-warning">
                </span>
                <span class="btn btn-danger">
                </span>
        </div>
</div>
<!-- #sidebar-shortcuts -->
<!-- .nav-list -->
<ul class="nav nav-list">
        <#list funcs as f0>
                <li class='open'>
                        <a href="#" class="dropdown-toggle">
                                <img src="${base}/${f0.icon}" style="width:25px">
                                <span class="menu-text">
                                        ${f0.name}
                                </span>
                                <b class="arrow icon-angle-down">
                                </b>
                        </a>
                        <ul class="submenu" style="display: block;">
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
                ace.settings.check('sidebar', 'collapsed')
        } catch(e) {}
</script>