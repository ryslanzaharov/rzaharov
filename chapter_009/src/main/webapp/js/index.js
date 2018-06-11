
window.onload = updatePage;

function updatePage() {
    var done = $('#done').prop('checked');
    console.log(done);
    $("#tasks").empty();
    $(
        $.ajax({
            type: 'GET',
            url: './index?showdone=' + done,
            complete: function (data) {
                var tasks = JSON.parse(data.responseText);
                for (var i = 0; i < tasks.length; i++) {
                    $("#tasks").append(getTasks(tasks[i]));
                }
                setHandlers();
            }
        })
    )
}

function getTasks(task) {
    var tr = document.createElement("tr");
    var id = document.createElement("td");
    id.innerHTML = task.id;
    tr.append(id);
    var desc = document.createElement("td");
    desc.innerHTML = task.desc;
    tr.append(desc);
    var date = new Date(task.creationTime);
    var time = document.createElement("td");
    time.innerHTML = date.getDate() + "." + (date.getMonth() + 1) + "." + date.getFullYear();
    tr.append(time);
    var done = document.createElement("td");
    var doneInput = document.createElement("input");
    doneInput.setAttribute("id", task.id);
    doneInput.setAttribute("type", "checkbox");
    doneInput.setAttribute("class", "marker");
    if (task.done) {
        doneInput.checked = true;
    }
    done.appendChild(doneInput);
    tr.appendChild(done);
    console.log(tr);
    return tr;

}

function setHandlers() {
    var checkers = document.getElementsByClassName("marker");
    for (var i = 0; i <checkers.length; i++) {
        checkers[i].onclick = function () {
            updateItem($(this).attr("id"), $(this).prop("checked"), $(this).attr("desc"));
        }
    }
}

function updateItem(id, done) {
    $(
        $.ajax({
            url:'./edit?id=' + id + '&done=' + done,
            type: 'POST',
            complete: function (data) {
                updatePage();
            }
        })
    );

}

function formSubmit() {
    var data = {'desc': $("#desc").val()};
    console.log(data);
    $(
        $.ajax({
            type: 'POST',
            url: './create',
            data: data,
            success: function (data, textStatus) {
                $.each(data, function (i, val) {
                    console.log(val);
                });
                updatePage();
            }
        }));
}
