app.controller('NewsCtrl',
  function ($scope, $routeParams, $http, $mdToast, userServ, Team, News) {
    $scope.editNews = new News();
    $scope.editNews.text = "";

    $scope.team = null;
    $scope.news = [];
    if ($routeParams.team && $routeParams.team !== "0") {
      $scope.team = Team.get({ shortName: $routeParams.team });
      $scope.news = News.getNews({ shortName: $routeParams.team });
    }

    $scope.newsLanding = News.getCategory({ id: 12 });
    $scope.newsAdmin = News.getCategory({ id: 18 });

    $scope.tinymceOptions = {
      height: 400,
      plugins: [
        'advlist autolink lists link image charmap print preview anchor',
        'searchreplace visualblocks code fullscreen ',
        'insertdatetime media table contextmenu paste code'
      ],
      toolbar: 'insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image | ibrowser',
      content_css: '//www.tinymce.com/css/codepen.min.css',
      image_description: false,
    };

    $scope.save = function (category) {
      //            $scope.editNews.text = tinymce.activeEditor.getContent();
      $scope.editNews.insertDate = $scope.editNews.insertDate || new Date();
      $scope.editNews.category = category || $scope.team.category.id;
      $scope.editNews.editor = null;
      //$scope.news.$save();
      //@todo /admin/jugend/news -> sonst keine sicherheit
      $http.post("/news", $scope.editNews).then(
        function () {
          $mdToast.show($mdToast.simple().theme("green").textContent("News gespeichert"));
        },
        function () {
          $mdToast.show($mdToast.simple().theme("red").textContent("News konnte nicht gespeichert werden"));
        });
    };

    $scope.delete = function (news) {
      news.$delete().then(function () {
        $mdToast.show($mdToast.simple().theme("green").textContent("News gelöscht"));
        $scope.news = News.getNews({ shortName: $routeParams.team });
        $scope.editNews = new News();
      },function () {
        $mdToast.show($mdToast.simple().theme("red").textContent("News konnte nicht gelöscht werden"));
      });
    };

    $scope.selectNews = function (toDelete) {
      $scope.editNews = toDelete;
      //$scope.news.remove($scope.news.indexof(toDelete));
    };

    $scope.uploadFile = function (file) {
      var uploadUrl = "/news/upload";
      var fd = new FormData();
      fd.append('file', file);
      $http.post(uploadUrl, fd, {
        transformRequest: angular.identity,
        headers: {
          'Content-Type': undefined
        }
      }).then(function (response) {
        $scope.editNews.text += ' <img src="' + response.data.path + '" alt=""/>';
      }, function () {
        $mdToast.show(
          $mdToast.simple()
            .textContent('Upload fehlgeschlagen')
            .hideDelay(3000)
        );
      });
    };
  });




