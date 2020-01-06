app.factory('Trainer', function ($resource, $http, $mdToast) {
  var trainerRes = $resource('/trainer/:id', { id: 'id' }, {

  }
  );

  trainerRes.sendMail = function (id, mail) {
    return $http.post('/trainer/' + id + '/contact', mail).then(
      function () {
        $mdToast.textContent("Mail wurde erfolgreich versendet").show();
      },
      function () {
        $mdToast.textContent("Mail konnte nicht versendet werden").show();
      }
    );
  };

  return trainerRes;
});



