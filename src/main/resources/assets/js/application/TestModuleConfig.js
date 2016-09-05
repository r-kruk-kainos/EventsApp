define(['angular'
    , 'application/TestModule'
    , 'application/controllers/MainController'
], function(angular, TestModule) {
    TestModule.config(function($stateProvider) {
        $stateProvider
            .state("root", {
                url: "/root",
                views: {
                    "@": {
                        templateUrl: "html/partials/test-main.html",
                        controller: 'MainController'
                    }
                }
            })
    });
    return TestModule;
});