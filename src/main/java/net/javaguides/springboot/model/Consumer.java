package net.javaguides.springboot.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "konsumen")
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 45, nullable = false)
    private String name;


    @Column(length = 45, nullable = false)
    private String alamat;


    @Column(length = 45, nullable = false)
    private String kota;

    @Column(length = 45, nullable = false)
    private String provinsi;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date tgl_registrasi;


    private boolean enabled;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getProvinsi() {
        return provinsi;
    }

    public void setProvinsi(String provinsi) {
        this.provinsi = provinsi;
    }

    public Date getTgl_registrasi() {
        return tgl_registrasi;
    }

    public void setTgl_registrasi(Date tgl_registrasi) {
        this.tgl_registrasi = tgl_registrasi;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
//                ", email='" + email + '\'' +
//                ", password='" + password + '\'' +
                ", firstName='" + name + '\'' +
//                ", lastName='" + lastName + '\'' +
                '}';
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}