define(['angular', 'application/TestModule'], function(angular, TestModule) {
    TestModule.controller("MainController", function($scope) {
        $scope.text = "hello from main";

    });
});
