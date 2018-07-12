/**
 * Created by zsyoung on 2017/2/28.
 */

var currentPage=1;

function initHousePage(blocks) {
    //循环生成数组
    var arr = [];
    var totalNumber = blocks.length; // 记录总数
    var maxButtons = 10; // 显示的分页按钮数量
    var pageSize = 5; // 每页显示记录数
    var totalPage = parseInt(Math.ceil(totalNumber / pageSize)); // 总页数
    blocks.forEach(function (block) {
        arr.push(block);
    });
    //每一页第一个li
    var rangeStartitem = (currentPage - 1) * pageSize;
    //开始页
    var rangeStart = Math.max(1, currentPage - parseInt(maxButtons / 2));
    //最后一页
    var rangeEnd = Math.min(totalPage, rangeStart + maxButtons - 1);
    var constr = pageCon(arr, rangeStartitem, pageSize);

    //table表头和按钮每次只出现一次
    var table = document.createElement("table");
    table.setAttribute("class", "table table-hover");
    table.appendChild(getThead());
    var container = document.getElementById("info_table");
    container.innerHTML = "";
    container.appendChild(getButton());
    container.appendChild(table);
    constr.forEach(function (constrOne) {
        container.appendChild(constrOne);
    });

    //创建分页模板
    var str = "";
    str += "<div class='pagination'>";
    str += "当前第" + currentPage + "页";
    //如果总页数大于1
    if (totalPage > 1) {
        //当前页不是第一页
        if (currentPage != 1) {
            str += '<a href="#!"  data-num="1"><span>|&lt;</span></a>';
            str += '<a href="#!"  data-num="' + (currentPage - 1) + '"><span>&lt;&lt;</span></a>';
        } else {
            //如果是第一页，禁用上一页按钮
            str += '<span>|&lt;</span>';
            str += '<span>&lt;&lt;</span>';
        }
        //中间页码
        for (var i = rangeStart; i <= rangeEnd; i++) {
            //如果是当前页的话，就禁用当前页的按钮
            if (i == currentPage) {
                str += '<span class="on">' + i + "</span>";
            } else {
                //否则就可以点击该页
                str += '<a href="#"  data-num="' + i + '"><span>' + i + "</span></a>";
            }
        }
        //当前页不是总页，即是最后一页
        if (currentPage != totalPage) {
            str += '<a href="#"  data-num="' + (currentPage + 1) + '"><span>&gt;&gt;</span></a>';
            str += '<a href="#"  data-num="' + totalPage + '"><span>&gt;|</span></a>';
        } else {
            //如果是最后页，禁用下一页
            str += '<span>&gt;&gt;</span>';
            str += '<span>&gt;|</span>';
        }
    }

    str += ' 一共' + totalPage + '页, ' + totalNumber + '条记录 </div>';


    createPager("pager-backward",str,blocks);

}
function createPager(pager_id,str,blocks) {
    var divpager = document.getElementById(pager_id);
    divpager.innerHTML = str;
    //获取所有生成的页面链接
    var listTag = divpager.getElementsByTagName('a');

    //绑定li事件
    for (var i = 0; i < listTag.length; i++) {
        listTag[i].onclick = function() {
            var currentPage = this.getAttribute('data-num');
            nowcurrentPage(currentPage,blocks);
            return false;
        };
    }
}
//传递页面
function nowcurrentPage(page,blocks) {
    currentPage = page;
    initHousePage(blocks);
}

//生成每页的数据
function pageCon(arr, rangeStartitem, len) {
    var constr = [];
    for (var i = rangeStartitem; i < rangeStartitem + len; i++) {
        if(arr[i]!=undefined)
        constr.push(arr[i]);
    }
    return constr;
}