app.run(function (sidenavServ, Team, $location, $mdSidenav, $window, userServ) {
  createDefaultNav();
  createAdminNav();

  function createDefaultNav() {
    var itemSetName = "Default";
    var teamNames = Team.getTeamNames(function () {
      angular.forEach(teamNames, function (teamName) {
        sidenavServ.addItemToSet(itemSetName, teamName[0], function () {
          openTeam(teamName[1]);
        });
      });
      sidenavServ.setTeamSelectable(itemSetName, false);
      sidenavServ.addDividerToSet(itemSetName);
      sidenavServ.addItemToSet(itemSetName, "Trainingszeiten", function () {
        openTrainingtime();
      });
      sidenavServ.addItemToSet(itemSetName, "Vorstand", function () {
        openManagement();
      });

      sidenavServ.addItemToSet(itemSetName, "Waldgeflüster", function () {
        openMagazine();
      });

      sidenavServ.addItemToSet(itemSetName, "Download", function () {
        openDownload();
      });

      sidenavServ.addItemToSet(itemSetName, "Kollektion", function () {
        openCollection();
      });

      sidenavServ.setTitleToSet("Default", "VfL Grafenwald");
      sidenavServ.setItemset("Default");
    });

    openTeam = function openTeam(team) {
      $location.path("/team/" + team);
      $mdSidenav('mainMenu').close();
    };

    function openTrainingtime() {
      $location.path("/trainingszeiten");
      $mdSidenav('mainMenu').close();
    }

    function openManagement() {
      $location.path("/vorstand");
      $mdSidenav('mainMenu').close();
    }

    function openMagazine() {
      $location.path("/magazine");
      $mdSidenav('mainMenu').close();
    }

    function openDownload() {
      $location.path("/download");
      $mdSidenav('mainMenu').close();
    }

    function openCollection() {
      $location.path("/collection");
      $mdSidenav('mainMenu').close();
    }
  }

  function createAdminNav() {
    var itemSetName = "Admin";

    function getTeamId() {
      var currentTeam = userServ.getCurrentTeam();
      var teamId = 0;
      if (currentTeam) {
        teamId = currentTeam;
      }
      return teamId;
    }

    sidenavServ.setTitleToSet("Admin", "VfL Grafenwald - Admin");
    sidenavServ.setTeamSelectable(itemSetName, true);
    
    sidenavServ.addItemToSet(itemSetName, "News", function () {
      $location.path("/admin/" + getTeamId() + "/news");
    });

    sidenavServ.addItemToSet(itemSetName, "Spielplan", function () {
      openGameplan();
    });

    sidenavServ.addItemToSet(itemSetName, "Upload", function () {
      $location.path("/admin/" + getTeamId() + "/upload");
    });

    sidenavServ.addItemToSet(itemSetName, "Waldgeflüster", function () {
      $location.path("/admin/magazine");
    });

    sidenavServ.addItemToSet(itemSetName, "Abläufe & Zuständigkeiten", function () {
      $window.open("/wiki/index.html", "_blank");
    });

    sidenavServ.addItemToSet(itemSetName, "Kalender", function () {
      openCalendar();
    });

    sidenavServ.addItemToSet(itemSetName, "Belegungen", function () {
      openResourceCalendar();
    });

    function openCalendar() {
      $location.path("/admin/calendar");
      $mdSidenav('mainMenu').close();
    }

    function openResourceCalendar() {
      $location.path("/admin/resourceCalendar");
      $mdSidenav('mainMenu').close();
    }

    function openGameplan() {
      $location.path("/admin/" + getTeamId() + "/gameplan");
      $mdSidenav('mainMenu').close();
    }
  }
});
