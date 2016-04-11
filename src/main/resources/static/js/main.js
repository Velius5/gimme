jQuery(document).ready(function($){
    
    /* PARALLAX EFFECT ON TOP */
    $('.main_header').parallax({imageSrc: 'images/main_header_bg.jpg'});
    
    /* WAYPOINTS */
	window.scrollTo(0, 0);
        var waypointBlackTop = new Waypoint({
          element: document.getElementById("topmenu"),
          handler: function(direction) {
            $("#topmenu").css({'background':'#1d1610'});
          },
          offset: -50 
        });

        var waypointTransparentTop = new Waypoint({
          element: document.getElementById('topmenu'),
          handler: function(direction) {
            $("#topmenu").css({'background':'transparent'});
          },
          offset: -20
        });

        /* GO TO MIDDLE FUNCTION  */
        function gotoMiddle(obj) {
            var myheight = $(obj).parent().height()/2;
            var objheight = $(obj).height()/2;
            $(obj).css('top', myheight-objheight);
        }
         gotoMiddle('.login_panel');
});
