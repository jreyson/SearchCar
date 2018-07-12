/**
 * Created by Satani on 2017/2/17.
 */

function initAnalysis() {

    var url="/getAllMakers";
    $.ajax({
        url:url,
        dataType:"json",
        success:function (data) {
            initSelectMaker(data.makers);
        }
    });
}
function initSelectMaker(makers) {


    var selector=document.getElementById("maker");
    makers.sort();
    makers.forEach(function (maker) {
        var option=document.createElement("option");

        option.text=maker;
        option.value=maker;
        selector.add(option,null);
    });
    console.log($("#maker").val());
    $("#maker").change(function(){
        var url="/getTypesByMaker";
        $.ajax({
            url:url,
            dataType:"json",
            data:{
                maker:$("#maker").val()
            },
            success:function (data) {
                initSelectType(data[$("#maker").val()]);
            }
        });
    });
}
function initSelectType(types) {
    types.sort();
    var selector=document.getElementById("type");
    selector.options.length=0;
    types.forEach(function (type) {
        var option=document.createElement("option");
        option.text=type;
        option.value=type;
        selector.add(option,null);
    });

    $("#type").change(function(){
        getAnalysisResult($("#type").val());
    });

}
function getAnalysisResult(description) {

    var url="/getAnalysisResult";
    $.ajax({
        url:url,
        dataType:"json",
        data:{
            "description":description
        },
        success: function (data) {
            renderingResult(data);
        }
    })

}

function renderingResult(data){
    console.log(data);
    var result=data.result;
    var originalPriceDiv=$("#original-price-pie");
    var presentPriceDiv=$("#present-price-pie");
    var mileageDiv=$("#mileage-pie");
    originalPriceDiv.innerHTML="";
    presentPriceDiv.innerHTML="";
    mileageDiv.innerHTML="";
    renderingOriginalPrice(result.originalPrice);
    renderingPresentPrice(result.presentPrice);
    renderingMileage(result.mileage);
}
/*绘制原价Pie图*/

function renderingOriginalPrice(result) {
    console.log(result);
    var chart=new CanvasJS.Chart("original-price-pie",initPieObj("Original Price Pie",result));
    chart.render();
}
/*绘制现价Pie图*/

function renderingPresentPrice(result) {
    console.log(result);
    var chart=new CanvasJS.Chart("present-price-pie",initPieObj("Present Price Pie",result));
    chart.render();
}
/*绘制里程Pie图*/

function renderingMileage(result) {
    console.log(result);
    var chart=new CanvasJS.Chart("mileage-pie",initPieObj("Mileage Pie",result));
    chart.render();
}

/*初始化Pie对象*/

function initPieObj(title,data) {
    var pieObj={};
    pieObj.title={};
    pieObj.title.text=title;
    pieObj.animationEnabled=true;
    pieObj.legend={
        verticalAlign: "center",
        horizontalAlign: "right",
        fontSize: 20,
        fontFamily: "Helvetica"
    };
    pieObj.theme="theme2";
    pieObj.data=initPieData(data);
    return pieObj;
}
/*初始Pie数据对象*/

function initPieData(data) {
    var pieData=[];
    var pieDataObj={
        type: "pie",
        indexLabelFontFamily: "Garamond",
        indexLabelFontSize: 12,
        indexLabel: "{label}",
        startAngle:-20,
        showInLegend: false,
        toolTipContent:"{y}%"
    };
    var dataPoints=[];
    data.forEach(function (item) {
        var dataPoint={};
        dataPoint.y=item.percent.toFixed(4)*100;
        var numbers=item.label.split("-");
        var low=parseFloat(numbers[0]);
        low=low.toFixed(2);
        var high=parseFloat(numbers[1]);
        high=high.toFixed(2);
        var label=low+"万-"+high+"万";
        dataPoint.legendText=label;
        dataPoint.label=label;
        dataPoints.push(dataPoint);
    });
    pieDataObj.dataPoints=dataPoints;
    pieData.push(pieDataObj);
    return pieData;
}