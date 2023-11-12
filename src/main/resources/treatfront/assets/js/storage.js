var storage = {
    setToken: function(token) {
        localStorage.setItem("token", token);
    },
    getToken: function() {
        return localStorage.getItem("token");
    },
    clear: function() {
        localStorage.removeItem("token");
    },
    // baseURL:"https://welcomed-guiding-hippo.ngrok-free.app/treatment"
    baseURL:"http://localhost:8080/treatment"

};




