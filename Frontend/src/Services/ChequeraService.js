import axios from "axios";

export default class ChequeraService {

    url = "http://localhost:9000/chequera";

    createChequera(cliente) {
        return axios.post(this.url, cliente);
    }

    readChequeras() {   
        return axios.get(this.url);
    }

    updateChequera(cliente) {
        return axios.put(this.url, cliente);
    }

    deleteChequera(id) { 
        return axios.delete(this.url + "/" + id);
    }

    getChequerasCuenta(id) {
        return axios.get(this.url + "/" + id);
    }

}