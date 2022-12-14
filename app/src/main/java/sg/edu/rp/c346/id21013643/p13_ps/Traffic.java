package sg.edu.rp.c346.id21013643.p13_ps;

public class Traffic {
    private String timestamp;
    private String image;
    private int camera_id;

    public Traffic(String timestamp, String image, int camera_id){
        this.camera_id = camera_id;
        this.image = image;
        this.timestamp = timestamp;
    }

    public int getCamera_id() {
        return camera_id;
    }

    public void setCamera_id(int camera_id) {
        this.camera_id = camera_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
    @Override
    public String toString() {
        return  "Camera id:  " + camera_id + "\n"
                + "" + "\n"
                + "Timestamp:  " + timestamp + "\n"
                + "" + "\n"
                + "Image:  " + image  + "\n";
    }

}