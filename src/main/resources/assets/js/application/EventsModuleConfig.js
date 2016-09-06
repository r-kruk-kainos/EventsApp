define(['angular'
    , 'application/EventsModule'
    , 'application/controllers/MainController'
], function(angular, EventsModule) {
    EventsModule.config(function($stateProvider) {
        $stateProvider
            .state("list", {
                url: "/list",
                views: {
                    "@": {
                        templateUrl: "html/partials/events-list.html",
                        controller: 'ListController'
                    }
                }
            })
            .state("addEvent", {
                url: "/addEvent",
                views: {
                    "@": {
                        templateUrl: "html/partials/events-add.html",
                        controller: 'ListController'
                    }
                }
            })
    });
    return EventsModule;
});