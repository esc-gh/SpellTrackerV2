"use strict";
  //////////////
 // SELECTORS//
//////////////
// DIVS
let resultsDiv = document.querySelector("#resultsDiv");

// INPUTS
let nameInput = document.querySelector("#nameInput");
let levelInput = document.querySelector("#levelInput");
let schoolInput = document.querySelector("#schoolInput");
let fNameInput = document.querySelector("#fNameInput")
let fLevelInput = document.querySelector("#fLevelInput")
let fSchoolInput = document.querySelector("#fSchoolInput")

// BUTTONS
let createBtn = document.querySelector("#createBtn");
let updateBtn = document.querySelector("#updateBtn");
let deleteBtn = document.querySelector("#deleteBtn");
let getAllBtn = document.querySelector("#getAllBtn");
let fNameBtn = document.querySelector("#fNameBtn");
let fLevelBtn = document.querySelector("#fLevelBtn");
let fSchoolBtn = document.querySelector("#fSchoolBtn");

// FUNCTIONS

// Print Results

function capitaliseFirstLetter(string) {
    return string.charAt(0).toUpperCase() + string.slice(1);
  }
  
let printResults = (result) => {
    let resultRow = document.createElement("tr");
    resultRow.setAttribute("class", "tr");

    let rowId = document.createElement("th");
    rowId.setAttribute("class", "th");
    rowId.textContent = result.id;

    let rowName = document.createElement("td");
    rowName.setAttribute("class", "td");
    rowName.setAttribute("class", "text-capitalize")
    rowName.textContent = capitaliseFirstLetter(result.name);

    let rowLevel = document.createElement("td");
    rowLevel.setAttribute("class", "td");
    rowLevel.textContent = result.level;

    let rowSchool = document.createElement("td");
    rowSchool.setAttribute("class", "td");
    rowSchool.textContent = result.school;

    let delBtn = document.createElement("button");
    delBtn.textContent = "Delete";
    delBtn.type = "button";
    delBtn.setAttribute("class", "btn btn-danger btn-sm");
    delBtn.setAttribute("style", "color: black")
    delBtn.setAttribute("onclick", `del(${result.id})`);

    let updBtn = document.createElement("button");
    updBtn.textContent = "Update";
    updBtn.type = "button";
    updBtn.setAttribute("class", "btn btn-secondary stn-sm")
    updBtn.setAttribute("style", "color: black")
    updBtn.setAttribute("onclick", `update(${result.id})`)

    resultRow.appendChild(rowId);
    resultRow.appendChild(rowName);
    resultRow.appendChild(rowLevel);
    resultRow.appendChild(rowSchool);
    resultRow.appendChild(delBtn);
    resultRow.appendChild(updBtn);
    tableBody.appendChild(resultRow);
   }

// Get ALL
let getAll = () => {
    axios.get("http://localhost:8080/spell/")
    .then(res => {
        tableBody.innerHTML = "";

        let results = res.data;
        for (let result of results) {
            printResults(result);
        }
    }).catch(err => console.log(err));
}
let getAllByName = () => {
    axios.get("http://localhost:8080/spell/name")
    .then(res => {
        tableBody.innerHTML = "";

        let results = res.data;
        for (let result of results) {
            printResults(result);
        }
    }).catch(err => console.log(err));
}
let getAllBySchool = () => {
    axios.get("http://localhost:8080/spell/school")
    .then(res => {
        tableBody.innerHTML = "";

        let results = res.data;
        for (let result of results) {
            printResults(result);
        }
    }).catch(err => console.log(err));
}

let getAllByLevel = () => {
    axios.get("http://localhost:8080/spell/lv")
    .then(res => {
        tableBody.innerHTML = "";

        let results = res.data;
        for (let result of results) {
            printResults(result);
        }
    }).catch(err => console.log(err));    
}

