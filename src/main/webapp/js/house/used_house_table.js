/**
 * Created by zsyoung on 2017/2/27.
 */
/**
 * 查询二手房信息并以表格形式展示出来
 * @param address 模糊地址
 */
var search_val="";
var excelUrl="";

function init() {
    $('input').blur(function(){
        var input_val=$('#detail_search').val();//原本的输入值
        search_val=input_val;
        initView(search_val);
    });

}
/**
 *
 * @param address
 */
function initView(address) {

    var url = "/getUsedHouseTable";
    $.ajax({
        url: url,
        dataType: "json",
        data: {
            address: address
        },
        success: function (data) {
            addHouseBlocks(data);
        }
    });
}
/**
 *
 * @param data
 */
function addHouseBlocks(data) {
    initHousePage(showHouseBlocks(data));
}
/**
 *
 * @param houses
 * @returns {Array}
 */
function showHouseBlocks(houses) {
    var  blocks=[];
    houses.forEach(function (house) {
        blocks.push(initHouseBlock(house));
    });

    return blocks;
}
/**
 *
 * @param house
 * @returns {Element}
 */
function initHouseBlock(house) {
    var table = document.createElement("table");
    table.setAttribute("class", "table table-hover");
    table.setAttribute("id","house_table");
    table.appendChild(getTbody(house.id, house.title, house.houseName, house.address, house.houseType, house.area, house.totalPrice, house.unitPrice));
    return table;
}
/**
 *
 * @returns {Element}
 */
function getButton() {
    var div = document.createElement("div");
    div.setAttribute("id","button_div");
    div.setAttribute("style","text-align:center;width:80%;");

    var addButton1 = document.createElement("button");
    addButton1.innerText = "添加";
    var alterButton1 = document.createElement("button");
    alterButton1.onclick=function () {
        alter();
    };
    alterButton1.innerText = "修改";
    var deleteButton1 = document.createElement("button");
    deleteButton1.innerText = "删除";
    var exportButton1 = document.createElement("button");
    exportButton1.innerText="导出为EXCEL";
    addButton1.setAttribute("class", "btn btn-info");
    addButton1.setAttribute("data-toggle","modal");
    addButton1.setAttribute("data-target","#myModal");
    alterButton1.setAttribute("class", "btn btn-warning");
    deleteButton1.setAttribute("class", "btn btn-danger");
    deleteButton1.onclick=function () {
      del();
    };
    exportButton1.setAttribute("class","btn btn-success");
    //添加按钮，点击后弹出模态框可以添加信息
    addButton1.onclick=function () {
       
        var modelDiv = document.getElementById("model_window");
        modelDiv.innerHTML="";
        modelDiv.appendChild(buildModalBox());
        var commitButton = document.getElementById("commit_button");
        //给提交更改按钮添加事件 把input里输入的值传过去
        commitButton.onclick=function() {
            addInfo($("#title_text").val(),$("#house_name_text").val(),$("#address_text").val(),$("#house_type_text").val(),$("#area_text").val(),$("#total_price_text").val(),$("#unit_price_text").val());
        }

    };
    //给导出数据按钮添加单击事件
    exportButton1.onclick=function(){
        exportInfo(search_val,excelUrl);
    };
    div.appendChild(addButton1);
    div.appendChild(alterButton1);
    div.appendChild(deleteButton1);
    div.appendChild(exportButton1);
    return div;
}
/**
 *
 * @returns {Element}
 */
function buildModalBox() {

    var fiveButton1Close=document.createElement("button");
    fiveButton1Close.setAttribute("type","button");
    fiveButton1Close.setAttribute("class","close");
    fiveButton1Close.setAttribute("data-dismiss","modal");
    fiveButton1Close.setAttribute("aria-hidden","true");


    var fiveH=document.createElement("h4");
    fiveH.setAttribute("class","modal-title");
    fiveH.setAttribute("id","myModalLabel");
    fiveH.innerText="添加一条数据";

    var fourDiv1=document.createElement("div");
    fourDiv1.setAttribute("class","modal-header");
    fourDiv1.appendChild(fiveButton1Close);
    fourDiv1.appendChild(fiveH);


    var fourDiv2=document.createElement("div");
    fourDiv2.setAttribute("class","modal-body");
    fourDiv2.appendChild(buildModalTable());

    var fiveButton3Close=document.createElement("button");
    fiveButton3Close.setAttribute("type","button");
    fiveButton3Close.setAttribute("class","btn btn-default");
    fiveButton3Close.setAttribute("data-dismiss","modal");
    fiveButton3Close.innerText="退出";
    fiveButton3Close.setAttribute("id","closeBtn");

    var fiveButton3Submit=document.createElement("button");
    fiveButton3Submit.setAttribute("type","button");
    fiveButton3Submit.setAttribute("class","btn btn-primary");
    fiveButton3Submit.setAttribute("id","commit_button");
    fiveButton3Submit.innerText="提交";

    var fourDiv3=document.createElement("div");
    fourDiv3.setAttribute("class","modal-footer");
    fourDiv3.appendChild(fiveButton3Close);
    fourDiv3.appendChild(fiveButton3Submit);

    var thirdDiv=document.createElement("div");
    thirdDiv.setAttribute("class","modal-content");
    thirdDiv.appendChild(fourDiv1);
    thirdDiv.appendChild(fourDiv2);
    thirdDiv.appendChild(fourDiv3);
    var secondDiv=document.createElement("div");
    secondDiv.setAttribute("class","modal-dialog");
    secondDiv.appendChild(thirdDiv);

    var firstDiv=document.createElement("div");
    firstDiv.setAttribute("class","modal fade in");
    firstDiv.setAttribute("id","myModal");
    firstDiv.setAttribute("tabindex","-1");
    firstDiv.setAttribute("role","dialog");
    firstDiv.setAttribute("aria-labelledby","myModalLabel");
    firstDiv.setAttribute("aria-hidden","true");
    firstDiv.setAttribute("style","display: block;");
    firstDiv.appendChild(secondDiv);
    return firstDiv;
    
}

