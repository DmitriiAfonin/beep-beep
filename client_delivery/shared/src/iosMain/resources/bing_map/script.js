var map,directionsManager;

function GetMap() {
map = new Microsoft.Maps.Map('#myMap', {

    credentials: 'Access_token',
    mapTypeId: Microsoft.Maps.MapTypeId.grayscale,
    minZoom: 2,
    maxZoom: 7
});
}

function getDirections(startLat, startLng, endLat, endLng) {
    // Load the Microsoft Maps Directions module
    Microsoft.Maps.loadModule('Microsoft.Maps.Directions', function () {

        // Initialize the DirectionsManager with the existing map
        const directionsManager = new Microsoft.Maps.Directions.DirectionsManager(map);

        // Configure the request options for route calculation
        const requestOptions = {
            routeMode: Microsoft.Maps.Directions.RouteMode.driving,
            routeDraggable: false,
            autoUpdateMapView: false
        };
        directionsManager.setRequestOptions(requestOptions);

        // Configure the render options for the route
        const renderOptions = {
            drivingPolylineOptions: {
                strokeColor: 'red',
                strokeThickness: 4
            },
            autoUpdateMapView: false
        };
        directionsManager.setRenderOptions(renderOptions);

        // Create waypoints using the provided latitude and longitude
        const startWaypoint = new Microsoft.Maps.Directions.Waypoint({
            location: new Microsoft.Maps.Location(startLat, startLng)
        });
        const endWaypoint = new Microsoft.Maps.Directions.Waypoint({
            location: new Microsoft.Maps.Location(endLat, endLng)

        });

        // Add the waypoints to the directions manager
        directionsManager.addWaypoint(startWaypoint);
        directionsManager.addWaypoint(endWaypoint);

        // Calculate the directions
        directionsManager.calculateDirections();
    });
}

function getRouteTime() {
    directionsManager.time
}

function clearDirections() {
directionsManager.clearAll();
directionsManager.clearDisplay();
}

function clearMap(){
map.entities.clear();
}

