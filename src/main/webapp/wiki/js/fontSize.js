/*! Help & Manual WebHelp 3 Script functions
Copyright (c) 2014-2016 by Tim Green. All rights reserved. Contact tg@it-authoring.com
*/
function hmFS(){var c=null,e=null,d=null,b=0;function a(h){function f(k){if(typeof k==="string"){k=parseInt(k,10);}c=hmDevice.baseFontSize*k;e=c;i();}function j(k){switch(k){case"phone":f(1.0,10);break;case"tablet":f(1.0,10);break;case"desktop":f(1,10);break;default:f(1,10);}}function g(k){hmWebHelp.extFuncs("fontSize",k);xMessage.callFunction("hmnavigation","extFuncs('fontSize',"+k+")");xMessage.callFunction("hmsearch","extFuncs('fontSize',"+k+")");xMessage.callFunction("hmindex","extFuncs('fontSize',"+k+")");}function i(){var k=false,l;if(typeof h=="object"){k=h[1];l=h[0];}else{l=h;}if(k&&k=="global"){g(l);return;}else{if(l){if(e<hmDevice.maxFontSize){e+5<=hmDevice.maxFontSize?e+=5:e=hmDevice.maxFontSize;}}else{if(e>hmDevice.minFontSize){e-5>=hmDevice.minFontSize?e-=5:e=hmDevice.minFontSize;}}document.getElementsByTagName("html")[0].style.fontSize=e+"%";if(window.name=="hmMainPage"){hmDevice.hmSetCookie("fontSize",e.toString(),1);setTimeout(function(){if(!hmDevice.phone){hmWebHelp.resizePanes(hmpage.FnavWidth());}},100);}if(hmDevice.phone){hmWebHelp.funcs.mobTBfix;}}}if(c==null){j(hmDevice.device);}else{i();}}return a;}if(typeof hmWebHelp!="undefined"){hmWebHelp.funcs.fontSize=hmFS();}else{funcs.fontSize=hmFS();}
