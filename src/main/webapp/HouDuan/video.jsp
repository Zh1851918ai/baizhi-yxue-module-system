<%@page isELIgnored="false" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<script>
    $(function () {
        // 初始化视频数据表格
        $('#video-tt').jqGrid({
            url: '${path}/video/query',
            datatype: 'json',
            colNames: ['id', '视频标题','简介','封面','视频','发布时间','用户','类别名称','分组','播放次数'],
            styleUI: 'Bootstrap',
            colModel: [
                {'name': 'id', editable: true},
                {'name': 'title', editable: true},
                {'name': 'intro', editable: true},
                {
                    name: 'coverUrl',
                    edittype: "file",
                    editable: true,
                    index: 'name asc, invdate',
                    width: 100,
                    align: "center",
                    //参数：各子的值,操作,行对象
                    formatter: function (cellvalue, options, rowObject) {
                        return "<img src='${path}/upload_file/img/"+cellvalue+"' width='64px' height='64px'>"
                    }
                },
                {name : 'videoUrl',editable:true,width : 180,align : "center",edittype:"file",formatter:
                    //参数：各子的值,操作,行对象
                        function(cellvalue, options, rowObject){
                            return "<video width='240px' height='120px' src='http://qhbevsi3y.hn-bkt.clouddn.com/" + cellvalue + "' controls poster='"+rowObject.videoUrl+"'/>";
                        }},

                /*{'name': 'videoUrl', editable: true},*/
                {'name': 'createTime', editable: true, edittype: 'date'},
                {'name': 'user.username', editable: false},
                {'name': 'category.name', editable: false},
                {'name': 'group.names', editable: false},
                {'name': 'play.play_num', editable: false},
            ],
            autowidth: true,
            mtype: 'get',
            pager: '#video-pager',
            rowList: [10, 20, 30],
            viewrecords: true,
            editurl: '${path}/video/edit',
            cellurl: '${path}/video/edit',
            multiselect: true,
            height: '300px',
            rownumbers: true,
            page: 1,
        }).jqGrid('navGrid', '#video-pager', {
                edit: true,
                add: true,
                del: true,
                edittext: "修改",
                addtext: "添加",
                deltext: "删除"
            },
            {
                closeAfterEdit:true,
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
                        url: "${path}/video/headUpload",
                        type: "post",
                        /**
                         * 需要在上传文件的时候，提交一个新添加数据的id,
                         *  由于我们是在信息添加成功后处理文件上传 ，所以需要根据id
                         *  修改一些文件在服务器的保存路径
                         */
                        data: {"id": id},
                        fileElementId: "videoUrl", //指定文件上传表单元素input的name属性值

                        success: function () {
                            //上传成功 所作的事情
                            //刷新页面
                            $("#userTable").trigger("reloadGrid");
                        }
                    });
                    return ['ok'];
                }
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
                        url: "${path}/video/headUpload",
                        type: "post",
                        /**
                         * 需要在上传文件的时候，提交一个新添加数据的id,
                         *  由于我们是在信息添加成功后处理文件上传 ，所以需要根据id
                         *  修改一些文件在服务器的保存路径
                         */
                        data: {"id": id},
                        fileElementId: "videoUrl", //指定文件上传表单元素input的name属性值

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
        <h2>视频信息</h2>
    </div>

    <table id="video-tt"></table>
    <div id="video-pager"></div>

</div>
