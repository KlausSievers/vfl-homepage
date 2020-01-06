app.controller('TrainingtimeCtrl',
        function ($scope, Team, Trainingtime, $locale) {
          $scope.teams = Team.query(function() {
            angular.forEach($scope.teams, function(team) {
             team.trainingtimeList = Trainingtime.get({shortName: team.shortName});
            });
          });


          $scope.days = $locale.DATETIME_FORMATS.DAY;


        }
);

