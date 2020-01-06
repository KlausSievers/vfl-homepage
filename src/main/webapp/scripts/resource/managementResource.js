app.factory('Management', function ($resource, $http, $mdToast) {
  var managementRes = $resource('/management/:id', {id: '@id'},{
    queryYouth: { method: 'GET', url: '/management/youth', isArray: true },
    querySenior: { method: 'GET', url: '/management/senior', isArray: true }
  });

  managementRes.sendMail = function (id, mail) {
    $http.post('/management/' + id + '/contact', mail).then(
            function () {
              $mdToast.show($mdToast.simple().textContent("Mail wurde erfolgreich versendet"));
            },
            function(){
              $mdToast.show($mdToast.simple().textContent("Mail konnte nicht versendet werden"));
            }
    );
  };
  return managementRes;
});
