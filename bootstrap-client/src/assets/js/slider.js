//
// slider.js
// Theme module
//

'use strict';

(function() {
  
  // Variables
  // =========

  var sliders = document.querySelectorAll('.slider');
  var sliderControls = document.querySelectorAll('.slider-control');
  var sliderBinded = document.querySelectorAll('[data-bind="slider"]');

  // Methods
  // =======

  function initSlider(elem) {
    var flkty = new Flickity(elem, {
      arrowShape: 'M 35 50 L 60 25 L 65 30 L 45 50 L 65 70 L 60 75 Z',
      cellAlign: 'left',
      contain: true,
      draggable: !elem.classList.contains('slider-no-draggable'),
      imagesLoaded: true,
      pageDots: false,
      prevNextButtons: !elem.classList.contains('slider-no-controls'),
      wrapAround: true
    });
  }

  function controlSlider(elem) {
    var direction = elem.getAttribute('data-slide');
    var selector = ( elem.getAttribute('data-target') ) ? elem.getAttribute('data-target') : elem.getAttribute('href');
    var target = document.querySelector(selector);

    // Get flickity instance
    var flkty = Flickity.data(target);
    
    // Slide specified direction
    if ( direction == 'previous' ) {
      flkty.previous();
    } else if ( direction == 'next' ) {
      flkty.next();
    }
  }

  function bindSliders(elem) {
    var flkty = Flickity.data(elem);

    // Listen to slide events
    flkty.on('change', function(index) {
      var selector = elem.getAttribute('data-target');
      var target = document.querySelector(selector);

      // Get flickity instance
      var flkty = Flickity.data(target);

      // Select slide
      flkty.select(index);
    })
  }

  // Events
  // ======

  // Init

  if ( sliders ) {
    [].forEach.call(sliders, function(elem) {
      initSlider(elem);
    });
  }

  // Control

  if ( sliderControls ) {
    [].forEach.call(sliderControls, function(elem) {
      elem.addEventListener('click', function(e) {
        e.preventDefault();

        controlSlider(this);
      });
    });
  }

  // Bind
  if ( sliderBinded ) {
    [].forEach.call(sliderBinded, function(elem) {
      bindSliders(elem);
    });
  }

})();