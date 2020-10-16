<%@page contentType="text/html;charset=utf8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<script>
    $(function () {
        // 初始化用户数据表格
        $('#category-tt').jqGrid({
            url: '${path}/comment/queryOne',
            datatype: 'json',
            colNames: ['编号', '评论名称','时间','视频','用户'],
            styleUI: 'Bootstrap',
            colModel: [
                {name: 'id', editable: true,align: "center"},
                {name: 'content', editable: true,align: "center"},
                {name: 'createTime', editable: true,align: "center",edittype: 'date'},
                {name : 'video.videoUrl',editable:false,width : 180,align : "center",edittype:"file",formatter:
                    //参数：各子的值,操作,行对象
                        function(cellvalue, options, rowObject){
                            return "<video width='240px' height='120px' src='http://qhbevsi3y.hn-bkt.clouddn.com/" + cellvalue + "' controls poster='"+rowObject.video.videoUrl+"'/>";
                        }},
                {name: 'user.username', editable: false,align: "center"},
            ],
            autowidth: true,
            afterSearch: function () {
                closeAfterAdd: true
            },
            mtype: 'get',
            pager: '#category-pager',
            rowList: [3, 6, 9],
            rowNum: 3,
            viewrecords: true,
            editurl: '${path}/comment/edit',
            cellurl: '${path}/comment/edit', // 编辑
            multiselect: true,
            height: '400px',
            rownumbers: true,
            page: 1,
            pgbuttons: true,
            viewrecords : true,
            subGrid : true,
            caption : "Grid as Subgrid",
            subGridRowExpanded : function(subgrid_id, row_id) {
                var subgrid_table_id, pager_id;
                subgrid_table_id = subgrid_id + "_t";
                pager_id = "p_" + subgrid_table_id;
                $("#" + subgrid_id).html(
                    "<table id='" + subgrid_table_id
                    + "' class='scroll' style='text-align: center'></table><div id='"
                    + pager_id + "' class='scroll'></div>");
                jQuery("#" + subgrid_table_id).jqGrid(
                    {
                        url : '${path}/comment/queryTwo?interact_id='+row_id,
                        editurl: '${path}/comment/edit',
                        /* cellurl: '${path}/category/edit?id=' + row_id,*/
                        datatype : "json",
                        styleUI: 'Bootstrap',
                        colNames : [ '编号', '评论名称', '时间', ],
                        colModel : [
                            {name : "id",  index : "num",width : 80,key : true},
                            {name : "content",  width : 130 ,editable: true,searchoptions:{sopt:["eq","cn"]}},
                            {name : "createTime",editable: false,index : "qty",width : 70,align : "right"},
                        ],
                        autowidth: true,
                        rowNum : 2,
                        pager : pager_id,
                        sortname : 'num',
                        sortorder : "asc",
                        height : '100%',
                        rowList: [2,6,9],
                        page: 1
                    });
                jQuery("#" + subgrid_table_id).jqGrid('navGrid',
                    "#" + pager_id, {
                        edit : true,
                        add : true,
                        del : true,
                        search: true,
                    },{
                        closeAfterEdit: true
                    },  //修改之后的额外操作
                    {
                        //关闭对话框
                        closeAfterAdd:true,
                    },  //添加之后的额外操作
                    {
                        closeAfterAdd:true
                    }, //删除之后的额外操作
                    {
                        closeAfterSearch: true
                    }
                );
            }
        }).jqGrid('navGrid', '#category-pager',{
                edit: true,
                add: true,
                del: true,
                search: true,
                edittext:"修改",
                addtext:"添加",
                deltext:"删除"
            },
            {
                closeAfterEdit: true
            },  //修改之后的额外操作
            {
                //关闭对话框
                editData :{'id':''},
                closeAfterAdd:true,
            },  //添加之后的额外操作
            {
                closeAfterAdd:true
            },   //删除之后的额外操作
            {
                closeAfterSearch:true
            }
        );
    });

</script>

<div class="panel panel-info">

    <div class="panel panel-heading">
        <h2>类别信息</h2>
    </div>

    <table id="category-tt"></table>
    <div id="category-pager"></div>

</div>



