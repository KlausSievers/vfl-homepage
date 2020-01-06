(function () {
  'use strict';

  app.directive('news', NewsDirective);

  function NewsDirective($sce) {
    function postLink(scope, element, attrs) {
      scope.$sce = $sce;
    }     

    return {
      scope: {
        newsList:'=list'
      },
      link: postLink,
      restrict: 'E',
      templateUrl: 'components/news/news.html'
    };
  }
})();