package com.douglas.compras.domain.enums;

public enum Perfil {

	ADMIN (1, "ROLE_ADMIN"),
	CLIENT (2, "ROLE_CLIENT");

	private int code;
	private String description;

	private Perfil(int code, String description) {
		this.code = code;
		this.description = description;
	}

	public int getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}
	
	public static Perfil toEnum(Integer code) {
		if(code == null) {
			return null;
		}
		
		for (Perfil status: Perfil.values()) {
			if(code.equals(status.getCode())) {
				return status;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + code);
	}
}
