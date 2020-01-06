var app = angular.module('VflHomepage', [ 'ngMaterial', 'ngLocale', 'ngResource', 'ngMessages', 'ngAnimate', 'ngRoute',
  'md.data.table', 'fixed.table.header', 'mdPickers', 'ui.tinymce']);


app.config(function($httpProvider){
  $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
});

app.config(function ($mdThemingProvider) {
  $mdThemingProvider.theme('default')
          .primaryPalette('green')
          .accentPalette('grey');
});


app.config(function($httpProvider){
  $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
});

