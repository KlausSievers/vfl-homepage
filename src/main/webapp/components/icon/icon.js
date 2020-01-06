(function () {
  'use strict';

  app.directive('swIcon', IconDirective);

  function IconDirective() {
    function link(scope, element, attributes) {
      scope.getIconName = function () {
         return scope.iconset + ":ic_" +scope.icon + "_24px";
      };

      scope.getIconWidth = function () {
        if (scope.width) {
          return scope.width;
        } else {
          return "100%";
        }
      };

      scope.getIconColor = function () {
        if (scope.color) {
          return {'fill': scope.color};
        }
      };
    }

    return {
      scope: {
        iconset: '@',
        icon: '@',
        defaultIcon: '@',
        color: '@?',
        width: '@?'
      },
      restrict: 'E',
      template: '<md-icon ng-style="getIconColor()" style="width: {{getIconWidth()}}" md-svg-icon="{{getIconName()}}" aria-label="{{icon}}" ></md-icon>',
      link: link
    };
  }
})();