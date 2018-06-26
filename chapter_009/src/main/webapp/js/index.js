
window.onload = updatePage;

function updatePage() {
    $(
        $.ajax({
            type: 'GET',
            url: './index',
            complete: function (data) {
                var tasks = JSON.parse(data.responseText);
                for (var i = 0; i < tasks.length; i++) {
                    $("#list").append(getTasks(tasks[i]));
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
    var img = document.createElement("img");
    img.setAttribute("width", 160);
    img.setAttribute("height", 160);
    img.setAttribute("src", task.photo);
    var photo = document.createElement("td");
    photo.appendChild(img);
    tr.append(photo);
    var mark = document.createElement("td");
    mark.innerHTML = task.mark;
    tr.append(mark);
    var model = document.createElement("td");
    model.innerHTML = task.model;
    tr.append(model);
    var body_type = document.createElement("td");
    body_type.innerHTML = task.body_type;
    tr.append(body_type);
    var engine = document.createElement("td");
    engine.innerHTML = task.engine.name;
    tr.append(engine);
    var type_engine = document.createElement("td");
    type_engine.innerHTML = task.engine.type_engine;
    tr.append(type_engine);

    var engine_cond = document.createElement("td");
    engine_cond.innerHTML = task.engine.condition;
    tr.append(engine_cond);
    var car_cond = document.createElement("td");
    car_cond.innerHTML = task.condition.condition
    tr.append(car_cond);
    var year_cond = document.createElement("td");
    year_cond.innerHTML = task.condition.year;
    tr.append(year_cond);
    var mileage_cond = document.createElement("td");
    mileage_cond.innerHTML = task.condition.mileage;
    tr.append(mileage_cond);
    var price = document.createElement("td");
    price.innerHTML = task.price;
    tr.append(price);
    var sale = document.createElement("td");
    sale.innerHTML = task.sale;
    tr.append(sale);
    console.log(tr);
    return tr;

}

function validate() {
    var result = true;
    var prodsel = document.getElementById("producer");
    var producer = prodsel.options[prodsel.selectedIndex].value;
    var bodysel = document.getElementById("body_type");
    var body_type = bodysel.options[bodysel.selectedIndex].value;
    var enginesel = document.getElementById("engine");
    var engine = enginesel.options[enginesel.selectedIndex].value;
    if (producer==null && body_type==null && engine==null) {
        result = false;
    }
    if (!result) {
        alert("Please correct input date.");
    }
    return result;
}
