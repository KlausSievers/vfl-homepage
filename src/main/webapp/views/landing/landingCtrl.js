app.controller('LandingCtrl',
  function ($scope, $mdMedia, $sce, $q, News, $http, $window, $filter, Match) {
    $scope.$mdMedia = $mdMedia;
    $scope.$sce = $sce;
    News.getCategory({ id: 12 }, (news) => {
      $scope.newsList = news;
    });


    $http.get('/magazine/documents').then(
      function (response) {
        if (response.data.length > 0) {
          $scope.magazine = $filter('orderBy')(response.data, '-documentDate')[0];
        } else {
          $scope.magazine = null;
        }
      });

      $scope.nextMatches = Match.next();
      $scope.prevMatches = Match.prev();

    $scope.download = function (uuid) {
      //document.location.href = '/archive/documents/' + uuid;
      $window.open("/magazine/" + uuid);
    };

    $scope.isSameDay = function(m1, m2) {
      return moment(m1).dayOfYear() === moment(m2).dayOfYear();
    }

    console.log($mdMedia('gt-md'));
    console.log($mdMedia('gt-xs'));
    console.log(($mdMedia('gt-xs') && !$mdMedia('gt-md')));
  }
);

