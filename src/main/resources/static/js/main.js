"use strict";
  //////////////
 // SELECTORS//
//////////////
// DIVS

// INPUTS
let nameInput = document.querySelector("#nameInput");
let levelInput = document.querySelector("#levelInput");
let schoolInput = document.querySelector("#schoolInput");

// BUTTONS
let createBtn = document.querySelector("#createBtn");
let updateBtn = document.querySelector("#updateBtn");
let deleteBtn = document.querySelector("#deleteBtn");
let getAllBtn = document.querySelector("#getAllBtn");

// FUNCTIONS
// Get ALL
let getAll = () => {
    axios.get("http://localhost:8080/spell/")
    .then(res => {
        console.log(res.data);
    }).catch(err => console.log(err));
}

// Create
let create = () => {

    if(!validateInputs()){
        alert("All fields need values!");
        return
    }

    let obj = {
        "name": nameInput.value,
        "level": levelInput.value,
        "school": schoolInput.value
    }

    axios.post("http://localhost:8080/spell", obj)
    .then(res => {
        // console.log(res.data);
        getAll();
    }).catch(err => console.log(err));
}

// Update
let update = () => {

    if(!validateName()){
        alert("Please enter the Name of the spell you wish to update!");
        return
    }

    let obj = {
        "name": "Wish",
        "level": 5,
        "school": "Conjuration"
    }

    axios.put("http://localhost:8080/spell/${nameInput}", obj)
    .then(res => {
        // console.log(res.data);
        getAll();
    }).catch(err => console.log(err));
}

// Delete
let del = () => {
    axios.delete("http://localhost:8080/spell/Wish")
    .then(res => {
        // console.log(res.data);
        getAll();
    }).catch(err => console.log(err));
}

let validateInputs = () => {
    if (nameInput.value === "" || levelInput.value === "" || schoolInput.value === "") {
        return false;
    } else {
        return true;
    }
}

let validateName = () => {
    if (nameInput.value === "") {
        return false;
    } else {
        return true;
    }
}

// EVENT LISTENERS
createBtn.addEventListener("click", create);
updateBtn.addEventListener("click", update);
deleteBtn.addEventListener("click", del);
getAllBtn.addEventListener("click", getAll)