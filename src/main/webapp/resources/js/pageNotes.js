/**
 * Created by fox on 29.09.16.
 */

function updateTableByData(data) {
    datatableApi.clear().rows.add(data).draw();
}

var failedNote;

function closeNoty() {
    if (failedNote) {
        failedNote.close();
        failedNote = undefined;
    }
}

function successNoty(text) {
    closeNoty();
    noty({
        text: text,
        type: 'success',
        layout: 'bottomRight',
        timeout: true
    });
}

function failNoty(event, jqXHR, options, jsExc) {
    closeNoty();
    var errorInfo = $.parseJSON(jqXHR.responseText);
    failedNote = noty({
        text: 'Failed: ' + jqXHR.statusText + '<br>' + errorInfo.cause + '<br>' + errorInfo.details.join('<br>'),
        type: 'error',
        layout: 'bottomRight'
    });
}

function show(lang) {
    window.location.href = window.location.href.split('?')[0] + '?lang=' + lang;
}
