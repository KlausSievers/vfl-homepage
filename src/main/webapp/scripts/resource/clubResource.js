app.factory('Club', function($resource){
  return $resource('/club/:id', {id:'@id'}, {

  });
});

