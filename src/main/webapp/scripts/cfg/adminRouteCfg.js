app.config(function ($routeProvider) {
  $routeProvider.when("/admin", {
    templateUrl: "views/admin/index.html",
    controller: "LandingCtrl"
  }).when("/admin/profile", {
    templateUrl: "views/admin/profile/profile.html",
    controller: "ProfileCtrl"
  }).when("/admin/:team/news", {
    templateUrl: "views/admin/news/news.html",
    controller: "NewsCtrl"
  }).when("/admin/:team/gameplan", {
    templateUrl: "views/admin/gameplan/gameplan.html",
    controller: "GameplanCtrl"
  }).when("/admin/magazine", {
    templateUrl: "views/admin/upload/magazine.html",
    controller: "UploadCtrl"
  }).when("/admin/:team/upload", {
    templateUrl: "views/admin/upload/upload.html",
    controller: "UploadCtrl"
  }).when("/admin/calendar", {
    templateUrl: "views/admin/calendar/calendar.html",
    controller: "CalendarCtrl"
  }).when("/admin/resourceCalendar", {
    templateUrl: "views/admin/resourceCalendar/resourceCalendar.html",
    controller: "ResourceCalendarCtrl"
  }).otherwise({
    redirectTo: "/"
  });
});