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
      var content = `<div class="col"  style=" width: 70%;padding: 1%;">
                  <label for="GenericMedicineName" class="text-dark" style="float: left;">Generic Medicine name</label>
                  <input type="text" class="form-control" id="GenericMedicineName" placeholder="Enter Generic Medicine Name" style="float: right; width: 80%;">
                </div>
                <br>
                <br>
                <div class="col"  style=" width: 70%;padding: 1%;">
                  <label for="BrandedMedicineName" class="text-dark" style="float: left;">Branded Medicine name</label>
                  <input type="text" class="form-control" id="BrandedMedicineName" placeholder="Enter Branded Medicine name" style="float: right; width: 80%;">
                </div>
                <br>
                <br>
                <div class="col"  style=" width: 70%;padding: 1%;">
                  <label for="DosageMedicine" class="text-dark" style="float: left;">Recommend Dosage</label>
                  <input type="text" class="form-control" id="DosageMedicine" placeholder="Enter How many intake per usee" style="float: right; width: 80%;">
                </div>
                <br>
                <br>`
      newdiv.innerHTML = content;
      document.getElementById('addMedicine').append(newdiv);


}