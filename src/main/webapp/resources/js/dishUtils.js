/**
 * Created by fox on 22.09.16.
 */

$(function () {
    $('#updated').datetimepicker({
        defaultDate: new Date(),
        format: 'Y-m-d'
    });
});

function getByDate() {
    $.ajax({
        url: ajaxUrl + 'getByDate',
        type: 'POST',
        data: {
            rest_id: $('#rest_id').val(),
            updated: $('#updated').val()
        },
        success: function (data) {
            updateTableByData(data);
            successNoty('Success');
        }
    });
}


