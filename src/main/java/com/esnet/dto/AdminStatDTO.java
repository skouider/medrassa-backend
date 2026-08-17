package com.esnet.dto;

// Basé sur les méthodes stats de AdminService
public class AdminStatDTO {

    private long nombreTotalInscriptions;
    private long nombreValidees;
    private long nombreEnAttente;
    private long nombreListeAttente;
    private long nombreRefusees;
    private long nombreAnnulees;
    private long nombreGarcons;
    private long nombreFilles;
    private long nombreCoran;
    private long nombrePreparatoire;

    public long getNombreTotalInscriptions() { return nombreTotalInscriptions; }
    public void setNombreTotalInscriptions(long n) { this.nombreTotalInscriptions = n; }

    public long getNombreValidees() { return nombreValidees; }
    public void setNombreValidees(long n) { this.nombreValidees = n; }

    public long getNombreEnAttente() { return nombreEnAttente; }
    public void setNombreEnAttente(long n) { this.nombreEnAttente = n; }

    public long getNombreListeAttente() { return nombreListeAttente; }
    public void setNombreListeAttente(long n) { this.nombreListeAttente = n; }

    public long getNombreRefusees() { return nombreRefusees; }
    public void setNombreRefusees(long n) { this.nombreRefusees = n; }

    public long getNombreAnnulees() { return nombreAnnulees; }
    public void setNombreAnnulees(long n) { this.nombreAnnulees = n; }

    public long getNombreGarcons() { return nombreGarcons; }
    public void setNombreGarcons(long n) { this.nombreGarcons = n; }

    public long getNombreFilles() { return nombreFilles; }
    public void setNombreFilles(long n) { this.nombreFilles = n; }

    public long getNombreCoran() { return nombreCoran; }
    public void setNombreCoran(long n) { this.nombreCoran = n; }

    public long getNombrePreparatoire() { return nombrePreparatoire; }
    public void setNombrePreparatoire(long n) { this.nombrePreparatoire = n; }
}