define([
    'angular',
    'application/eventsModule',
    'application/eventsModuleConfig'
], function (angular) {
    'use strict';

    angular.element().ready(function () {
        angular.bootstrap(document, ['eventsModule']);
    });
});