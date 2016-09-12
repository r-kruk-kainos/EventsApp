define(['angular', 'application/auth/eventsAuthModule', 'application/auth/services/eventsAuthService'], function(angular, eventsAuthModule) {
    eventsAuthModule.controller("eventsLoginController", function($scope, $http, $state, eventsAuthService, Notification, $cookieStore) {

        $scope.login = function () {
            eventsAuthService.getTokenForUser($scope.username, $scope.password).then(function(response) {
                eventsAuthService.login(response.data);
                $state.go("main.list");
            } , function(failure) {
                $http.defaults.headers.common['Authorization'] = '';
                $cookieStore.remove('accessToken');
                if(failure.status === 404) Notification.error("Invalid credentials.");
            });
        }

    });
});
