define(['angular'
    , 'application/auth/eventsAuthModule'
    , 'application/auth/controllers/eventsLoginController'
    , 'application/auth/services/eventsAuthService'
], function (angular, module) {
    module.config(function ($stateProvider) {
        $stateProvider
            .state("main.login", {
                url: "/login",
                views: {
                    "@": {
                        templateUrl: "/html/partials/events-login.html",
                        controller: "eventsLoginController"
                    }
                },
                resolve: {
                    validateSession: function ($http, eventsAuthService, $state, Notification) {
                        if (eventsAuthService.userHasToken()) {
                            eventsAuthService.isUserLoggedInWithValidToken().then(function () {
                                $state.go('main.list');
                            }, function () {
                                Notification.error('Your session has expired. Please log in.');
                            });
                        }
                        else {
                            Notification.warning('Please log in');
                        }
                    }
                }

            })
    });

    return module;
});
