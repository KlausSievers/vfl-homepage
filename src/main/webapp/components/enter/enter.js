app.directive('onEnter', EnterDirective);

function EnterDirective() {
  function link(scope, element, attributes) {
    var eventName = 'keyup';

    function executeEnter(event) {
      //Enter
      if (event.keyCode === 13 && event.target.tagName !== 'BUTTON' && event.target.tagName !== 'INPUT') {
        scope.onEnter();
      }
    }
    element[0].addEventListener(eventName, executeEnter);

    scope.$on('$destroy', function () {
      element[0].removeEventListener(eventName, executeEnter);
    });
  }

  return {
    scope: true,
    restrict: 'A',
    link: link
  };
}