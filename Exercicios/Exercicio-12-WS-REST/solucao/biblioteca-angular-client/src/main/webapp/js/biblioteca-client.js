var biblioteca = angular.module("biblioteca", []);

biblioteca.controller("autores", function($scope, $http) {
    $http.get('http://localhost:8080/biblioteca-ws-rest/restapi/autor').
    then(function(result) {
        $scope.autores = result.data;
    });
});

// Veja https://docs.angularjs.org/api/ngResource/service/$resource 
// para uma implementação mais eficiente usando $resource