define(['angular'
, 'uiRouter'
, 'application/auth/eventsAuthModule'
, 'application/auth/eventsAuthModuleConfig'
, 'notification'
], function (angular) {
    return angular.module("eventsModule", ['ui.router', 'eventsAuthModule', 'ui-notification'])
});
