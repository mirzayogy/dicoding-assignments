import './anak-item.js';

class AnakList extends HTMLElement {
    set anaks(anaks) {
        this._anaks = anaks;
        this.render();
    }

    renderError(message) {
        this.innerHTML = "";
        this.innerHTML += `<h2 class="placeholder">${message}</h2>`;
    }

    render() {
        this._anaks.forEach(anak => {
            const anakItemElement = document.createElement("anak-item");
            anakItemElement.anak = anak
            this.appendChild(anakItemElement);
        })
    }
}

customElements.define("anak-list", AnakList);