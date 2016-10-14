var ajaxUrl = 'ajax/votes/';

function makeVote(rest_id) {
    $.ajax({
        type: "POST",
        url: ajaxUrl,
        data: 'rest_id=' + rest_id,
        success: function () {
            changeVoteColor(rest_id);
            successNoty('Saved');
        }
    });
}

function changeVoteColor(rest_id) {
    $('#voting tr').removeClass("bg-success");
    var name = 'votedRest_' + rest_id;
    $('#'+ name).addClass("bg-success");
}