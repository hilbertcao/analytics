/*! cookiejs v1.0.8 | MIT (c) 2016 kenny wang | https://github.com/jaywcjlove/cookie.js */
!function(e){if("object"==typeof exports&&"undefined"!=typeof module)module.exports=e();else if("function"==typeof define&&define.amd)define([],e);else{var t;t="undefined"!=typeof window?window:"undefined"!=typeof global?global:"undefined"!=typeof self?self:this,t.cookie=e()}}(function(){function e(e){return!!e&&"[object Object]"===Object.prototype.toString.call(e)}function t(e){return e instanceof Array}function n(e){return Array.prototype.slice.call(e)}function r(){return this instanceof r?void 0:new r}var o=Object.names||function(e){var t=[],n="";for(n in e)e.hasOwnProperty(n)&&t.push(n);return t};r.prototype={get:function(e){for(var t=e+"=",n=document.cookie.split(";"),r=0;r<n.length;r++){for(var o=n[r];" "==o.charAt(0);)o=o.substring(1,o.length);if(0==o.indexOf(t))return unescape(o.substring(t.length,o.length))}return!1},set:function(t,n,r){if(e(t))for(var o in t)t.hasOwnProperty(o)&&this.set(o,t[o],n);else{var i=e(r)?r:{expires:r},u=void 0!==i.expires?i.expires:"",a=typeof u,f=void 0!==i.path?";path="+i.path:";path=/",c=i.domain?";domain="+i.domain:"",s=i.secure?";secure":"";"string"===a&&""!==u?u=new Date(u):"number"===a&&(u=new Date(+new Date+864e5*u)),""!==u&&"toGMTString"in u&&(u=";expires="+u.toGMTString()),document.cookie=t+"="+escape(n)+u+f+c+s}},remove:function(e){e=t(e)?e:n(arguments);for(var r=0,o=e.length;o>r;r++)this.set(e[r],"",-1);return e},clear:function(e){return this.remove(o(this.all()))},all:function(){if(""===document.cookie)return{};for(var e=document.cookie.split("; "),t={},n=0,r=e.length;r>n;n++){var o=e[n].split("=");t[unescape(o[0])]=unescape(o[1])}return t}};var i=function(t,n,o){var i=arguments;return 0===i.length?r().clear():2!==i.length||n?"string"!=typeof t||n?e(t)||i.length>1&&t&&n?r().set(t,n,o):null===n?r().remove(t):r().all():r().get(t):r().clear(t)};for(var u in r.prototype)i[u]=r.prototype[u];return i});

