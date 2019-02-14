//
// Dropdown ==================================
//

'use strict';

(function() {

  // Variables
  // =========

  var dropdown = document.querySelectorAll('.dropup, .dropright, .dropdown, .dropleft');
  var dropdownSubmenuToggle = document.querySelectorAll('.dropdown-menu .dropdown-toggle');


  // Methods
  // =======

  function toggleSubmenu(elem) {
    var dropdownSubmenus = elem.closest('.dropdown-menu').querySelectorAll('.dropdown-menu');
    var dropdownSubmenu = elem.parentElement.querySelector('.dropdown-menu');

    // Hide siblings menus
    [].forEach.call(dropdownSubmenus, function(menu) {
      if ( menu !== dropdownSubmenu ) {
        menu.classList.remove('show');
      }
    });

    // Toggle current menu
    dropdownSubmenu.classList.toggle('show');
  }

  function hideMenu(elem) {
    var dropdownSubmenus = elem.querySelectorAll('.dropdown-menu');

    // Hide submenus
    [].forEach.call(dropdownSubmenus, function(menu) {
      menu.classList.remove('show');
    });
  }


  // Events
  // ======

  if ( dropdownSubmenuToggle ) {
    [].forEach.call(dropdownSubmenuToggle, function(elem) {
      elem.addEventListener('click', function(e) {
        e.preventDefault();
        e.stopPropagation();

        // Toggle submenu
        toggleSubmenu(elem);
      });
    });
  }

  $(dropdown).on('hide.bs.dropdown', function(e) {
    hideMenu(this);
  });


})();