app.factory('LoginService', function ($resource) {
    return $resource(':action', {},
            {
                authenticate: {
                    method: 'POST',
                    url: '/login',
                    headers: {'Content-Type': 'application/x-www-form-urlencoded'}
                }
            }
    );
});