app.config(function ($locationProvider, $routeProvider) {
//  $locationProvider.hashPrefix('!');

  $routeProvider.when("/", {
    templateUrl: "views/landing/landing.html",
    controller: "LandingCtrl"
  }).when("/team/:team", {
    templateUrl: "views/team/team.html",
    controller: "TeamCtrl"
  }).when("/trainingszeiten", {
    templateUrl: "views/trainingtime/trainingtime.html",
    controller: "TrainingtimeCtrl"
  }).when("/impressum", {
    templateUrl: "views/impressum/impressum.html"
  }).when("/dataprivacy", {
    templateUrl: "views/dataprivacy/dataprivacy.html"
  }).when("/login", {
    templateUrl: "views/login/login.html",
    controller: "LoginCtrl"
  }).when("/vorstand", {
    templateUrl: "views/managment/managment.html",
    controller: "ManagementCtrl"
  }).when("/mail/:id", {
    templateUrl: "views/mail/mail.html",
    controller: "MailCtrl"
  }).when("/download", {
    templateUrl: "views/download/download.html",
    controller:'DownloadCtrl'
  }).when("/magazine", {
    templateUrl: "views/magazine/magazine.html",
    controller:'MagazineCtrl'
  }).when("/collection", {
    templateUrl: "views/collection/collection.html"
  }).otherwise({
    redirectTo: "/"
  });
});

