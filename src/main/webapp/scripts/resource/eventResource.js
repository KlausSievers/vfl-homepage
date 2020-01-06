app.factory('Event', function ($resource) {
  return $resource('/calendar/:id', {id: '@id'},
          {});
});
