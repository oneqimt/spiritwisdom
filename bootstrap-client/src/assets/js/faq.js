//
// faq.js
// Theme module
//

'use strict';

(function() {

  // Variables
  // =========

  var faqCollapse = document.querySelectorAll('.faq-item .collapse');

  // Methods
  // =======

  function toggleCollapse($this) {
    var $faq = $this.closest('.faq');
    var $faqCollapse = $faq.find('.collapse').not($this);

    $faqCollapse.collapse('hide');
  }

  // Events
  // ======

  if ( faqCollapse ) {
    [].forEach.call(faqCollapse, function(elem) {
      var $this = $(elem);

      $this.on('show.bs.collapse', function() {
        toggleCollapse($this);
      });
    });
  };
  
})();