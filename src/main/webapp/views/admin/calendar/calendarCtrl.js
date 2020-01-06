app.controller('CalendarCtrl',
  function (dialogServ) {
    //https://fullcalendar.io/

    var calendarEl = document.getElementById('calendar');
    var calendar = new FullCalendar.Calendar(calendarEl, {
      locale: 'de',
      plugins: ['dayGrid'],
      defaultView: 'dayGridMonth',
      header: {
        left: 'prev,next today addEventButton',
        center: 'title',
        right: 'dayGrid,dayGridWeek,dayGridMonth'
      },
      customButtons: {
        addEventButton: {
          text: '+',
          click: function () {
            dialogServ.open("Termin anlegen", "views/admin/calendar/event/event.html").then(function () {
            //hier noch das Event irgendwo hinzufügen
            });
          }
        }
      },
      navLinks: true, // can click day/week names to navigate views
      editable: true,
      eventLimit: true, // allow "more" link when too many events
      eventSources: [
        {
          url: '/calendar',
          type: 'GET'
        }
      ]
    });
    calendar.render();
  });

