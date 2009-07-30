/**
 * 
 */
package tyrelion.music;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

/**
 * @author jahudi
 * This is an extension of the original Slick Music class. It does nothing more than adding a
 * String variable for the category the music belongs to.
 */
public class TyrelionMusic extends Music {

	/**
	 * The category this TyrelionMusic belongs to. It will be initialized by the MusicManager.
	 */
	private String category;
	
	/**
	 * @see org.newdawn.slick.Music
	 * @param category The category this TyrelionMusic belongs to.
	 */
	public TyrelionMusic(String ref, String category, boolean streamingHint)
			throws SlickException {
		super(ref, streamingHint);
		this.category = category;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

}
