app.factory('Match', function($resource){
    return $resource('/match/:id', {id:'@id'}, {
      nextToTeam: {method: 'GET', url: '/match/team/:shortName/next' },
      prevToTeam: {method: 'GET', url: '/match/team/:shortName/prev' },
      queryToTeam: {method: 'GET', url: '/match/team/:shortName/', isArray: true },
      next: {method: 'GET', url: '/match/next', isArray: true },
      prev: {method: 'GET', url: '/match/prev', isArray: true }

    });
});

