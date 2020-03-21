<html>
<head>
    <meta charset="UTF-8">
    <title>商品详情页</title>
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="row clearfix">
    <div class="col-md-4 column">
        <table class="table table-bordered table-hover">
            <thead>
            <tr>
                <th>订单ID</th>
                <th>总金额</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>${orderMasterDto.orderId}</td>
                <td>${orderMasterDto.orderAmount}</td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-bordered table-hover">
                <thead>
                <tr>
                    <th>商品id</th>
                    <th>商品名称</th>
                    <th>价格</th>
                    <th>数量</th>
                    <th>总额</th>
                </tr>
                </thead>
                <tbody>
                <#list orderMasterDto.orderDetailList as orderDetail>
                <tr>
                    <td>${orderDetail.productId}</td>
                    <td>${orderDetail.productName}</td>
                    <td>${orderDetail.productPrice}</td>
                    <td>${orderDetail.productQuantity}</td>
                    <td>${orderDetail.productPrice * orderDetail.productQuantity}</td>
                </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-12 column">
        <#if orderMasterDto.orderMasterEnums().mssage == "新订单">
            <a href="http://sellst.natapp1.cc/sell/seller/order/finish?orderId=${orderMasterDto.orderId}" class="btn btn-default btn-primary">完结订单</a>
            <a href="http://sellst.natapp1.cc/sell/seller/order/cancel?orderId=${orderMasterDto.orderId}" type="button" class="btn btn-default btn-danger">取消订单</a>
        </#if>
        </div>
    </div>
</div>
</body>
</html>