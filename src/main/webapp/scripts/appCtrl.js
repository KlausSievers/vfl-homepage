app.controller('AppCtrl',
  function ($scope, $mdSidenav, $location, $mdMedia, dialogServ, userServ, sidenavServ) {

    $scope.$mdMedia = $mdMedia;
    $scope.userServ = userServ;
    $scope.sidenavServ = sidenavServ;

    //          userServ.authenticate();

    $scope.toggleMainMenu = function () {
      $mdSidenav('mainMenu').toggle();
    };

    $scope.openHome = function () {
      $location.path("/");
      sidenavServ.setItemset("Default");
      $mdSidenav('mainMenu').close();
    };

    $scope.openAdmin = function () {
      $location.path("/admin");
      sidenavServ.setItemset("Admin");
      $mdSidenav('mainMenu').close();
    };

    $scope.openProfile = function () {
      $location.path("/admin/profile");
    };

    $scope.openLoginDlg = function () {
      dialogServ.open("Anmelden", "views/login/login.html").then(function () {
        $scope.selectedTeam = userServ.getCurrentTeam();
      });
    };

    $scope.handleLoginChange = function () {
      if (!userServ.isAuthenticated()) {
        $scope.openLoginDlg();
      } else {
        userServ.logout();
      }
    };

    $scope.setSelectedTeam = function(selectedTeam) {
      userServ.setCurrentTeam(selectedTeam);
    };
  }
);