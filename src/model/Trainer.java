package src.model;

public class Trainer {

    private String name;
    private boolean gender;//0 = boy, 1 = girl
    private int trainerId;

    private int secretId;
    public Trainer(String name, boolean gender, int trainerId, int secretId){
        this.name = name;
        this.gender = gender;
        this.trainerId = trainerId;
        this.secretId = secretId;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public int getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }

    public int getSecretId() {
        return secretId;
    }

    public void setSecretId(int secretId) {
        this.secretId = secretId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