(function () {
    var params = {};
    //Document对象数据
    if(document) {
        params.domain = document.domain || '';
    }

    /**
     * 截取url里面的不带参数的url段
     * @param url
     * @returns {string}
     */
    var getUrl = function(url){
        if(!url){

            return "";
        }

        var startIndex = url.indexOf("/");
        if (startIndex != -1) {

            url =  url.substring(startIndex)
        }

        var endIndex = url.indexOf("?");
        if (endIndex != -1) {

            url =  url.substring(0,endIndex)
        }

        return url;
    };

    /**
     * 获取设备终端类型
     * @returns {*}
     */
    var getTeminalType = function(){

        var u = navigator.userAgent.toLowerCase();
        if (/(iphone|ipad|ipod|ios)/i.test(u)) {
            return "ios";
        }
        if (/(android)/i.test(u)) {
            return "android";
        }
        return "pc";
    };

    /**
     * 获取url里面带的参数
     * @param url
     * @returns {*}
     */
    var getParamFromUrl = function(url) {
        var paramList = new Object();
        if(!url){

            return "";
        }
        var index = url.indexOf("?");
        if (index != -1) {
            var str = url.substr(index+1);
            var strs = str.split("&");
            for(var i = 0; i < strs.length; i ++) {
                paramList[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);
            }
        }
        return paramList;
    };

    var getParamFromPost = function(paramStr) {
        var paramList = new Object();
        var strs = paramStr.split("&");
        for(var i = 0; i < strs.length; i ++) {
            paramList[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);
        }
        return paramList;
    };

    var copyObject = function(source,target){
        for (var prop in source){
            target[prop] = source[prop];
        }
    };

    var copyObjectWithFiledName= function(source,target,sourceFiledName,targetFiledName){
        for (var prop in source){

            if(prop.toUpperCase() == sourceFiledName.toUpperCase()){
                target[targetFiledName] = source[prop];
            }
        }
    };

    var encodeStr = function(str){

        return decodeURI(JSON.stringify(str));
    };

    var decodeStr = function(str){

        return JSON.parse(str);
    };

    var report = function(paramsObject){

        paramsObject = paramsObject||{};

        //拼接参数串
        var args = '';
        for(var field in paramsObject) {
            if(args != '') {
                args += '&';
            }
            args += field + '=' + encodeURIComponent(paramsObject[field]);
        }

        var recordsStr = cookie("recordList")||"[]";
        var records = decodeStr(recordsStr);
        //给最近的记录加个时间戳
        paramsObject.eventTime = new Date().getTime();

        records.push(paramsObject);

        //保证不超过5个
        if(records.length>5){

            var l = records.length-5;
            for(var i=0;i<l;i++){
                records.shift();
            }
        }

        cookie("recordList",encodeStr(records),{       //设置cookie，并设置过期时间7天，路径、域
            "expires": 100000,
            "path": '/',
            "domain":".ab.com"
        });

        //通过Image对象请求后端脚本
        var img = new Image(1, 1);

        //设置终端类型
        paramsObject.teminalType = getTeminalType();
        img.src = 'http://analytics.ab.com/1.gif?' + args +"&v=" + new Date().getTime()+"&event=" + encodeStr(paramsObject);
    };

    var formsubmitFunction = function(){
        console.info("提交表单");
        //复制toJson
        var paramsObject = {};

        //先考虑取cookie里面的userName和userId
        var userName = cookie("userName")||"";
        var userId = cookie("uid")||cookie("userId")||"";

        if(userName!=""){
            paramsObject.username = userName;
        }

        if(userId!=""){
            paramsObject.userId = userId;
        }

        copyObject(params,paramsObject);
        var urlParams = getParamFromUrl(this.action);

        copyObjectWithFiledName(urlParams,paramsObject,"uid","userId");
        copyObjectWithFiledName(urlParams,paramsObject,"userId","userId");
        copyObjectWithFiledName(urlParams,paramsObject,"userName","userName");

        for(var i=0;i<this.elements.length;i++){
            var element = this.elements[i];
            var name = element.name||"";
            if(name.toUpperCase() == "userId".toUpperCase() || name.toUpperCase() == "userName".toUpperCase()) {
                paramsObject[name] = element.value;
            }
        }

        //表单提交的时候默认是提交到method,method为空，可能就是提交到本页
        if(this.action != ""){
            paramsObject.url = getUrl(this.action);
        }else{
            paramsObject.url = document.url;
        }

        report(paramsObject);
    };

    for(var i=0;i<document.forms.length;i++){
        var form = document.forms[i];
        form.addEventListener("submit",formsubmitFunction);
    }

    (function(XHR) {
        "use strict";
        var open = XHR.prototype.open;
        var send = XHR.prototype.send;
        XHR.prototype.open = function(method, url, async, user, pass) {
            this._url = url;
            open.call(this, method, url, async, user, pass);
        };
        XHR.prototype.send = function(data) {
            var self = this;
            var oldOnReadyStateChange;
            var url = this._url;
            function onReadyStateChange() {
                if(self.readyState == 4) {

                    //拦截ajax代码
                    console.info("ajax请求"+"url:"+this._url);
                    //复制
                    var paramsObject = {};

                    //先考虑取cookie里面的userName和userId
                    var userName = cookie("userName")||"";
                    var userId = cookie("uid")||cookie("userId")||"";

                    if(userName!=""){
                        paramsObject.username = userName;
                    }

                    if(userId!=""){
                        paramsObject.userId = userId;
                    }
                    
                    copyObject(params,paramsObject);
                    var urlParams = getParamFromUrl(url);

                    var _params = getParamFromPost(data);
                    copyObjectWithFiledName(urlParams,paramsObject,"uid","userId");
                    copyObjectWithFiledName(urlParams,paramsObject,"userId","userId");
                    copyObjectWithFiledName(urlParams,paramsObject,"userName","userName");
                    copyObjectWithFiledName(_params,paramsObject,"uid","userId");
                    copyObjectWithFiledName(_params,paramsObject,"userId","userId");
                    copyObjectWithFiledName(_params,paramsObject,"userName","userName");
                    paramsObject.url = getUrl(this._url);
                   report(paramsObject);
                }
                if(oldOnReadyStateChange) {
                    oldOnReadyStateChange();
                }
            }
            /* Set xhr.noIntercept to true to disable the interceptor for a particular call */
            if(!this.noIntercept) {
                if(this.addEventListener) {
                    this.addEventListener("readystatechange", onReadyStateChange, false);
                } else {
                    oldOnReadyStateChange = this.onreadystatechange;
                    this.onreadystatechange = onReadyStateChange;
                }
            }
            send.call(this, data);
        }
    })(XMLHttpRequest);
})();