package raytracer.textures;


import raytracer.utilities.RGB;
import raytracer.utilities.Vector2D;
import raytracer.utilities.Vector3D;

public class SimpleTexture implements Texture {

	RGB color;

	public SimpleTexture(RGB color) {
		this.color = color;
	}

	@Override
	public RGB getValue(Vector2D vector2, Vector3D vector3) {
		return this.color;
	}

}
