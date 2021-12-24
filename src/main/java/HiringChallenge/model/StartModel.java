package HiringChallenge.model;

import lombok.Data;

@Data
public class StartModel {

    private String token;
    private String status;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static StartModel build(String token, String status) {
        StartModel model = new StartModel();
        model.token = token;
        model.status = status;

        return model;
    }
}
