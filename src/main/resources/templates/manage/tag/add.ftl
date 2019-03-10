<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>添加标签 - ${SITE_NAME} - JEESNS后台管理系统 - Powered By JEESNS</title>
    <meta name="keywords" content="${SITE_KEYS}"/>
    <meta name="description" content="${SITE_DESCRIPTION}"/>
    <meta name="author" content="JEESNS"/>
    <link href="${basePath}/static/res/common/css/bootstrap.min.css" rel="stylesheet">
    <link href="${basePath}/static/res/common/css/font-awesome.min.css" rel="stylesheet">
    <link href="${basePath}/static/res/manage/css/AdminLTE.css" rel="stylesheet">
    <link href="${basePath}/static/res/manage/css/skins/_all-skins.css" rel="stylesheet">
    <link href="${basePath}/static/res/plugins/layer/skin/layer.css" rel="stylesheet">
    <link href="${basePath}/static/res/plugins/iCheck/all.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="${basePath}/static/res/common/js/html5shiv.js"></script>
    <script src="${basePath}/static/res/common/js/respond.min.js"></script>
    <![endif]-->
    <script src="${basePath}/static/res/common/js/jquery-2.1.1.min.js"></script>
    <script src="${basePath}/static/res/common/js/jquery.form.js"></script>
    <script src="${basePath}/static/res/common/js/bootstrap.min.js"></script>
    <script src="${basePath}/static/res/manage/js/app.js"></script>
    <script src="${basePath}/static/res/plugins/layer/layer.js"></script>
    <script src="${basePath}/static/res/common/js/jeesns.js"></script>
</head>
<body class="hold-transition">
<div class="wrapper">
    <section class="content">
        <div class="row">
            <div class="col-md-12">
                <form class="form-horizontal jeesns_form" role="form" action="${managePath}/tag/save" method="post" callback="parentReload">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">名称</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="name" name="name" data-type="require">
                            <input type="hidden" class="form-control" id="funcType" name="funcType" value="${funcType}">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-info jeesns-submit">保存</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </section>
</div>
</body>
</html>