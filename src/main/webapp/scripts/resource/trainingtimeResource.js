app.factory('Trainingtime', function ($resource) {
  var teamRes = $resource('/trainingtime/:shortName', { shortName: '@teamShortName' },
    {
      'get': {
        method: 'GET',
        transformResponse: transform,
        isArray: true
      }
    });

  function transform(data, headersGetter, status) {
    var trainingtimeList = angular.fromJson(data);

    //@todo muss auch irgendwie von jackson möglich sein
    if (trainingtimeList) {
      for (var i = 0; i < trainingtimeList.length; i++) {
        trainingtimeList[i].startTime = moment(trainingtimeList[i].startTime, "HH:mm").toDate();
        trainingtimeList[i].endTime = moment(trainingtimeList[i].endTime, "HH:mm").toDate();
      }
    }

    return trainingtimeList;
  }

  return teamRes;
});

