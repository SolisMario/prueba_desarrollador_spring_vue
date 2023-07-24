import axios from "axios";

export default class ChequeService {

    url = "http://localhost:9000/cheque";

    createCheque(cliente) {
        return axios.post(this.url, cliente);
    }

    readCheques() {   
        return axios.get(this.url);
    }

    pagarCheque(cliente) {
        return axios.put(this.url + '/pagar', cliente);
    }

    cancelarCheque(cliente) { 
        return axios.put(this.url + "/cancel", cliente);
    }

    getChequesCuenta(id) {
        return axios.get(this.url + "/" + id);
    }

}