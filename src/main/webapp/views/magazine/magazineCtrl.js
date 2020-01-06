app.controller('MagazineCtrl', function ($scope, $http, $window) {
  $http.get('/magazine/documents').then(
          function (response) {
            $scope.metadataList = response.data;
          });

  $scope.download = function (uuid) {
    //document.location.href = '/archive/documents/' + uuid;
    $window.open("/magazine/" + uuid);
  };
});

