/*! Help & Manual WebHelp 3 Script functions
Copyright (c) 2014-2016 by Tim Green. All rights reserved. Contact tg@it-authoring.com
*/
function showToolbar(){var a=0,b;return function(){a=1200;if(hmpage.headerclosed&&hmpage.Fshortpage()){hmWebHelp.pageDimensions.headerDown();hmpage.$headerbox.css({"box-shadow":"1px 1px 2px #676767",right:"4px",left:"4px"});if(b){clearInterval(b)}b=setInterval(function(){a-=200;if(a<=0){clearInterval(b);hmWebHelp.pageDimensions.headerUp();hmpage.$headerbox.css({"box-shadow":"0 0 0",right:"5px",left:"5px"})}},200)}}}hmWebHelp.funcs.showToolbar=new showToolbar();
