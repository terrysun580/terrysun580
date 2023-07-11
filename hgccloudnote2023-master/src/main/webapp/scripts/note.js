function showMoveNoteWindow(){
    //弹出对话框
    $(".opacity_bg").show();//显示背景
    var url = base_url+"/alert/alert_move.html"
    $("#can").load(url,function(){
        var bookList = $("#book_list li");//获取li集合
        for(var i = 0;i<bookList.length;i++){
            var bookName = $(bookList[i]).text();
            var bookId = $(bookList[i]).data("bookId");
            //拼一个option
            var s_opt = "<option value='"+bookId+"'>"+bookName+"</option>";
            //将option添加到下拉单中
            $("#moveSelect").append(s_opt)
        }
    });
    return false; //阻止冒泡
}

function viewShareNote() {
    //将笔记设为选中状态
    $("#search_list li a").removeClass("checked")
    $(this).find("a").addClass("checked");
    //获取shareId
    var shareId = $(this).data("shareId");
    //console.log(shareId);
    //发送ajax  Share
    $.ajax({
        url: base_url + "/note/loadShare.do",
        type:"post",
        data: {"shareId":shareId},
        dataType: "json",
        success: function(result){
            console.log(result)
            checkNoteDetailPage(5);//切换笔记详情页；
            var title = result.data.cn_share_title;
            var body = result.data.cn_share_body;
            $("#noput_note_title").html(title);
            $("#noput_note_body").html(body);
        },
        error:function(){
            alert("加载笔记信息失败")
        }
    })
}

function checkNoteDetailPage(index){
    $(".col-sm-7").hide();
    $("#pc_part_"+index).show()
}

function sureSearchShare(event) {
    var code = event.keyCode;
    //console.log(code);
    if(code == 13){//按回车键处理：
        //获取查询关键字
        var keyword = $("#search_note").val().trim();
        //发送ajax请求
        $.ajax({
            url: base_url + "/note/search.do",
            type:"post",
            data: {"keyword":keyword},
            dataType: "json",
            success: function(result){
                console.log(result)
                //切换列表
                showNoteList(6);
                //清空原有列表
                $("#search_list").empty();
                //展示列表
                //获取后台数据  list   拼接成一个li 追加到ul中
                var notes = result.data;
                for(var i=0;i<notes.length;i++){
                    var title = notes[i].cn_share_title;
                    var shareId = notes[i].cn_share_id;
                    //console.log(title)
                    //拼接一个li
                    var s_li  ='<li class="online">';
                    s_li  +='<a>';
                    s_li  +='<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom">'
                    s_li  +='</i>' + title;
                    s_li  +='</a>'
                    s_li  +='</li>';
                    //绑定shareId
                    var $li = $(s_li);
                    $li.data("shareId",shareId);
                    //将li添加到搜索结果区
                    $("#search_list").append($li);
                }
            },
            error:function(){
                alert("检索失败")
            }
        })
    }
}

function showNoteList(index) {
    //隐藏全部列表
    $(".col-xs-3").hide();
    //将指定列表打开
    $("#pc_part_" + index).show();
}

function sureShareNote(){
    var $li = $("#note_list a.checked").parent();
    var noteId = $li.data("noteId");
    $.ajax({
        url: base_url + "/note/share.do",
        type: "post",
        data: {"noteId": noteId},
        dataType: "json",
        success :function(result){
            alert(result.msg)
        },
        error:function(){
            alert("分享笔记失败")
        }
    })
}

function sureRecycleNote() {
    //noteId
    var $li = $("#note_list a.checked").parent();
    var noteId = $li.data("noteId");
    //console.log(noteId);
    $.ajax({
        url: base_url + "/recycle/recycle.do",
        type: "post",
        data: {"noteId":noteId},
        dataType: "json",
        success: function(result){
            console.log(result);
            if(result.status == 0){
                //删除 li
                $li.remove()
                //提示信息
                alert(result.msg);
            }
        },
        error: function(){
            alert("将笔记放入回收站失败");
        }
    });
}

function showNoteMenu() {
    $("#note_list").on("click",".btn_slide_down",function(){
        //console.log($(this))
        //将所有笔记列表隐藏
        $("#note_list .note_menu").hide();
        //显示当前点击按钮的菜单
        var $li = $(this).parents("li");
        var $menu = $li.find(".note_menu");
        $menu.slideDown(1000);
    });
    //追加鼠标对菜单的处理
    $("#note_list").on("mouseover",".note_menu",function(){
        $(this).show();//保持显示状态
    });
    $("#note_list").on("mouseout",".note_menu",function(){
        $(this).hide();//隐藏菜单
    });
}

