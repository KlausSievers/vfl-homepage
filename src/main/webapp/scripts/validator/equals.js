app.directive('equals', function() {
  return {
    require: 'ngModel',
    link: function(scope, elm, attrs, ctrl) {
      var compareField = attrs.equals;
      ctrl.$validators.equals = function(modelValue, viewValue) {
        if(scope[compareField]) {
          return angular.equals(scope[compareField], modelValue);
        }

        return true;
      };
    }
  };
});
