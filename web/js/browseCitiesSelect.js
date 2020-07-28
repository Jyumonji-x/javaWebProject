// 二级联动实现国家对应的城市显示
$(document).ready(function () {
    $.get("citySelect.upload",{iso:$("#countries option:selected").val()}, function (data) {
        console.log($("#countries option:selected").val());
        console.log(data[0]);
        let lists = document.getElementById("cities");

        lists.innerHTML = "";

        //<option value="0">Filter by Country</option>
        let line = document.createElement("option");
        line.value = 'null';
        line.innerHTML = "Filter by City";
        lists.append(line);
        for (let i = 0; i < data.length; i++) {
            line = document.createElement("option");
            line.value = data[i].geoNameID;
            line.innerHTML = data[i].asciiName;
            lists.append(line);
        }
    });
});
$(document).ready(function(){
    $("#countries").change(function() {
        $.get("citySelect.upload",{iso:$("#countries option:selected").val()}, function (data) {
            console.log($("#countries option:selected").val());
            console.log(data[0]);
            let lists = document.getElementById("cities");

            lists.innerHTML = "";

            //<option value="0">Filter by Country</option>
            let line = document.createElement("option");
            line.value = 'null';
            line.innerHTML = "Filter by City";
            lists.append(line);
            for (let i = 0; i < data.length; i++) {
                line = document.createElement("option");
                line.value = data[i].geoNameID;
                line.innerHTML = data[i].asciiName;
                lists.append(line);
            }
        })
    })
});