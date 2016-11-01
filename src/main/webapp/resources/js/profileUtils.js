/**
 * Created by fox on 29.09.16.
 */
var ajaxUrl = "ajax/profile/";
var form = $("#detailsUserForm");

$(function () {
    $.get(ajaxUrl + "get", function (data) {
        $.each(data, function (key, value) {
            form.find("input[name='" + key + "']").val(value);
        });
    });
})

function checkPass() {
    var password = $("#password").val();
    var newpass = $("#newpass").val();
    var confpass = $("#confirmpass").val();
    if (password === null || password === "") {
        alert(i18n["common.enpass"]);
        return false;
    }
    if (newpass !== confpass) {
        alert(i18n["common.confirm"]);
        return false;
    }
    return true;
}

function sendData(reg) {
    var link = "";
    if (reg) {
        link = "register";
    } else {
        link = "profile";
    }
    if (checkPass()) {
        if (!reg) {
            $("#confirmpass").prop("disabled", true);
            if ($("#newpass").val() === null || $("#newpass").val() === "") $("#newpass").prop("disabled", true);
        }
        form.submit(function () {
            $.ajax({
                type: "POST",
                url: link,
                data: form.serialize(),
                success: successNoty("Form was sent")
            });
        });
        form.submit();
    }
    return false;
}
