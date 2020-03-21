<html>
<#include "../common/header.ftl">

<body>
<div id="wrapper" class="toggled">

<#--边栏sidebar-->
    <#include "../common/nav.ftl">

<#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <form role="form" method="post" action="http://sellst.natapp1.cc/sell/seller/category/save">
                        <div class="form-group">
                            <label>类目名称</label>
                            <input name="categoryName" type="text" class="form-control" value="${(category.categoryName)!""}"/>
                        </div>

                        <div class="form-group">
                            <label>类目编号</label>
                            <input name="categoryType" type="text" class="form-control" value="${(category.categoryType)!""}"/>
                        </div>

                        <input hidden type="text" name="categoryId" value="${(category.categoryId)!''}">
                        <button type="submit" class="btn btn-default">Submit</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>