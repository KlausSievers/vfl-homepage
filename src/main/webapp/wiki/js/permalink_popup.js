/*! Help & Manual WebHelp 3 Script functions
Copyright (c) 2014-2016 by Tim Green. All rights reserved. Contact tg@it-authoring.com
*/
var hmpldata={show:true,copy:"Copy permalink to current topic",hurl:"",sorry:"Sorry, your browser can't bookmark pages using this function. Right-click to copy the permalink, then press Ctrl+D to bookmark the topic with the copied permalink.",noserver:"This page is stored locally. You can only bookmark pages on a web server!",manualcopy:"Right-click on the permalink to copy to the clipboard.",select:"Copy Permalink",bookmark:"Bookmark Topic",close:"Close Dialog",copied:"Permalink copied to clipboard:",title:"Permalink"};hmPopupsObject.permalink_popup='<input type="button" id="bookmarkPermalink" value="'+hmpldata.bookmark+'" /><input type="button" id="closePermalink" value="'+hmpldata.close+'" /><p class="permalink_info">'+hmpldata.manualcopy+'</p><textarea id="plinkBox" cols="58" rows="3" readonly="readonly"></textarea>';function iC(){var a=this;a.closePermalink=function(){hmWebHelp.xPopup.hmKillPopup(true)};a.bookmarkPermalink=function(){var c=$("title").text();var b=$("textarea#plinkBox").text();if(hmBrowser.server){if(window.sidebar&&window.sidebar.addPanel){window.sidebar.addPanel(c,b,"");a.closePermalink()}else{if("AddFavorite" in window.external){window.external.AddFavorite(b,c);a.closePermalink()}else{$("textarea#plinkBox").focus().select();alert(hmpldata.sorry)}}}else{alert(hmpldata.noserver);a.closePermalink()}};a.alertPermalink=function(){$("textarea#plinkBox").focus().select();alert(hmpldata.manualcopy)};a.clipboardInit=function(){$("input[id*='Permalink']").css("cursor","pointer");$("input#closePermalink").on("click",function(){a.closePermalink()});$("input#bookmarkPermalink").on("click",function(){a.bookmarkPermalink()});var b=$("title").text(),c=document.location.href;c=c.replace(/ /g,"%20");$("textarea#plinkBox").text(c);setTimeout(function(){$("textarea#plinkBox").focus().select()},300)}}hmWebHelp.funcs.permalink_popup=function(a){if(!hmBrowser.server){alert("You can only save permalinks when the help is stored on a web server.");return}if($("div#navigationmenu").is(":visible")){hmWebHelp.hamburgerMenu()}if($("textarea#plinkBox").length<1){hmWebHelp.funcs.initClipboard=new iC()}a[1]=hmPopupsObject.permalink_popup;a[3]=hmWebHelp.funcs.initClipboard.clipboardInit;a[4]=["495px","125px",(($(window).width()-($("div#navigationmenu").outerWidth()+490))+"px"),(($("div#topicbody").position().top+120)+"px"),"large","permalinkPopup"];hmWebHelp.extFuncs("hmXpopup",a)};