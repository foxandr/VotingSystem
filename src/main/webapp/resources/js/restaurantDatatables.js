var ajaxUrl = 'ajax/admin/restaurants/';
var datatableApi;

function updateRestaurantTable() {
    $.get(ajaxUrl, updateTableByData);
}

$(function () {
    datatableApi = $('#datatable').DataTable({
        "ajax": {
            "url": ajaxUrl,
            "dataSrc": ""
        },
        "paging": false,
        "info": false,
        "filter": false,
        "columns": [
            {
                "data": "name"
            },
            {
                "data": "address"
            },
            {
                "data": "registred",
                "render": function (date, type, row) {
                    if (type == 'display') {
                        return '<span>' + date.substring(0, 10) + '</span>';
                    }
                    return date;
                }
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": function (date, type, row) {
                    return renderEditRestaurantBtn(type, row, 'restaurants.edit');
                }
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderDeleteRestaurantBtn
            }
        ],
        "order": [
            [
                0,
                "asc"
            ]
        ],
        "initComplete": makeEditableRestaurant
    });
});