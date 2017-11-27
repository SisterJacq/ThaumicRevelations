package mortvana.thaumrev.util;

import thaumcraft.api.aspects.Aspect;

public class AspectStack {

	Aspect aspect;
	int size;

	public AspectStack(Aspect aspect, int size) {
		this.aspect = aspect;
		this.size = size;
	}

	public AspectStack(Aspect aspect) {
		this(aspect, 1);
	}

	public void setAspect(Aspect aspect) {
		this.aspect = aspect;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Aspect getAspect() {
		return aspect;
	}

	public int getSize() {
		return size;
	}
}
