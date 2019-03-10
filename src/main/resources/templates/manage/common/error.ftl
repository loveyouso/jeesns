<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <title>错误 - ${SITE_NAME} - JEESNS后台管理系统 - Powered By JEESNS</title>
    <meta name="keywords" content="${SITE_KEYS}"/>
    <meta name="description" content="${SITE_DESCRIPTION}"/>
    <meta name="author" content="JEESNS"/>
    <link href="${basePath}/static/res/common/css/bootstrap.min.css" rel="stylesheet">
    <link href="${basePath}/static/res/common/css/font-awesome.min.css" rel="stylesheet">
    <link href="${basePath}/static/res/manage/css/AdminLTE.css" rel="stylesheet">
    <link href="${basePath}/static/res/manage/css/skins/_all-skins.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="${basePath}/static/res/common/js/html5shiv.min.js"></script>
    <script src="${basePath}/static/res/common/js/respond.min.js"></script>
    <![endif]-->
    <script src="${basePath}/static/res/common/js/jquery-2.1.1.min.js"></script>
    <script src="${basePath}/static/res/common/js/bootstrap.min.js"></script>
    <script src="${basePath}/static/res/plugins/layer/layer.js"></script>
    <script src="${basePath}/static/res/manage/js/app.js"></script>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
<#include "/manage/common/header.ftl"/>
    <div class="content-wrapper">
        <section class="content">
            <div class="box-body">
                <div class="row">
                    <div class="alert alert-error alert-dismissible text-center">
                        ${msg}
                    </div>
                </div>
            </div>
        </section>
    </div>
<#include "/manage/common/footer.ftl"/>
</div>
</body>
</html>
