/**
 * Created by Satani on 2017/2/16.
 */


function getSource(maker,type) {
    var url="/getUsedCars";
    $.ajax({
        url:url,
        dataType:"json",
        data:{
            "maker":maker,
            "type":type
        },
        success:function (data) {
            addCarBlocks(data);
        }
    });
}

function addCarBlocks(data) {
    initPage(encapsulateCarBlocks(data));
}

function encapsulateCarBlocks(cars) {
    var blocks=[];
    cars.forEach(function (car) {
        blocks.push(initCarBlock(car));
    });
    return blocks;
}

function initCarBlock(car) {
  var div=document.createElement("div");
    div.setAttribute("class","list-infoBox");
    div.appendChild(getPictureA(car.description,car.target,car.picture));
    div.appendChild(getInfoP(car.description,car.target));
    div.appendChild(getFCP(car.city,car.register,car.mileage));
    div.appendChild(getPrice("超值",car.presentPrice,car.originalPrice));
    var li=document.createElement("li");
    li.appendChild(div);
    return li;

}

function getPictureA(title,target,imgSrc) {

    var img=document.createElement("img");
    img.setAttribute("width","290");
    img.setAttribute("height","194");
    img.setAttribute("src",imgSrc);
    img.setAttribute("alt",title);
    var a=document.createElement("a");
    a.setAttribute("title",title);
    a.setAttribute("href",target);
    a.setAttribute("target","_blank");
    a.setAttribute("class","imgtype");
    a.appendChild(img);
    return a;
}

function getInfoP(title,target) {
    var a=document.createElement("a");
    a.setAttribute("title",title);
    a.setAttribute("href",target);
    a.setAttribute("target","_blank");
    a.setAttribute("class","info-title");
    a.innerText=title;

    var p=document.createElement("p");
    p.setAttribute("class","infoBox");
    p.appendChild(a);
    return p;
}

function getFCP(city,date,mileage) {

    var citySpan=document.createElement("span");
    citySpan.setAttribute("class","tag-gray");
    citySpan.innerText=city;

    var registerSpan=document.createElement("span");
    registerSpan.innerText=date+"上牌";
    var em=document.createElement("em");
    em.setAttribute("class","shuxian");
    em.innerText="|";

    var p=document.createElement("p");
    p.setAttribute("class","fc-gray");
    p.appendChild(citySpan);
    p.appendChild(registerSpan);
    p.appendChild(em);
    p.append("\t行驶 "+mileage+"万公里\t");
    return p;
}

function getPrice(saleType,presentPrice,originalPrice) {

    var em=document.createElement("em");
    em.setAttribute("class","tag-yellow");
    em.innerText=saleType;
    var span=document.createElement("span");
    var i=document.createElement("i");
    i.setAttribute("class","fc-org priType");
    i.innerText=presentPrice+"万";
    span.appendChild(i);
    var s=document.createElement("s");
    s.innerText=originalPrice+"万";
    var p=document.createElement("p");
    p.setAttribute("class","priType-s");
    p.appendChild(em);
    p.appendChild(span);
    p.appendChild(s);
    return p;
}


