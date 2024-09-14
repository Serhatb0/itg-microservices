import axios from "axios";
import AuthHeader from "./AuthHeader";

axios.defaults.baseURL = "http://localhost:8765/api/";//process.env.REACT_APP_BASE_API_URL;

class CartService {
    getAll() {
        return axios.get(`http://localhost:8765/api/shopping-cart`, { headers: AuthHeader() });
    }

    getById(cartId) {
        return axios.get(`http://localhost:8765/api/shopping-cart/${cartId}`, { headers: AuthHeader() });
    }

    getByName(name) {
        return axios.get(`http://localhost:8765/api/shopping-cart/by-name/${name}`, { headers: AuthHeader() });
    }

    create(name) {
        return axios.post(`http://localhost:8765/api/shopping-cart?name=${name}`, null, { headers: AuthHeader() });
    }

    delete(cartId) {
        return axios.delete(`http://localhost:8765/api/shopping-cart/${cartId}`, { headers: AuthHeader() });
    }

    deleteProduct(cartId, productId) {
        return axios.delete(`http://localhost:8765/api/shopping-cart/${cartId}/products/${productId}`, { headers: AuthHeader() });
    }

    getTotalPrice(cartId){
        return axios.get(`http://localhost:8765/api/shopping-cart/totalprice/${cartId}`, { headers: AuthHeader() });
    }

    deleteAll() {
        return axios.delete(`http://localhost:8765/api/shopping-cart/deleteAll`, { headers: AuthHeader() });
    }

    addProducts(cartId, data) {
        return axios.post(`http://localhost:8765/api/shopping-cart/${cartId}`, data, { headers: AuthHeader() });
    }
}

const cartService = new CartService();

export default cartService;