function main() {
  
  const baseUrl = "http://apbakus.id/api/anak";
  
  const getAnak = () => {
    fetch(`${baseUrl}/read.php`)
    .then(response => {      
      return response.json();
    })
    .then(responseJson => {
      if(responseJson.error) {
        showResponseMessage(responseJson.message);
      } else {
        renderAllAnaks(responseJson.records);
      }
    })
    .catch(error =>{
      showResponseMessage(responseJson.error);
    })
  };

  const getSekolah = () => {
    fetch("http://apbakus.id/api/sekolah/read.php")
    .then(response => {      
      return response.json();
    })
    .then(responseJson => {
      if(responseJson.error) {
        showResponseMessage(responseJson.message);
      } else {
        renderSekolah(responseJson.records);
      }
    })
    .catch(error =>{
      showResponseMessage(responseJson.error);
    })
  };
  
  const getOneAnak = (anak) => {
    
    fetch(`${baseUrl}/read_one.php`, {
      method: "POST",
      body: JSON.stringify(anak)
    })
    .then(response => {
      return response.json();
    })
    .then(responseJson => {
      
      if(responseJson.error) {
        showResponseMessage(responseJson.message);
      } else {
        renderOneAnak(responseJson);
      }
    })
    .catch(error =>{
      showResponseMessage(responseJson.error);
    })
  };
  
  const insertAnak = (anak) => {
    fetch(`${baseUrl}/create.php`, {
      method: "POST",
      body: JSON.stringify(anak)
    })
    .then(response => {
      return response.json();
    })
    .then(responseJson => {
      showResponseMessage(responseJson.message);
      getAnak();
    })
    .catch(error => {
      showResponseMessage("Error Insert " + error);
    })
  };
  
  const updateAnak = (anak) => {
    fetch(`${baseUrl}/update.php`, {
      method: "POST",
      body: JSON.stringify(anak)
    })
    .then(response => {
      return response.json();
    })
    .then(responseJson => {
      
      showResponseMessage(responseJson.message);
      getAnak();
    })
    .catch(error => {
      console.log(error);

      showResponseMessage(error);
    })
  };
  
  const removeAnak = (anak) => {
    fetch(`${baseUrl}/delete.php`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(anak)
    })
    .then(response => {
      return response.json();
    })
    .then(responseJson => {
      showResponseMessage(responseJson.message);
      getAnak();
    })
    .catch(error => {
      showResponseMessage(error);
    })
  };
  
  
  
  
  
  
  /*
  jangan ubah kode di bawah ini ya!
  */
  
  const renderAllAnaks = (records) => {
    const listAnakElement = document.querySelector("#listAnak");
    listAnakElement.innerHTML = "";
    
    records.forEach(anak => {
      listAnakElement.innerHTML += `
      <div class="col-lg-4 col-md-6 col-sm-12" style="margin-top: 12px;">
      <div class="card">
      <div class="card-body">
      <h5>(${anak.id}) ${anak.nama_lengkap}</h5>
      <p>${anak.nama_ortu}</p>
      <button type="button" class="btn btn-primary button-edit" id="${anak.id}">Edit</button>
      <button type="button" class="btn btn-danger button-delete" id="${anak.id}">Hapus</button>
      </div>
      </div>
      </div>
      `;
    });
    
    const buttons = document.querySelectorAll(".button-delete");
    buttons.forEach(button => {
      button.addEventListener("click", event => {
        const anak = {
          id: Number.parseInt(event.target.id)
        }
        removeAnak(anak);
      })
    })
    
    const buttons_edit = document.querySelectorAll(".button-edit");
    buttons_edit.forEach(button => {
      button.addEventListener("click", event => {
        const anak = {
          id: Number.parseInt(event.target.id)
        }
        getOneAnak(anak);
      })
    })
  };
  
  const renderOneAnak = (anak) => {
    document.querySelector("#id_anak").value = anak.id;
    document.querySelector("#nama_lengkap").value = anak.nama_lengkap
    document.querySelector("#nama_panggilan").value = anak.nama_panggilan

    if(anak.jenis_kelamin=="Laki-laki"){
      document.querySelector("#laki-laki").checked = true
    }else{
      document.querySelector("#perempuan").checked = true
    }
    
    // document.querySelector("#jenis_kelamin").value = anak.jenis_kelamin
    document.querySelector("#tanggal_lahir").value = anak.tanggal_lahir
    document.querySelector("#nama_ortu").value = anak.nama_ortu
    document.querySelector("#alamat").value = anak.alamat
    document.querySelector("#no_kontak").value = anak.no_kontak
    document.querySelector("#id_sekolah").value = anak.id_sekolah
  };

  const renderSekolah = (sekolah) => {
    var sel = document.getElementById('id_sekolah');


    sekolah.forEach(school => {
      var opt = document.createElement('option');

      opt.appendChild(document.createTextNode(school.nama_sekolah));
      opt.value = school.id;
      sel.appendChild(opt);    
    });
  };
  
  const showResponseMessage = (message = "Check your internet connection") => {
    alert(message);
  };
  
  document.addEventListener("DOMContentLoaded", () => {
    
    const id_anak = document.querySelector("#id_anak");
    const nama_lengkap = document.querySelector("#nama_lengkap");
    const nama_panggilan = document.querySelector("#nama_panggilan");
    let jenis_kelamin = "Laki-laki";
    
    if (document.querySelector("#perempuan").checked==true){
      jenis_kelamin = "Perempuan";
    }
    
     
    const tanggal_lahir = document.querySelector("#tanggal_lahir");
    const nama_ortu = document.querySelector("#nama_ortu");
    const alamat = document.querySelector("#alamat");
    const no_kontak = document.querySelector("#no_kontak");
    const id_sekolah = document.querySelector("#id_sekolah");
    const buttonSave = document.querySelector("#buttonSave");
    const buttonUpdate = document.querySelector("#buttonUpdate");
    
    buttonSave.addEventListener("click", function () {
      const anak = {
        id: Number.parseInt(id_anak.value),
        nama_lengkap: nama_lengkap.value,
        nama_panggilan: nama_panggilan.value,
        jenis_kelamin: jenis_kelamin,
        tanggal_lahir: tanggal_lahir.value,
        nama_ortu: nama_ortu.value,
        alamat: alamat.value,
        no_kontak: no_kontak.value,
        id_sekolah: id_sekolah.value,
        id_pengguna: "1"
      };
      insertAnak(anak)
      
    });
    
    buttonUpdate.addEventListener("click", function () {
      const anak = {
        id: Number.parseInt(id_anak.value),
        nama_lengkap: nama_lengkap.value,
        nama_panggilan: nama_panggilan.value,
        jenis_kelamin: jenis_kelamin,
        tanggal_lahir: tanggal_lahir.value,
        nama_ortu: nama_ortu.value,
        alamat: alamat.value,
        no_kontak: no_kontak.value,
        id_sekolah: id_sekolah.value,
        id_pengguna: "1"
      };
      
      updateAnak(anak)
    });
    getAnak();
    getSekolah();
  });
}

export default main;
