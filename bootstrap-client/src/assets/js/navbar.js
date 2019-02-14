//
// navbar.js
// Theme module
//

'use strict';

(function() {
  
  // Variables
  // =========

  var navbar = document.querySelector('.navbar');
  var navbarCollapse = document.querySelector('.navbar-collapse');

  var isLight = false;
  var isCollapsed = false;
  var isTogglable = navbar.classList.contains('navbar-togglable');

  // Methods
  // =======

  function makeNavbarLight() {

    if ( !isLight && isTogglable ) {
      navbar.classList.remove('navbar-dark');
      navbar.classList.add('navbar-light');

      isLight = true;
    }
  }
  
  function makeNavbarDark() {

    if ( isLight && isTogglable ) {
      navbar.classList.remove('navbar-light');
      navbar.classList.add('navbar-dark');

      isLight = false;
    }
  }

  function toggleNavbar(event, index) {

    if ( event == 'fpOnLeave' ) {

      if ( index == 0 ) {
        makeNavbarDark();
      } else {
        makeNavbarLight();
      }

    } else if ( event == 'collapse' ) {
      var scrollTop = window.pageYOffset;

      if ( scrollTop == 0 && isCollapsed ) {
        makeNavbarDark();
      } else {
        makeNavbarLight();
      }

    } else if ( event == 'scroll' || event == 'load' ) {
      var scrollTop = window.pageYOffset;

      if ( scrollTop == 0 && !isCollapsed && isLight ) {
        makeNavbarDark();
      } else if ( scrollTop !== 0 && !isLight ) {
        makeNavbarLight();
      }

    }

  }

  // Events
  // ======

  // Window events

  "load scroll fpOnLeave".split(' ').forEach(function(e) {
    window.addEventListener(e, function(e) {
      var type = e.type;
      var index = ( e.detail ) ? e.detail.destination.index : undefined;

      toggleNavbar(type, index);
    });
  });

  // Collapse events

  $(navbarCollapse).on({
    'show.bs.collapse': function() {
      toggleNavbar('collapse');

      isCollapsed = true;
    },
    'hidden.bs.collapse': function() {
      toggleNavbar('collapse');

      isCollapsed = false;
    }
  });

})();