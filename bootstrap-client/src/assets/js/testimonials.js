//
// testimonials.js
// Theme module
//

'use strict';

(function() {
  
  // Variables
  // =========

  var testimonialSliders = document.querySelectorAll('.testimonial-slider');

  // Methods
  // =======

  function initSlider(slider) {
    new Flickity(slider, {
      cellAlign: 'center',
      initialIndex: 2,
      prevNextButtons: false,
      pageDots: false,
      contain: true,
      wrapAround: true,
      imagesLoaded: true,
      percentPosition: true
    });
  }

  // Events
  // ======

  // Init slider

  if ( testimonialSliders ) {
    [].forEach.call(testimonialSliders, function(slider) {
      initSlider(slider);
    });
  }

})();