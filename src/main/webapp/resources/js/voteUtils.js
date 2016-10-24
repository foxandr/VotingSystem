function makeVoteEditable() {
    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        failNoty(event, jqXHR, options, jsExc);
    });

    // var token = $("meta[name='_csrf']").attr("content");
    // var header = $("meta[name='_csrf_header']").attr("content");
    // $(document).ajaxSend(function(e, xhr, options) {
    //     xhr.setRequestHeader(header, token);
    // });
}
function makeVote(rest_id) {
    $.ajax({
        type: "POST",
        url: ajaxUrl,
        data: 'rest_id=' + rest_id,
        success: function () {
            changeVoteColor(rest_id);
            successNoty('Saved');
            updateTable();
        }
    });
}

function changeVoteColor(rest_id) {
    $('#voting tr').removeClass("success");
    $('#voting a').removeClass("hidden");
    var name = 'votedRest_' + rest_id;
    var linkname = 'votedLink_' + rest_id;
    $('#'+ name).addClass("success");
    $('#'+ linkname).addClass("hidden");
}

function updateTable() {
    $.ajax({
        url: ajaxUrl + 'getVoteResults',
        type: 'POST',
        success: function (data) {
            updateTableByData(data);
        }
    });
}