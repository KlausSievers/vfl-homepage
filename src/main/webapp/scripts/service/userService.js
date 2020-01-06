app.factory('userServ', function ($http, $location, $q, $mdToast, Team, sidenavServ) {
  var roles = [];
  var teams = [];
  var authenticated = false;
  var name = '';
  var currentTeam = null;

  function login(credentials) {
    var config = {
      headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
      transformRequest: function (obj) {
        var str = [];
        for (var p in obj)
          str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
        return str.join("&");
      }
    };

    return $q(function (resolve, reject) {
      $http.post("/login", credentials, config).then(
        function (response) {
          $http.get('user').then(function (response) {
            var data = response.data;
            if (data.name) {
              roles = [];
              teams = [];
              angular.forEach(data.authorities, function (authority) {
                roles.push(authority.authority.toUpperCase());
              });

              var teamNames = Team.getTeamNames(function () {
                angular.forEach(teamNames, function (teamName) {
                  if (hasRole(teamName[0].toUpperCase())) {
                    teams.push(teamName);
                  }
                });

                if (teams.length > 0) {
                  currentTeam = teams[0][1];
                }

                resolve(response.data);
              },reject);

              authenticated = true;
              name = data.name;
              
            } else {
              authenticated = false;
              reject(response);
            }
          }, function () {
            authenticated = false;
            reject(response);
          });
        }, function (response) {
          reject(response);
        });
    });
  }

  function logout() {
    $http.get("/logout").then(function () {
      authenticated = false;
      currentTeam = null;
      $location.path("/");
      sidenavServ.setItemset("Default");

    });
  }

  function changePassword(newPassword) {
    $http.post("/user/updatePassword", { 'newPassword': newPassword }).then(function () {
      $mdToast.show($mdToast.simple().theme("green").textContent("Password wurde gespeichert"));
    },
      function () {
        $mdToast.show($mdToast.simple().theme("red").textContent("Password konnte nicht gespeichert werden"));
      });
  }

  function isAuthenticated() {
    return authenticated;
  }

  function hasRole(role) {
    return roles.indexOf(role) >= 0;
  }

  function getTeams() {
    return teams;
  }

  function getName() {
    return name;
  }

  function setCurrentTeam(team) {
    currentTeam = team;
  }

  function getCurrentTeam() {
    return currentTeam;
  }

  return {
    authenticate: login,
    isAuthenticated: isAuthenticated,
    changePassword: changePassword,
    logout: logout,
    hasRole: hasRole,
    getTeams: getTeams,
    getName: getName,
    setCurrentTeam: setCurrentTeam,
    getCurrentTeam: getCurrentTeam
  };
});

