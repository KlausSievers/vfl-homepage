app.controller('ResourceCalendarCtrl',
  function () {
    //https://fullcalendar.io/
    var calendarEl = document.getElementById('resourceCalendar');
    var calendar = new FullCalendar.Calendar(calendarEl, {
      schedulerLicenseKey: 'GPL-My-Project-Is-Open-Source',
      locale: 'de',
      plugins: ['interaction','resourceTimeline'],     
      defaultView: 'resourceTimelineDay',
      resourceLabelText: 'Belegungen',
      height: 550,
      header: {
        left: 'today prev,next',
        center: 'title',
        right: 'resourceTimelineDay,resourceTimelineWeek,resourceTimelineMonth,resourceTimelineYear'
      },
      resources: '/views/admin/resourceCalendar/resources.json',
      events:'/calendar/resource'
    });
    calendar.render();
  });
