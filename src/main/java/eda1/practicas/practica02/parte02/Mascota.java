package eda1.practicas.practica02.parte02;

public class Mascota implements Comparable<Mascota>{
    private final String nombre;
    private final String especie;

    public Mascota(String nombre, String especie) {
        this.nombre = nombre.trim().toLowerCase();
        this.especie = especie.trim().toLowerCase();
    }

    @Override
    public String toString(){
        return this.nombre + "-" + this.especie;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        if (!(other instanceof Mascota)) return false;
        return this.compareTo((Mascota)other) == 0;
    }

    @Override
    public int compareTo(Mascota other) {
        int cmp = this.nombre.compareTo(other.nombre);
        return cmp != 0 ? cmp : this.especie.compareTo(other.especie);
    }
}
