/*! Help & Manual WebHelp 3 Script functions
Copyright (c) 2014-2016 by Tim Green. All rights reserved. Contact tg@it-authoring.com
*/
hmWebHelp.funcs.toggleToggles=function(){var a=hmAnimate;if(HMToggles.length!=null){var b=true;for(var c=0;c<HMToggles.length;c++){if(HMToggles[c].getAttribute("hm.state")=="1"){b=false;break}}if(HMToggles.length>20&&a){hmAnimate=false}HMToggleExpandAll(b);hmAnimate=a}};
