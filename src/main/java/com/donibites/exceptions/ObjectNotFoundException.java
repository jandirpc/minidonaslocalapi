package com.donibites.exceptions;

public class ObjectNotFoundException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ObjectNotFoundException(Long id) {
        super("Registro no encontrado : " + id);
    }

}