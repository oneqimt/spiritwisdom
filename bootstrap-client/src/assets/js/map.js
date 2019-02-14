//
// map.js
// Theme module
//

'use strict';

(function() {
  
  // Variables
  // =========

  var maps = document.querySelectorAll('.map-container');

  // Methods
  // =======

  function initMap(elem) {

    // Get map data
    var zoom = parseInt(elem.getAttribute('data-zoom'));
    var markers = JSON.parse(elem.getAttribute('data-markers'));
    var styles = [
        {"featureType":"all", "elementType":"geometry.fill", "stylers":[{"weight":"2.00"}]},
        {"featureType":"all","elementType":"geometry.stroke","stylers":[{"color":"#9c9c9c"}]},
        {"featureType":"all","elementType":"labels.text","stylers":[{"visibility":"on"}]},
        {"featureType":"landscape","elementType":"all","stylers":[{"color":"#f2f2f2"}]},
        {"featureType":"landscape","elementType":"geometry.fill","stylers":[{"color":"#ffffff"}]},
        {"featureType":"landscape.man_made","elementType":"geometry.fill","stylers":[{"color":"#ffffff"}]},
        {"featureType":"poi","elementType":"all","stylers":[{"visibility":"on"}]},//NOTE I turned these on
        {"featureType":"road","elementType":"all","stylers":[{"saturation":-100}, {"lightness":45}]},
        {"featureType":"road","elementType":"geometry.fill","stylers":[{"color":"#eeeeee"}]},
        {"featureType":"road","elementType":"labels.text.fill","stylers":[{"color":"#7b7b7b"}]},
        {"featureType":"road","elementType":"labels.text.stroke","stylers":[{"color":"#ffffff"}]},
        {"featureType":"road.highway","elementType":"all","stylers":[{"visibility":"simplified"}]},
        {"featureType":"road.arterial","elementType":"labels.icon","stylers":[{"visibility":"off"}]},
        {"featureType":"transit","elementType":"all","stylers":[{"visibility":"off"}]},
        {"featureType":"water","elementType":"all","stylers":[{"color":"#46bcec"}, {"visibility":"on"}]},
        {"featureType":"water","elementType":"geometry.fill","stylers":[{"color":"#c8d7d4"}]},
        {"featureType":"water","elementType":"labels.text.fill","stylers":[{"color":"#070707"}]},
        {"featureType":"water","elementType":"labels.text.stroke","stylers":[{"color":"#ffffff"}]}];
    var center = {
      lat: markers[0][0],
      lng: markers[0][1]
    };

    // Init map
    var map = new google.maps.Map(elem, {
      center: center,
      styles: styles,
      zoom: zoom
    });

    // Get bounds
    var bounds = new google.maps.LatLngBounds();

    // Create markers
    markers.forEach(function(item, i, arr) {
      var position = {
        lat: item[0],
        lng: item[1]
      };
      var marker = new google.maps.Marker({
        position: position,
        map: map
      });

      // Extend bounds
      bounds.extend(position);
    });

    // Fit markers into bounds
    if ( !zoom ) {
      map.fitBounds(bounds);
    }
  }

  // Events
  // ======

  // Init map

  if ( maps ) {
    [].forEach.call(maps, function(elem) {
      initMap(elem);
    });
  }

})();