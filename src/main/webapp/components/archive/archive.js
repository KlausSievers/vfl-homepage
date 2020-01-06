app.directive('fileModel', ['$parse', function ($parse) {
    return {
      restrict: 'A',
      link: function (scope, element, attrs) {
        var model = $parse(attrs.fileModel);
        var modelSetter = model.assign;

        element.bind('change', function () {
          scope.$apply(function () {
            modelSetter(scope, element[0].files[0]);
          });
        });
      }
    };
  }]);

app.service('ArchiveService', ['$http', '$rootScope', function ($http, $rootScope) {
    this.search = function (name, date) {
      $http.get("http://localhost:8090/archive/documents", {
        params: {
          person: name,
          date: date
        }
      }).then(function (response) {
        $rootScope.metadataList = response.data;
      });
    }
  }]);

app.service('fileUpload', function ($http, ArchiveService, $mdToast) {
  this.uploadFileToUrl = function (uploadUrl, file, issue) {
    var fd = new FormData();
    fd.append('file', file);
    fd.append('issue', issue);
    $http.post(uploadUrl, fd, {
      transformRequest: angular.identity,
      headers: {
        'Content-Type': undefined
      }
    }).then(function () {
      ArchiveService.search(null, null);
      $mdToast.show(
              $mdToast.simple()
              .textContent('Upload erfolgreich')
              .hideDelay(3000)
              );
    },function () {
      ArchiveService.search(null, null);
      $mdToast.show(
              $mdToast.simple()
              .textContent('Upload  fehlgeschlagen')
              .hideDelay(3000)
              );
    });
  };
});
