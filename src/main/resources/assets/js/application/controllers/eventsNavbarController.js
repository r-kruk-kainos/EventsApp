define(['angular', 'application/eventsModule', 'application/auth/services/eventsAuthService'], function(angular, eventsModule) {
    eventsModule.controller("eventsNavbarController", ['$scope' , '$http', '$cookieStore', '$state', 'eventsAuthService', function($scope, $http, $cookieStore, $state, eventsAuthService) {

        $scope.logout = function () {
            eventsAuthService.logout();
            $scope.isUserLoggedIn = false;
            $state.go("main.login");
        }

        $scope.$watch(eventsAuthService.userHasToken, function(watchedObject) {
            $scope.isUserLoggedIn = watchedObject;
        }) ;

    }]);
});