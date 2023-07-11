function showRecycleNoteWindow(){
    //弹出对话框
    $(".opacity_bg").show();//显示背景
    var url = base_url+"/alert/alert_delete_note.html"
    $("#can").load(url);
    return false; //阻止冒泡
}
function showAddNoteWindow() {
    //判断是否有选中的笔记本
    var $bookLi = $("#book_list a.checked");
    if($bookLi.length==0){
        alert("请选择笔记本");
    }else{
        //弹出对话框
        $(".opacity_bg").show();//显示背景
        var url =
            base_url+"/alert/alert_note.html";
        $("#can").load(url);
    }

}

function closeWindow() {
    //隐藏背景
    $(".opacity_bg").hide();
    //清空对话框内容
    $("#can").empty();
}

function showAddBookWindow() {

    //弹出对话框
    $(".opacity_bg").show();//显示背景
    var url = base_url+"/alert/alert_notebook.html"
    $("#can").load(url);

}