var app = angular.module('myApp', []);

app.controller("NameController", function ($scope, $http) {

    $scope.connect = function () {

        var data = {
            name: $scope.name
        };

        var config = {
            headers: {
                'Content-Type': 'application/json;charset=utf-8;'
            }
        };
        $http.post('/api/service/connect', data, config)
            .success(function (data, status, headers, config) {
                $scope.PostDataResponse = data;
                if (status == 200) {
                    $("#nameInputDiv").animate({
                        height: 'toggle'
                    });

                }
            })
            .error(function (data, status, header, config) {
                window.alert("Can not to connect, check your connection or try another username");
            })


    };
});

