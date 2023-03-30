function Postman() {

    this.init = function () {
        console.log("Postman ready !")

        return this;
    }

    this.get = function (url, params, successCallback, errorCallback) {
        if (params != undefined) {
            url = parseGetParam(url, params);
        }

        let request = setup('GET', url, successCallback, errorCallback);
        request.send();
    }

    this.post = function (url, formData, successCallback, errorCallback) {
        let request = setup('POST', url, successCallback, errorCallback);
        request.send(formData);
    }

    this.put = function (url, formData, successCallback, errorCallback) {
        let request = setup('PUT', url, successCallback, errorCallback);
        request.send(formData);
    }

    this.delete = function (url, formData, successCallback, errorCallback) {
        let request = setup('DELETE', url, successCallback, errorCallback);
        request.send(formData);
    }

    function parseGetParam(url, params) {
        let paramCount = Object.keys(params).length;
        let count = 0;
        if (paramCount > 0) {
            url += '?';
            for (const property in params) {
                url += property + '=' + params[property];
                count++;
                if (count < paramCount) {
                    url += '&';
                }
            }
        }

        return url;
    }

    function setup(method, url, successCallback, errorCallback) {
        let request = new XMLHttpRequest();
        request.open(method, url, true);

        request.onload = function () {
            if (this.status >= 200 && this.status < 400) {
                successCallback(this.response);
            } else {
                console.log('SERVER RETURN ERROR');
                if (errorCallback != undefined) {
                    errorCallback(this.response);
                }
            }
        }

        request.onerror = function () {
            // showAjaxFailedMessage();
            console.log(this.response)
        }

        return request;
    }
}