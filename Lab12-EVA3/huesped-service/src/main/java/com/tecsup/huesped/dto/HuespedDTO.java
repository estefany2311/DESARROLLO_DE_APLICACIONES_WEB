package com.tecsup.huesped.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class HuespedDTO {

    @NotBlank(message = "Los nombres son obligatorios")
    @Size(max = 80, message = "Los nombres no pueden superar 80 caracteres")
    private String nombres;

    @NotBlank(message = "Los apellidos son obligatorios")
    @Size(max = 80, message = "Los apellidos no pueden superar 80 caracteres")
    private String apellidos;

    @NotBlank(message = "El documento es obligatorio")
    @Pattern(regexp = "^[A-Za-z0-9]{8,12}$",
            message = "El documento debe tener de 8 a 12 letras o numeros")
    private String documento;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El correo no tiene un formato valido")
    @Size(max = 120, message = "El correo no puede superar 120 caracteres")
    private String correo;

    @Pattern(regexp = "^[0-9]{9}$", message = "El telefono debe tener 9 digitos")
    private String telefono;

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