/**
 *
 * @returns {Element}
 */
function buildModalTable() {

    var modalTable=document.createElement("table");
    modalTable.setAttribute("class","table table-bordered");
    modalTable.innerHTML=
        "<caption>云凯二手房</caption>" +
        "<thead> " +
        "<tr> <th>标题</th> <th>小区</th> <th>地址</th> </tr> " +
        "<tr> " +
        "<td><input type='text' id='title_text'></td> " +
        "<td><input type='text' id='house_name_text'></td> " +
        "<td><input type='text' id='address_text'></td> " +
        "</tr> " +
        "<tr> " +
        "<th>房间类型</th> <th>面积</th> <th>总价</th> " +
        "</tr> " +
        "<tr> " +
        "<td>" +
        "<input type='text' id='house_type_text'></td> <td><input type='text' id='area_text'></td> <td><input type='text' id='total_price_text'></td> </tr> <tr> <th>均价</th> </tr> <tr> <td><input type='text' id='unit_price_text'></td></tr> </thead>";
    return modalTable;
}
/**
 * 表头部分
 * @returns {Element}
 */
function getThead() {
    var checkBox = document.createElement("input");
    checkBox.type="checkBox";
    checkBox.checked=false;
    var th0 = document.createElement("th");
    th0.appendChild(checkBox);
    var th1 = document.createElement("th");
    var th2 = document.createElement("th");
    var th3 = document.createElement("th");
    var th4 = document.createElement("th");
    var th5 = document.createElement("th");
    var th6 = document.createElement("th");
    var th7 = document.createElement("th");
    var th8 = document.createElement("th");

    th1.innerText = "id";
    th2.innerText = "标题";
    th3.innerText = "小区";
    th4.innerText = "地址";
    th5.innerText = "房间类型";
    th6.innerText = "面积";
    th7.innerText = "总价";
    th8.innerText = "均价";
    var tr = document.createElement("tr");
    tr.setAttribute("class","thead_tr");
    tr.appendChild(th0);
    tr.appendChild(th1);
    tr.appendChild(th2);
    tr.appendChild(th3);
    tr.appendChild(th4);
    tr.appendChild(th5);
    tr.appendChild(th6);
    tr.appendChild(th7);
    tr.appendChild(th8);
    var thead = document.createElement("thead");
    thead.onclick=function () {
      checkBox.checked=!checkBox.checked;
        var all_row_check_box=document.getElementsByClassName("row_check_box");
        for(var row in all_row_check_box){
            all_row_check_box[row].checked=checkBox.checked;

        }
    };
    thead.appendChild(tr);
    return thead;

}
/**
 * 从后台取数据生成表格行
 * @param id
 * @param title
 * @param houseName
 * @param address
 * @param houseType
 * @param area
 * @param totalPrice
 * @param unitPrice
 * @returns {Element}
 */
