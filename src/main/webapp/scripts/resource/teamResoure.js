app.factory('Team', function ($resource) {
  var teamRes = $resource('/team/:shortName', { shortName: '@teamShortName' },
    {
      'get': {
        method: 'GET'
      },
      'query': {
        method: 'GET',
        isArray: true
      },
      'getTeamNames': {
        method: "GET",
        url: "/team/names",
        isArray: true
      }
    });


  return teamRes;
});

