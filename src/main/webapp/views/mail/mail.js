app.controller('MailCtrl',
        function ($scope, $routeParams, mailServ) {
          $scope.mail = {};
          
          var self = this;
          $scope.send = function() {
            mailServ.sendMail($routeParams.id, $scope.mail);
          };
        }
);

