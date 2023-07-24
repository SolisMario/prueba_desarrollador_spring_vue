import axios from "axios";

export default class CuentaService {

    url = "http://localhost:9000/cuenta";

    createCuenta(cuenta) {
        return axios.post(this.url, cuenta);
    }

    readCuentas() {
        return axios.get(this.url);
    }

    updateCuenta(cuenta) {
        return axios.put(this.url, cuenta);
    }

    deleteCuenta(id) { 
        return axios.delete(this.url + "/" + id);
    }

    getCuentasCliente(id) {
        return axios.get(this.url + "/" + id);
    }

}