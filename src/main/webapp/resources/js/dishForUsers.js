/**
 * Created by fox on 22.09.16.
 */

var ajaxUrl = "ajax/dishes/";
var datatableApi;

$(function () {
    datatableApi = $("#datatable").DataTable({
        "ajax": {
            "url": ajaxUrl + "getByDate",
            "type": "POST",
            "data": {
                "rest_id": $("#rest_id").val(),
                "updated": $("#updated").val()
            },
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
                "data": "price"
            },
            {
                "data": "updated",
                "render": function (date, type, row) {
                    if (type === "display") {
                        return "<span>" + date.substring(0, 10) + "</span>";
                    }
                    return date;
                }
            }
        ],
        "order": [
            [
                0,
                "asc"
            ]
        ]
    });
});

$(function () {
    $("#updated").datetimepicker({
        defaultDate: new Date(),
        format: "Y-m-d",
        pickTime: false
    });
    var d = new Date();
    $('#updated').val(d.getFullYear() + "-" + ((d.getMonth() + 1 > 9) ? (d.getMonth() + 1) : "0" + (d.getMonth() + 1)) + "-" + ((d.getDate() > 9) ? d.getDate() : "0" + d.getDate()));
});

function getByDate() {
    $.ajax({
        url: ajaxUrl + "getByDate",
        type: "POST",
        data: {
            rest_id: $('#rest_id').val(),
            updated: $('#updated').val()
        },
        success: function (data) {
            updateTableByData(data);
            successNoty("Success");
        }
    });
}


