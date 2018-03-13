jQuery.extend({
    alertWindow:function(e,n){var e=e,r;n===undefined?r="#00a8b7":r=n;
        if($("body").find(".alertWindow1").length===0){
            var i='<div class="alertWindow1" ' +
                'style="width: 100%;height: 100%; ' +
                'background:rgba(0,0,0,0.5);position: fixed; ' +
                'left:0px; top: 0px; z-index: 9999;">' +
                '<div  style="width: 360px; height: 120px;background: #66ccff;' +
                'margin: 300px auto;border: none;border-radius: 10px">'+
                '<div  style="width: inherit;height: 20px;">'+
                '<div class="alertWindowCloseButton1" ' +
                'style="float: right; width: 10px; height: 30px;margin-right:5px;' +
                'font-family:\'microsoft yahei\';color:'+r+';cursor: pointer;"></div>'+
                "</div>"+'<div id="successImg" class="alertWindowTitle" ' +
                'style="margin-top:10px;text-align:center;' +
                'font-family:\'Verdana, Geneva, Arial, Helvetica, sans-serif\';' +
                'font-size: 18px;font-weight: normal;color: '+r+';">'+"</div>"+
                '<div class="alertWindowContent" style="width:360px;height: 30px;text-align:center;' +
                'font-size: 24px;color: white;margin-top:10px;">'+e+"</div>"+"</div>"+"</div>";
            $("body").append(i);
            var s=$(".alertWindow1");
            //2秒后自动关闭窗口
            setTimeout(function(){s.hide()},2000);
        }
        else {$(".alertWindowContent").text(e),$(".alertWindow1").show(),setTimeout(function(){$(".alertWindow1").hide()},1000);}
    }
});

function myClose(t){
    $(t).hide();
}

/**
 js由毫秒数得到年月日
 使用： (new Date(data[i].creationTime)).Format("yyyy-MM-dd hh:mm:ss.S")
 */

Date.prototype.Format = function (fmt) { //author: tony
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
};



//定义函数--从给定的毫秒数中取出年、月、日进行拼接
function getMyDate(str){
    var oDate = new Date(str),
        oYear = oDate.getFullYear(),
        oMonth = oDate.getMonth()+1,
        oDay = oDate.getDate(),
        oHour = oDate.getHours(),
        oMin = oDate.getMinutes(),
        oSen = oDate.getSeconds(),
        oTime = oYear +'-'+ getzf(oMonth) +'-'+ getzf(oDay) +' '+ getzf(oHour) +':'+ getzf(oMin)+':'+getzf(oSen);//最后拼接时间
    return oTime;
};
//定义函数--补0操作
function getzf(num){
    if(parseInt(num) < 10){
        num = '0'+num;
    }
    return num;
}

function jsGetAge(startDate)
{
    // 获得今天的时间
    var date = new Date();
    startDate = new Date(startDate);
    var newDate = date.getTime() - startDate.getTime();
    // 向下取整  例如 10岁 20天 会计算成 10岁
    // 如果要向上取整 计算成11岁，把floor替换成 ceil
    return Math.floor(newDate / 1000 / 60 / 60 / 24 / 365);

}