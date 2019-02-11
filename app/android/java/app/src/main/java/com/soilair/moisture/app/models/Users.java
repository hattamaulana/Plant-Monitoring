package com.soilair.moisture.app.models;

public class Users {
    public static String _ID;
    public static String ID_USER;
    public static String FIRST_NAME;
    public static String LAST_NAME;
    public static String PHOTO;
    public static String EMAIL = "email";
    public static String PASSWORD;
    public static String GENDER;
    public static String COUNTRY;
    public static String CITY;
    public static String LOCATION;
    public static String LOCATION_LANGTITUDE;
    public static String LOCATION_LONGTITUDE;

    private int _id;
    private int idUser;
    private String firstName;
    private String lastName;
    private String photo;
    private String email;
    private String password;
    private String gender;
    private String country;
    private String city;
    private String location;
    private String locationLangtitude;
    private String locationLongtitude;

    public int get_id() {
        return _id;
    }

    public Users set_id(int _id) {
        this._id = _id;
        return this;
    }

    public int getIdUser() {
        return idUser;
    }

    public Users setIdUser(int idUser) {
        this.idUser = idUser;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Users setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Users setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPhoto() {
        return photo;
    }

    public Users setPhoto(String photo) {
        this.photo = photo;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Users setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Users setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public Users setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public Users setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getCity() {
        return city;
    }

    public Users setCity(String city) {
        this.city = city;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public Users setLocation(String location) {
        this.location = location;
        return this;
    }

    public String getLocationLangtitude() {
        return locationLangtitude;
    }

    public Users setLocationLangtitude(String locationLangtitude) {
        this.locationLangtitude = locationLangtitude;
        return this;
    }

    public String getLocationLongtitude() {
        return locationLongtitude;
    }

    public Users setLocationLongtitude(String locationLongtitude) {
        this.locationLongtitude = locationLongtitude;
        return this;
    }
}
