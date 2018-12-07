
window.onload = updatePage;

function updatePage() {

    $.ajax({
        type: 'POST',
        url: './statistic',
        data : data,
        complete: function (data) {
            var tasks = JSON.parse(data.responseText);
            for (var i = 0; i < tasks.length; i++) {
                $("#list").append(getTasks(tasks[i]));
            }
        }
    })
}

function getTasks(task) {
    var tr = document.createElement("tr");
    var id = document.createElement("td");
    id.innerHTML = task.id;
    tr.append(id);
    var url = document.createElement("td");
    mark.innerHTML = task.url;
    tr.append(url);
    var short_url = document.createElement("td");
    short_url.innerHTML = task.short_url;
    tr.append(short_url);
    return tr;

}


