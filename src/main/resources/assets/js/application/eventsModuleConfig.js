define(['angular'
    , 'application/eventsModule'
    , 'application/controllers/MainController'
    , 'application/controllers/eventsNavbarController'
], function (angular, module) {
    module.config(function ($stateProvider, $urlRouterProvider) {
        $stateProvider
            .state("main", {
                abstract: true,
                views: {
                    "navbar@": {
                        templateUrl: "/html/partials/events-navbar.html",
                        controller: "eventsNavbarController"
                    }
                }
            })
            .state("main.list", {
                url: "/list",
                views: {
                    "@": {
                        templateUrl: "/html/partials/events-list.html",
                        controller: "MainController"
                    }
                }
            })

        $urlRouterProvider.otherwise("/login");
    });
    return module;
});