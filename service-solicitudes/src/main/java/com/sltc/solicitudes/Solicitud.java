package com.sltc.solicitudes;

public class Solicitud {
    private Long id;
    private Double costoEstimatado;
    private Double costoFinal;
    private Integer tiempoEstimado;
    private Integer tiempoReal;
    private String estado;
    private Long idCliente;
    private Long idContenedor;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Double getCostoEstimatado() { return costoEstimatado; }
    public void setCostoEstimatado(Double costoEstimatado) { this.costoEstimatado = costoEstimatado; }

    public Double getCostoFinal() { return costoFinal; }
    public void setCostoFinal(Double costoFinal) { this.costoFinal = costoFinal; }

    public Integer getTiempoEstimado() { return tiempoEstimado; }
    public void setTiempoEstimado(Integer tiempoEstimado) { this.tiempoEstimado = tiempoEstimado; }

    public Integer getTiempoReal() { return tiempoReal; }
    public void setTiempoReal(Integer tiempoReal) { this.tiempoReal = tiempoReal; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Long getIdCliente() { return idCliente; }
    public void setIdCliente(Long idCliente) { this.idCliente = idCliente; }

    public Long getIdContenedor() { return idContenedor; }
    public void setIdContenedor(Long idContenedor) { this.idContenedor = idContenedor; }
}