function sureUpdateNote(){

    //检查是否选中笔记
    var $li = $("#note_list li a.checked").parent();
    if($li.length == 0){
        alert("请选择要保存的笔记")
    }else{
        //获取请求参数
        var noteId = $li.data("noteId");
        var noteTitle = $("#input_note_title").val().trim()
        var noteBody = um.getContent();

        $.ajax({
            url: base_url + "/note/update.do",
            type: "post",
            data:{"noteId":noteId,"noteTitle":noteTitle,"noteBody":noteBody},
            dataType: "json",
            success: function(result){
                //console.log(result);
                if(result.status == 0){
                    //更新笔记的li
                    var str  ='<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>' + noteTitle;
                    str  +='<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down">'
                    str  +='<i class="fa fa-chevron-down"></i></button>';
                    $li.find("a").html(str);
                    //提示成功
                    alert(result.msg);
                }
            },
            error: function(){
                alert("更新笔记失败");
            }
        });
    }
}

function sureAddNote() {
    //获取 noteTitle  bookId   userId
    var noteTitle = $("#input_note").val().trim();
    var $li = $("#book_list li a.checked").parent();
    var bookId = $li.data("bookId");
    //console.log(bookId,noteTitle);
    $.ajax({
        url: base_url + "/note/add.do",
        type: "post",
        data:{"bookId":bookId,"userId":userId,"noteTitle":noteTitle},
        dataType: "json",
        success: function(result){
            //console.log(result);
            closeWindow();//关闭窗口
            var noteId = result.data;//获取noteId
            createNoteLi(noteTitle,noteId);//创建一个新的笔记li
            alert(result.msg);//提示成功
        },
        error:function(){
            alert("添加笔记失败")
        }
    });
}

function createNoteLi(noteTitle,noteId) {
    var s_li  ='<li class="online">';
    s_li  +='<a>';
    s_li  +='<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>' + noteTitle;
    s_li  +='<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down">'
    s_li  +='<i class="fa fa-chevron-down"></i></button>'
    s_li  +='</a>'
    s_li  +='<div class="note_menu" tabindex="-1">'
    s_li  +='<dl>'
    s_li  +='<dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt>'
    s_li  +='<dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>'
    s_li  +='<dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>'
    s_li  +='</dl>'
    s_li  +='</div>'
    s_li  +='</li>';
    //将noteId绑定到li上
    var $li = $(s_li);
    $li.data("noteId",noteId);
    //将li追加到ul中
    $("#note_list").append($li)
}
function loadNoteDetail() {
    $("#note_list li a").removeClass("checked")
    $(this).find("a").addClass("checked");
    //获取请求参数
    var noteId = $(this).data("noteId");
    //发送ajax
    $.ajax({
        url: base_url +"/note/load.do",
        type: "post",
        data: {"id":noteId},
        dataType: "json",
        success: function(result){
            //console.log(result);
            if(result.status == 0){
                var noteTitle = result.data.cn_note_title;
                var noteBody = result.data.cn_note_body;
                //设置标题和内容
                $("#input_note_title").val(noteTitle)
                um.setContent(noteBody)
            }
        },
        error: function(){
            alert("显示笔记详情失败")
        }
    });
}

function loadBookNotes(){
    //将点中的笔记本设置为选中模式
    $("#book_list li a").removeClass("checked")
    $(this).find("a").addClass("checked");
    //获取bookId
    var bookId = $(this).data("bookId");
    $.ajax({
        url: base_url +"/note/loadnotes.do",
        type: "post",
        data:{"bookId":bookId},
        dataType: "json",
        success: function(result){
            if(result.status == 0){
                var notes = result.data;//获取笔记列表
                //在展示新的笔记列标前，清除原有笔记列表；
                $("#note_list").empty();
                for(var i=0;i<notes.length;i++){
                    //获取笔记的 id  title;
                    var noteTitle = notes[i].cn_note_title;
                    var noteId = notes[i].cn_note_id;
                    //拼成一个笔记li
                    var s_li  ='<li class="online">';
                    s_li  +='<a>';
                    s_li  +='<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>' + noteTitle;
                    s_li  +='<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down">'
                    s_li  +='<i class="fa fa-chevron-down"></i></button>'
                    s_li  +='</a>'
                    s_li  +='<div class="note_menu" tabindex="-1">'
                    s_li  +='<dl>'
                    s_li  +='<dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt>'
                    s_li  +='<dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>'
                    s_li  +='<dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>'
                    s_li  +='</dl>'
                    s_li  +='</div>'
                    s_li  +='</li>';
                    //将noteId绑定到li上
                    var $li = $(s_li);
                    $li.data("noteId",noteId);
                    //将li追加到ul中
                    $("#note_list").append($li)
                }
            }
        },
        error: function(){
            alert("加载笔记列表失败")
        }
    });
}