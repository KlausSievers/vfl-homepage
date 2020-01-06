(function () {
  'use strict';

  app.directive('match', MatchDirective);

  function MatchDirective() {
    return {
      scope: {
        title: '@',
        match: '='
      },
      restrict: 'E',
      templateUrl: 'components/match/match.html'
    };
  }
})();