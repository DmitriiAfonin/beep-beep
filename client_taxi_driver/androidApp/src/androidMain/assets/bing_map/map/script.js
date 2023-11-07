var map,directionsManager;
function getMap() {
map = new Microsoft.Maps.Map('#myMap', {
    credentials: 'Access_token',
    mapTypeId: Microsoft.Maps.MapTypeId.road,
    minZoom: 4,
    maxZoom: 18
});
          getPin();
}

function getPin(){
    var pushpin = new Microsoft.Maps.Pushpin(map.getCenter(), null);
    map.entities.push(pushpin);
}

function getDirections(currentLat,currentLng,lat,lng){
clearPin();
if(directionsManager!=null){
clearDirections()
}
Microsoft.Maps.loadModule('Microsoft.Maps.Directions', function () {
    directionsManager = new Microsoft.Maps.Directions.DirectionsManager(map);
    directionsManager.setRequestOptions({
    routeMode: Microsoft.Maps.Directions.RouteMode.driving,
    routeDraggable: false,
    distanceUnit: Microsoft.Maps.Directions.DistanceUnit.km,
    maxRoutes: 1,
    routeOptimization: Microsoft.Maps.Directions.RouteOptimization.timeWithTraffic
    });
    directionsManager.setRenderOptions({
                   drivingPolylineOptions: {
                       strokeColor: 'red',
                       strokeThickness: 8,
                   },
                   autoUpdateMapView:false,
                   lastWaypointPushpinOptions: {
                     title: '',
                     subTitle: '',
                     text: '',
                     color: 'black'
                   },
                    firstWaypointPushpinOptions: {
                       title: '',
                       subTitle: '',
                       text: '',
                        },
               });
    var waypoint1 = new Microsoft.Maps.Directions.Waypoint({ location: new Microsoft.Maps.Location(currentLat,currentLng) });
    var waypoint2 = new Microsoft.Maps.Directions.Waypoint({ location: new Microsoft.Maps.Location(lat,lng) });
    directionsManager.addWaypoint(waypoint1);
    directionsManager.addWaypoint(waypoint2);
    directionsManager.calculateDirections();
});
}

function clearDirections() {
directionsManager.clearAll();
directionsManager.clearDisplay();
}

function createInfiniteLoopFunction(latitude,lng) {
            return function() {
            clearPin()
            if(directionsManager!=null)
            clearDirections()
    var pushpin = new Microsoft.Maps.Pushpin(new Microsoft.Maps.Location(latitude,lng), {
        icon: 'user_pin.svg',
     anchor: new Microsoft.Maps.Point(20, 20)
  });
   Microsoft.Maps.loadModule('Microsoft.Maps.SpatialMath', function () {
    let circle = createCircle(new Microsoft.Maps.Location(latitude, lng),120,'rgba(31, 0, 0, 0.16)')
        map.entities.push(circle);
       map.entities.push(pushpin);})
                    map.setView({ center: new Microsoft.Maps.Location(latitude, lng), minZoom: 16,
            maxZoom: 18});
            };
        }

function createCircle(center, radius, color) {
    var locs = Microsoft.Maps.SpatialMath.getRegularPolygon(center, radius, 80, Microsoft.Maps.SpatialMath.Meters);
    return new Microsoft.Maps.Polygon(locs, { fillColor: color, strokeThickness: 0 });
}

function clearPin(){
map.entities.clear();
}




