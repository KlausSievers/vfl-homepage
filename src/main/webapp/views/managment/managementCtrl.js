app.controller('ManagementCtrl',
        function ($scope, Management) {
          $scope.senior = Management.querySenior();
          $scope.youth = Management.queryYouth();
        }
);

