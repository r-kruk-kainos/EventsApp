define([
    'angular',
    'application/TestModule',
    'application/TestModuleConfig'
], function (angular) {
    'use strict';

    angular.element().ready(function () {
        angular.bootstrap(document, ['TestModule']);
    });
});