app.controller('DownloadCtrl', function ($scope, $http, $window) {
  $http.get('/archive/documents').then(
          function (response) {
            $scope.metadataList = response.data;
          });

  $scope.download = function (uuid) {
    //document.location.href = '/archive/documents/' + uuid;
    $window.open("/archive/document/" + uuid);
  };
});

