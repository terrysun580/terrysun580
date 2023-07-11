function sureAddBook() {

    //获取笔记本名称
    var bookName = $("#input_notebook").val().trim();
    //console.log(bookName);
    $.ajax({
        url: base_url+"/notebook/add.do",
        type: "post",
        data: {"userId":userId,"bookName":bookName},
        dataType: "json",
        success: function(result){
            //console.log(result)
            //关闭对话框
            closeWindow();
            //添加一个笔记本li
            var bookId = result.data;
            createBookLi(bookName,bookId);
            //提示成功
            alert("添加笔记本成功")
        },
        error:function(){
            alert("添加笔记本失败")
        }

    });

}

function createBookLi(bookName,bookId) {
    var s_li ='<li class="online">'
    s_li +='<a>'
    s_li +='<i class="fa fa-book" title="online" rel="tooltip-bottom">'
    s_li +='</i>'+bookName+'</a>'
    s_li +='</li>'
    var $li = $(s_li);
    //将bookId绑定给$li
    $li.data("bookId",bookId);
    //将li添加到ul中
    $("#book_list").append($li);
}

function loadUserBooks(){
    $.ajax({
        url: base_url + "/notebook/loadbooks.do",
        type: "post",
        data: {"userId":userId},
        dataType: "json",
        success: function(result){
            //console.log(result);
            if(result.status == 0){
                var books = result.data;
                for(var i = 0;i < books.length;i++){
                    //获取笔记本名称  和 对应ID
                    var bookName = books[i].cn_notebook_name;
                    var bookId = books[i].cn_notebook_id;
                    //拼一个li
                    var s_li ='<li class="online">'
                    s_li +='<a>'
                    s_li +='<i class="fa fa-book" title="online" rel="tooltip-bottom">'
                    s_li +='</i>'+bookName+'</a>'
                    s_li +='</li>'
                    var $li = $(s_li);
                    //将bookId绑定给$li
                    $li.data("bookId",bookId);
                    //将li添加到ul中
                    $("#book_list").append($li);
                }
            }
        },
        error: function(){
            alert("加载笔记本列表失败")
        }
    });
}