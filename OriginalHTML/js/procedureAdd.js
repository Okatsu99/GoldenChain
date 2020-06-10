function addInput(){
      var newdiv = document.createElement('span');
      var content = `<div class="col"  style=" width: 70%;padding: 1%;">
                        <label for="PatientName" class="text-dark" style="float: left;">Procedure/s Done</label>
                        <select  class="form-control procedureList" style="float: right; width: 85%;">
                        <optgroup label="Procedures...">
                          <option value="#">Procedures...</option>
                          <option value="bunot">Bunot</option>
                          <option value="pasta">Pasta</option>
                          <option value="cleaning">Cleaning</option>
                          <option value="cleaning">Surgery</option>
                          <option value="cleaning">Ortho</option>
                        </optgroup>
                      </select>
                      <br>
                      <br>
                      <input  class="form form-control" type="text" name="amount" id="amount1" placeholder="Enter amount cost..." style="float: right; width: 85%;">
                      </div>
                      <br>
                      <br>`
      newdiv.innerHTML = content;
      document.getElementById('testing').append(newdiv);


}
function addMedicine(){
      var newdiv = document.createElement('div');
      var content = `<br>
                    <div class="col"  style=" width: 73%;">
                  <label for="GenericMedicineName" class="text-dark" style="float: left;">Generic Medicine name</label>
                  <input type="text" class="form-control" id="GenericMedicineName" placeholder="Enter Generic Medicine Name" style="float: right; width: 80%;">
                 </div>
                 <br>
                 &emsp;&emsp;&emsp;&emsp;
                <div class="col"  style=" width: 73%;">
                  <label for="BrandedMedicineName" class="text-dark" style="float: left;">Branded Medicine name</label>
                  <input type="text" class="form-control" id="BrandedMedicineName" placeholder="Enter Branded Medicine name" style="float: right; width: 80%;">
                </div>
                <br>
                
                 &emsp;&emsp;&emsp;&emsp;
                <div class="col"  style=" width: 73%;">
                  <label for="DosageMedicine" class="text-dark" style="float: left;">Recommend Dosage</label>
                  <input type="text" class="form-control" id="DosageMedicine" placeholder="Enter How many intake per usee" style="float: right; width: 80%;">
                </div>
                <br>
                <br>
                 <div class="col"  style=" width: 73%;padding: 1%;">
                  <label for="Notes" class="text-dark" style="float: left;">Notes</label>
                  <textarea class="form-control" id="Notes" aria-label="With textarea" rows="4" style="width: 80%; resize: none; float: right;"></textarea>
                  
                  </div>
                  <br>
                  <br>`
      newdiv.innerHTML = content;
      document.getElementById('addMedicine').append(newdiv);


}
function patient_record_edit() {

    var parent = document.getElementById('main-div');
    var data = parent.getElementsByClassName('data');

    for(var i = 0; i < data.length; i++) {

        var current = data[i];
        var newdiv = document.createElement('div');
        newdiv.classList.add('data');


        if(i == 0) {
            newdiv.innerHTML = `<input type="text" name="first-name" class="input-data form form-control" >`;
        }

        else if(i == 1) {
            newdiv.innerHTML = `<input type="text" name="last-name" class="input-data form form-control">`;
        }

        else if(i == 2) {
            newdiv.innerHTML = `<select id="gender">
                                    <option value="male">Male</option>
                                    <option value="female">Female</option>
                                </select>`;
        }

        else if(i == 3) {
            newdiv.innerHTML = `<input type="text" name="contact" class="input-data form form-control">`;
        }

        else if(i == 4) {
            newdiv.innerHTML = `<input type="date" name="birthdate" class="input-data form form-control">`;
        }  

        else if(i == 5) {
            newdiv.innerHTML = `<input type="email" name="email" class="input-data form form-control">`;
        }

        else if(i == 6) {
            newdiv.innerHTML = `<textarea name="address" class="input-data form form-control"></textarea>`;
        }  

        else if(i == 7) {
            newdiv.innerHTML = `<select id="clinic">
                            <option value="clinic1">Clinic 1</option>
                            <option value="clinic2">Clinic 2</option>
                        </select>`;
        }  

        current.replaceWith(newdiv);          

    }

    document.getElementsByClassName('modal-btn')[0].innerHTML = `<button onclick="patient_record_save()" class="btn btn-success">Save Changes</button>`;
}


function patient_record_save() {

    var parent = document.getElementById('main-div');
    var data = parent.getElementsByClassName('data');


    for(var i = 0; i < data.length; i++) {

        var current = data[i];
        var newdiv = document.createElement('div');
        newdiv.classList.add('data');

        if(i == 0) {
            newdiv.innerHTML = `<p name="first-name" class="info"> </p>`;
        }

        else if(i == 1) {
            newdiv.innerHTML = `<p name="last-name" class="info"> </p>`;
        }

        else if(i == 2) {
            newdiv.innerHTML = `<p name="gender" class="info"> </p>`;
        }

        else if(i == 3) {
            newdiv.innerHTML = `<p name="contact" class="info"> </p>`;
        }

        else if(i == 4) {
            newdiv.innerHTML = `<p name="birthdate" class="info"> </p>`;
        }  

        else if(i == 5) {
            newdiv.innerHTML = `<p name="email" class="info"> </p>`;
        }

        else if(i == 6) {
            newdiv.innerHTML = `<p name="address" class="info"> </p>`;
        }  

        else if(i == 7) {
            newdiv.innerHTML = `<p name="clinic" class="info"> </p>`;
        }  

        parent.replaceChild(newdiv, current);
    }

    document.getElementsByClassName('modal-btn')[0].innerHTML = `<button onclick="patient_record_edit()" class="btn btn-primary"> Edit Data </button>`;
} 

