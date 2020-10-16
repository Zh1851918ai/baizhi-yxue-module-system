<%@page isELIgnored="false" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<script>
    $(function () {
        // 初始化用户数据表格
        $('#user-tt').jqGrid({
            url: '${path}/user/page',
            datatype: 'json',
            colNames: ['用户编号','账号名称','手机号','签名','头像','账户状态','注册时间','学分','绑定微信号'],
            styleUI: 'Bootstrap',
            colModel: [
                {name: 'id', editable: true},
                {name: 'username', editable: true},
                {name: 'mobile', editable: true},
                {name: 'sign', editable: true},
                {
                    name: 'headShow',
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
                /*{name: 'status', editable: true,edittype:'select',editoptions:{value:"1:冻结;2:正常"}},*/
                {name : 'status',editable: true,edittype:'select',editoptions:{value:"1:冻结;2:正常"},align:"center",
                    //参数：各子的值,操作,行对象
                    formatter:function(cellvalue, options, rowObject){
                        if(cellvalue==1){ //1=正常   绿色按钮   点击冻结
                            return "<button class='btn btn-success' onclick='updateStatus(\""+rowObject.id+"\",\""+rowObject.status+"\")'>正常</button>"
                        }else{ //0=不正常   红色按钮   点击解除冻结
                            return "<button class='btn btn-danger' onclick='updateStatus(\""+rowObject.id+"\",\""+rowObject.status+"\")'>冻结</button>"
                        }
                    }
                },


                {name: 'regTime', editable: true,edittype: 'date'},
                {name: 'score', editable: true},
                {name: 'wechat', editable: false},

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
                            $("#user-tt").trigger("reloadGrid");
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
                        $("#user-tt").trigger("reloadGrid");
                    }
                });
                return ['ok'];
            }
        },
            {}   //删除之后的额外操作
        );
    });

    //修改状态  id  status
    function updateStatus(id,status) {

        //1   改为0
        //0   改为1
        if(status==1){
            $.post("${path}/user/edit",{"id":id,"status":"1","oper":"edit"},function () {
                //刷新表单
                $("#userTable").trigger("reloadGrid");
            })
        }else{
            $.post("${path}/user/edit",{"id":id,"status":"2","oper":"edit"},function () {
                //刷新表单
                $("#userTable").trigger("reloadGrid");
            })
        }

    }



</script>
<div class="panel panel-info">

    <div class="panel panel-heading">
        <h2>用户信息</h2>
    </div>

    <form action="${path}/excl/userExport" method="post">
        <input type="submit" value="导出">
    </form>

    <form action="${path}/excl/userImport" method="post" enctype="multipart/form-data">
        <input type="file" name="multipartFile">
        <input type="submit" value="导入">
    </form>


    <table id="user-tt"></table>
    <div id="user-pager"></div>

</div>


