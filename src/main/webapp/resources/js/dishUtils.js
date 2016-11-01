/**
 * Created by fox on 22.09.16.
 */
$(function () {
    $("#updated").datetimepicker({
        defaultDate: new Date(),
        format: "Y-m-d",
        pickTime: false
    });
    var d = new Date();
    $('#updated').val(d.getFullYear() + "-" + ((d.getMonth() + 1 > 9) ? (d.getMonth() + 1) : "0" + (d.getMonth() + 1)) + "-" + ((d.getDate() > 9) ? d.getDate() : "0" + d.getDate()));
});

var form;

function addDish(key) {
    form.find(":input").val("");
    form.find("input[name='rest_id_upd']").val($("#rest_id").val());
    $("#modalTitle").html(i18n[key]);
    $("#editRow").modal();
}

function updateDishRow(id, rest_id, key) {
    $("#modalTitle").html(i18n[key]);
    $.get(ajaxUrl + "get?id=" + id + "&rest_id=" + rest_id, function (data) {
        $.each(data, function (key, value) {
            form.find("input[name='" + key + "']").val(
                key === "dateTime" ? value.replace("T", " ").substr(0, 16) : value
            );
            form.find("input[name='rest_id_upd']").val($("#rest_id").val());
        });
        $("#editRow").modal();
    });
}

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

function deleteDishRow(id, rest_id) {
    $.ajax({
        url: ajaxUrl + "delete",
        type: "POST",
        data: {
            id: id,
            rest_id: rest_id
        },
        success: function () {
            getByDate();
            successNoty("Deleted");
        }
    });
}

function saveDish() {
    $.ajax({
        type: "POST",
        url: ajaxUrl,
        data: form.serialize(),
        success: function (data) {
            $('#editRow').modal("hide");
            getByDate();
            successNoty("Saved");
        }
    });
}

function makeDishEditable() {
    form = $("#detailsDishForm");

    form.submit(function () {
        saveDish();
        return false;
    });

    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        failNoty(event, jqXHR, options, jsExc);
    });
}

function renderDeleteDishBtn(data, type, row) {
    var rest_id = $("#rest_id").val();
    if (type === "display") {
        return "<a class=\"btn btn-xs btn-danger\" onclick=\"deleteDishRow(" + row.id + ", " + rest_id + ");\">" + i18n["common.delete"] + "</a>";
    }
}

function renderEditDishBtn(type, row, key) {
    var rest_id = $("#rest_id").val();
    if (type === "display") {
        return "<a class=\"btn btn-xs btn-primary\" onclick=\"updateDishRow(" + row.id + ", " + rest_id + ",'" + key + "');\">" + i18n["common.edit"] + "</a>";
    }
}


