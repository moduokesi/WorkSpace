var storage = {
    setToken: function(token) {
        localStorage.setItem("token", token);
    },
    getToken: function() {
        return localStorage.getItem("token");
    },
    clear: function() {
        localStorage.removeItem("token");
    }
};

