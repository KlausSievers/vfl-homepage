app.controller('AdminCtrl',
        function ($scope, $mdMedia, $sce, News ) {
          $scope.newsList = News.getCategory({id: 18});
        }
);

