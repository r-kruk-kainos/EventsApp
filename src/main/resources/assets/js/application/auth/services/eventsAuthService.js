define(['angular', 'application/auth/eventsAuthModule'], function (angular, eventsAuthModule) {
   eventsAuthModule.service('eventsAuthService', ['$http', '$cookieStore', function ($http, $cookieStore) {

       var service = {
            getTokenForUser: getTokenForUser,
            userHasToken: userHasToken,
            isUserLoggedInWithValidToken: isUserLoggedInWithValidToken,
            logout:logout,
            login:login
       };

        return service;

       ////////////////////////////

         function getTokenForUser(name, password) {
             return $http.post('api/oauth2/token', {email: name, password: password});
        };

         function isUserLoggedInWithValidToken() {
                $http.defaults.headers.common['Authorization'] = 'bearer ' + $cookieStore.get('accessToken');
                return $http.get('api/ping/auth');
        };

        function userHasToken() {
            return ($cookieStore.get('accessToken')) ? true : false;
        };

        function logout() {
            $http.defaults.headers.common['Authorization'] = '';
            $cookieStore.remove('accessToken');
        }

        function login(accessToken) {
            $http.defaults.headers.common['Authorization'] = 'bearer ' + accessToken;
            $cookieStore.put('accessToken', accessToken);
        }

    }]);
});
