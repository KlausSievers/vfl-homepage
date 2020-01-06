/*! Help & Manual WebHelp 3 Script functions
Copyright (c) 2014-2016 by Tim Green. All rights reserved. Contact tg@it-authoring.com
*/
function hmatoc(){var n=this,i="Jump to:&nbsp;",e=parseInt("3",10),k="No contents for this topic",r="Contents of this topic",l="Jump to top of page",j="Scroll to Top of Page",a=parseInt("45",10),c="empty",m=false;function b(){var u=window.location.search.lastIndexOf("zoom_highlight")>0;if(!u){var s=document.getElementsByTagName("FONT");if(s.length>0){var t="";for(var v=0;v<s.length;v++){t=s[v].style.cssText;if(t.indexOf("BACKGROUND-COLOR")==0){u=true;break}}}}return u}function d(v,s){var u,t;if(s==0){return v}if((s>0)&&(s<=20)){s=20}u=v.split("");if(u.length>s){for(t=u.length-1;t>-1;--t){if(t>s){u.length=t}else{if(" "===u[t]){u.length=t;break}}}u.push("...")}return u.join("")}function p(t){return t.replace(/^\s+|\s+$/g,"")}function h(s){heading=s.replace(/\&/g,"&amp;");s=s.replace(/</g,"&lt;");s=s.replace(/>/g,"&gt;");return s}function q(){var t=$("div#hmpagebody");var s=t.position().top+(parseFloat(t.css("borderTopWidth"),10)/2);$("div#autoTocWrapper").css("top",s+"px")}function g(){var B,t,y,I,F,G,x,D,C,H,w,v,E,s=false,A='data=""',u=$("span[class*=_atoc]").children("a.dropdown-toggle");E=$("[class*='_atoc']").filter("[class^='p_']").add(u);if(E.length>=e){B=[];for(var z=0;z<E.length;z++){s=false;t=E[z];y=$(E[z]).text();y=p(y);y=h(y);I=$(t).attr("class");if(I==="dropdown-toggle"){I=$(t).parent("span").attr("class");s=true}F=(I.indexOf("_atocs_")!=-1);if(y.length==1){y=y.replace(/\xa0/,"")}if(y!=""){m=true;G=y.replace(/\"/g,"'");y=d(y,a);if(!s){x="autoTOC"+z;t.innerHTML='<a id="'+x+'"></a>'+t.innerHTML;A='data="" '}else{x=t.id;A='data="'+x+'" '}if(!F){D='<li class="autoTOC mainlink" id="src_'+x+'" '+A+'title="'+i+G+'"><span><i class="icon icon-ok-squared"></i></span><p class="autoTOC">'+y+"</p></li>"}else{D='<li class="autoTOC subTOC mainlink" id="src_'+x+'" '+A+'title="'+i+G+'"><span><i class="icon icon-ok"></i></span><p class="autoTOC subTOC">'+y+"</p></li>"}B.push(D)}}}else{return}if((B[0])&&(B[0]!="")){C="";H=document.getElementById("autoTocWrapper");for(var z=0;z<B.length;z++){C=C+B[z]}C='<li id="toplink" class="autoTOC toplink" title="'+l+'"><span><i class="icon icon-angle-circled-up"></i></span><p class="autoTOC">'+j+"</p></li>"+C;C='<div id="autoTocMiddle"><div id="autoTocInner"><ul id="autoTocList">'+C+"</ul></div></div>";H.innerHTML=C;$("li.mainlink").on(hmBrowser.touchstart+".atoc_clicks",function(){var L=b(),K=$(this).attr("id"),M=K.replace(/src_/,""),N=$("a[id='"+M+"']"),J=$(this).attr("data");if((u.length!=null)&&(!L)){if(J!==""){hmWebHelp.extFuncs("hmDoToggle",{method:"HMToggle",obj:{target:$("a#"+J),speed:0,mode:"expand"}})}}hmWebHelp.scrollTopic(N);return false});$("li.toplink").on(hmBrowser.touchstart+".atoc_clicks",function(){var J=b();hmpage.$scrollBox.scrollTo(0,600,{onAfter:function(){if((u.length>0)&&(!J)){hmWebHelp.extFuncs("hmDoToggle",{method:"hmToggleToggles",obj:{speed:0,mode:"collapse"}})}}});return false})}}function f(){if($("div#navigationmenu").is(":visible")){hmWebHelp.hamburgerMenu()}q();$(window).on("resize.atocResize",function(){q()});$("div#autoTocWrapper").slideDown("fast",function(){$("div#unclicker").on(hmBrowser.touchstart,function(){o()}).show()})}function o(t){var s="fast";if(typeof t=="string"&&t=="snap"){s=0}$("div#autoTocWrapper").slideUp(s,function(){$(window).off("resize.atocResize");$("div#unclicker").off(hmBrowser.touchstart).hide()})}return function(s){if($("div#unclicker").length<1){$("div#hmpagebody").prepend('<div id="unclicker" />')}if($("#autoTocWrapper").html()==""){g()}if($("div#autoTocWrapper").is(":hidden")){clickDelayA=new Date().getTime();f()}else{clickDelayB=new Date().getTime();if(clickDelayB-clickDelayA>1000){o(s)}}}}hmWebHelp.funcs.hm_autotoc=new hmatoc();