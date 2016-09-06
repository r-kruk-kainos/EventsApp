define(['angular', 'application/EventsModule'], function(angular, EventsModule) {
    EventsModule.controller("MainController", function($scope) {
        $scope.text = "hello from main";

    });
});
