import axios from "axios";
import AuthHeader from "./AuthHeader";

axios.defaults.baseURL = "http://localhost:8764/api/";//process.env.REACT_APP_BASE_API_URL;

class ProductService {
    getAll() {
        return axios.get(`http://localhost:8764/api/product`, { headers: AuthHeader() });
    }

    get(id) {
        return axios.get(`http://localhost:8764/api/product/${id}`, { headers: AuthHeader() });
    }
    

    create(data) {
        return axios.post("http://localhost:8764/api/product", data, { headers: AuthHeader() });
    }

    update(id, data) {
        return axios.put(`http://localhost:8764/api/product/${id}`, data, { headers: AuthHeader() });
    }

    delete(id) {
        return axios.delete(`http://localhost:8764/api/product/${id}`, { headers: AuthHeader() });
    }

    deleteAll() {
        return axios.delete(`http://localhost:8764/api/product/deleteAll`, { headers: AuthHeader() });
    }
}

const productService = new ProductService();

export default productService;