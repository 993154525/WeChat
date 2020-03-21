<html>
<#include "../common/header.ftl">
<body>

<div id="wrapper" class="toggled">
    <!-- 侧边栏 -->
    <#include "../common/nav.ftl">

    <!-- 主题内容 -->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th>类目id</th>
                            <th>名字</th>
                            <th>类型</th>
                            <th>创建时间</th>
                            <th>修改时间</th>
                            <th>修改</th>
                        </tr>
                        </thead>
                        <tbody>
            <#list categoryServiceAll as categoryService>
            <tr>
                <td>${categoryService.categoryId}</td>
                <td>${categoryService.categoryName}</td>
                <td>${categoryService.categoryType}</td>
                <td>${categoryService.createTime}</td>
                <td>${categoryService.updateTime}</td>
                <td><a href="http://sellst.natapp1.cc/sell/seller/category/index?categoryId=${categoryService.categoryId}">修改</a></td>
            </tr>
            </#list>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>