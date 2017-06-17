package mortvana.thaumicrevelations.melteddashboard.item;

/**
 *  Class of an Object used for storing data relating to colorizing items.
 *
 *  @author Mortvana
 */
public class ColorEntry {

    public String template, texture;
    public int color;

    /**
     *
     * @param template - Grayscale version of the texture, which is colorized if thex colorized texture is not found.
     * @param texture - Colorized version of the texture, which is used if found, for items with custom sprites.
     * @param color - Hexadecimal RGB color to colorize the template with.
     */
    public ColorEntry(String template, String texture, int color) {
        this.template = template;
        this.texture = texture;
        this.color = color;
    }

}