define([
    'angular',
    'application/EventsModule',
    'application/EventsModuleConfig'
], function (angular) {
    'use strict';

    angular.element().ready(function () {
        angular.bootstrap(document, ['EventsModule']);
    });
});