function getTbody(id, title, houseName, address, houseType, area, totalPrice, unitPrice) {
    var checkBox = document.createElement("input");
    checkBox.type="checkBox";
    checkBox.name="test";
    checkBox.setAttribute("id","row-"+id);
    checkBox.setAttribute("class","row_check_box");
    checkBox.checked=false;
    var td0 = document.createElement("td");
    td0.appendChild(checkBox);
    var td1 = document.createElement("td");
    td1.value=id;
    var td2 = document.createElement("td");
    var td3 = document.createElement("td");
    var td4 = document.createElement("td");
    var td5 = document.createElement("td");
    var td6 = document.createElement("td");
    var td7 = document.createElement("td");
    var td8 = document.createElement("td");

    td1.id="row-"+id+"-id";
    td2.id="row-"+id+"-title";
    td3.id="row-"+id+"-houseName";
    td4.id="row-"+id+"-address";
    td5.id="row-"+id+"-houseType";
    td6.id="row-"+id+"-area";
    td7.id="row-"+id+"-totalPrice";
    td8.id="row-"+id+"-unitPrice";



    td1.innerText = id;
    td2.innerText = title;
    td3.innerText = houseName;
    td4.innerText = address;
    td5.innerText = houseType;
    td6.innerText = area;
    td7.innerText = totalPrice;
    td8.innerText = unitPrice;

    var tr = document.createElement("tr");
    tr.appendChild(td0);
    tr.appendChild(td1);
    tr.appendChild(td2);
    tr.appendChild(td3);
    tr.appendChild(td4);
    tr.appendChild(td5);
    tr.appendChild(td6);
    tr.appendChild(td7);
    tr.appendChild(td8);

    var tbody = document.createElement("tbody");
    tbody.setAttribute("id","tbody");
    tr.setAttribute("class","tbody_tr");
    tbody.appendChild(tr);
    tbody.onclick=function () {
     checkBox.checked=!checkBox.checked;
    };
    return tbody;
}
/**
 *
 */
function alter() {

    var checkers=document.getElementsByClassName("row_check_box");
    var alterChecker=findCheckedChecker(checkers);
    if(alterChecker==null)
        alert("only one can alter!");
    else {
        var checkerId=alterChecker.id;
        var id=document.getElementById(checkerId+"-id").innerText;
        var title=document.getElementById(checkerId+"-title").innerText;
        var houseName=document.getElementById(checkerId+"-houseName").innerText;
        var address=document.getElementById(checkerId+"-address").innerText;
        var houseType=document.getElementById(checkerId+"-houseType").innerText;
        var area=document.getElementById(checkerId+"-area").innerText;
        var totalPrice=document.getElementById(checkerId+"-totalPrice").innerText;
        var unitPrice=document.getElementById(checkerId+"-unitPrice").innerText;

        var modelDiv = document.getElementById("model_window");
        modelDiv.innerHTML="";
        modelDiv.appendChild(buildModalBox());

        var closeBtn=document.getElementById("closeBtn");

        closeBtn.onclick=function () {
            document.getElementById("model_window").innerHTML="";
        };

        var title_text=document.getElementById("title_text");
        var house_name_text=document.getElementById("house_name_text");
        var address_text=document.getElementById("address_text");
        var house_type_text=document.getElementById("house_type_text");
        var area_text=document.getElementById("area_text");
        var total_price_text=document.getElementById("total_price_text");
        var unit_price_text=document.getElementById("unit_price_text");

        title_text.value=title;
        house_name_text.value=houseName;
        address_text.value=address;
        house_type_text.value=houseType;
        area_text.value=area;
        total_price_text.value=totalPrice;
        unit_price_text.value=unitPrice;
        var commitButton = document.getElementById("commit_button");
        //给提交更改按钮添加事件 把input里输入的值传过去
        commitButton.onclick=function() {
            var checkers=document.getElementsByClassName("row_check_box");
            var alterChecker=findCheckedChecker(checkers);
            alterInfo(alterChecker.id.substr(4,alterChecker.id.length),$("#title_text").val(),$("#house_name_text").val(),$("#address_text").val(),$("#house_type_text").val(),$("#area_text").val(),$("#total_price_text").val(),$("#unit_price_text").val());
        };
    }
}
/**
 *
 * @param checkers
 * @returns {*}
 */
function findCheckedChecker(checkers) {
    var checkedChecker=[];
    for(var checker=0;checker<checkers.length;checker++){
        console.log(checkers[checker].checked);
        if(checkers[checker].checked)
            checkedChecker.push(checkers[checker]);
    }
    if(checkedChecker.length>1)
        return null;
    else
        return checkedChecker[0];
}
/**
 *
 */
function del() {
    var checkers=document.getElementsByClassName("row_check_box");
    if(findCheckedCheckers(checkers)==null){
        alert("one or more item");
    }
    else {
        var idList="";
        findCheckedCheckers(checkers).forEach(function (checker) {
            idList+=checker.id.substr(4,checker.id.length)+" ";
        });
        deleteInfo(idList);
    }
}
function findCheckedCheckers(checkers) {
    var checkedCheckers=[];
    for(var checker=0;checker<checkers.length;checker++){
        console.log(checkers[checker].checked);
        if(checkers[checker].checked)
            checkedCheckers.push(checkers[checker]);
    }
    if(checkedCheckers.length==0)
        return null;
    else
        return checkedCheckers;

}







