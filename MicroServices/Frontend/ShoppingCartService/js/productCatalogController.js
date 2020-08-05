productCatalogApp.controller('productCatalogController', [
    '$scope',
    function ($scope) {
        $scope.model = {
            market: {
                naziv: "Trenutno selektirani market!"
            },
            proizvodi: [
                {
                    naziv: "CD Santana",
                    url: "https://images-na.ssl-images-amazon.com/images/G/01/digital/music/merch/2019/W" +
                            "eeklyBuild/060719/060719_Santaas_DM_US_JL_1440x720._CB462130114_.jpg",
                    cijena: "23,99 KM",
                    stanje: "25",
                    opis: "Album Santana."
                }, {
                    naziv: "CD Pavarotti",
                    url: "https://images-na.ssl-images-amazon.com/images/G/01/digital/music/merch/2019/W" +
                            "eeklyBuild/060719/060719_Pavarotti_DM_US_JL_1440x720._CB462127215_.jpg",
                    cijena: "23,99 KM",
                    stanje: "25",
                    opis: "Album Pavarotti."
                }, {
                    naziv: "CD Avicii",
                    url: "https://images-na.ssl-images-amazon.com/images/G/01/digital/music/merch/2019/W" +
                            "eeklyBuild/060719/060719_Avicii_DM_US_JL_1440x720._CB462127331_.jpg",
                    cijena: "23,99 KM",
                    stanje: "25",
                    opis: "Album Avicii."
                }, {
                    naziv: "CD Tyga",
                    url: "https://images-na.ssl-images-amazon.com/images/G/01/digital/music/merch/2019/W" +
                            "eeklyBuild/060719/060719_Tyga_DM_US_JL_1440x720._CB462128873_.jpg",
                    cijena: "23,99 KM",
                    stanje: "25",
                    opis: "Album Tyga."
                }
            ]
        };

        $scope.showShoppingCartNotification = showShoppingCartNotification;
    
        function showShoppingCartNotification() {
            var notification = document.getElementById("shoppingCartNotification");

            notification.className = "show";

            setTimeout(function () {
                notification.className = notification
                    .className
                    .replace("show", "");
            }, 2000);
        }
    }
]);