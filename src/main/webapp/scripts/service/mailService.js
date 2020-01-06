app.factory('mailServ', function ($http, $mdToast) {
  function sendMail(id, mail) {
    $http.post('/mail/' + id, mail).then(
      function () {
        $mdToast.show($mdToast.simple().textContent("Mail wurde erfolgreich versendet"));
      },
      function () {
        $mdToast.show($mdToast.simple().textContent("Mail konnte nicht versendet werden"));
      }
    );
  }

  return {
    sendMail: sendMail
  };
});