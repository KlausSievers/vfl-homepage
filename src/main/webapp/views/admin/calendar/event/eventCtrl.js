app.controller('EventCtrl',
  function ($scope, $mdToast, Event) {
    $scope.event = new Event();
    $scope.resource = {
      P1: false,
      P2: false,
      K1: false,
      K2: false,
      K3: false,
      K4: false,
      H1: false,
      H2: false
    };

    $scope.save = function () {
      var startTime = moment($scope.startTime);
      $scope.event.start = moment($scope.startDate).add({ hours: startTime.hours(), minutes: startTime.minutes() }).toDate();

      var endTime = moment($scope.endTime);
      $scope.event.end = moment($scope.endDate).add({ hours: endTime.hours(), minutes: endTime.minutes() }).toDate();

      var resourceNames = Object.getOwnPropertyNames($scope.resource);
      var resoureArray = [];
      
      angular.forEach(resourceNames, function(resourceName) {
        if($scope.resource[resourceName]) {
          resoureArray.push(resourceName);
        }
      });

      $scope.event.resourceId = resoureArray.join(',');

      $scope.event.$save(function (savedEvent) {
        $mdToast.show($mdToast.simple().textContent("Termin gespeichert"));
        $scope.close(savedEvent);
      },
        function () {
          $mdToast.show($mdToast.simple().textContent("Termin konnte nicht gepeichert werden"));
        }
      );
    };
  });