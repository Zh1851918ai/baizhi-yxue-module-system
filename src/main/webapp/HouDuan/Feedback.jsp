<%@page isELIgnored="false" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<script>
    $(function () {
        // 初始化用户数据表格
        $('#user-tt').jqGrid({
            url: '${path}/feedback/page',
            datatype: 'json',
            colNames: ['编号','标题','内容','时间','用户'],
            styleUI: 'Bootstrap',
            colModel: [
                {name: 'id', editable: true},
                {name: 'title', editable: true},
                {name: 'content', editable: true},
                {name: 'createTime', editable: true,edittype: 'date'},
                {name: 'user.username', editable: true}, ,

            ],
            autowidth: true,
            mtype: 'get',
            pager: '#user-pager',
            rowList: [10, 20, 30],
            rowNum:3,
            viewrecords: true,
            editurl: '${path}/user/edit',
            cellurl: '${path}/user/edit',
            multiselect: true,
            height: '300px',
            rownumbers: true,
            page: 1,
        }).jqGrid('navGrid', '#user-pager', {
                edit: true,
                add: true,
                del: true,
                edittext: "修改",
                addtext: "添加",
                deltext: "删除"
            },
            {
                closeAfterEdit:true
            },  //修改之后的额外操作
            {//添加之后的额外操作
                // 添加的同时提交额外参数
                editData:{id:''},
                closeAfterAdd: true,//关闭添加框
                afterSubmit: function (data) {  //添加成功之后执行的内容
                    //1.数据入库    文件数据不对   文件没有上传
                    //2.文件异步上传   添加成功之后  上传
                    //3.修改文件路径   （id,要的的字段内容）
                    //id=  data.responseText

                    /**
                     * data是添加完成后，响应回来的结果
                     */
                    /* console.log(data.responseText)
                     let id =data.responseText;*/
                    console.log(data.responseJSON.rows.id)
                    let id = data.responseJSON.rows.id;

                    $.ajaxFileUpload({
                        url: "${path}/user/headUpload",
                        type: "post",
                        /**
                         * 需要在上传文件的时候，提交一个新添加数据的id,
                         *  由于我们是在信息添加成功后处理文件上传 ，所以需要根据id
                         *  修改一些文件在服务器的保存路径
                         */
                        data: {"id": id},
                        fileElementId: "headShow", //指定文件上传表单元素input的name属性值

                        success: function () {
                            //上传成功 所作的事情
                            //刷新页面
                            $("#userTable").trigger("reloadGrid");
                        }
                    });
                    return ['ok'];
                }
            },
            {}   //删除之后的额外操作
        );
    });
</script>
<div class="panel panel-info">

    <div class="panel panel-heading">
        <h2>用户信息</h2>
    </div>

    <form action="${path}/excl/export" method="post">
        <input type="submit" value="导出">
    </form>

    <form action="${path}/excl/import" method="post" enctype="multipart/form-data">
        <input type="file" name="multipartFile">
        <input type="submit" value="导入">
    </form>

    <table id="user-tt"></table>
    <div id="user-pager"></div>

</div>


