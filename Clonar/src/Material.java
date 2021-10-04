
public class Material {
    private int rebote;
    private int rozamiento;
    public Material(int rebote, int rozamiento) {
        super();
        this.rebote = rebote;
        this.rozamiento = rozamiento;
    }
    public Material() {
        this(0,0);
    }
    public int getRebote() {
        return rebote;
    }
    public void setRebote(int rebote) {
        this.rebote = rebote;
    }
    public int getRozamiento() {
        return rozamiento;
    }
    public void setRozamiento(int rozamiento) {
        this.rozamiento = rozamiento;
    }
    @Override
    public String toString() {
        return "Material [rebote=" + rebote + ", rozamiento=" + rozamiento + "]";
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + rebote;
        result = prime * result + rozamiento;
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Material other = (Material) obj;
        if (rebote != other.rebote)
            return false;
        if (rozamiento != other.rozamiento)
            return false;
        return true;
    }
}

