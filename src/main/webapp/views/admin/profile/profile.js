app.controller('ProfileCtrl', function ($scope, userServ) {
    $scope.changePassword = function() {
      userServ.changePassword($scope.newPassword);
    };
  });




