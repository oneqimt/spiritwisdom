//
// animation.js
// Theme component
//

'use strict';

(function() {

  // Variables
  // =========

  var animations = document.querySelectorAll('[data-toggle="animation"]');

  // Methods
  // =======

  function animate(elem, event, index) {
    if ( event == 'fpAfterLoad' ) {
      var currentSection = elem.closest('.fp-section').classList.contains('active');
      
      if ( currentSection ) {
        elem.classList.add('animate');
      }
    } else {
      elem.classList.add('animate');
    }
  }

  // Events
  // ======

  "load fpAfterLoad".split(' ').forEach(function(event) {
    window.addEventListener(event, function(e) {
      var event = e.type;

      [].forEach.call(animations, function(elem) {
        var trigger = elem.getAttribute('data-animation-trigger');

        // Window events
        if ( trigger == 'load' && event == 'load' ) {
          animate(elem, event);
        }

        // Fullpage.js events
        if ( trigger == 'fpAfterLoad' && event == 'fpAfterLoad' ) {
          var index = e.detail.destination.index;

          animate(elem, event, index);
        }

        // Waypoints.js events
        if ( trigger == 'enter' && event == 'load' || trigger =='entered' && event == 'load' ) {
          new Waypoint.Inview({
            element: elem,
            enter: function() {
              if ( trigger == 'enter' ) {
                animate(elem, event);
              }
            },
            entered: function() {
              if ( trigger == 'entered' ) {
                animate(elem, event);
              }
            }
          });
        }

      });

    });
  });
  
})();