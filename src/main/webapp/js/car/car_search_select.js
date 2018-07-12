/**
 * Created by Satani on 2017/2/17.
 */
$(document).ready(initMakerItem());
/*初始化车辆品牌视图栏目*/
function initMakerItem() {

    var url = "/getAllMakers";
    $.ajax({
        url: url,
        dataType: "json",
        success: function (data) {
            initSelectMaker(data.makers);
        }
    });
}
/*初始化车辆品牌选择*/
function initSelectMaker(makers) {
    var maker_list=document.getElementById("maker_item");
    makers.sort();
    makers.forEach(function (maker) {

            var dd = document.createElement("dd");
            var a=document.createElement("a");
            a.setAttribute("href","#");
            a.innerText=maker;
            dd.appendChild(a);

            a.onclick=function () {
                initTypeItem(this.innerText) ;
            };
            maker_list.appendChild(dd);


    });
    initMakerList();
}
/*初始化车辆类型视图栏目*/
function initTypeItem(maker) {
    var url="/getTypesByMaker";
    $.ajax({
        url:url,
        dataType:"json",
        data:{
            maker:maker
        },
        success:function (data) {
            var type_list=document.getElementById("type_item");
            type_list.innerHTML="";

            var baseDT=document.createElement("dt");
            baseDT.innerText="车系:";
            type_list.appendChild(baseDT);

            var baseDD=document.createElement("dd");
            baseDD.setAttribute("class","select-all selected");
            var baseA=document.createElement("a");
            baseA.setAttribute("href","#");
            baseA.innerText="不限";
            baseDD.appendChild(baseA);
            type_list.appendChild(baseDD);

            var types=data[maker];
            types.forEach(function (type) {
                var dd=document.createElement("dd");
                var a=document.createElement("a");
                a.setAttribute("href","#");
                a.innerText=type;
                addClickEvent(a);
                dd.appendChild(a);
                type_list.appendChild(dd);
            });
            initTypeList();
        }
    });
}
/*添加车辆品牌栏目样式*/
function initMakerList() {
    $("#maker_item").find("dd").click(function () {
        $(this).addClass("selected").siblings().removeClass("selected");
        if ($(this).hasClass("select-all")) {
            $("#selectA").remove();
        } else {
            var copyThisA = $(this).clone();
            if ($("#selectA").length > 0) {
                $("#selectA").find("a").html($(this).text());
            } else {
                $(".select-result dl").append(copyThisA.attr("id", "selectA"));
            }
        }
    });

    $("#selectA").on("click",null, function () {
        $(this).remove();
        $("#maker_item .select-all").addClass("selected").siblings().removeClass("selected");
    });

    $(".select dd").on("click", null,function () {
        if ($(".select-result dd").length > 1) {
            $(".select-no").hide();
        } else {
            $(".select-no").show();
        }
    });
}
/*添加车辆类型栏目样式*/
function initTypeList() {
    $("#type_item dd").click(function () {
        $(this).addClass("selected").siblings().removeClass("selected");
        if ($(this).hasClass("select-all")) {
            $("#selectB").remove();
        } else {
            var copyThisB = $(this).clone();
            if ($("#selectB").length > 0) {
                $("#selectB a").html($(this).text());
            } else {
                $(".select-result dl").append(copyThisB.attr("id", "selectB"));
            }
        }
    });

    $("#selectB").on("click",null, function () {
        $(this).remove();
        $("#type_item .select-all").addClass("selected").siblings().removeClass("selected");
    });
}
/*搜索*/
function addClickEvent(obj) {
    obj.onclick=function () {
        getSource(obj.innerText,obj.innerText);
    }
}


