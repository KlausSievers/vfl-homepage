/*! Help & Manual WebHelp 3 Script functions
Copyright (c) 2014-2016 by Tim Green. All rights reserved. Contact tg@it-authoring.com
*/
function resizeHeader(){var a=function(){return hmpage.$topicbox.position().top};return function(d){return;var b=(hmpage.FgeaderHeight()+d);hmpage.headerheightStatic=b;var c=(a()+d);hmpage.$headerbox.css({height:b+"px"});if(!hmpage.Fshortpage()){hmpage.$navwrapper.css({top:(c-1)+"px"});hmpage.$topicbox.css({top:c+"px"})}hmpage.$headerpara.css({"font-size":"22px"})}}hmWebHelp.funcs.resizeHeader=new resizeHeader;
