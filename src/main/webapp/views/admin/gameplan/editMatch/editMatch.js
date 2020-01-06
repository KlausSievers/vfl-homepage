app.controller('EditMatchCtrl', function ($scope, $filter, $mdToast, Club) {
  var clubs = Club.query();

  $scope.match = angular.copy($scope.options.match);

  if ($scope.match.kickoff) {
    $scope.kickoffDate = moment($scope.match.kickoff);
    $scope.kickoffTime = moment($scope.match.kickoff).toDate();
  } else {
    $scope.kickoffDate = moment();
    $scope.kickoffTime = moment().toDate();
  }

  $scope.kickoffDate = $scope.kickoffDate.hours(0).minutes(0).seconds(0).toDate();

  $scope.opponentSearchText = '';

  $scope.getClubs = function () {
    return $filter('filter')(clubs, { name: $scope.opponentSearchText });
  };

  $scope.save = function () {
    var time = moment($scope.kickoffTime);
    $scope.match.kickoff = moment($scope.kickoffDate).add({ hours: time.hours(), minutes: time.minutes() }).toDate();
    //$scope.match.kickoff = $scope.kickoffTime.toISOString();
    if (angular.isNumber($scope.match.team)) {
      $scope.match.team = { teamId: $scope.match.team };
    }
    $scope.match.$save(function (savedMatch) {
      $mdToast.show($mdToast.simple().textContent("Spiel gespeichert"));
      $scope.close(savedMatch);
    },
      function () {
        $mdToast.show($mdToast.simple().textContent("Spiel konnte nicht gepeichert werden"));
      }
    );
  };
});




