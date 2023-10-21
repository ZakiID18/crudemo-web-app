package com.prodemy.crudemo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity // To know that this is an entity file
@Table(name = "students") // Provide table
public class Student {
	
	@Id // Provides primary key of entity
	// @GeneratedValue(strategy = GenerationType.IDENTITY) // Provides generation strategies for pk values (Auto Increment)
	@Column(name="nim")
	private long nim;
	
	@Column(name="nama")
	private String nama;
	
	@Column(name="alamat")
	private String alamat;
	
	@Column(name="tgl")
	private String tgl;
	
	public long getNim() {
		return nim;
	}
	public void setNim(long nim) {
		this.nim = nim;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public String getAlamat() {
		return alamat;
	}
	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}
	public String getTgl() {
		return tgl;
	}
	public void setTgl(String tgl) {
		this.tgl = tgl;
	}

}
