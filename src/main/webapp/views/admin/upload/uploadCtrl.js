app.controller('UploadCtrl', function ($scope, $routeParams,$filter, fileUpload, Team) {
    var team = Team.get({shortName: $routeParams.team});
    
    $scope.uploadFile = function (route) {
      var file = $scope.myFile;
      var issue = $scope.issue || null;
      console.log('file is ' + JSON.stringify(file));
      var uploadUrl = '/'+route+'/upload';
      fileUpload.uploadFileToUrl(uploadUrl, file, issue);
    };
  });

app.controller('ArchiveCtrl', function ($scope, $http) {
  $scope.search = function (name, date) {
    $http.get("/archive/documents", {
      params: {
        person: name,
        date: date
      }
    }).then(function (response) {
      $scope.metadataList = response.data;
    });
  };
});
