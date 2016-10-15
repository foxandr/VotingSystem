/**
 * Created by fox on 29.09.16.
 */

var form;

function makeEditableUser() {
    form = $('#detailsUserForm');

    form.submit(function () {
        saveUser();
        return false;
    });

    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        failNoty(event, jqXHR, options, jsExc);
    });

    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
}

function addUser(key) {
    form.find(":input").val("");
    $('#modalTitle').html(i18n[key]);
    $('#editRow').modal();
}

function updateUserRow(id, key) {
    $('#modalTitle').html(i18n[key]);
    $.get(ajaxUrl + id, function (data) {
        $.each(data, function (key, value) {
            form.find("input[name='" + key + "']").val(
                key === "dateTime" ? value.replace('T', ' ').substr(0, 16) : value
            );
        });
        $('#editRow').modal();
    });
}

function deleteUserRow(id) {
    $.ajax({
        url: ajaxUrl + '/delete',
        type: 'POST',
        data: 'id=' + id,
        success: function () {
            updateUserTable();
            successNoty('Deleted');
        }
    });
}

function recoverUserRow(id) {
    $.ajax({
        url: ajaxUrl + '/recover',
        type: 'POST',
        data: 'id=' + id,
        success: function () {
            updateUserTable();
            successNoty('Recovered');
        }
    });
}

function saveUser() {
    $.ajax({
        type: "POST",
        url: ajaxUrl,
        data: form.serialize(),
        success: function () {
            $('#editRow').modal('hide');
            updateUserTable();
            successNoty('Saved');
        }
    });
}

function renderEditUserBtn(type, row, key) {
    if (type == 'display') {
        return '<a class="btn btn-xs btn-primary" onclick="updateUserRow(' + row.id + ',\'' + key + '\');">' + i18n['common.edit'] + '</a>';
    }
}

function renderDeleteUserBtn(data, type, row) {
    if (type == 'display') {
        return '<a class="btn btn-xs btn-danger" onclick="deleteUserRow(' + row.id + ');">' + i18n['common.delete'] + '</a>';
    }
}

function renderRecoverUserBtn(data, type, row) {
    if (type == 'display') {
        return '<a class="btn btn-xs btn-warning" onclick="recoverUserRow(' + row.id + ');">' + i18n['common.recover'] + '</a>';
    }
}