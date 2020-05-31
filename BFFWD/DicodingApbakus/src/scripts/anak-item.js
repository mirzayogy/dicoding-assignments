class AnakItem extends HTMLElement {
    set anak(anak) {
        this._anak = anak;
        this.render();
    }

    render() {
        
        this.innerHTML = `
      <div class="card" style="margin-top: 12px;">
      <div class="card-body">
      <h5>(${this._anak.id}) ${this._anak.nama_lengkap}</h5>
      <p>${this._anak.nama_ortu}</p> 
      <button type="button" class="btn btn-danger button-delete float-right" id="${this._anak.id}">Hapus</button>
      <button type="button" class="btn btn-primary button-edit float-right" id="${this._anak.id}">Edit</button>
      </div>
      </div>
      `;
    }
}

customElements.define("anak-item", AnakItem);