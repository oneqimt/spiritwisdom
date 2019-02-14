//
// fullpage.js
// Theme module
//

'use strict';

(function() {

  // Variables
  // =========

  var fpWrapper = document.querySelector('.fp-wrapper');

  // Methods
  // =======

  function init(fpWrapper) {

    new fullpage(fpWrapper, {

      // License
      licenseKey: 'CA9DA262-9BD6447E-B1BF8C11-D1480312',

      // Navigation
      anchors: ['hero', 'about-1', 'about-2', 'services', 'promo', 'testimonials', 'pricing'],

      // Custom selectors
      sectionSelector: '.fp-section',

      // Scrolling
      scrollingSpeed: 700,
      easingcss3: 'cubic-bezier(0.55, 0.055, 0.675, 0.19)',
      scrollOverflow: true,

      // Design
      responsiveWidth: 768,

      // Events
      afterLoad: function(origin, destination, direction) {
        var event = new CustomEvent('fpAfterLoad', {'detail': {
          origin: origin,
          destination: destination,
          direction: direction
        }});

        window.dispatchEvent(event);
      },
      onLeave: function(origin, destination, direction) {
        var event = new CustomEvent('fpOnLeave', {'detail': {
          origin: origin,
          destination: destination,
          direction: direction
        }});
        
        window.dispatchEvent(event);
      }
    });
  }

  // Events
  // ======

  if ( fpWrapper ) {
    init(fpWrapper);
  }

})();