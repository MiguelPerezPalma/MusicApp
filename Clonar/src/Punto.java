public class Punto implements Cloneable{
    protected int x;
    protected int y;
    protected Material material;
    
    public Punto(int x, int y,Material material) {
        super();
        this.x = x;
        this.y = y;
        this.material=material;
    }
    public Punto() {
        super();
        x=y=0; //x=0;y=0;
        material=new Material();
    }
    
    public Material getMaterial() {
        return material;
    }
    public void setMaterial(Material material) {
        this.material = material;
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((material == null) ? 0 : material.hashCode());
        result = prime * result + x;
        result = prime * result + y;
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
        Punto other = (Punto) obj;
        if (material == null) {
            if (other.material != null)
                return false;
        } else if (!material.equals(other.material))
            return false;
        if (x != other.x)
            return false;
        if (y != other.y)
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "Punto [x=" + x + ", y=" + y + ", material=" + material + "]";
    }
    @Override
    protected Object clone() throws CloneNotSupportedException {
        Punto pclone=null;
        pclone=(Punto) super.clone();
        
        return super.clone();
    }
}
