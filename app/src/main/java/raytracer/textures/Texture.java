package raytracer.textures;


import raytracer.utilities.RGB;
import raytracer.utilities.Vector2D;
import raytracer.utilities.Vector3D;

public interface Texture {
	
	public RGB getValue(Vector2D vector2, Vector3D vector3);

}
