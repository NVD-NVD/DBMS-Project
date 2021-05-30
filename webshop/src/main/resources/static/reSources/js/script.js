$(document).ready(function () {
    $(".owl-carousel").owlCarousel({
        items: 1,
        loop: true,
        mouseDrag: true,
        autoplay: true, // cho item chạy
        autoplayTimeout: 5000, // chỉnh tốc chạy 1000 = 1s
    });
});