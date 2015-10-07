package raytracer.shapes;


import raytracer.textures.Texture;
import raytracer.utilities.RGB;
import raytracer.utilities.Ray;
import raytracer.utilities.Vector3D;

public interface Surface {
	
	public double getT();
	public boolean hit(Ray ray, double tSubZero, double tSub1, float time);
	public RGB getColor();
	public float getReflectance();
	public boolean shadowHit(Ray ray, float tSubZero, float tSub1, float time);
	public RGB getAmbientColor(float ambience, Vector3D hitPoint);
	public Texture getTexture();
	

}
