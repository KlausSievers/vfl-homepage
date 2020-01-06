app.controller('TeamCtrl',
  function ($scope, $routeParams, $locale, $sce, $mdMedia, Team, Match, Trainingtime) {
    $scope.$mdMedia = $mdMedia;
    $scope.team = Team.get({ shortName: $routeParams.team });
    $scope.trainingtime = Trainingtime.get({ shortName: $routeParams.team });


    $scope.$sce = $sce;

    $scope.imgError = false;
    $scope.setImgError = function () {
      $scope.imgError = true;
    };

    $scope.nextMatch = Match.nextToTeam({ shortName: $routeParams.team });
    $scope.prevMatch = Match.prevToTeam({ shortName: $routeParams.team });
    $scope.matches = Match.queryToTeam({ shortName: $routeParams.team });

    $scope.days = $locale.DATETIME_FORMATS.DAY;


    $scope.getGoalsHome = function (match) {
      return angular.isNumber(match.goalsHome) ? match.goalsHome : "-";
    };

    $scope.getGoalsAway = function (match) {
      return angular.isNumber(match.goalsAway) ? match.goalsAway : "-";
    };
  }
);

