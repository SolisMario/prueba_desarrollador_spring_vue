import axios from "axios";

export default class ClienteService {

    url = "http://localhost:9000/user";

    createClient(cliente) {
        return axios.post(this.url, cliente);
    }

    readClients() {
        return axios.get(this.url);
    }

    updateClient(cliente) {
        return axios.put(this.url, cliente);
    }

    deleteClient(id) { 
        return axios.delete(this.url + "/" + id);
    }

    getClient(id) {
        return axios.get(this.url + "/byId/" + id);
    }

}