let findByName = () => {
    if(!validateFName()){
        alert("Spell name:\nPlease enter a spell name!");
        return
    }

    let capitalFName = capitaliseFirstLetter(fNameInput.value);

    axios.get(`http://localhost:8080/spell/${capitalFName}`)
    .then(res => {
        tableBody.innerHTML = "";

        printResults(res.data)

    }).catch(err => console.log(err));    
}

let findByLevel = () => {
    if(!validateFLevel()){
        alert("Spell level:\nMust be between 0-9!");
        return
    }

    axios.get(`http://localhost:8080/spell/lv/${fLevelInput.value}`)
    .then(res => {
        tableBody.innerHTML = "";

        let results = res.data;
        for (let result of results) {
            printResults(result);
        }
    }).catch(err => console.log(err));    
}

let findBySchool = () => {
    if(!validateFSchool()){
        alert("School of Magic:\nPlease select an option!");
        return
    }

    axios.get(`http://localhost:8080/spell/school/${fSchoolInput.value}`)
    .then(res => {
        tableBody.innerHTML = "";

        let results = res.data;
        for (let result of results) {
            printResults(result);
        }
    }).catch(err => console.log(err));    
}

// Create
let create = () => {

    if(!validateCreate()){
        alert("Create:\nAll fields need values!");
        return
    }

    if(!validateLevel()){
        alert("Spell level:\nMust be between 0-9!");
        return
    }

    if(!validateSchool()){
        alert("School of Magic:\nPlease select an option!");
        return
    }

    let capitalName = capitaliseFirstLetter(nameInput.value);

    let obj = {
        "name": capitalName,
        "level": levelInput.value,
        "school": schoolInput.value
    }

    axios.post("http://localhost:8080/spell", obj)
    .then(res => {
        nameInput.value = "";
        levelInput.value = "";
        getAll();
    }).catch(err => console.log(err));
}

// Update
let update = (id) => {

    if(!validateUpdate()){
        alert("Update:\nPlease enter the new Level and School of this spell!");
        return
    }

    if(!validateLevel()){
        alert("Spell level:\nMust be between 0-9!");
        return
    }

    if(!validateSchool()){
        alert("School of Magic:\nPlease select an option!");
        return
    }

    let obj = {
        "name": "placeholder",
        "level": levelInput.value,
        "school": schoolInput.value
    }

    axios.put(`http://localhost:8080/spell?id=${id}`, obj)
    .then(res => {
        // console.log(res.data);
        getAll();
    }).catch(err => console.log(err));
}

// Delete
let del = (id) => {
    axios.delete(`http://localhost:8080/spell?id=${id}`)
    .then(res => {
        // console.log(res.data);
        getAll();
    }).catch(err => console.log(err));
}

let validateCreate = () => {
    if (nameInput.value === "" || levelInput.value === "" || schoolInput.value === "") {
        return false;
    } else {
        return true;
    }
}

let validateUpdate = () => {
    if (levelInput.value === "" || schoolInput.value === "") {
        return false;
    } else {
        return true;
    }
}

let validateFName = () => {
    if (fNameInput.value === "") {
        return false;
    } else {
        return true;
    }
}

let validateLevel = () => {
    if (levelInput.value > 9 || levelInput.value < 0) {
        return false;
    } else {
        return true;
    }
}

let validateFLevel = () => {
    if (fLevelInput.value > 9 || fLevelInput.value < 0) {
        return false;
    } else {
        return true;
    }
}

let validateSchool = () => {
    if (schoolInput.value === "Select School of Magic...") {
        return false;
    } else {
        return true;
    }
}

let validateFSchool = () => {
    if (fSchoolInput.value === "Select School of Magic...") {
        return false;
    } else {
        return true;
    }
}

// EVENT LISTENERS
createBtn.addEventListener("click", create);
idBtn.addEventListener("click", getAll);
nameBtn.addEventListener("click", getAllByName);
levelBtn.addEventListener("click", getAllByLevel);
schoolBtn.addEventListener("click", getAllBySchool);
fNameBtn.addEventListener("click", findByName);
fLevelBtn.addEventListener("click", findByLevel);
fSchoolBtn.addEventListener("click", findBySchool);