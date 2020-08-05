marketInfoApp.controller('marketInfoController', [
    '$scope',
    function ($scope) {
        $scope.model = {
            marketi: [
                {
                    naziv: "Bingo",
                    url: "https://depo.ba/media/pictures/2015/10/13/thumbs/561d0c84-9588-4667-9ddc-43e90" +
                        "a0a0a6d-bingo-lukavica-preview.jpg",
                    lokacija: "Sarajevo",
                    adresa: "Džemala Bjedića 23"
                },
                {
                    naziv: "Amko",
                    url: "http://depo.ba/media/pictures/2016/08/09/thumbs/57a9e383-791c-4166-bf87-6c840a0a0a66-amko-komerc-4-preview.JPG",
                    lokacija: "Sarajevo",
                    adresa: "Zmaja od Bosne 16"
                },
                {
                    naziv: "Hoše Komerc",
                    url: "http://depo.ba/media/pictures/2016/08/09/thumbs/57a9e383-791c-4166-bf87-6c840a0a0a66-amko-komerc-4-preview.JPG",
                    lokacija: "Sarajevo",
                    adresa: "Ložionička 16"
                }
            ]

        };
    }
]);