import axios from "axios";

//axios.defaults.baseURL = "http://localhost:8766/api/";//process.env.REACT_APP_BASE_API_URL;

class AuthService {
    
    login(username, password) {
        return axios
            .post("http://localhost:8766/api/user/signin", { username, password })
            .then((response) => {
                if (response.data.accessToken) {
                    localStorage.setItem("user", JSON.stringify(response.data));
                }

                return response.data;
            });
    }

    logout() {
        localStorage.removeItem("user");
    }

    register(username, email, password) {
        return axios.post("user/signup", {
            username,
            email,
            password,
        });
    }
}

const authService = new AuthService();

export default authService;