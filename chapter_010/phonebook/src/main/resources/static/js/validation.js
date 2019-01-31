

function updatePage() {
    $("#list").empty();
    var rate_value = "null";
    if (document.getElementById('fn').checked) {
        rate_value = document.getElementById('fn').value;
    }
    else if (document.getElementById('ln').checked) {
        rate_value = document.getElementById('ln').value;
    }
    else if (document.getElementById('tn').checked) {
        rate_value = document.getElementById('tn').value;
    }
    alert(rate_value);
    console.log(data);
    $.ajax({
        type: 'POST',
        url: './index',
        data :  JSON.stringify({ "rate_value":rate_value}),
        success:function (data) {
            location.reload();
        },
        error: function( xhr, textStatus ) {
            alert( [ xhr.status, textStatus ] );
        }
    })
}

function openAcc() {
    var login = $("#login").val();
    var password = $("#password").val();
    var firstname = $("#firstname").val();
    var lastname = $("#lastname").val();
    var patronymic = $("#patronymic").val();
    var enabled = true;
    var result = null;
    var valid_log = /^[A-Z]+$/i;
    if(!login.match(valid_log) ) {
        result =result +  ", use english characters"
    }
    if (login==null || login.length < 3) {
        result = ",login at least 3 characters"
    }
    if (password==null || password.length < 5) {
        result =result + ",login at least 5 characters"
    }
    if (firstname==null || firstname.length < 5) {
        result = result + ",login at least 5 characters"
    }
    if (lastname==null || lastname.length < 5) {
        result = result + ",login at least 5 characters"
    }
    if (patronymic==null || patronymic.length < 5) {
        result =result +  ",login at least 5 characters"
    }
    if (result==null) {
    $.ajax({
        type: "POST",
        url: "/registration",
        contentType: "application/json",
        data:  JSON.stringify({"login": login, "password": password, "firstname": firstname, "lastname": lastname
            , "patronymic": patronymic, "enabled":enabled}),
        success: function(data) {
            alert("Your account is opened." );
            location.reload();
        },
        error: function( xhr, textStatus ) {
            alert( [ xhr.status, textStatus ] );
        }
    }) }
    else {
        alert("Your account is not opened " + result )
    }

}

function addContact() {
    var firstname = $("#firstname").val();
    var lastname = $("#lastname").val();
    var patronymic = $("#patronymic").val();
    var telephone_number = $("#telephone_number").val();
    var home_phone_number = $("#home_phone_number").val();
    var address = $("#address").val();
    var e_mail = $("#e_mail").val();
    var result = null;
    var phoneno = /^(\+380)([0-9]{9})$/;
    if (firstname==null || firstname.length < 4) {
        result = result + ",login at least 5 characters"
    }
    if (lastname==null || lastname.length < 4) {
        result = result + ",login at least 5 characters"
    }
    if (patronymic==null || patronymic.length < 4) {
        result =result +  ",login at least 5 characters"
    }
    if (telephone_number==null || telephone_number.length<13) {
        result =result +  ", change telephone numbers"
    }
    if(!telephone_number.match(phoneno) ) {
        result =result +  ", change telephone numbers"
    }
    if (result==null) {
        $.ajax({
            type: "POST",
            url: "addcontacts",
            contentType: "application/json",
            data: JSON.stringify({
                "firstname": firstname,
                "lastname": lastname,
                "patronymic": patronymic,
                "telephone_number": telephone_number,
                "home_phone_number": home_phone_number,
                "address": address,
                "e_mail": e_mail
            }),
            success: function (data) {
                alert("Your account is opened. ");
                location.reload();
            },
            error: function (xhr, textStatus) {
                alert([xhr.status, textStatus]);
            }
        })
    } else {
            alert("Your account is not opened" + result )
        }
}

function editContact() {
    var firstname = $("#firstname").val();
    var lastname = $("#lastname").val();
    var patronymic = $("#patronymic").val();
    var telephone_number = $("#telephone_number").val();
    var home_phone_number = $("#home_phone_number").val();
    var address = $("#address").val();
    var e_mail = $("#e_mail").val();
    alert(firstname, lastname, patronymic);
    var result = null;
    var phoneno = /^(\+380)([0-9]{9})$/;
    if (firstname==null || firstname.length < 4) {
        result = result + ",login at least 5 characters"
    }
    if (lastname==null || lastname.length < 4) {
        result = result + ",login at least 5 characters"
    }
    if (patronymic==null || patronymic.length < 4) {
        result =result +  ",login at least 5 characters"
    }
    if (telephone_number==null || telephone_number.length<13) {
        result =result +  ", change telephone numbers"
    }
    if(!telephone_number.match(phoneno) ) {
        result =result +  ", change telephone numbers"
    }
    if (result==null) {
        $.ajax({
            type: "POST",
            url: "editcontacts",
            contentType: "application/json",
            data: JSON.stringify({
                "firstname": firstname,
                "lastname": lastname,
                "patronymic": patronymic,
                "telephone_number": telephone_number,
                "home_phone_number": home_phone_number,
                "address": address,
                "e_mail": e_mail
            }),
            success: function (data) {
                alert("Your account is opened. ");
                location.reload();
            },
            error: function (xhr, textStatus) {
                alert([xhr.status, textStatus]);
            }
        })
    } else {
        alert("Your account is not opened" + result )
    }
}




