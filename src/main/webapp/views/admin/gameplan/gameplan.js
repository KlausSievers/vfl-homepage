app.controller('GameplanCtrl', function ($scope, $routeParams, $mdToast, $mdMedia, Team, Match, dialogServ) {
  $scope.$mdMedia = $mdMedia;
  
  var team = null;
  if ($routeParams.team) {
    $scope.matches = Match.queryToTeam({ shortName: $routeParams.team });
    team = Team.get({ shortName: $routeParams.team });
  }

  $scope.getNameHome = function (match) {
    return match.home ? "VfL Grafenwald" : match.opponent.name;
  };
  $scope.getNameAway = function (match) {
    return match.home ? match.opponent.name : "VfL Grafenwald";
  };

  $scope.getGoalsHome = function (match) {
    return angular.isNumber(match.goalsHome) ? match.goalsHome  : "-";
  };

  $scope.getGoalsAway = function (match) {
    return  angular.isNumber(match.goalsAway) ?  match.goalsAway : "-";
  };

  $scope.delete = function (match) {
    match.$delete({ shortName: $routeParams.team }).then(function () {
      $mdToast.show($mdToast.simple().theme("green").textContent("Spiel gelöscht"));
      $scope.matches = Match.queryToTeam({ shortName: $routeParams.team });
    }, function () {
      $mdToast.show($mdToast.simple().theme("red").textContent("Spiel konnte nicht gelöscht werden"));
    });
  };


  $scope.add = function () {
    dialogServ.open('Spiel anlegen', '/views/admin/gameplan/editMatch/editMatch.html', { match: new Match({ team: team }), result: false}).then(
      function (savedMatch) {
        $scope.matches.push(savedMatch);
      });
  };

  $scope.edit = function (match) {
    dialogServ.open('Spiel bearbeiten', '/views/admin/gameplan/editMatch/editMatch.html', { match: match, result: true }).then(
      function (savedMatch) {
        angular.copy(savedMatch, match);
      }
    );
  };

});




