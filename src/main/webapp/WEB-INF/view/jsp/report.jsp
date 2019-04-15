<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Title</title>
    <script src="js/jquery-1.9.0.js"></script>
    <script>
        $(function () {
            // 导入
            $("#import").on("click", function () {
                alert("导入");
                <%--$.ajax({--%>
                    <%--url:'${pageContext.request.contextPath}/importExcel',--%>
                    <%--type:'post',--%>
                    <%--data:${"#file"},--%>
                    <%--async:true,--%>
                    <%--enctype:'multipart/form-data',--%>
                    <%--dataType:'text',--%>
                    <%--success: function (data) {--%>
                        <%--alert(data);--%>
                    <%--},--%>
                    <%--error:function () {--%>
                        <%--alert("upload failed!");--%>
                    <%--}--%>
                <%--});--%>
            });

            //导出
            $("#export").on("click", function () {
                $.ajax({
                    url:'${pageContext.request.contextPath}/exportExcel',
                    type:'post',
                    contentType: 'application/ms-excel;charset=utf-8',
                    headers : {'Content-Disposition':'attachment;filename=testX.xlsx'},
                    async:true,
                    // datatype:'json',
                    success:function (data) {
                        // alert(data);
                        alert("success");
                    },
                    error:function (data) {
                        // alert(data);
                        alert("error");
                    }
                });
            });


           $("#export").on("click", function () {
                var url = '${pageContext.request.contextPath}/exportExcel';
                var xhr=null;
                try {
                    xhr=new XMLHttpRequest()
                }catch(e) {
                    xhr=new ActiveXObject("Microsoft.XMLHTTP")
                }
                xhr.open('POST', url, true);
                xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                xhr.responseType = "blob"; // 返回类型blob
                // 定义请求完成的处理函数，请求前也可以增加加载框/禁用下载按钮逻辑
                xhr.onload = function () {
                    // 请求完成
                    if (this.status === 200) {//返回200
                        var blob = this.response;
                        var reader = new FileReader();
                        reader.readAsDataURL(blob);    //转换为base64，可以直接放入a表情href
                        reader.onload = function (e) {
                            // 转换完成，创建一个a标签用于下载
                            var a = document.createElement('a');
                            a.download = '客户列表.xlsx';
                            a.href = e.target.result;
                            $("body").append(a); //修复firefox中无法触发click
                            a.click();
                            $(a).remove();
                        }
                    }};
                xhr.send();
            });

        });


    </script>
</head>
<body>

<form action="importExcel" method="post" enctype="multipart/form-data">
    导入excel:<input id="file" type="file" name="file">
    <input type="submit" value="提交">
</form>
<%--<button id="import">提交</button><br/>--%>
<button id="export">导出excel</button>

</body>
</html>
