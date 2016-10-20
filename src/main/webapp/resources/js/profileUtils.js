/**
 * Created by fox on 29.09.16.
 */
var ajaxUrl = 'ajax/profile/';
var form = $('#detailsUserForm');

$(function () {
    $.get(ajaxUrl + 'get', function (data) {
        $.each(data, function (key, value) {
            form.find("input[name='" + key + "']").val(value);
        });
    });
})

function sendData(reg) {
    var link = 'profile';
    if (reg) link = 'register';
    form.submit(function () {
        $.ajax({
            type: "POST",
            url: link,
            data: form.serialize(),
            success: successNoty('Saved')
        });
    });
    return false;
}
