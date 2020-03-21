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
                            <th>订单id</th>
                            <th>姓名</th>
                            <th>手机号</th>
                            <th>地址</th>
                            <th>金额</th>
                            <th>订单状态</th>
                            <th>支付状态</th>
                            <th>创建时间</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>
            <#list orderMaster.content as orderDTO>
            <tr>
                <td>${orderDTO.orderId}</td>
                <td>${orderDTO.buyerName}</td>
                <td>${orderDTO.buyerPhone}</td>
                <td>${orderDTO.buyerAddress}</td>
                <td>${orderDTO.orderAmount}</td>
                <td>${orderDTO.orderMasterEnums().mssage}</td>
                <td>${orderDTO.orderMasterPayEnums().message}</td>
                <td>${orderDTO.createTime}</td>
                <td><a href="http://sellst.natapp1.cc/sell/seller/order/detail?orderId=${orderDTO.orderId}">详情</a></td>
                <td>
                    <#if orderDTO.orderMasterEnums().mssage == "新订单">
                        <a href="http://sellst.natapp1.cc/sell/seller/order/cancel?orderId=${orderDTO.orderId}">取消</a></#if>
                </td>
            </tr>
            </#list>
                        </tbody>
                    </table>
                </div>

                <!-- 分页 -->
                <div class="col-md-12 column">
                    <ul class="pagination pull-right">
                    <#--首页逻辑-->
                <#if currentPage lte 1>
                    <li class="disabled"><a href="#">上一页</a></li>
                <#else >
                     <li><a href="http://sellst.natapp1.cc/sell/seller/order/list?page=${currentPage-1}&size=${size}">上一页</a>
                     </li>
                </#if>

                    <#--循环-->
                <#list 1..orderMaster.getTotalPages() as index>
                    <#if currentPage == index>
                        <li class="disabled"><a href="#">${index}</a></li>
                    <#else>
                        <li>
                            <a href="http://sellst.natapp1.cc/sell/seller/order/list?page=${index}&size=${size}">${index}</a>
                        </li>
                    </#if>
                </#list>

                    <#--下一页逻辑-->
                <#if currentPage gte orderMaster.getTotalPages()>
                    <li class="disabled"><a href="#">下一页</a></li>
                <#else >
                     <li><a href="http://sellst.natapp1.cc/sell/seller/order/list?page=${currentPage+1}&size=${size}">下一页</a>
                     </li>
                </#if>
                    </ul>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>