function patient_sec_edit() {

    var parent = document.getElementById('main-div');
    var data = parent.getElementsByClassName('data');

    for(var i = 0; i < data.length; i++) {

        var current = data[i];
        var newdiv = document.createElement('div');
        newdiv.classList.add('data');


        if(i == 0) {
            newdiv.innerHTML = `<input type="text" name="first-name" class="input-data form form-control" >`;
        }

        else if(i == 1) {
            newdiv.innerHTML = `<input type="text" name="last-name" class="input-data form form-control">`;
        }

        else if(i == 2) {
            newdiv.innerHTML = `<select id="gender">
                                    <option value="male">Male</option>
                                    <option value="female">Female</option>
                                </select>`;
        }

        else if(i == 3) {
            newdiv.innerHTML = `<input type="email" name="email" class="input-data form form-control">`;
        }  

        else if(i == 4) {
            newdiv.innerHTML = `<input type="text" name="contact" class="input-data form form-control">`;
        }  

        else if(i == 5) {
            newdiv.innerHTML = `<select id="clinic">
                            <option value="clinic1">Clinic 1</option>
                            <option value="clinic2">Clinic 2</option>
                        </select>`;
        }  

        current.replaceWith(newdiv);          

    }

    document.getElementsByClassName('modal-btn')[0].innerHTML = `<button onclick="patient_sec_save()" class="btn btn-primary">Save Changes</button>`;
}


function patient_sec_save() {

    var parent = document.getElementById('main-div');
    var data = parent.getElementsByClassName('data');


    for(var i = 0; i < data.length; i++) {

        var current = data[i];
        var newdiv = document.createElement('div');
        newdiv.classList.add('data');

        if(i == 0) {
            newdiv.innerHTML = `<p name="first-name" class="info"> </p>`;
        }

        else if(i == 1) {
            newdiv.innerHTML = `<p name="last-name" class="info"> </p>`;
        }

        else if(i == 2) {
            newdiv.innerHTML = `<p name="gender" class="info"> </p>`;
        }

        else if(i == 3) {
            newdiv.innerHTML = `<p name="email" class="info"> </p>`;
        }

        else if(i == 4) {
            newdiv.innerHTML = `<p name="clinic" class="info"> </p>`;
        }  

        parent.replaceChild(newdiv, current);
    }

    document.getElementsByClassName('modal-btn')[0].innerHTML = `<button onclick="patient_sec_edit()" class="btn btn-primary"> Edit Data </button>`;
} 

function appoint_edit(){
  var parent = document.getElementById('main-div');
    var data = parent.getElementsByClassName('data');

    for(var i = 0; i < data.length; i++) {

        var current = data[i];
        var newdiv = document.createElement('div');
        newdiv.classList.add('data');


        if(i == 0) {
            newdiv.innerHTML = `<select id = "displayPatient" class = "form form-control" style="width: 100%;">
                            <option> Patient Id — Patient Last Name, Patient First Name </option>
                            </select>`;
        }

        else if(i == 1) {
            newdiv.innerHTML = `<input type="text" name="patientFirstName" class="input-data form form-control">`;
        }

        else if(i == 2) {
            newdiv.innerHTML = `<input type="text" name="patientLastName" class="input-data form form-control">`;
        }

        else if(i == 3) {
            newdiv.innerHTML = `<input type="date" name="appointmentDate" class="input-data form form-control">`;
        }  

        else if(i == 4) {
            newdiv.innerHTML = `<input type="time" name="appointmentTime" class="input-data form form-control">`;
        }  

        else if(i == 5) {
            newdiv.innerHTML = `<textarea class="form form-control sizeText"rows="4" style="width: 100%;" name="doctorNotes" form="Order_Page"id="m_box" ></textarea>`;
        }  

        current.replaceWith(newdiv);          

    }

    document.getElementsByClassName('modal-btn')[0].innerHTML = `<button onclick="appoint_save()" class="btn btn-success">Save Changes</button>`;
}

function appoint_save() {

    var parent = document.getElementById('main-div');
    var data = parent.getElementsByClassName('data');


    for(var i = 0; i < data.length; i++) {

        var current = data[i];
        var newdiv = document.createElement('div');
        newdiv.classList.add('data');

        if(i == 0) {
            newdiv.innerHTML = `<p name="patient-id" class="info"> </p>`;
        }

        else if(i == 1) {
            newdiv.innerHTML = `<p name="patient-fname" class="info"> </p>`;
        }

        else if(i == 2) {
            newdiv.innerHTML = `<p name="patient-lname" class="info"> </p>`;
        }

        else if(i == 3) {
            newdiv.innerHTML = `<p name="date" class="info"> </p>`;
        }

        else if(i == 4) {
            newdiv.innerHTML = `<p name="time" class="info"> </p>`;
        }  
        else if(i == 5) {
            newdiv.innerHTML = `<p name="notes" class="info"> </p>`;
        }  

        parent.replaceChild(newdiv, current);
    }

    document.getElementsByClassName('modal-btn')[0].innerHTML = `<button onclick="appoint_edit()" class="btn btn-warning"> Edit Data </button>`;
} 