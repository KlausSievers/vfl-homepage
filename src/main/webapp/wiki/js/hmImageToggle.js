/*! Help & Manual WebHelp 3 Script functions
Copyright (c) 2014-2016 by Tim Green. All rights reserved. Contact tg@it-authoring.com
*/
var HMImageToggle=function(h){var a,t=false,j=h.attr("id"),v=h.attr("data-src0"),u=h.attr("data-src1"),b=h.attr("data-state"),l=h.attr("data-title0")?h.attr("data-title0"):null,k=h.attr("data-title1")?h.attr("data-title1"):null,o=h.attr("data-caption0")?h.attr("data-caption0"):null,m=h.attr("data-caption1")?h.attr("data-caption1"):null,e=o?h.parents("div")[0]:null,n=h.offset().left,f=h.offset().top,r={},w={},g={},p,s=0,i=new Image();g.w=$(window).width()-30,g.h=$(window).height()-30,r.w=h.width();r.h=h.height();p=g.h;hFactor=r.h/r.w;if(!m){$("body").append('<div id="imagetogglebox"></div>')}else{$("body").append('<div id="imagetogglebox"><div id="imagecaptionbox"><p class="zoomedcaption">'+m+"</p></div></div>");t=$("div#imagecaptionbox")}a=$("div#imagetogglebox");$(i).css({width:"100%",height:"auto"});function d(y,x){var z=(typeof x=="undefined")?"fast":x;a.animate({width:r.w,height:r.h,top:f,left:n},z,function(){a.hide();a.remove();h.attr("data-state","0");if(typeof hmxtoggle=="undefined"){$("body,html").css("overflow","hidden")}})}hmWebHelp.funcs.closeImageToggle=d;function c(x){x.stopPropagation();if(a.width()<w.w){if(w.w>$(window).width()||w.h>$(window).height()){$("body,html").css("overflow","auto")}if(t){t.hide()}a.animate({top:0,left:0,width:w.w,height:w.h},"fast",function(){a.css("position","absolute");$("div#imagezoom").hide()})}else{d()}}function q(y,x){while(y.w>x.w||y.h>x.h){y.w-=5;y.h=y.w*hFactor}return y}i.onload=function(){var B={},y,A,z=0,x=0;w.w=B.w=i.width;w.h=B.h=i.height;B=q(B,g);a.append(i);a.append('<div id="imagezoom"><img id="imagezoomer" src="./images/ZoomIn.png" border="0"/></div>');$(i).on("click",d);a.css({left:n+"px",top:f+"px",width:r.w+"px",height:r.h+"px"});a.show();s=parseFloat(getComputedStyle(a[0]).getPropertyValue("border-top-width"),10);if(t){t.css({width:B.w+"px"});while(B.h>p-(t.height()+10)){B.w-=5;B.h=B.w*hFactor}x=t.outerHeight();t.css({width:""})}y=($(window).height()-(B.h+s*2))/2;A=($(window).width()-(B.w+s*2))/2;if(t){y=5;z=x+s*2}if(w.w>B.w){$("div#imagezoom").on("click",c).show()}a.animate({width:B.w,height:B.h+z,top:y,left:A},"fast",function(){h.attr("data-state","1");if(t){var C=$(i).height()+t.outerHeight();a.css("height",C+"px")}})};i.src=u};hmWebHelp.funcs.hmImageToggle=HMImageToggle;
