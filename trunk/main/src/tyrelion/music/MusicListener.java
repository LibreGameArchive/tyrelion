/**
 * 
 */
package tyrelion.music;

import org.newdawn.slick.Music;

/**
 * The MusicManager controls music playback, looping and the playback volume.
 * @author jahudi
 */
public class MusicListener implements org.newdawn.slick.MusicListener {

	/**
	 * Will cast the refering Music as TyrelionMusic and start the playback of the next
	 * track of its category.
	 * @see org.newdawn.slick.MusicListener#musicEnded(org.newdawn.slick.Music)
	 */
	public void musicEnded(Music music) {
		
		TyrelionMusic tMusic = (TyrelionMusic) music;
		MusicManager.getInstance().loop(tMusic.getCategory());

	}

	/**
	 * Not used.
	 * @see org.newdawn.slick.MusicListener#musicSwapped(org.newdawn.slick.Music, org.newdawn.slick.Music)
	 */
	public void musicSwapped(Music music, Music newMusic) {
		// TODO Auto-generated method stub

	}

}
