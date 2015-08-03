var map;
var markersArray = [];

function initialize(canvas,lat,lng){
      changeMapPoint(canvas,lat,lng);
}
            
function changeMapPoint(canvas,x,y) {
    deleteOverlays();
    var latlng = new google.maps.LatLng(x, y);
    var myOptions = {
        zoom: 12,
        center: latlng,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    };
    map = new google.maps.Map(canvas,myOptions);
    addIcon(latlng,"Your position.","images/mc/maptag.png");
}

function track(locations,times){
    deleteOverlays();
    var flightPath = new google.maps.Polyline({
        path: locations,
        strokeColor: "#FF0000",
        strokeOpacity: 1.0,
        strokeWeight: 2
    });

    flightPath.setMap(map);
    markersArray.push(flightPath);
    
    for (var i = 0; i < locations.length; i++) {
        addIcon(locations[i],times[i],"images/mc/maptag.png");
    }
}

function addIcon(location,title,icon)
{
    var myLatLng = location;
    var marker = new google.maps.Marker({
        position: myLatLng,
        map: map,
        icon: icon,
        title: title
    });
    markersArray.push(marker);
}

function deleteOverlays() {
    if (markersArray) {
        for (i in markersArray) {
            markersArray[i].setMap(null);
        }
        markersArray.length = 0;
    }
}