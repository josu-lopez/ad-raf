package org.egibide;

public class Empleado {
    private Integer idEmpleado;
    private String apellido;
    private Integer departamento;
    private Double salario;

    public Empleado(Integer idEmpleado, String apellido, Integer departamento, Double salario) {
        this.idEmpleado = idEmpleado;
        this.apellido = apellido;
        this.departamento = departamento;
        this.salario = salario;
    }

    public Empleado() {
        super();
    };

    @Override
    public String toString() {
        return "Empleado{" +
                "idEmpleado=" + idEmpleado +
                ", apellido='" + apellido + '\'' +
                ", departamento=" + departamento +
                ", salario=" + salario +
                '}';
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Integer departamento) {
        this.departamento = departamento;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }
}
