//
// alerts.js
// Theme module
//

'use strict';

(function() {

  // Variables
  // =========

  var LIFETIME = 5000;

  // Methods
  // =======

  function toggleAlert(detail) {
    var alert = document.createElement('div');

    alert.classList.add(
      'alert',
      'alert-' + detail.type,
      'alert-fixed',
      'fade',
      'show'
    );

    alert.innerHTML = detail.message.replace('0 -', '');

    // Append to body
    document.body.appendChild(alert);

    // Close alert
    setTimeout(function() {
      $(alert).alert('close');
    }, LIFETIME);

  }

  // Events
  // ======

  window.addEventListener('alert.show', function(e) {
    toggleAlert(e.detail);
  });

})();