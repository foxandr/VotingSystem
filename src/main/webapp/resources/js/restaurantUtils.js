var form;

 function makeEditableRestaurant() {
     form = $('#detailsRestForm');

     form.submit(function () {
         saveRestaurant();
         return false;
     });

     $(document).ajaxError(function (event, jqXHR, options, jsExc) {
          failNoty(event, jqXHR, options, jsExc);
     });
 }

function addRestaurant(key) {
    form.find(":input").val("");
    $('#modalTitle').html(i18n[key]);
    $('#editRow').modal();
}

function updateRestaurantRow(id, key) {
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

function deleteRestaurantRow(id) {
    $.ajax({
        url: ajaxUrl + id,
        type: 'DELETE',
        success: function () {
            updateRestaurantTable();
            successNoty('Deleted');
        }
    });
}

function saveRestaurant() {
    $.ajax({
        type: "POST",
        url: ajaxUrl,
        data: form.serialize(),
        success: function () {
            $('#editRow').modal('hide');
            updateRestaurantTable();
            successNoty('Saved');
        }
    });
}

function renderEditRestaurantBtn(type, row, key) {
    if (type == 'display') {
        return '<a class="btn btn-xs btn-primary" onclick="updateRestaurantRow(' + row.id + ',\'' + key + '\');">' + i18n['common.edit'] + '</a>';
    }
}

function renderDeleteRestaurantBtn(data, type, row) {
    if (type == 'display') {
        return '<a class="btn btn-xs btn-danger" onclick="deleteRestaurantRow(' + row.id + ');">' + i18n['common.delete'] + '</a>';
    }
}
