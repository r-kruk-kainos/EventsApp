'use strict';

//noinspection JSFileReferences
require.config({
    baseUrl: 'js/',
    paths: {
        'angular': 'lib/angular/angular.min',
        'uiRouter': 'lib/angular-ui-router/release/angular-ui-router.min',
        'ngCookies': 'lib/angular-cookies/angular-cookies',
        'notification': 'lib/angular-ui-notification/dist/angular-ui-notification'
    },
    shim: {
        'angular': {
            exports: 'angular'
        },
        'uiRouter' : ['angular'],
        'ngCookies': {
            exports: 'ngCookies',
            deps: ['angular']
        },
        'notification': ['angular']
    },
    deps: ['application/bootstrap']
});