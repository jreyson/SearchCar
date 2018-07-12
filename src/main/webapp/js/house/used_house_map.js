/**
 * Created by Satani on 2017/2/22.
 */
/*地图画板*/
var map=null;
/**
 * 查询二手房信息
 * @param address 模糊地址
 */
function queryHouse(address) {
    var url="/getUsedHouseVisual";
    $.ajax({
        url:url,
        dataType:"json",
        data:{
            address:address
        },
        success:function (houseList) {
            houseList.forEach(function (house) {
                bindHousePosition(house);
            });
        }
    });
}

/**
 * 展示二手房地图信息
 * @param address 模糊地址
 */
function showHouseMap(address) {
    if(map==null){
        map = new BMap.Map("allMap");
        map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
        map.addControl(new BMap.MapTypeControl());   //添加地图类型控件
    }
    map.clearOverlays();
    queryHouse(address);
    bindMapPosition(address);

}

/**
 * 绑定二手房信息到地图
 * @param house 二手房信息
 */
function bindHousePosition(house) {
    var url="http://api.map.baidu.com/place/v2/suggestion";
    var ak="iD2gwtGfo1p98lPenidUyx8h";
    var city="重庆市";
    $.ajax({
        url:url,
        dataType:"JSONP",
        data:{
            query:house.houseLocation,
            region:city,
            output:"json",
            ak:ak
        },
        success:function (msg) {
            if(msg.result.length>0){
                var result=msg.result[0];
                var visualHouse={};
                var random=(Math.random()/10000)*randomFlag();
                console.log(random);
                visualHouse.lat=result.location.lat+random;
                visualHouse.lng=result.location.lng+random;
                visualHouse.houseAddress=house.houseAddress;
                visualHouse.houseType=house.houseType;
                visualHouse.houseUnitPrice=house.houseUnitPrice;
                visualHouse.houseDescription=house.houseDescription;
                markHouseLocation(visualHouse);
            }
        }
    });
}

/**
 * 绑定地图中心位置
 * @param map 模糊地址
 */
function bindMapPosition(map) {
    var url="http://api.map.baidu.com/place/v2/suggestion";
    var ak="iD2gwtGfo1p98lPenidUyx8h";
    var city="重庆市";
    $.ajax({
        url:url,
        dataType:"JSONP",
        data:{
            query:map,
            region:city,
            output:"json",
            ak:ak
        },
        success:function (msg) {
            if(msg.result.length>0){
                var result=msg.result[0];
                var visualHouse={};
                visualHouse.lat=result.location.lat;
                visualHouse.lng=result.location.lng;
                markMapLocation(visualHouse);
            }
        }
    });
}

/**
 * 标注房源信息
 * @param house 房源信息
 */
function markHouseLocation(house) {
    console.log(house.lat+"\t"+house.lng+"\t"+house.info);
    var point = new BMap.Point(house.lng,house.lat);
    var marker=new BMap.Marker(point);
    map.addOverlay(marker);
    var info=new BMap.InfoWindow("<p style='font-size:20px;color:red'>"+house.houseDescription+"<br/>"+house.houseAddress+"<br/>"+house.houseType+"<br/>"+house.houseUnitPrice+"</p>");
    marker.addEventListener("click",function () {
       this.openInfoWindow(info);
    });
}

/**
 * 地图定位
 * @param center 地图中心位置
 */
function markMapLocation(center) {


    var point = new BMap.Point(center.lng,center.lat);
    map.centerAndZoom(point,15);
    console.log(center.lng+"\t"+center.lat);
}

/*随机正负*/
function randomFlag() {
    var baseNumber=parseInt(Math.random()*100000);
    return Math.pow(-1,baseNumber%2);
}