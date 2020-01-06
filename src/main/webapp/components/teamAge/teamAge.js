(function () {
  'use strict';

  app.directive('teamAge', TeamAgeDirective);

  function TeamAgeDirective($filter) {
    function link(scope) {
      function formatYear(year) {
        return $filter('date')(year, 'yyyy');
      }

      scope.getYearString = function() {
        if(scope.yearStart && scope.yearEnd) {
          if(scope.yearStart !== scope.yearEnd) {
            return formatYear(scope.yearStart) + ' - ' + formatYear(scope.yearEnd);
          } else {
            return formatYear(scope.yearStart);
          }
        } else if (scope.yearStart) {
          return formatYear(scope.yearStart)+' und älter';
        }else if (scope.yearEnd) {
          return formatYear(scope.yearEnd)+' und jünger';
        }
      };
    }

    return {
      scope: {
        yearStart: '@',
        yearEnd: '@'
      },
      link: link,
      restrict: 'E',
      templateUrl: 'components/teamAge/teamAge.html'
    };
  }
})();