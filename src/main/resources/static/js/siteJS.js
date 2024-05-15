$('#assessmentStartYear').on('change', function() {
    let pathname = window.location.pathname;
    let url = pathname.split("/");
    let origin = window.location.origin;
    if (url.length == 2) {
        window.location.href = origin + "/" + url[1] + "/start/" + this.value
    } else if (url.length == 3) {
        window.location.href = origin + "/" + url[1] + "/" + url[2] + "/" + this.value
    } else if (url.length == 4) {
        window.location.href = origin + "/" + url[1] + "/" + url[2] + "/" + this.value
    }
});