function openAcc() {
    var username = $("#username").val();
    $.ajax({
        type: "POST",
        url: "/registerAccount",
        contentType: "application/json",
        data: JSON.stringify({"username": username}),
        success: function (data) {
            if (data.trim() == '') {
                alert("Your account is not opened, change your login");
            } else {
                alert("Your account is opened,  password: " + data);
            }
            location.reload();
        },
        error: function (xhr, textStatus) {
            alert([xhr.status, textStatus]);
        }
    })

}

function addUrl() {
    var url = $("#url").val();
    $.ajax({
        type: "POST",
        url: "/register",
        contentType: "application/json",
        data: JSON.stringify({"url": url}),
        success: function (data) {
            if (data.trim() == '') {
                alert("Your url is not opened, change your url");
            } else {
                alert("Your url is added,  short url: " + data);
            }

            location.reload();
        },
        error: function (xhr, textStatus) {
            alert([xhr.status, textStatus]);
        }
    })

}



