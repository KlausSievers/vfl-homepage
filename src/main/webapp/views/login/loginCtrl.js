app.controller('LoginCtrl',
//see https://spring.io/blog/2015/01/12/the-login-page-angular-js-and-spring-security-part-ii
//https://spring.io/guides/tutorials/spring-security-and-angular-js/
        function ($rootScope, $scope, $location, $mdDialog, userServ, sidenavServ) {
          $scope.credentials = {};
          $scope.login = function () {
            userServ.authenticate($scope.credentials).then(function () {
              $location.path("/admin");
              $scope.error = false;
              sidenavServ.setItemset("Admin");
              $mdDialog.hide();
            }, function () {
              $scope.error = true;
            });
          };
        });