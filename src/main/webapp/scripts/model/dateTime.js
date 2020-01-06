app.directive('dateTime', function ($filter) {
    return {
        require: '?ngModel',
        link: function (scope, elem, attrs, ctrl) {
            if (!ctrl) return;

            ctrl.$formatters.unshift(function () {
                return $filter('date')(ctrl.$modelValue, 'dd.MM.yyyy HH:mm');
            });

            ctrl.$parsers.unshift(function (viewValue) {
                var date = moment(viewValue);
                if (!date.isValid()) {
                    date = moment(viewValue, "dd.MM.YYYY HH:mm");
                }
                return date.toDate();
            });
        }
    };
});

app.directive('time', function ($filter) {
    return {
        require: '?ngModel',
        link: function (scope, elem, attrs, ctrl) {
            if (!ctrl) return;

            ctrl.$formatters.unshift(function () {
                return $filter('date')(ctrl.$modelValue, 'HH:mm');
            });

            ctrl.$parsers.unshift(function (viewValue) {
                var date = moment(viewValue);
                if (!date.isValid()) {
                    date = moment(viewValue, "HH:mm");
                }
                return date.toDate();
            });
        }
    };
});

app.directive('date', function ($filter) {
    return {
        require: '?ngModel',
        link: function (scope, elem, attrs, ctrl) {
            if (!ctrl) return;

            ctrl.$formatters.unshift(function () {
                return $filter('date')(ctrl.$modelValue, 'dd.MM.yyyy');
            });

            ctrl.$parsers.unshift(function (viewValue) {
                var date = moment(viewValue, "DD.MM.YYYY");
                return date.toDate();
            });
        }
    };
});