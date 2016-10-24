var ajaxUrl = 'ajax/votes/';
var datatableApi;

$(function () {
    datatableApi = $('#datatable').DataTable({
        "ajax": {
            "url": ajaxUrl + 'getVoteResults',
            "type": "POST",
            "dataSrc": ""
        },
        "bSort": false,
        "paging": false,
        "info": false,
        "filter": false,
        "initComplete": makeVoteEditable
    });
});