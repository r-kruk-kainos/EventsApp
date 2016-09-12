define(['angular', 'application/eventsModule', 'application/auth/services/eventsAuthService'], function(angular, eventsModule) {
    eventsModule.controller("MainController", function($scope, $http, $state, eventsAuthService, Notification) {

        $scope.check = function () {
            eventsAuthService.isUserLoggedInWithValidToken().then(function(response) {
                console.log($http.defaults.headers.common['Authorization']);
            } , function(failure) {
                console.log($http.defaults.headers.common['Authorization']);
            });
        }

    });
});