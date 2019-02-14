//
// newsletter.js
// Theme component
//

'use strict';

(function() {

  // Variables
  // =========

  var form = document.querySelector('#mc-embedded-subscribe-form');
  var formEmail = document.querySelector('#mce-EMAIL');
  var formClone = document.querySelectorAll('.form-mailchimp-clone');
  var formCloneEmail = document.querySelectorAll('.form-mailchimp-clone input[type="email"]');

  var message = {
    error: 'Could not connect to the registration server. Please try again later.'
  };

  // Methods
  // =======

  function signUp(form) {

    $.ajax({
      type: form.getAttribute('method'),
      url: form.getAttribute('action'),
      data: $(form).serialize(),
      cache: false,
      dataType: 'json',
      contentType: "application/json; charset=utf-8",
      error: function(err) {
        var event = new CustomEvent('alert.show', {'detail': {
          type: 'danger',
          message: message.error
        }});

        window.dispatchEvent(event);
      },
      success: function(data) {
        
        if (data.result != 'success') {
          var event = new CustomEvent('alert.show', {'detail': {
            type: 'danger',
            message: data.msg
          }});

          window.dispatchEvent(event);
        } else {

          // Show a confirmation
          var event = new CustomEvent('alert.show', {'detail': {
            type: 'success',
            message: data.msg
          }});

          window.dispatchEvent(event);
          
          // Reset the form
          form.reset();
        }
      }
    });
  }

  function imitateSignUp(form) {
    if ( form ) {
      form.submit();
    }
  }

  function copyInputContent(formCloneEmail, formEmail) {
    var val = formCloneEmail.value;

    if ( formEmail ) {
      formEmail.value = val;
    }
  }

  // Events
  // ======

  // Sign up to a Mailchimp newsletter campaign on form submit
  if ( form ) {
    form.addEventListener('submit', function(e) {
      e.preventDefault();

      signUp(form);
    });
  }

  // Imitate form submission on clone submit
  if ( formClone ) {
    [].forEach.call(formClone, function(elem) {
      elem.addEventListener('submit', function(e) {
        e.preventDefault();

        imitateSignUp(form);
      });
    });
  }

  // Copy input content to the original form input field
  if ( formCloneEmail ) {
    [].forEach.call(formCloneEmail, function(elem) {
      elem.addEventListener('keyup', function() {
        copyInputContent(this, formEmail);
      });
    });
  }

})();