/**
 * Created by zsyoung on 2017/2/28.
 */

/**
 * 添加房源信息
 * @param title
 * @param house_name
 * @param address
 * @param house_type
 * @param area
 * @param total_price
 * @param unit_price
 */
function addInfo(title,house_name,address,house_type,area,total_price,unit_price) {

    var url="/addOneHouse";
    $.ajax({
        url:url,
        dataType:"json",
        data:{
            title:title,
            house_name:house_name,
            address:address,
            house_type:house_type,
            area:area,
            total_price:total_price,
            unit_price:unit_price
        },
        success:function (result) {
            alert(result);
        }
    });
}

/**
 * 修改房源信息
 * @param id
 * @param title
 * @param house_name
 * @param address
 * @param house_type
 * @param area
 * @param total_price
 * @param unit_price
 */
function alterInfo(id,title,house_name,address,house_type,area,total_price,unit_price) {
    var url="/updateOneHouse";
    $.ajax({
        url:url,
        dataType:"json",
        data:{
            id:id,
            title:title,
            house_name:house_name,
            address:address,
            house_type:house_type,
            area:area,
            total_price:total_price,
            unit_price:unit_price
        },
        success:function (result) {
            alert(result);
        }
    });
}

/**
 * 删除房源信息
 * @param idList
 */
function deleteInfo(idList) {
    var  url ="/deleteBatchHouse";
    $.ajax({
        url: url,
        dataType: "json",
        data: {
            idList: idList
        },
        success: function (result) {
            alert(result);
            initView(document.getElementById("detail_search").value);
        }
    });
}

/*测试*/
/**
 * 房源信息导出
 * @param address 房源地址
 */
function exportInfo(address,excelUrl) {
    console.log(address,excelUrl);
    var url ="/ExportExcel";
    $.ajax({
        url: url,
        dataType: "json",
        data:{
            address: address,
            excelUrl:excelUrl
        },
        success:function () {

        }
    });
}