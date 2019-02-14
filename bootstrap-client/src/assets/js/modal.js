//
// modal.js
// Theme module
//

'use strict';

(function() {
  
  // Variables
  // =========

  var modal = document.querySelector('.modal');

  // Methods
  // =======

  function startVideo(video) {
    video.play();
  }

  function pauseVideo(video) {
    video.pause();
  }

  // Events
  // ======

  $(modal).on('shown.bs.modal hide.bs.modal', function(e) {
    var video = this.querySelector('video');

    if ( video && e.type == 'shown' ) {
      startVideo(video);
    } else if ( video && e.type == 'hide' ) {
      pauseVideo(video);
    }
  });

})();